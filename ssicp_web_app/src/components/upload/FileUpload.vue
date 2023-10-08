<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { ElMessage } from 'element-plus';
  import { upload as uploadFile, getMessage } from '@/api/file.js';
  const upload = ref(null);

  const props = defineProps(['modelValue']);

  const emits = defineEmits(['update:modelValue']);

  const fileList = ref([]);
  async function initFile() {
    if (props.modelValue) {
      const res = await getMessage(props.modelValue);
      fileList.value = [{ name: res.data.name }];
    } else {
      fileList.value = [];
    }
  }
  onMounted(initFile);
  watch(() => props.modelValue, initFile);

  const handleExceed = (files, uploadFiles) => {
    ElMessage({
      message: '超出限制，请先删除已上传文件',
      type: 'warning',
    });
  };

  function handleSuccess(response, file, files) {
    console.log(response);
    emits('update:modelValue', response.data.code);
  }
  function handleRemove(file, files) {
    emits('update:modelValue', null);
  }
</script>

<template>
  <el-upload
    class="upload"
    ref="upload"
    :limit="1"
    drag
    :file-list="fileList"
    :on-exceed="handleExceed"
    :on-remove="handleRemove"
    :on-success="handleSuccess"
    :auto-upload="true"
    :http-request="(options) => uploadFile(options.file)"
  >
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      将文件拖进来
      <em>点击上传</em>
    </div>
    <template #tip>
      <div class="el-upload__tip">
        <!-- jpg/png files with a size less than 500kb -->
      </div>
    </template>
  </el-upload>
</template>

<style scoped>
  .upload {
    width: 100%;
  }
</style>
