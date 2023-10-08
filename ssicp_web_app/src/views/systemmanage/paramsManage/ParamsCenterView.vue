<script setup name="UserCenter">
  import { reactive, ref, onMounted, watch } from 'vue';
  import { pageList, remove } from '@/api/config.js';
  import paramsManage from './ParamsManage.vue';
  import { ElMessage } from 'element-plus';

  const configs = ref([]);

  const params = reactive({
    keyword: '',
    startTime: '',
    endTime: '',
    page: 1,
    size: 10,
  });

  const configdata = ref();

  const dialogTitle = ref();

  const visible = ref();

  const dateRange = ref();

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

  async function search() {
    let data = await pageList(params);
    console.log(data);
    configs.value = data.data.data;
    params.total = data.data.total;
  }

  //修改按钮
  function update(row) {
    dialogTitle.value = '修改参数';
    configdata.value = row;
    visible.value = !visible.value;
    search();
  }

  //删除按钮
  async function del(id) {
    await remove(id);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }

  //添加按钮
  function add() {
    dialogTitle.value = '添加参数';
    configdata.value = {
      publicFlag: 'Y',
    };
    visible.value = !visible.value;
    search();
  }

  onMounted(() => {
    search();
  });
</script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form @submit.prevent :inline="true" :model="params" class="form">
        <el-form-item label="参数名称">
          <el-input v-model="params.keyword" placeholder="请输入参数名称" />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="dateRange"
            type="daterange"
            range-separator="到"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          />
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="add" v-permission="'config.update'">
        添加
      </el-button>
    </template>

    <template #table>
      <el-table border :data="configs">
        <el-table-column type="index" label="序号" align="center" />
        <el-table-column prop="configName" label="参数名称" align="center" />
        <el-table-column prop="configKey" label="参数键名" align="center" />
        <el-table-column prop="configValue" label="参数键值" align="center" />
        <el-table-column prop="configType" label="系统内置" align="center" />
        <el-table-column prop="remark" label="备注" align="center" />
        <el-table-column
          align="center"
          prop="createTime"
          label="创建时间"
          :formatter="$formatter.datetime"
        />
        <el-table-column label="操作" fixed="right" align="center" width="300">
          <template #default="scope">
            <div>
              <el-button
                text
                icon="Edit"
                type="primary"
                @click="update(scope.row)"
                v-permission="'config.update'"
              >
                修改
              </el-button>

              <el-button
                icon="Delete"
                type="danger"
                v-confirm="() => del(scope.row.configId)"
                text
                v-permission="'config.delete'"
              >
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <div class="userCenter">
    <!-- 添加计划框 -->
    <el-dialog v-model="visible" :title="dialogTitle" width="30%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
      <paramsManage :config="configdata" @save="add" />
    </el-dialog>
  </div>
</template>

<style scoped lang="scss"></style>
