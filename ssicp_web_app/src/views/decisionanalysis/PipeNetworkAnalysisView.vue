<script setup>
  import { findAllDeviceData } from '@/api/surveillance/device';
  import { reactive, onMounted, ref } from 'vue';
  import {
    pageList,
    weatherInfo,
  } from '@/api/decisionanalysis/PipeNetworkAnalysis.js';
  import _ from 'lodash';
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    tid: '',
    total: 0,
  });

  //表格渲染集合
  const users = ref([]);

  //查询按钮
  async function search() {
    let pipes = [];
    let { data } = await findAllDeviceData();
    console.log('1111', data);
    _.forEach(data, (value, key) => {
      if (value.device.parentDeviceType == 4) {
        //管段
        pipes.push({
          pipelineId: '',
          pipelineName: value.device.deviceName,
          gp: '',
          nominalDiameter: value.extra.pipe.nominalDiameter.toFixed(1),
          pipelineLength: value.extra.pipe.pipeLength.toFixed(1),
          pipelineFlow: value.realTimeData.流量
            ? value.realTimeData.流量.indexValue.toFixed(2)
            : 0,
          velocityFlow: value.realTimeData.流速
            ? value.realTimeData.流速.indexValue.toFixed(2)
            : 0,
          pressureDrop: value.realTimeData.比压降
            ? value.realTimeData.比压降.indexValue.toFixed(2)
            : 0,
          insulationThickness:
            value.extra.pipeInsulation.insulationMaterialThickness.toFixed(1),
          insulationTemperature: 0,
          insulationEfficiency: 0,
          insulationActualHeatflux: 0,
          insulationTheoreticalHeatflux: 0,
          insulationQualifiedHeatflux: 0,
          insulationEvaluation: 0,
          insulationPressureDrop: 0,
          condensate: 0,
          condensateLocation: 0,
        });
      }
    });
    users.value = pipes;
    // params.total = data.data.total;
  }

  const weather = reactive({});
  onMounted(() => {
    weatherInfo(320113).then((data) => {
      Object.assign(weather, data.lives[0]);
    });
    search();
  });
</script>

<template>
  <!-- <LeftRightContainer>
    <template #left>
      <PipelineSelecter />
    </template>
    <template #right> -->
  <Container class="rightmenu" pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params" class="form">
        <el-form-item label="关键字">
          <el-input v-model="params.keyword" placeholder="关键字" />
        </el-form-item>
        <el-form-item label="按组过滤">
          <el-select v-model="params.tid" placeholder="请选择(单选)">
            <el-option label="yali.dictLabel" value="yali.dictValue" />
          </el-select>
        </el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form>
    </template>
    <template #table>
      <el-table :data="users">
        <el-table-column
          align="center"
          :label="'环境温度(℃)：' + weather.temperature"
        >
          <el-table-column
            align="center"
            width="55px"
            type="index"
            label="序号"
          />
          <el-table-column
            align="center"
            min-width="90px"
            prop="pipelineName"
            label="管段名称"
          />
          <!-- <el-table-column align="center" width="55px" prop="gp" label="分组" /> -->
        </el-table-column>
        <el-table-column
          align="center"
          :label="'环境风力(级)：' + weather.windpower"
        >
          <el-table-column
            align="center"
            min-width="90px"
            prop="nominalDiameter"
            label="公称直径"
          />
          <el-table-column
            align="center"
            min-width="90px"
            prop="pipelineLength"
            label="长度(m)"
          />
          <el-table-column
            align="center"
            min-width="90px"
            prop="pipelineFlow"
            label="流量(t/h)"
          />
          <el-table-column
            align="center"
            min-width="95px"
            prop="velocityFlow"
            label="流速(m/s)"
          />
          <el-table-column
            align="center"
            min-width="135px"
            prop="pressureDrop"
            label="比压降(MPa/ka)"
          />
        </el-table-column>
        <el-table-column align="center" :label="'天气状况：' + weather.weather">
          <el-table-column label="保温层">
            <el-table-column
              align="center"
              min-width="95px"
              prop="insulationThickness"
              label="厚度(mm)"
            />
            <el-table-column
              align="center"
              min-width="107px"
              prop="insulationTemperature"
              label="表面温度(℃)"
            />
            <el-table-column
              align="center"
              min-width="107px"
              prop="insulationEfficiency"
              label="保温效率(%)"
            />
            <el-table-column
              align="center"
              min-width="145px"
              prop="insulationActualHeatflux"
              label="实际热流(w/m^2)"
            />
            <el-table-column
              align="center"
              min-width="145px"
              prop="insulationQualifiedHeatflux"
              label="理论热流(w/m^2)"
            />
            <el-table-column
              align="center"
              min-width="145px"
              prop="insulationEvaluation"
              label="合格热流(w/m^2)"
            />
            <el-table-column
              align="center"
              min-width="90px"
              prop="insulationEvaluation"
              label="保温评价"
            />
            <el-table-column
              align="center"
              min-width="145px"
              prop="insulationPressureDrop"
              label="比温降(℃/km)"
            />
          </el-table-column>
          <el-table-column label="凝结水">
            <el-table-column
              align="center"
              min-width="145px"
              prop="condensate"
              label="凝结水量(kg/h)"
            />
            <el-table-column
              align="center"
              min-width="110px"
              prop="condensateLocation"
              label="产生位置(m)"
            />
          </el-table-column>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- </template>
  </LeftRightContainer> -->
</template>

<style lang="scss" scoped></style>
