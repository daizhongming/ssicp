<script setup>
  import { pageList } from '@/api/surveillance/Pipework.js';
  import { reactive, ref, onMounted, watch } from 'vue';
  const emits = defineEmits(['okok']);
  const props = defineProps(['count', 'tt', 'pid', 'notpid']);
  const params = reactive({
    keyword: '',
    region: '',
    total: 0,
    page: 1,
    size: 10,
  });

  const pipes = ref([]);

  async function search() {
    let data = await pageList(params);
    console.log('看看回显', props.pid);
    pipes.value = data.data.data;
    params.total = data.data.total;

    if (props.tt == '三通') {
      if (props.pid) {
        pid.value = JSON.parse(props.pid);
      } else {
        pid.value = [];
      }
    } else if (props.tt == '弯头') {
      if (props.pid) {
        pid.value = props.pid.replace(/[\[\]"]/g, '');
      } else {
        pid.value = '';
      }
    } else {
      pid.value = props.pid;
    }
  }

  async function checkArray(arr) {
    return arr.length === 1 && arr[0] === '';
  }

  const pid = ref([]);

  watch(
    () => props.pid,
    (newValue, oldValue) => {
      search();
    }
  );
  watch(
    () => props.notpid,
    (newValue, oldValue) => {
      search();
    }
  );

  async function dadio(currentRow, oldCurrentRow) {
    if (props.devicetype != 'exits') {
      console.log(currentRow.id, oldCurrentRow);
      if (!props.notpid.includes(currentRow.id)) {
        pid.value = currentRow.id;
      }
    }
    // else {
    //   let exists = pid.value.some((item) => item === currentRow.id);
    //   if (!exists) {
    //     pid.value.push(currentRow.id);
    //   }
    // }
  }

  onMounted(async () => {
    search();
  });

  const selectedRows = ref([]);
  const multipleTableRef = ref();

  async function submitForm() {
    emits('okok', pid.value);
    pid.value = '';
  }
</script>

<template>
  <FormCard title="管段查询">
    <el-row :gutter="16">
      <el-col :span="10">
        <el-form-item label="关键字">
          <el-input v-model="params.keyword" placeholder="关键字" />
        </el-form-item>
      </el-col>
      <el-col :span="5">
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-col>
    </el-row>
    <el-table
      :data="pipes"
      class="table"
      border
      highlight-current-row
      @selection-change="handleSelectionChange"
      ref="multipleTableRef"
    >
      <el-table-column
        v-if="props.tt != '三通'"
        align="center"
        label="单选"
        width="55"
      >
        <template #default="scope">
          <el-radio-group v-model="pid">
            <el-radio
              :label="scope.row.id"
              :disabled="props.notpid.includes(scope.row.id)"
            >
              {{}}
            </el-radio>
          </el-radio-group>
        </template>
      </el-table-column>

      <el-table-column v-else align="center" label="多选" width="55">
        <template #default="scope">
          <el-checkbox-group
            v-model="pid"
            :max="props.count == 3 ? 2 : props.count"
          >
            <el-checkbox
              :label="scope.row.id"
              :disabled="props.notpid.includes(scope.row.id)"
            >
              {{}}
            </el-checkbox>
          </el-checkbox-group>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="pipeCode" label="管段编号" />
      <el-table-column align="center" prop="pipeName" label="管段名称" />
      <el-table-column align="center" prop="pipeLength" label="长度（m）" />
      <el-table-column align="center" prop="startPosition" label="起始位置" />
    </el-table>
  </FormCard>
  <Footer>
    <el-button size="large" @click="submitForm()">确定</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>
