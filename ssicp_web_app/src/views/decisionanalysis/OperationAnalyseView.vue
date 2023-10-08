<script setup>
  import { reactive, onMounted, ref, computed } from 'vue';
  import { findAllDeviceData } from '@/api/surveillance/device';
  import { dictStore as useDictStore } from '@/stores/dict';
  import _ from 'lodash';
  const dictStore = useDictStore();
  const formInline = reactive({
    keyword: '',
    page: 1,
    size: 10,
    tid: '',
  });

  //表格渲染集合

  const chanqi = ref([]);
  const yongqi = ref([]);

  const arraySpanMethod = ({ row, column, rowIndex, columnIndex }) => {
    //用汽点合并行
    if (rowIndex === chanqi.value.length + 1 && columnIndex === 1) {
      return { rowspan: yongqi.value.length + 1, colspan: 1 };
    }

    //产汽点合并行
    if (rowIndex === 0 && columnIndex === 1) {
      return { rowspan: chanqi.value.length + 1, colspan: 1 };
    }

    if (rowIndex === yongqi.value.length + chanqi.value.length + 2) {
      if (columnIndex === 1) {
        return { rowspan: 1, colspan: 3 };
      }
      if (columnIndex === 2 || columnIndex === 3) {
        return { rowspan: 0, colspan: 0 };
      }
    }

    if (rowIndex === yongqi.value.length + chanqi.value.length + 3) {
      if (columnIndex === 1) {
        return { rowspan: 1, colspan: 3 };
      }
      if (columnIndex === 2 || columnIndex === 3) {
        return { rowspan: 0, colspan: 0 };
      }
    }

    if (columnIndex === 1) {
      if (
        (rowIndex >= 1 && rowIndex <= chanqi.value.length) ||
        (rowIndex > chanqi.value.length + 1 &&
          rowIndex <= yongqi.value.length + chanqi.value.length + 1)
      ) {
        return { rowspan: 0, colspan: 0 };
      }
    }

    //产气点合计
    if (rowIndex === chanqi.value.length && columnIndex === 2) {
      return { rowspan: 1, colspan: 2 };
    }

    if (rowIndex === chanqi.value.length && columnIndex === 3) {
      return { rowspan: 0, colspan: 0 };
    }

    if (rowIndex === chanqi.value.length && columnIndex === 3) {
      return { rowspan: 0, colspan: 0 };
    }

    // //用气点合计
    if (
      rowIndex === yongqi.value.length + chanqi.value.length + 1 &&
      columnIndex === 2
    ) {
      return { rowspan: 1, colspan: 2 };
    }

    if (
      rowIndex === yongqi.value.length + chanqi.value.length + 1 &&
      columnIndex === 3
    ) {
      return { rowspan: 0, colspan: 0 };
    }
  };

  const enums = reactive({
    operation_statistic_type: dictStore.dictData('operation_statistic_type')
      .all,
  });

  //查询按钮
  async function onSubmit() {
    let { data } = await findAllDeviceData();
    let chan = [];
    let yong = [];
    _.forEach(data, (value, key) => {
      if (value.device.parentDeviceType == 5) {
        //产汽点
        chan.push({
          code: value.device.deviceCode,
          flowRate: value.realTimeData.流量.indexValue.toFixed(2),
          gaugePressure: 0,
          heat: value.realTimeData.热量.indexValue.toFixed(2),
          id: value.device.id,
          name: value.device.deviceName,
          pipelineId: '',
          remark: '',
          specificEnthalpy: value.realTimeData.比焓.indexValue.toFixed(2),
          temperature: value.realTimeData.温度.indexValue.toFixed(2),
          type: value.device.parentDeviceType,
        });
      } else if (value.device.parentDeviceType == 6) {
        //用汽点
        yong.push({
          code: value.device.deviceCode,
          flowRate: value.realTimeData.流量.indexValue.toFixed(2),
          gaugePressure: 0,
          heat: value.realTimeData.热量.indexValue.toFixed(2),
          id: value.device.id,
          name: value.device.deviceName,
          pipelineId: '',
          remark: '',
          specificEnthalpy: value.realTimeData.比焓.indexValue.toFixed(2),
          temperature: value.realTimeData.温度.indexValue.toFixed(2),
          type: value.device.parentDeviceType,
        });
      }
    });
    console.log('什么玩意？', data);
    chanqi.value = chan;
    yongqi.value = yong;
    // Object.assign(users, data.data)
  }

  const mergedData = computed(() => {
    const merged = [];

    let countflowRate1 = +0;
    let countheat1 = +0;
    // 处理第一组数据
    chanqi.value.forEach((item) => {
      countflowRate1 += +item.flowRate;
      countheat1 += +item.heat;
      merged.push(item);
    });
    merged.push({
      code: '合计',
      flowRate: countflowRate1,
      heat: countheat1,
    });

    // 处理第二组数据
    let countflowRate = +0;
    let countheat = +0;
    yongqi.value.forEach((item) => {
      merged.push(item);
      countflowRate += +item.flowRate;
      countheat += +item.heat;
    });
    merged.push({
      code: '合计',
      flowRate: countflowRate,
      heat: countheat,
    });
    merged.push({ type: '冷量水里(t/h)', flowRate: '11', heat: '' });
    merged.push({ type: '散热里(GJ/h)', flowRate: '11', heat: '' });

    return merged;
  });

  onMounted(() => {
    onSubmit();
  });
</script>

<template>
  <!-- <LeftRightContainer>
    <template #left>
      <PipelineSelecter />
    </template>
    <template #right> -->
  <Container>
    <template #form>
      <el-form :inline="true" :model="formInline">
        <el-form-item label="关键字">
          <el-input
            v-model="formInline.keyword"
            placeholder="根据内容智能模糊查询"
          />
        </el-form-item>
        <el-form-item label="统计类型">
          <el-select v-model="formInline.tid" placeholder="全部(单选)">
            <el-option
              v-for="yali in enums.operation_statistic_type"
              :label="yali.dictLabel"
              :value="yali.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #table>
      <el-table
        :data="mergedData"
        :span-method="arraySpanMethod"
        border
        class="aaaaaa"
      >
        <el-table-column type="index" label="序号" align="center" width="55px" />
        <el-table-column prop="type" label="统计类型" align="center">
          <template #default="scope">
            <span
              v-if="
                scope.row.type != '冷量水里(t/h)' &&
                scope.row.type != '散热里(GJ/h)'
              "
            >
              {{ useDictStore().device_type().label(scope.row.type) }}
            </span>
            <span v-else>
              {{ scope.row.type }}
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="code" label="编号" align="center" />
        <el-table-column prop="name" label="名称" align="center" />
        <el-table-column prop="flowRate" label="流量(t/h)" align="center" />
        <el-table-column
          prop="gaugePressure"
          label="表压(MPa)"
          align="center"
        />
        <el-table-column prop="temperature" label="温度(℃)" align="center" />
        <el-table-column
          prop="specificEnthalpy"
          label="比焓(kj/kg)"
          align="center"
        />
        <el-table-column prop="heat" label="热量(GJ/h)" align="center" />
        <el-table-column prop="remark" label="备注" align="center" />
      </el-table>
    </template>
  </Container>
  <!-- </template> -->
  <!-- </LeftRightContainer> -->
</template>

<style scoped>
  .el-table /deep/ .el-table__row.is-selected {
    background-color: #f0f0f0; /* 你希望的选中高亮颜色 */
  }
</style>
