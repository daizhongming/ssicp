<script setup>
  import { reactive, ref, onMounted } from 'vue';
  import DecisionPlanEdit from '@/views/decisionanalysis/components/DecisionPlanEdit.vue';
  import { findPage, deleteById, updateReadCount } from '@/api/decisionPlan.js';
  import { download as downloadFile, downloadUrl } from '@/api/file.js';
  import { ElMessage } from 'element-plus';
  import { dictStore as useDictStore } from '@/stores/dict';
  const dictStore = useDictStore();
  const params = reactive({
    name: '',
    fileType: '',
    page: 1,
    size: 10,
    total: 0,
  });

  const plans = ref([]);
  async function search() {
    const response = await findPage(params);
    plans.value = response.data.data;
    params.total = response.data.total;
  }
  onMounted(() => {
    search();
  });

  const plan = ref({});
  const dialogVisible = ref(false);
  function add() {
    plan.value = {};
    dialogVisible.value = true;
  }

  function update(row) {
    plan.value = { ...row };
    dialogVisible.value = true;
  }

  async function deleteCommand(id) {
    await deleteById(id);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }

  async function download(row) {
    await downloadFile(row.fileCode, row.fileName);
    await updateReadCount(row.id);
    await search();
  }

  async function preview(row) {
    console.log(row);
    await updateReadCount(row.id);
    await search();
    const url = await downloadUrl(row.fileCode, row.fileName);
    window.open(url, '_blank');
  }
</script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params">
        <el-form-item label="预案名称">
          <el-input v-model="params.name" placeholder="预案名称" clearable />
        </el-form-item>
        <el-form-item label="文件类型">
          <el-select v-model="params.fileType" placeholder="状态" clearable>
            <el-option
              :label="fileType.dictLabel"
              :value="fileType.dictValue"
              :key="fileType.dictValue"
              v-for="fileType in dictStore.file_types().all"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="add" v-permission="'decisionPlan.upload'">
        上传预案
      </el-button>
    </template>
    <template #table>
      <el-table border :data="plans">
        <el-table-column type="index" align="center" label="序号" width="55px"/>
        <el-table-column
          prop="name"
          label="预案名称"
          align="center"
          min-width="160"
        />
        <el-table-column
          prop="uploadTime"
          label="上传时间"
          width="160"
          :formatter="$formatter.datetime"
          align="center"
        />
        <el-table-column prop="uploadUser" label="上传用户" align="center" />
        <el-table-column
          prop="fileName"
          label="文件名"
          align="center"
          min-width="160"
        >
          <template #default="scope">
            <el-button
              type="primary"
              @click="preview(scope.row)"
              text
              v-permission="'decisionPlan.download'"
            >
              {{ scope.row.fileName }}
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          prop="fileType"
          label="文件类型"
          :formatter="(...p) => dictStore.file_types().label(p[2])"
        />
        <el-table-column prop="fileSize" label="文件大小" align="center" />
        <el-table-column prop="readCount" label="查看次数" align="center" />
        <el-table-column
          align="center"
          prop="lastReadTime"
          label="最后查看时间"
          width="160"
          :formatter="$formatter.datetime"
        />
        <el-table-column label="操作" align="center" width="200">
          <template #default="scope">
            <div>
              <el-button
                text
                v-permission="'decisionPlan.upload'"
                icon="Edit"
                type="primary"
                @click="update(scope.row)"
              >
                修改
              </el-button>
              <el-button
                icon="Delete"
                v-permission="'decisionPlan.delete'"
                type="danger"
                v-confirm="() => deleteCommand(scope.row.id)"
                text
              >
                删除
              </el-button>

              <el-button
                text
                icon="Edit"
                type="primary"
                v-permission="'decisionPlan.download'"
                @click="download(scope.row)"
              >
                下载
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- 权限框 -->
  <el-dialog
    v-model="dialogVisible"
    title="预案管理"
    width="30%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <DecisionPlanEdit
      :oldPlan="plan"
      @save="
        search();
        dialogVisible = false;
      "
    />
  </el-dialog>
</template>

<style scoped></style>
