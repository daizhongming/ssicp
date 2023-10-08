<script setup>
  import { ref, onMounted, watch, reactive } from 'vue';
  import { upload as uploadFile, getUrl } from '@/api/file.js';

  function code2file(fileCode) {
    return {
      id: fileCode,
      url: getUrl(fileCode),
    };
  }
  const props = defineProps(['modelValue', 'disabled']);
  const list = ref([]);
  function init() {
    list.value = props.modelValue.map(code2file);
  }
  onMounted(init);
  watch(() => props.modelValue, init);

  const emits = defineEmits(['update:modelValue']);

  function handleSuccess(response, file, files) {
    props.modelValue.push(response.data.code);
    emits('update:modelValue', props.modelValue);
  }
  function handleRemove(file, files) {
    console.log(file, files);
    const index = props.modelValue.findIndex((id) => file.id !== id);
    if (index > -1) {
      props.modelValue.splice(index, 1);
    }
    emits('update:modelValue', props.modelValue);
  }

  //预览

  const dialogImageUrl = ref('');
  const dialogVisible = ref(false);
  const imagePreview = ref(null);
  function handlePreview(file) {
    dialogImageUrl.value = file.url;
    dialogVisible.value = true;
  }
  function click() {
    console.log(arguments);
  }
</script>

<template>
  <el-text
    type="info"
    v-if="props.disabled === true && props.modelValue.length === 0"
  >
    无图
  </el-text>
  <el-upload
    v-else
    ref="upload"
    :disabled="props.disabled"
    :limit="0"
    :multiple="true"
    accept=".jpg,.png,.gif"
    :file-list="list"
    list-type="picture-card"
    :on-remove="handleRemove"
    :on-success="handleSuccess"
    :on-preview="handlePreview"
    :auto-upload="true"
    :http-request="(options) => uploadFile(options.file)"
  >
    <el-icon v-if="!props.disabled"><Plus /></el-icon>
  </el-upload>
  <div class="aaa">
    <el-image-viewer
      class="a"
      hide-on-click-modal
      @close="
        () => {
          dialogVisible = false;
        }
      "
      v-if="dialogVisible"
      :url-list="[dialogImageUrl]"
    />
  </div>
</template>

<style scoped lang="scss">
  :deep().el-upload-list--picture-card {
    --el-upload-list-picture-card-size: 60px;
  }

  :deep().el-upload--picture-card {
    --el-upload-picture-card-size: 60px;
  }
  :deep().el-upload-list.el-upload-list--picture-card.is-disabled {
    .el-upload.el-upload--picture-card {
      display: none;
    }
  }
  .aaa {
    :deep().el-image-viewer__btn {
      background-color: rgba(128, 128, 128, 0.5);
    }
  }
</style>
