<script setup>
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import { reactive, onMounted, ref, watch, getCurrentInstance } from 'vue';
  import {
    pageListOutcome as pageList,
    load,
  } from '@/api/surveillance/manualInspection.js';
  import excludeFromDisplay from './ExcludeFromDisplay.vue';
  import manualoutcomeManage from './ManualoutcomeManage.vue';
  import { findAllInspector as searchUser } from '@/api/user.js';
  import { useRouter } from 'vue-router';
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    planCode: '',
    queryFlag: 'main',
  });
  const diatitle = ref('');
  const outcomes = ref([]);
  async function search() {
    let data = await pageList(params);
    params.total = data.data.total;
    outcomes.value = data.data.data;
    outcomes.value.forEach(async (row) => {
      conuts(row);
    });
    $utils.highlight(outcomes.value, ['planName'], params.keyword);
  }

  async function conuts(row) {
    let data = await load(row.outcomeCode);
    row.loads = data.data;
  }

  function devicecount(deviceType, row) {
    try {
      return Object.keys(row[deviceType]).length;
    } catch {
      return 0;
    }
  }

  //用户初始化枚举
  const users = ref([]);
  const router = useRouter();
  async function initialize() {
    if (router.currentRoute.value.query.planCode) {
      params.planCode = router.currentRoute.value.query.planCode;
    }
    let responseuser = await searchUser();
    users.value = responseuser.data;
  }

  function actionStatusing(row, column, cellValue) {
    if (row.state == 1) {
      //正常
      return '未处理';
    } else if (row.state == 2) {
      return '已处理';
    } else {
      //异常
      return '-';
    }
  }

  function outcomeTypeing(row, column, cellValue) {
    if (row.state == 0) {
      //正常
      return {
        str: '正常',
        color: 'green',
      };
    } else if (row.state == 3) {
      let a = outcomes.value
        .filter((item) => item.planCode == row.planCode)
        .sort((a, b) => new Date(b.createTime) - new Date(a.createTime));
      row.newiterationNumber = a[0].iterationNumber;
      return {
        str: '未做',
        color: '#8BEBF2',
      };
    } else {
      //异常
      return { str: '发现异常', color: 'red' };
    }
  }

  function countMetricItems(deviceType, row) {
    // 正常项数量
    let zheng = 0;
    // 异常项数量
    let yi = 0;
    //全部
    let all = 0;
    try {
      Object.keys(row[deviceType]).forEach(async (key) => {
        zheng += row[deviceType][key].manualOutcomeDetails.filter((kk) => {
          return kk.alarmLevel == 3;
        }).length;
      });
      Object.keys(row[deviceType]).forEach(async (key) => {
        yi += row[deviceType][key].manualOutcomeDetails.filter((kk) => {
          return kk.alarmLevel != null && kk.alarmLevel != 3;
        }).length;
      });
      Object.keys(row[deviceType]).forEach(async (key) => {
        all += row[deviceType][key].manualOutcomeDetails.length;
      });
    } catch {}

    // return `<span style="color:green;">${zheng}</span>/<span style="color:red;">${yi}</span>/<span>${
    //   all
    // }</span>`;
    return {
      zheng: zheng,
      yi: yi,
      all: all,
    };
  }

  function countJudgementItems(row) {
    // 正常项数量
    let zheng = 0;
    // 异常项数量
    let yi = 0;
    //全部
    let all = 0;

    try {
      Object.keys(row).forEach((key1) => {
        Object.keys(row[key1]).forEach((key2) => {
          zheng += row[key1][key2].manualOutcomeObserves.filter((kk) => {
            return kk.decide == 0;
          }).length;
          yi += row[key1][key2].manualOutcomeObserves.filter((kk) => {
            return kk.decide == 1;
          }).length;
          all += row[key1][key2].manualOutcomeObserves.length;
        });
      });

      // Object.keys(row[deviceType]).forEach(async (key) => {
      //   zheng += row[deviceType][key].manualOutcomeDetails.filter((kk) => {
      //     return kk.alarmLevel == 3;
      //   }).length;
      // });
    } catch {}
    return {
      zheng: zheng,
      yi: yi,
      all: all,
    };
  }

  async function jieguo(val) {
    if (val === '1' || val === '3') {
      params.actionStatus = '';
    }
  }

  async function zhuangtai(val) {
    if (val) {
      params.outcomeType = '2';
    } else {
      params.outcomeType = '';
    }
  }

  const outcome = ref();
  const dispose = ref(false);
  const dialogVisible = ref(false);
  const dialogVisible1 = ref(false);
  async function handle(row) {
    if (row.state == 3) {
      dialogVisible1.value = !dialogVisible1.value;
    } else {
      dialogVisible.value = !dialogVisible.value;
    }
    outcome.value = row;
  }
  onMounted(initialize(), search());
</script>

<template>
  <!-- <LeftRightContainer>
    <template #left>
      <PipelineSelecter></PipelineSelecter>
    </template>
    <template #right> -->
  <Container class="rightmenu" pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params" class="form">
        <el-form-item label="计划名称">
          <el-input
            style="width: 12rem"
            clearable
            v-model="params.keyword"
            placeholder="请输入"
          />
        </el-form-item>
        <el-form-item label="计划时段">
          <el-date-picker
            clearable
            v-model="dateRange"
            type="daterange"
            style="width: 20rem"
            range-separator="到"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          />
        </el-form-item>
        <el-form-item label="巡检结果">
          <el-select
            style="width: 13rem"
            clearable
            v-model="params.outcomeType"
            placeholder="请选择(单选)"
            @change="jieguo"
          >
            <el-option label="正常" value="1" />
            <el-option label="异常" value="2" />
            <el-option label="未做" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="处理状态">
          <el-select
            clearable
            style="width: 13rem"
            v-model="params.actionStatus"
            placeholder="请选择(单选)"
            @change="zhuangtai"
          >
            <el-option label="未处理" value="1" />
            <el-option label="已处理" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="巡检负责人">
          <el-select
            clearable
            style="width: 13rem"
            v-model="params.responsibleName"
            placeholder="请选择(单选)"
          >
            <el-option
              v-for="item in users"
              :key="item.name"
              :label="item.nickName"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #table>
      <el-table :data="outcomes">
        <el-table-column align="center" label="人工巡检信息">
          <el-table-column
            align="center"
            width="55px"
            type="index"
            label="序号"
          />
          <el-table-column
            align="center"
            min-width="130px"
            prop="planName"
            label="计划名称"
          >
            <template #default="scope">
              <span v-html="scope.row.planName_highlight"></span>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="iterationNumber"
            label="巡检次数"
          />
          <el-table-column
            align="center"
            width="150px"
            prop="dueDate"
            :formatter="$formatter.datetime"
            label="巡检结束时间"
          />
          <el-table-column
            align="center"
            width="90px"
            prop="state"
            label="巡检结果"
          >
            <template #default="scope">
              <span :style="{ color: outcomeTypeing(scope.row).color }">
                {{ outcomeTypeing(scope.row).str }}
              </span>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="state"
            :formatter="actionStatusing"
            label="巡检状态"
          />
          <el-table-column align="center" width="150px" label="处理完成时间">
            <template #default="scope">
              <span v-if="scope.row.state == 2">
                {{ $date.datetime(scope.row.createTime) }}
              </span>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column align="center" label="装置数量统计">
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="仪表(个)"
          >
            <template #default="scope">
              {{ devicecount(1, scope.row.loads) }}
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="阀门(个)"
          >
            <template #default="scope">
              {{ devicecount(2, scope.row.loads) }}
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="疏水阀(个)"
          >
            <template #default="scope">
              {{ devicecount(3, scope.row.loads) }}
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="管段(个)"
          >
            <template #default="scope">
              {{ devicecount(4, scope.row.loads) }}
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="产用汽点"
          >
            <template #default="scope">
              {{
                devicecount(5, scope.row.loads) +
                devicecount(6, scope.row.loads)
              }}
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column
          align="center"
          label="人工巡检结果统计(总数|正常|异常/项)"
        >
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="仪表(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{ countMetricItems(1, scope.row.loads).zheng }}
              </font>
              /
              <font style="color: red">
                {{ countMetricItems(1, scope.row.loads).yi }}
              </font>
              /
              <font>{{ countMetricItems(1, scope.row.loads).all }}</font>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="阀门(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{ countMetricItems(2, scope.row.loads).zheng }}
              </font>
              /
              <font style="color: red">
                {{ countMetricItems(2, scope.row.loads).yi }}
              </font>
              /
              <font>{{ countMetricItems(2, scope.row.loads).all }}</font>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="疏水阀(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{ countMetricItems(3, scope.row.loads).zheng }}
              </font>
              /
              <font style="color: red">
                {{ countMetricItems(3, scope.row.loads).yi }}
              </font>
              /
              <font>{{ countMetricItems(3, scope.row.loads).all }}</font>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="管段(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{ countMetricItems(4, scope.row.loads).zheng }}
              </font>
              /
              <font style="color: red">
                {{ countMetricItems(4, scope.row.loads).yi }}
              </font>
              /
              <font>{{ countMetricItems(4, scope.row.loads).all }}</font>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionType"
            label="观察项(个)"
          >
            <template #default="scope">
              <font style="color: green">
                {{ countJudgementItems(scope.row.loads).zheng }}
              </font>
              /
              <font style="color: red">
                {{ countJudgementItems(scope.row.loads).yi }}
              </font>
              /
              <font>{{ countJudgementItems(scope.row.loads).all }}</font>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="120px"
            prop="inspectionType"
            label="汇总统计(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{
                  countJudgementItems(scope.row.loads).zheng +
                  countMetricItems(4, scope.row.loads).zheng +
                  countMetricItems(3, scope.row.loads).zheng +
                  countMetricItems(2, scope.row.loads).zheng +
                  countMetricItems(1, scope.row.loads).zheng
                }}
              </font>
              /
              <font style="color: red">
                {{
                  countJudgementItems(scope.row.loads).yi +
                  countMetricItems(4, scope.row.loads).yi +
                  countMetricItems(3, scope.row.loads).yi +
                  countMetricItems(2, scope.row.loads).yi +
                  countMetricItems(1, scope.row.loads).yi
                }}
              </font>
              /
              <font>
                {{
                  countJudgementItems(scope.row.loads).all +
                  countMetricItems(4, scope.row.loads).all +
                  countMetricItems(3, scope.row.loads).all +
                  countMetricItems(2, scope.row.loads).all +
                  countMetricItems(1, scope.row.loads).all
                }}
              </font>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column
          align="center"
          label="操作[自定义列]"
          width="150px"
          fixed="right"
        >
          <template #default="scope">
            <el-button
              text
              icon="Edit"
              type="primary"
              @click="
                handle(scope.row),
                  (dispose = false),
                  (diatitle = '查看人工巡检实绩详情')
              "
            >
              查看
            </el-button>
            <el-button
              text
              icon="View"
              type="primary"
              :disabled="scope.row.state != 1"
              @click="
                handle(scope.row),
                  (dispose = true),
                  (diatitle = '处理人工巡检异常')
              "
            >
              处理
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- </template>
  </LeftRightContainer> -->

  <!-- 处理弹出框 -->
  <el-dialog
    v-model="dialogVisible"
    :title="diatitle"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <manualoutcomeManage
      :outcome="outcome"
      :dispose="dispose"
      @save="
        search();
        dialogVisible = false;
      "
    />
  </el-dialog>

  <!-- 查看未做 -->
  <el-dialog
    v-model="dialogVisible1"
    title="未做列表"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <excludeFromDisplay :outcome="outcome" @save="dialogVisible1 = false" />
  </el-dialog>
</template>

<style scoped></style>
