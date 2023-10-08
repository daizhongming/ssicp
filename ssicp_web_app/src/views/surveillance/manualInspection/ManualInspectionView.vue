<script setup>
  // import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import { reactive, onMounted, ref, watch, getCurrentInstance } from 'vue';
  import {
    pageListPlan as pageList,
    removePlan as remove,
    upordown as updatePlans,
  } from '@/api/surveillance/manualInspection.js';
  import manualInspectionManage from './ManualInspectionManage.vue';
  import { findAllInspector as searchUser } from '@/api/user.js';
  import { ElMessage } from 'element-plus';
  import { useRouter } from 'vue-router';
  import { dictStore } from '@/stores/dict';
  import { getDevicesBypId } from '@/api/surveillance/device.js';
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    startTime: '',
    endTime: '',
    responsibleName: '',
    inspectionType: '',
    total: 0,
    order: '',
    prop: 'start_time',
    dateRange: null,
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

  //路由
  const router = useRouter();
  function doCmd(cmd, planCode) {
    if (cmd) {
      router.push({ name: cmd, query: { planCode: planCode } });
    }
  }

  //修改状态
  async function upordown(row) {
    console.log('状态', row);
    await updatePlans(row);
  }

  const plans = ref([]);

  //用户初始化枚举
  const users = ref([]);
  async function initialize() {
    let responseuser = await searchUser();
    users.value = responseuser.data;
    console.log('用户初始化', users.value);
  }

  async function search() {
    console.log(params.startTime, params.endTime);
    let data = await pageList(params);
    params.total = data.data.total;
    $utils.highlight(data.data.data, ['planName'], params.keyword);
    plans.value = data.data.data;
    //计算项
    plans.value.forEach(async (row) => {
      await conuts(row);
    });
  }

  async function conuts(row) {
    //仪表个数/指标项
    let yibiao = 0;
    //阀门个数/指标项
    let famen = 0;
    //疏水阀个数/指标项
    let shushuifa = 0;
    //管段个数//指标项
    let guanduan = 0;

    let pids = JSON.parse(row.pipeIds);
    if (Array.isArray(pids)) {
      const results = await Promise.all(
        pids.map(async (pid) => {
          const data = await getDevicesBypId(pid);
          return data.data;
        })
      );

      // 对所有结果进行汇总计算
      for (const data of results) {
        yibiao += data.yibiao.length;
        famen += data.famen.length;
        shushuifa += data.shushuifa.length;
      }
      guanduan = pids.length;
    }

    row.yibiaoCount = yibiao;
    row.famenCount = famen;
    row.shushuifaCount = shushuifa;
    row.guanduanCount = guanduan;
  }
  //排序
  function sortChange({ order, prop }) {
    params.order = order;
    params.prop = prop;
    search();
  }

  const oldPlan = ref({});
  const dialogVisible = ref(false);
  //添加按钮
  function add() {
    dialogVisible.value = true;
    oldPlan.value = {};
  }

  function openUserUpdate(row) {
    dialogVisible.value = true;
    oldPlan.value = row;
  }

  async function deletePlan(planCode) {
    await remove(planCode);
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    search();
  }
  onMounted(search(), initialize());
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
        <FormCard>
          <el-form-item label="人工巡检名称">
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
              clearable
              v-model="dateRange"
              type="daterange"
              range-separator="到"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
            />
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
          <el-form-item label="巡检类型">
            <el-select
              clearable
              style="width: 13rem"
              v-model="params.inspectionType"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="item in dictStore().manual_inspection_type().all"
                :label="item.dictLabel"
                :value="item.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button icon="Search" @click="search">查询</el-button>
          </el-form-item>
        </FormCard>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="add">添加计划</el-button>
    </template>
    <template #table>
      <el-table
        :data="plans"
        :header-cell-class-name="cellClass"
        class="table"
        @sort-change="sortChange"
      >
        <el-table-column align="center" label="人工巡检信息">
          <el-table-column
            align="center"
            width="55px"
            type="index"
            label="序号"
          />

          <el-table-column
            align="center"
            min-width="150px"
            prop="planName"
            label="人工巡检名称"
          >
            <template #default="scope">
              <el-link
                type="primary"
                :underline="false"
                @click="
                  doCmd('manualInspectionManualoutcome', scope.row.planCode)
                "
              >
                <span v-html="scope.row.planName_highlight"></span>
              </el-link>
            </template>
          </el-table-column>

          <el-table-column align="center" width="90px" prop="" label="巡检类型">
            <template #default="scope">
              {{
                dictStore()
                  .manual_inspection_type()
                  .label(scope.row.inspectionType)
              }}
            </template>
          </el-table-column>

          <el-table-column align="center" width="90px" prop="" label="巡检周期">
            <template #default="scope">
              {{ scope.row.cycle
              }}{{ dictStore().time_unit().label(scope.row.cycleUnit) }}
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            width="90px"
            prop="iterationCount"
            label="次数"
          />

          <el-table-column
            align="center"
            width="90px"
            prop="nickName"
            label="负责人"
          ></el-table-column>
        </el-table-column>

        <el-table-column align="center" label="人工巡检信息">
          <el-table-column
            align="center"
            width="150px"
            prop="startTime"
            :formatter="$formatter.datetime"
            label="计划开始时间"
          />

          <el-table-column
            align="center"
            width="110px"
            prop="yibiaoCount"
            label="仪表数量(个)"
          />
          <el-table-column
            align="center"
            width="110px"
            prop="famenCount"
            label="阀门数量(个)"
          />
          <el-table-column
            align="center"
            width="120px"
            prop="shushuifaCount"
            label="疏水阀数量(个)"
          />
          <el-table-column
            align="center"
            width="110px"
            prop="guanduanCount"
            label="管段数量(个)"
          />
        </el-table-column>
        <el-table-column align="center" label="巡检任务分析统计">
          <el-table-column
            align="center"
            width="110px"
            prop="iterationCount"
            label="总数(项)"
          />

          <el-table-column
            align="center"
            width="110px"
            prop="completedCount"
            label="已完成(项)"
          />

          <el-table-column
            align="center"
            width="110px"
            prop="pipeNumber"
            label="完成率"
          >
            <template #default="scope">
              {{
                isNaN(scope.row.completedCount / scope.row.iterationCount)
                  ? '0'
                  : (
                      (scope.row.completedCount / scope.row.iterationCount) *
                      100
                    ).toFixed(2)
              }}%
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column align="center" label="巡检任务分析统计">
          <el-table-column
            align="center"
            width="90px"
            prop="anomalyCount"
            label="发现次数"
          />

          <el-table-column
            align="center"
            width="90px"
            prop="anomalyNumber"
            label="发现率"
          >
            <template #default="scope">
              {{
                isNaN(scope.row.anomalyCount / scope.row.iterationCount)
                  ? '0'
                  : (
                      (scope.row.anomalyCount / scope.row.iterationCount) *
                      100
                    ).toFixed(2)
              }}%
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            width="90px"
            prop="handledAnomalyCount"
            label="已处理(项)"
          />

          <el-table-column
            align="center"
            width="90px"
            prop="anomalyNumber"
            label="完成率"
          >
            <template #default="scope">
              {{
                isNaN(scope.row.handledAnomalyCount / scope.row.anomalyCount)
                  ? '0'
                  : (
                      (scope.row.handledAnomalyCount / scope.row.anomalyCount) *
                      100
                    ).toFixed(2)
              }}%
            </template>
          </el-table-column>
        </el-table-column>

        <el-table-column align="center" fixed="right" label="操作">
          <el-table-column
            align="center"
            width="90px"
            prop="inspectionStart"
            label="任务状态"
          >
            <template #default="scope">
              <el-switch
                :active-value="0"
                :inactive-value="1"
                active-text="启用"
                inactive-text="停用"
                inline-prompt
                v-model="scope.row.inspectionStart"
                @change="upordown(scope.row)"
              />
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            label="操作[自定义列]"
            width="150px"
            fixed="right"
          >
            <template #default="scope">
              <div>
                <el-button
                  type="primary"
                  text
                  icon="Edit"
                  @click="openUserUpdate(scope.row)"
                  v-permission="'IntelligentInspection.update'"
                >
                  修改
                </el-button>
                <el-button
                  icon="Delete"
                  type="danger"
                  v-confirm="() => deletePlan(scope.row.planCode)"
                  v-permission="'IntelligentInspection.delete'"
                  text
                >
                  删除
                </el-button>
              </div>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- </template>
  </LeftRightContainer> -->
  <!-- 添加弹出框 -->
  <el-dialog
    v-model="dialogVisible"
    title="人工巡检设置"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <manualInspectionManage
      :users="users"
      :oldPlan="oldPlan"
      @save="search(), (dialogVisible = false)"
    />
  </el-dialog>
</template>

<style scoped></style>
