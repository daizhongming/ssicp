<script setup>
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import { reactive, ref, onMounted, watch } from 'vue';
  import {
    pageList,
    findByid,
  } from '@/api/surveillance/InspectionException.js';
  import { listAllData } from '@/api/dict.js';
  import ExceptionManage from '@/components/surveillance/ExceptionManage.vue';

  const params = reactive({
    startTime: '',
    endTime: '',
    disposeState: '',
    abnormalType: '',
    inspectionType: '',
    total: 0,
    page: 1,
    size: 10,
  });
  const dateRange = ref();
  watch(
    () => dateRange.value,
    (newVal) => {
      if (newVal && newVal.length === 2) {
        params.startTime = newVal[0];
        params.endTime = newVal[1];
      } else {
        params.startTime = null;
        params.endTime = null;
      }
    }
  );

  const enums = reactive({});
  const dicttypes = ref([
    'inspection_type',
    'patrol_exception_type',
    'inspection_processing_type',
  ]);
  //枚举检索
  async function enumerate() {
    //获取压力等级
    let data = await listAllData(dicttypes.value);
    Object.assign(enums, data.data);
  }

  const visible = ref(false);
  const exception = ref({});
  const lookorupdate = ref(true);
  async function dispose(id, i) {
    let data = await findByid(id);
    exception.value = data.data;
    visible.value = !visible.value;
    if (i === 1) {
      lookorupdate.value = false;
    } else if (i === 2) {
      lookorupdate.value = true;
      console.log('fasldfjasdlkfjas', lookorupdate.value);
    }
  }

  //查询
  const exceptions = ref([]);
  async function search() {
    let data = await pageList(params);
    exceptions.value = data.data.data;
    params.total = data.data.total;
  }

  onMounted(() => {
    search();
    enumerate();
  });
</script>

<template>
  <LeftRightContainer>
    <template #left>
      <PipelineSelecter />
    </template>
    <template #right>
      <Container class="rightmenu" pagination :params="params" @search="search">
        <template #form>
          <el-form :inline="true" :model="params" class="form">
            <el-row :gutter="0">
              <el-col :span="4">
                <el-form-item label="巡检类型">
                  <el-select
                    v-model="params.inspectionType"
                    placeholder="请选择(单选)"
                  >
                    <el-option
                      v-for="item in enums.inspection_type"
                      :key="item.dictValue"
                      :label="item.dictLabel"
                      :value="item.dictValue"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="异常类型">
                  <el-select
                    v-model="params.abnormalType"
                    placeholder="请选择(单选)"
                  >
                    <el-option
                      v-for="item in enums.patrol_exception_type"
                      :key="item.dictLabel"
                      :label="item.dictLabel"
                      :value="item.dictLabel"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="处理类型">
                  <el-select
                    v-model="params.disposeState"
                    placeholder="请选择(单选)"
                  >
                    <el-option
                      v-for="item in enums.inspection_processing_type"
                      :key="item.dictValue"
                      :label="item.dictLabel"
                      :value="item.dictValue"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="发生时间段">
                  <el-date-picker
                    v-model="dateRange"
                    type="daterange"
                    range-separator="到"
                    start-placeholder="开始时间"
                    end-placeholder="结束时间"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item>
                  <el-button icon="Search" @click="search">查询</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
        <template #commands>
          <el-button icon="Plus" @click="add">上报异常</el-button>
        </template>
        <template #table>
          <el-table :data="exceptions" class="table" border>
            <el-table-column
              align="center"
              width="55px"
              type="index"
              label="序号"
            />
            <el-table-column
              align="center"
              width="110px"
              prop="planName"
              label="巡检任务名称"
            />
            <el-table-column
              align="center"
              width="90px"
              prop="inspectionType"
              label="巡检类型"
              :formatter="
                (...p) => $formatter.dict(enums.inspection_type, p[2])
              "
            />
            <el-table-column
              align="center"
              width="90px"
              prop="pipelineName"
              label="管线名称"
            />
            <el-table-column
              align="center"
              width="90px"
              prop="startPosition"
              label="起始位置"
            />
            <el-table-column
              align="center"
              width="90px"
              prop="endPosition"
              label="结束位置"
            />
            <el-table-column
              align="center"
              width="185px"
              prop="abnormalTypes"
              label="异常类型"
            />
            <el-table-column
              align="center"
              width="90px"
              prop="abnormalLocation"
              label="异常位置"
            />
            <el-table-column
              align="center"
              width="165px"
              prop="createTime"
              label="异常发现时间"
              :formatter="$formatter.datetime"
            />
            <el-table-column
              align="center"
              width="90px"
              prop="disposeState"
              label="处理类型"
              :formatter="
                (...p) =>
                  $formatter.dict(enums.inspection_processing_type, p[2])
              "
            />
            <el-table-column
              align="center"
              width="400px"
              prop="describe"
              label="异常描述"
            />
            <el-table-column label="操作处理" fixed="right" align="center">
              <template #default="scope">
                <div>
                  <el-button
                    icon="Edit"
                    type="primary"
                    v-if="scope.row.disposeState == 0"
                    @click="dispose(scope.row.id, 1)"
                    text
                    v-permission="'inspectionException.handle'"
                  >
                    处理
                  </el-button>
                </div>
                <div>
                  <el-button
                    icon="Edit"
                    type="primary"
                    v-if="scope.row.disposeState == 1"
                    @click="dispose(scope.row.id, 2)"
                    text
                  >
                    查看
                  </el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </Container>
    </template>
  </LeftRightContainer>
  <div>
    <el-dialog
      v-model="visible"
      title="异常处理"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <ExceptionManage
        :exception="exception"
        :lookorupdate="lookorupdate"
        @save="
          search();
          visible = false;
        "
      />
    </el-dialog>
  </div>
</template>

<style scoped lang="scss"></style>
