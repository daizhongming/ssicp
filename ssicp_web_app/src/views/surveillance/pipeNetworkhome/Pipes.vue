<script setup>
  import { reactive, ref, onMounted, watch } from 'vue';
  import { pageList as pipePageList } from '@/api/surveillance/Pipework.js';
  import { dictStore } from '@/stores/dict';
  import { cloneDeep } from 'lodash';
  const props = defineProps(['pipeIds']);
  const emits = defineEmits(['settings']);
  const pipesok = ref([]);

  watch(
    () => props.pipeIds,
    (newValue, oldValue) => {
      echo();
    }
  );

  //新增行
  async function addRow() {
    pipesok.value.push({});
  }
  //删除行
  async function deleteRow(row) {
    const index = pipesok.value.indexOf(row);
    if (index > -1) {
      pipesok.value.splice(index, 1);
      props.pipeIds.splice(index, 1);
    }
  }

  const pipes = ref([]);
  async function echo() {
    let data = await pipePageList({
      total: 0,
      page: 1,
      size: 10000,
    });
    pipes.value = data.data.data;
    if (props.pipeIds) {
      // 创建一个新数组来存储按 props.pipeIds 顺序排序的 pipes 对象
      const sortedPipesok = [];

      // 遍历 props.pipeIds，并查找对应的 pipe 对象
      for (const id of props.pipeIds) {
        const pipe = pipes.value.find((item) => item.id === id);
        if (pipe) {
          sortedPipesok.push(cloneDeep(pipe)); // 使用深拷贝以避免引用问题
        }
      }

      // 更新 pipesok 的值
      pipesok.value = sortedPipesok;
    } else {
      pipesok.value = [];
    }
  }

  async function handlePipeCodeChange(row, index) {
    console.log('111', pipes.value);
    const selectedPipe = Object.assign(
      {},
      pipes.value.find((pipe) => pipe.id == row)
    );
    if (selectedPipe) {
      pipesok.value[index].pipeName = selectedPipe.pipeName;
      pipesok.value[index].pipeLength = selectedPipe.pipeLength;
      pipesok.value[index].safeGrade = selectedPipe.safeGrade;
      props.pipeIds[index] = selectedPipe.id;
    } else {
      pipesok.value[index] = {};
      props.pipeIds[index] = '';
    }
  }

  onMounted(echo);
</script>

<template>
  <el-table :data="pipesok" height="15vh" class="el-table_withoutBottomBorder">
    <template #empty>
      <el-button icon="Plus" type="primary" @click="addRow()"></el-button>
    </template>
    <el-table-column align="center" type="index" label="序号" />
    <el-table-column align="center" label="管段编号" width="120">
      <template #default="scope">
        <el-select
          v-model="pipesok[scope.$index].id"
          filterable
          @change="(value) => handlePipeCodeChange(value, scope.$index)"
          @clear="handlePipeCodeChange(scope.row.id, scope.$index)"
          placeholder="请选择管段编码"
        >
          <el-option
            v-for="pipe in pipes"
            :key="pipe.id"
            :label="pipe.pipeCode"
            :value="pipe.id"
            :disabled="pipesok.map((item) => item.id).includes(pipe.id)"
          />
        </el-select>
      </template>
    </el-table-column>

    <el-table-column
      align="center"
      prop="pipeName"
      label="管道名称"
    ></el-table-column>

    <el-table-column
      align="center"
      prop="pipeLength"
      label="长度(m)"
    ></el-table-column>

    <el-table-column
      align="center"
      prop="safeGrade"
      :formatter="(row) => dictStore().security_level().label(row.safeGrade)"
      label="安全等级"
    />

    <el-table-column
      header-align="center"
      prop=""
      label="操作"
      width="150"
      fixed="right"
    >
      <template #default="{ row, $index }">
        <el-button icon="Delete" type="danger" @click="deleteRow(row)" text>
          删除
        </el-button>

        <el-button
          icon="Plus"
          v-if="$index === pipesok.length - 1"
          text
          type="primary"
          @click="addRow()"
        >
          添加
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<style lang="scss">
</style>
