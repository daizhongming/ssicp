<script setup>
  import { reactive, ref, onMounted } from 'vue';
  import { pagePlansList, del } from '@/api/surveillance/knowledgeBase.js';
  import knowledgeBaseManage from './knowledgeBaseManage.vue';
  import { ElMessage } from 'element-plus';
  import { useRouter } from 'vue-router';
  // 查询参数
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    total: 0,
  });

  const bases = ref([]);
  async function search() {
    let data = await pagePlansList(params);
    bases.value = data.data.data;
    params.total = data.data.total;
  }

  const dialogTitle = ref('');
  const dialogVisible = ref(false);
  const kid = ref();
  //添加按钮
  async function handleAdd() {
    dialogTitle.value = '新增知识库信息';
    dialogVisible.value = true;
    kid.value = '';
  }

  //修改按钮
  async function edit(id) {
    dialogTitle.value = '修改知识库信息';
    dialogVisible.value = true;
    kid.value = id;
  }

  //路由
  const router = useRouter();
  function doCmd(cmd, id) {
    if (cmd) {
      router.push({ name: cmd, query: { id: id } });
    }
  }

  async function delbyid(id) {
    await del(id);
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    search();
  }

  onMounted(search);
</script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params">
        <el-form-item label="关键字" prop="keyword">
          <el-input
            v-model="params.keyword"
            placeholder="请输入字典名称"
            clearable
            style="width: 240px"
          />
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">搜索</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="handleAdd">添加知识</el-button>
    </template>
    <template #table>
      <el-table :data="bases" border>
        <el-table-column type="index" label="序号" align="center" width="60px" />
        <el-table-column label="知识标题" align="center" min-width="120px">
          <template #default="scope">
            <el-link
              type="primary"
              :underline="false"
              @click="doCmd('knowledgeBaseDetailedKnowledgeBase', scope.row.id)"
            >
              {{ scope.row.title }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          label="问题现象"
          :show-overflow-tooltip="true"
          align="center"
          min-width="150px"
          prop="question"
        />
        <el-table-column label="装置范围" align="center" prop="deviceRange" min-width="150px">
          管段|阀门
        </el-table-column>
        <el-table-column
          label="指标范围"
          min-width="200px"
          align="center"
          prop="indicatorRange"
        >
          温度|阀体前温度|阀体后温度
        </el-table-column>
         <el-table-column
          label="创建时间"
          min-width="150px"
          align="center"
          prop="createTime"
          :formatter="$formatter.datetime"
        />
        <el-table-column label="作者" min-width="70px" align="center" prop="responsibleName" />
        <el-table-column label="查询次数" min-width="90px" align="center" prop="readCount" />
        <el-table-column label="推送次数" min-width="90px"  align="center" prop="pushCount">
          2
        </el-table-column>
        <el-table-column
          label="有效性占比(无/有)"
          align="center"
          min-width="140px"
          prop="validityPercentage"
        >
          1/2
        </el-table-column>
        <el-table-column label="操作[自定义列]" align="center" min-width="140px" fixed="right">
          <template #default="scope">
            <el-button
              icon="Edit"
              text
              type="primary"
              @click="edit(scope.row.id)"
            >
              修改
            </el-button>

            <el-button
              icon="Delete"
              type="danger"
              v-confirm="() => delbyid(scope.row.id)"
              text
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- 处理弹出框 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
    <knowledgeBaseManage :kid="kid" @save="(dialogVisible = false), search" />
  </el-dialog>
</template>

<style scoped lang="scss"></style>
