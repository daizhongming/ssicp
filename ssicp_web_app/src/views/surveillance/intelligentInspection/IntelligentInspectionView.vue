<script setup>
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import IntelligentInspectionManage from './IntelligentInspectionManage.vue';
  import {
    pagePlansList,
    removePlan,
    upordown as updatePlans,
  } from '@/api/surveillance/intelligentInspection.js';
  import { reactive, onMounted, ref, watch, getCurrentInstance } from 'vue';
  import { useRouter } from 'vue-router';
  import { ElMessage } from 'element-plus';
  import { findAllInspector as searchUser } from '@/api/user.js';
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
  //表格渲染集合
  const plans = ref([]);

  //查询按钮
  async function search() {
    let data = await pagePlansList(params);
    plans.value = data.data.data;
    params.total = data.data.total;
    $utils.highlight(data.data.data, ['planName'], params.keyword);
    plans.value.forEach(async (row) => {
      await conuts(row);
    });
  }

  //修改状态
  async function upordown(row) {
    console.log('状态', row);
    await updatePlans(row);
  }

  //路由
  const router = useRouter();
  function doCmd(cmd, planCode) {
    if (cmd) {
      router.push({ name: cmd, query: { planCode: planCode } });
    }
  }

  //删除按钮
  async function deletePlan(planCode) {
    await removePlan(planCode);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }

  const inspectionPlan = ref({});
  const userDialogVisible = ref();
  //添加按钮
  function addPlan() {
    inspectionPlan.value = {};
    userDialogVisible.value = true;
  }

  // 修改按钮
  function openUserUpdate(oldPlan) {
    inspectionPlan.value = { ...oldPlan };
    userDialogVisible.value = true;
  }
  //用户初始化枚举
  const users = ref([]);
  async function initialize() {
    //初始化用户
    let responseuser = await searchUser();
    users.value = responseuser.data;
  }

  function nexttime(row, column, cellValue) {
    let timesarray = JSON.parse(row.inspectionTimes);
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth() + 1;
    const currentDay = currentDate.getDate();
    const currentHours = currentDate.getHours();
    const currentMinutes = currentDate.getMinutes();

    let nextTaskTime = null;
    let found = false; // 标志是否找到下一个任务时间

    for (const taskTime of timesarray) {
      const [hour, minute] = taskTime.split(':').map(Number);

      if (
        (hour > currentHours ||
          (hour === currentHours && minute > currentMinutes)) && // 找到今天还未执行的任务
        (!nextTaskTime ||
          hour < nextTaskTime.getHours() ||
          (hour === nextTaskTime.getHours() &&
            minute < nextTaskTime.getMinutes()))
      ) {
        nextTaskTime = new Date(
          currentYear,
          currentMonth - 1,
          currentDay,
          hour,
          minute
        );
        found = true;
      }
    }

    // 如果今天的任务都执行完了，找到明天最早的任务时间
    if (!found) {
      const tomorrowDate = new Date(
        currentYear,
        currentMonth - 1,
        currentDay + 1
      );
      const earliestTime = timesarray
        .map((taskTime) => {
          const [hour, minute] = taskTime.split(':').map(Number);
          return new Date(
            tomorrowDate.getFullYear(),
            tomorrowDate.getMonth(),
            tomorrowDate.getDate(),
            hour,
            minute
          );
        })
        .sort((a, b) => a - b)[0];

      nextTaskTime = earliestTime;
    }

    const formattedNextTaskTime = `${nextTaskTime.getFullYear()}-${(
      nextTaskTime.getMonth() + 1
    )
      .toString()
      .padStart(2, '0')}-${nextTaskTime
      .getDate()
      .toString()
      .padStart(2, '0')} ${nextTaskTime
      .getHours()
      .toString()
      .padStart(2, '0')}:${nextTaskTime
      .getMinutes()
      .toString()
      .padStart(2, '0')}`;

    return formattedNextTaskTime;
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
    //产用汽点个数//指标项
    let chan = 0;
    let yong = 0;
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
        chan += data.chanqidian.length;
        yong += data.yongqidian.length;
      }
      guanduan = pids.length;
    }

    //计算项数
    let item = JSON.parse(row.item);

    row.yibiaoCount = yibiao;
    row.yibiaoNumber =
      item.instrument_index != null ? yibiao * item.instrument_index.length : 0;
    row.famenCount = famen;
    row.famenNumber =
      item.steam_traps_index != null
        ? famen * item.steam_traps_index.length
        : 0;
    row.shushuifaCount = shushuifa;
    row.shushuifaNumber =
      item.valve_index != null ? shushuifa * item.valve_index.length : 0;
    row.guanduanCount = guanduan;
    row.guanduanNumber =
      item.pipe_index != null ? guanduan * item.pipe_index.length : 0;
    row.gasCount = chan + yong;
    row.gasNumber =
      item.steam_production_point_index == null
        ? item.with_steam_spots_index == null
          ? 0
          : yong * item.with_steam_spots_index.length
        : item.with_steam_spots_index == null
        ? chan * item.steam_production_point_index.length
        : chan * item.steam_production_point_index.length +
          yong * item.with_steam_spots_index.length;
  }

  onMounted(() => {
    search();
    initialize();
  });
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
        <el-form-item label="负责人">
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
        <el-form-item label="任务状态">
          <el-select
            clearable
            style="width: 13rem"
            v-model="params.inspectionStart"
            placeholder="请选择(单选)"
          >
            <el-option label="启用" value="0" />
            <el-option label="停用" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button
        icon="Plus"
        @click="addPlan"
        v-permission="'IntelligentInspection.update'"
      >
        配置规则
      </el-button>
    </template>
    <template #table>
      <el-table :data="plans" class="table">
        <el-table-column label="智能巡检信息" align="center">
          <el-table-column
            align="center"
            width="55px"
            type="index"
            label="序号"
          />
          <el-table-column
            min-width="150px"
            prop="planName"
            label="计划巡检名称"
            align="center"
          >
            <template #default="scope">
              <el-link
                type="primary"
                :underline="false"
                @click="
                  doCmd(
                    'intelligentInspectionInspectionoutcome',
                    scope.row.planCode
                  )
                "
              >
                <span v-html="scope.row.planName_highlight"></span>
              </el-link>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="100px"
            prop="cycle"
            label="周期 : 次/天"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="startTime"
            :formatter="$formatter.date"
            label="计划开始时间"
          />
        </el-table-column>
        <el-table-column label="巡检装置与指标数量统计" align="center">
          <el-table-column
            align="center"
            width="109px"
            prop="yibiaoCount"
            label="仪表(个)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="yibiaoNumber"
            label="指标(项)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="famenCount"
            label="阀门(个)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="famenNumber"
            label="指标(项)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="shushuifaCount"
            label="疏水阀(个)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="shushuifaNumber"
            label="指标(项)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="guanduanCount"
            label="管段(个)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="guanduanNumber"
            label="指标(项)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="gasCount"
            label="产用汽点(个)"
          />
          <el-table-column
            align="center"
            width="109px"
            prop="gasNumber"
            label="指标(项)"
          />
        </el-table-column>
        <el-table-column label="巡检执行情况" align="center">
          <el-table-column
            align="center"
            width="110"
            prop="totalCount"
            label="已巡检(次数)"
          />

          <el-table-column
            align="center"
            width="140px"
            label="下次巡检时间"
            :formatter="nexttime"
          />

          <el-table-column
            align="center"
            width="95px"
            prop="nonZeroCount"
            label="异常次数"
          />
        </el-table-column>
        <el-table-column label="操作" fixed="right" align="center">
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

  <div class="userCenter">
    <!-- 添加计划框 -->
    <el-dialog
      v-model="userDialogVisible"
      title="配置智能巡检规则"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <IntelligentInspectionManage
        :oldPlan="inspectionPlan"
        :uuu="users"
        @save="
          search();
          userDialogVisible = false;
        "
      />
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped></style>