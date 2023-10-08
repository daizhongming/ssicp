<script setup>
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import {
    pageOutcomesList,
    loadOutcome,
  } from '@/api/surveillance/intelligentInspection.js';
  import { reactive, onMounted, ref, watch, getCurrentInstance } from 'vue';
  import { findAllInspector as searchUser } from '@/api/user.js';
  import InspectionoutcomeManage from './InspectionoutcomeManage.vue';
  import { useRouter } from 'vue-router';
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    startTime: '',
    endTime: '',
    responsibleName: '',
    confirm: '',
    outcomeType: '',
    actionStatus: '',
    planCode: '',
    total: 0,
  });
  const dateRange = ref(null);
  watch(
    () => dateRange.value,
    (newVal) => {
      if (newVal && newVal.length === 2) {
        params.startTime = newVal[0];
        params.endTime = newVal[1];
      } else {
        params.startTime = null;
        params.endTime = null;
      }
    }
  );

  //表格渲染集合1
  const outcomes = ref([]);
  //查询按钮
  async function search() {
    let data = await pageOutcomesList(params);
    outcomes.value = data.data.data;
    params.total = data.data.total;
    outcomes.value.forEach(async (row) => {
      conuts(row);
    });
    $utils.highlight(data.data.data, ['planName'], params.keyword);
  }

  async function conuts(row) {
    let data = await loadOutcome(row.outcomeCode);
    row.loads = data.data;
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
        zheng += row[deviceType][key].filter((kk) => {
          return kk.alarmLevel == 3;
        }).length;
      });
      Object.keys(row[deviceType]).forEach(async (key) => {
        yi += row[deviceType][key].filter((kk) => {
          return kk.alarmLevel != null && kk.alarmLevel != 3;
        }).length;
      });
      Object.keys(row[deviceType]).forEach(async (key) => {
        all += row[deviceType][key].length;
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

  const users = ref([]);
  async function initialize() {
    let responseuser = await searchUser();
    users.value = responseuser.data;
  }

  const outcome = ref();
  const dispose = ref(false);
  const dialogVisible = ref(false);
  async function handle(row) {
    outcome.value = row;
    dialogVisible.value = true;
  }

  async function cellClass(row) {
    if (row.columnIndex === 0) {
      return 'disabledCheck';
    }
  }

  function formatTotalIndexNumber(cellValue) {
    if (typeof cellValue === 'string') {
      let numbers = cellValue.split('/');
      return `<span style="color:green;">${numbers[0]}</span>/<span style="color:red;">${numbers[1]}</span>/<span>${numbers[2]}</span>`;
    } else {
      // 返回一个默认值或者抛出一个错误
      return '';
    }
  }

  function outcomeTypeing(row) {
    console.log(row);
    if (row.state == 0) {
      //正常
      return { span: `<span>正常</span>` };
    } else {
      //异常
      return { span: `<span style="color:red;">发现异常</span>` };
    }
  }

  function actionStatusing(row, column, cellValue) {
    if (row.state == 1) {
      //正常
      return { span: `<span style="color:red;">未处理</span>` };
    } else if (row.state == 2) {
      return { span: `<span">已处理</span>` };
    } else {
      //异常
      return { span: `<span">-</span>` };
    }
  }

  async function jieguo(val) {
    if (val === '1') {
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

  const router = useRouter();
  onMounted(async () => {
    if (router.currentRoute.value.query.planCode) {
      params.planCode = router.currentRoute.value.query.planCode;
    }
    await search();
    initialize();
  });
</script>

<template>
  <!-- <LeftRightContainer>
    <template #left>
      <PipelineSelecter />
    </template>
    <template #right> -->
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params" class="form">
        <el-form-item label="计划巡检名称">
          <el-input
            clearable
            style="width: 12rem"
            v-model="params.keyword"
            placeholder="请输入"
          />
        </el-form-item>
        <el-form-item label="计划时间段">
          <el-date-picker
            style="width: 20rem"
            v-model="dateRange"
            type="daterange"
            range-separator="到"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          />
        </el-form-item>
        <el-form-item label="巡检结果">
          <el-select
            v-model="params.outcomeType"
            clearable
            style="width: 13rem"
            @change="jieguo"
            placeholder="全部"
          >
            <el-option label="正常" value="1" />
            <el-option label="异常" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="处理状态">
          <el-select
            v-model="params.actionStatus"
            clearable
            style="width: 13rem"
            @change="zhuangtai"
            placeholder="全部"
          >
            <el-option label="未处理" value="1" />
            <el-option label="已处理" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="负责人">
          <el-select
            clearable
            style="width: 13rem"
            v-model="params.responsibleName"
            class="m-2"
            placeholder="选择负责人"
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
      <el-table
        :data="outcomes"
        :header-cell-class-name="cellClass"
        class="table"
      >
        <el-table-column prop="" label="智能巡检信息" align="center">
          <el-table-column
            align="center"
            width="55px"
            type="index"
            label="序号"
          />
          <el-table-column
            align="center"
            min-width="110px"
            prop="planName"
            label="计划巡检名称"
          >
            <template #default="scope">
              <span v-html="scope.row.planName_highlight"></span>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="110px"
            prop="iterationNumber"
            label="巡检次数"
          />

          <el-table-column
            prop="createTime"
            align="center"
            width="150px"
            :formatter="$formatter.datetime"
            label="巡检结束时间"
          />

          <el-table-column align="center" width="90px" label="巡检结果">
            <template #default="scope">
              <span v-html="outcomeTypeing(scope.row).span"></span>
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            width="90px"
            :formatter="actionStatusing"
            label="处理状态"
          >
            <template #default="scope">
              <span v-html="actionStatusing(scope.row).span"></span>
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            width="150px"
            prop="updateTime"
            :formatter="$formatter.datetime"
            label="处理完成时间"
          />
        </el-table-column>

        <el-table-column
          align="center"
          width="90px"
          prop=""
          label="装置数量统计"
        >
          <el-table-column
            align="center"
            width="75px"
            prop="instrumentCount"
            label="仪表(个)"
          />
          <el-table-column
            align="center"
            width="75px"
            prop="steamTrapsCount"
            label="阀门(个)"
          />

          <el-table-column
            align="center"
            width="90px"
            prop="valveCount"
            label="疏水阀(个)"
          />

          <el-table-column
            align="center"
            width="75px"
            prop="pipeCount"
            label="管段(个)"
          />

          <el-table-column align="center" width="110px" label="产用汽点(个)">
            <template #default="scope">
              {{
                scope.row.steamProductionPointCount +
                scope.row.withSteamSpotsCount
              }}
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column
          prop=""
          label="智能巡检结果统计(总数|正常|异常/项)"
          align="center"
        >
          <el-table-column
            align="center"
            width="110px"
            prop="numInstrument"
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
            width="110px"
            prop="numSteamTraps"
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
            width="110px"
            prop="numValve"
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
            width="110px"
            prop="numPipe"
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
            width="110px"
            prop="totalIndexNumber"
            label="产用汽点(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{
                  countMetricItems(5, scope.row.loads).zheng +
                  countMetricItems(6, scope.row.loads).zheng
                }}
              </font>
              /
              <font style="color: red">
                {{
                  countMetricItems(5, scope.row.loads).yi +
                  countMetricItems(6, scope.row.loads).yi
                }}
              </font>
              /
              <font>
                {{
                  countMetricItems(5, scope.row.loads).all +
                  countMetricItems(6, scope.row.loads).all
                }}
              </font>
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            width="110px"
            prop="totalIndexNumber"
            label="汇总统计(项)"
          >
            <template #default="scope">
              <font style="color: green">
                {{
                  countMetricItems(4, scope.row.loads).zheng +
                  countMetricItems(3, scope.row.loads).zheng +
                  countMetricItems(2, scope.row.loads).zheng +
                  countMetricItems(1, scope.row.loads).zheng
                }}
              </font>
              /
              <font style="color: red">
                {{
                  countMetricItems(4, scope.row.loads).yi +
                  countMetricItems(3, scope.row.loads).yi +
                  countMetricItems(2, scope.row.loads).yi +
                  countMetricItems(1, scope.row.loads).yi
                }}
              </font>
              /
              <font>
                {{
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
          prop=""
          label="操作[自定义列]"
          fixed="right"
          width="150px"
        >
          <template #default="scope">
            <el-button
              text
              type="primary"
              icon="Search"
              @click="handle(scope.row), (dispose = false)"
            >
              查看
            </el-button>
            <el-button
              text
              type="primary"
              icon="Edit"
              :disabled="scope.row.state !== 1"
              @click="handle(scope.row), (dispose = true)"
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
    title="查看智能巡检结果"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <InspectionoutcomeManage
      :outcome="outcome"
      :dispose="dispose"
      @save="
        search();
        dialogVisible = false;
      "
    />
  </el-dialog>
</template>

<style lang="scss" scoped></style>
