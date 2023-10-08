<script setup name="jobCenterView">
  import { reactive, ref, onMounted, getCurrentInstance } from 'vue';
  import {
    search as searchJobs,
    remove as removeJob,
    updateStatus as updateJobStatus,
    runOne,
  } from '@/api/job';
  import JobManage from './components/JobManage.vue';
  import { ElMessage } from 'element-plus';

  const { $date } = getCurrentInstance().appContext.config.globalProperties;

  // 数据集合
  const jobs = ref([]);
  async function search() {
    const response = await searchJobs();
    jobs.value = response.data;
  }
  onMounted(search);

  // 任务管理
  const jobDialogVisible = ref(false);
  const job = ref(null);
  function addJob() {
    job.value = {
      status: 1,
      jobDataMap: '{}',
      startTime: $date.format(new Date(), 'YYYY-MM-DD'),
    };
    jobDialogVisible.value = true;
  }
  function updateJob(row) {
    job.value = { ...row, status: row.status === 'NORMAL' ? 1 : 0 };
    jobDialogVisible.value = true;
  }
  async function deleteJob(job) {
    await removeJob(job);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }
  async function start(job) {
    await updateJobStatus(job, 1);
    ElMessage({
      message: '开启成功',
      type: 'success',
    });
    search();
  }

  async function stop(job) {
    await updateJobStatus(job, 0);
    ElMessage({
      message: '关闭成功',
      type: 'success',
    });
    search();
  }
  async function manualRun({ jobName, jobGroupName, jobDataMap }) {
    await runOne({ jobName, jobGroupName, jobDataMap });
    ElMessage({
      message: '执行成功',
      type: 'success',
    });
    search();
  }
</script>

<template>
  <Container>
    <template #commands>
      <el-button icon="Plus" @click="addJob" v-permission="'jobmanage.update'">
        添加任务
      </el-button>
      <el-button icon="Search" @click="search">刷新</el-button>
    </template>
    <template #table>
      <el-table border :data="jobs">
        <el-table-column type="index" label="序号" align="center" width="80" />
        <el-table-column
          prop="jobName"
          label="任务"
          align="center"
          width="300"
        />
        <el-table-column prop="jobGroupName" label="任务组" align="center" />
        <el-table-column
          prop="jobClass"
          label="实现类"
          align="center"
          width="200"
        >
          <template #default="scope">
            <div v-if="scope.row.jobClass">
              {{
                scope.row.jobClass.substring(
                  scope.row.jobClass.lastIndexOf('.') + 1
                )
              }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="jobDataMap"
          label="数据"
          align="center"
          min-width="200"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          prop="description"
          label="任务描述"
          align="center"
          width="300"
        />
        <el-table-column
          prop="cronExpressions"
          label="cron表达式"
          align="center"
          width="120"
        >
          <template #default="scope">
            <div v-if="scope.row.cronExpressions">
              <div
                v-for="cronExpression in scope.row.cronExpressions.split(';')"
                :key="cronExpression"
              >
                <el-tag>
                  {{ cronExpression }}
                </el-tag>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="时间间隔" align="center" width="120">
          <template #default="scope">
            <el-tag v-if="scope.row.timeIntervalString">
              {{ scope.row.timeIntervalString }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="startTime"
          label="开始时间"
          align="center"
          width="100"
          :formatter="$formatter.date"
        />
        <el-table-column
          prop="nextFireTime"
          label="下次执行时间"
          width="200"
          align="center"
          :formatter="$formatter.datetimeWithSecond"
        />
        <el-table-column
          prop="status"
          label="任务状态"
          align="center"
          width="100"
        />
        <el-table-column label="操作" align="center" width="300">
          <template #default="scope">
            <div>
              <el-button
                text
                icon="Edit"
                type="primary"
                @click="updateJob(scope.row)"
                v-if="scope.row.jobClass"
                v-permission="'jobmanage.update'"
              >
                修改
              </el-button>
              <el-button
                icon="Delete"
                type="danger"
                v-confirm="() => deleteJob(scope.row)"
                text
                v-permission="'jobmanage.delete'"
              >
                删除
              </el-button>
              <el-button
                text
                icon="Edit"
                type="primary"
                v-if="scope.row.jobClass && scope.row.status !== 'NORMAL'"
                @click="start(scope.row)"
                v-permission="'jobmanage.start'"
              >
                启动
              </el-button>
              <el-button
                text
                icon="Edit"
                type="primary"
                v-if="scope.row.jobClass && scope.row.status === 'NORMAL'"
                @click="stop(scope.row)"
                v-permission="'jobmanage.start'"
              >
                暂停
              </el-button>
              <el-button
                text
                icon="Edit"
                type="primary"
                v-if="scope.row.jobClass"
                @click="manualRun(scope.row)"
                v-permission="'jobmanage.start'"
              >
                手动执行
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- 用户管理框 -->
  <el-dialog v-model="jobDialogVisible" title="任务管理" width="30%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
    <JobManage
      :oldJob="job"
      @save="
        search();
        jobDialogVisible = false;
      "
    />
  </el-dialog>
</template>

<style scoped lang="scss"></style>
