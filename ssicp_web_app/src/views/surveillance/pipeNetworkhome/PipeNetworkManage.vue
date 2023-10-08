<script setup>
  import { reactive, ref, onMounted, watch } from 'vue';
  import Pipes from './Pipes.vue';
  import PipeNetManage from './PipeNetManage.vue';
  import { insert, update, load } from '@/api/surveillance/PipeNetwork.js';
  import { ElMessage } from 'element-plus';
  const props = defineProps(['pipeline', 'nowdata']);
  const emits = defineEmits(['okok']);

  const pipeline = ref({});

  const pipeIds = ref([]);

  //提交
  async function submitForm() {
    pipeline.value.pipeIds = JSON.stringify(pipeIds.value);
    if (pipeline.value.id) {
      //修改
      await update(pipeline.value);
    } else {
      //添加
      await insert(pipeline.value);
    }
    emits('okok');
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
  }

  watch(
    () => props.pipeline,
    (newValue, oldValue) => {
      echo();
    }
  );

  watch(
    () => props.nowdata,
    (newValue, oldValue) => {
      echo();
    }
  );

  async function echo() {
    pipeline.value = {};
    pipeIds.value = [];
    console.log(props.pipeline);
    if (props.pipeline) {
      pipeline.value = props.pipeline;
      pipeIds.value = JSON.parse(props.pipeline.pipeIds || '[]');
      //回显
    }
  }

  onMounted(echo);
</script>

<template>
  <FormCard title="管线基本信息">
    <PipeNetManage :pipeline="pipeline" />
  </FormCard>
  <FormCard title="管段信息">
    <Pipes :pipeIds="pipeIds" />
  </FormCard>
  <Footer>
    <el-button size="large" type="primary" @click="submitForm()">
      确定
    </el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>
