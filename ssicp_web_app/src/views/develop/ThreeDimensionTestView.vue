<script setup>
  import { onMounted, reactive, ref } from 'vue';
  import { listThreeDimensionClients, threeDimensionTest } from '@/api/develop';
  import { ElMessage } from 'element-plus';

  async function search() {
    const { data } = await listThreeDimensionClients();
    clientList.value = data;
  }
  onMounted(search);
  const clientList = ref([]);
  const params = reactive({
    clientId: '',
    position: `{
  "type": "position",
  "data": [
    "pipe1",
    "pipe2"
  ]
}`,
    alarm: `{
  "type": "alarm",
  "data": {
    "id": "pipe1",
    "type": "pipe",
    "alarmLevel": 0,
    "alarmIndex": "温度",
    "alarmValue": 20
  }
}`,
    inspection: `{
  "type": "inspection",
  "data": {
    "paths": [
      "pipe1",
      "pipe2",
      "pipe4",
      "pipe5"
    ],
    "alarms": [
      {
        "id": "pipe1",
        "type": "pipe",
        "alarmLevel": 0,
        "alarmIndex": "温度",
        "alarmValue": 20
      }
    ]
  }
}`,
  });

  const activeName = ref('first');
  async function send(field) {
    if (!params.clientId) {
      ElMessage({
        type: 'warning',
        message: '请选择要发送的客户端',
      });
      return;
    }

    if (!params[field]) {
      ElMessage({
        type: 'warning',
        message: '请输入要发送的内容',
      });
      return;
    }
    try {
      JSON.parse(params[field]);
    } catch (e) {
      ElMessage({
        type: 'warning',
        message: '输入的内容不是json格式',
      });
      return;
    }
    await threeDimensionTest(params.clientId, params[field]);
    ElMessage({
      type: 'success',
      message: '发送成功',
    });
  }
</script>

<template>
  <el-form :inline="true">
    <el-form-item label="客户端">
      <el-select v-model="params.clientId">
        <el-option
          v-for="item in clientList"
          :key="item"
          :label="item"
          :value="item"
        />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button @click="search">刷新客户端列表</el-button>
    </el-form-item>
  </el-form>
  <el-tabs v-model="activeName">
    <el-tab-pane label="定位" name="first">
      <el-form label-width="60px">
        <el-form-item label="请求">
          <el-input
            v-model="params.position"
            type="textarea"
            :autosize="{ minRows: 20, maxRows: 20 }"
          />
        </el-form-item>
        <el-form-item>
          <el-button @click="send('position')">执行</el-button>
        </el-form-item>
      </el-form>
    </el-tab-pane>
    <el-tab-pane label="报警" name="second">
      <el-form label-width="60px">
        <el-form-item label="请求">
          <el-input
            v-model="params.alarm"
            type="textarea"
            :autosize="{ minRows: 20, maxRows: 20 }"
          />
        </el-form-item>
        <el-form-item>
          <el-button @click="send('alarm')">执行</el-button>
        </el-form-item>
      </el-form>
    </el-tab-pane>
    <el-tab-pane label="巡检" name="third">
      <el-form label-width="60px">
        <el-form-item label="请求">
          <el-input
            v-model="params.inspection"
            type="textarea"
            :autosize="{ minRows: 20, maxRows: 20 }"
          />
        </el-form-item>
        <el-form-item>
          <el-button @click="send('inspection')">执行</el-button>
        </el-form-item>
      </el-form>
    </el-tab-pane>
  </el-tabs>
</template>

<style scoped lang="scss"></style>
