<script setup>
  import { reactive, ref, onMounted, watch } from 'vue';
  import { add, update } from '@/api/config.js';
  import { ElMessage } from 'element-plus';

  const props = defineProps(['config']);
  const emits = defineEmits(['save']);
  const configdata = ref({});
  onMounted(echo);

  async function echo() {
    configdata.value = props.config;
  }

  async function okok() {
    if (configdata.value.configId) {
      console.log('修改', configdata.value);
      await update(configdata.value)
    } else {
      console.log('添加', configdata.value);
      await add(configdata.value)
    }
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    emits("save")
  }

  watch(
    () => props.config,
    (newValue, oldValue) => {
      configdata.value = { ...newValue };
    }
  );
</script>

<template>
  <el-form label-width="150px">
    <el-row :gutter="16">
      <el-col :span="24">
        <el-form-item label="参数名称" required>
          <el-input v-model="configdata.configName" />
        </el-form-item>
      </el-col>
      <el-col :span="24"></el-col>
      <el-col :span="24">
        <el-form-item label="参数键名" required>
          <el-input v-model="configdata.configKey" />
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="参数键值" required>
          <el-input v-model="configdata.configValue" />
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="系统内置" required>
          <el-switch
            v-model="configdata.configType"
            active-text="是"
            inactive-text="否"
            active-value="Y"
            inactive-value="N"
          />
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="备注">
          <el-input v-model="configdata.remark" />
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <Footer>
    <el-button  size="large"  v-if="!configdata.configId" @click="okok()">添加</el-button>
    <el-button  size="large"  v-if="configdata.configId" @click="okok()">修改</el-button>
  </Footer>
</template>

<style scoped></style>
