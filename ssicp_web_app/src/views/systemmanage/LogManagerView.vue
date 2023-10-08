<script setup>
  import { reactive, ref, onMounted, nextTick } from 'vue';
  import { findAll } from '@/api/log.js';

  // 日志搜索
  const params = reactive({
    keyword: '',
    successFlag: '',
    page: 1,
    size: 10,
    total: 0,
    order: '',
    prop: '',
    dateRange: null,
  });

  const logs = ref([]);
  async function search() {
    const dateRange = params.dateRange;
    const paramsNew = { ...params };
    delete paramsNew.dateRange;
    if (dateRange && dateRange.length == 2) {
      console.log(paramsNew);
      paramsNew.dateRangeStart = dateRange[0];
      paramsNew.dateRangeEnd = dateRange[1];
    }
    let data = await findAll(paramsNew);
    logs.value = data.data.data;
    params.total = data.data.total;
  }
  onMounted(search);

  // 查看明细
  const log = ref({});
  const scrollbarRef = ref(null);
  const dialogVisible = ref(false);
  function seeDetail(row) {
    log.value = { ...row };
    dialogVisible.value = true;
    nextTick(() => {
      scrollbarRef.value.setScrollTop(0);
    });
  }
  //排序
  function sortChange({ order, prop }) {
    params.order = order;
    params.prop = prop;
    search();
  }
</script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params" class="form" @submit.prevent>
        <el-form-item label="操作">
          <el-input v-model="params.keyword" placeholder="请输入操作" />
        </el-form-item>
        <el-form-item label="结果">
          <el-select clearable v-model="params.successFlag">
            <el-option label="成功" value="1"></el-option>
            <el-option label="失败" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="请求时间">
          <el-date-picker
            value-format="YYYY-MM-DD"
            v-model="params.dateRange"
            type="daterange"
            range-separator="到"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          />
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">搜索</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #table>
      <el-table :data="logs" class="table" border @sort-change="sortChange">
        <el-table-column type="index" label="序号" width="80" align="center" />
        <el-table-column
          label="用户名称"
          align="center"
          prop="createUserNickName"
          width="100"
          :show-overflow-tooltip="true"
        />

        <el-table-column
          label="请求时间"
          sortable="custorm"
          align="center"
          width="180"
          prop="createTime"
          :formatter="$formatter.datetimeWithSecond"
        />

        <el-table-column label="IP地址" width="150" align="center" prop="ip" />
        <el-table-column label="操作" prop="classTitle" min-width="300">
          <template #default="scope">
            {{ scope.row.classTitle + '--' + scope.row.methodTitle }}
          </template>
        </el-table-column>

        <el-table-column label="结果" align="center" width="100">
          <template #default="scope">
            <el-tag type="danger" v-if="scope.row.error">失败</el-tag>
            <el-tag type="success" v-else>成功</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="接口耗时"
          align="center"
          prop="costTime"
          width="120"
          sortable="custorm"
        >
          <template #default="scope">{{ scope.row.costTime }}ms</template>
        </el-table-column>

        <el-table-column label="明细" align="center" width="100">
          <template #default="scope">
            <el-button
              text
              type="primary"
              icon="View"
              @click="seeDetail(scope.row)"
              v-permission="'logmanager.details'"
            >
              明细
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- 明细弹出框 -->
  <el-dialog v-model="dialogVisible" title="请求响应明细" width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
    <FormCard title="请求入参">
      <pre>{{ JSON.parse(log.request) }}</pre>
    </FormCard>

    <FormCard title="错误信息" v-if="log.error">
      {{ log.error }}
    </FormCard>
    <FormCard title="响应结果" v-else>
      <el-scrollbar height="50vh" ref="scrollbarRef">
        <pre>{{ JSON.parse(log.response) }}</pre>
      </el-scrollbar>
    </FormCard>
  </el-dialog>
</template>

<style scoped lang="scss"></style>
