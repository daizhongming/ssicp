<script setup>
  import { reactive, ref, onMounted, getCurrentInstance } from 'vue';
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import { pageList, del } from '@/api/surveillance/device.js';
  import CompensatorManagementManage from './CompensatorManagementManage.vue';
  import { dictStore } from '@/stores/dict';
  import { datetime as date } from '@/utils/formatter.js';
  import { pageList as pageListPipe } from '@/api/surveillance/Pipework.js';
  import { ElMessage } from 'element-plus';
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const params = reactive({
    keyword: '',
    parentDeviceType: '7',
    total: 0,
    page: 1,
    size: 10,
  });

  const compensators = ref([]);

  //查询
  async function search() {
    let data = await pageList(params);
    compensators.value = data.data.data;
    params.total = data.data.total;
    $utils.highlight(
      data.data.data,
      ['deviceCode', 'deviceName'],
      params.keyword
    );
  }

  const pipes = ref([]);
  onMounted(async () => {
    let data = await pageListPipe({
      keyword: '',
      region: '',
      total: 0,
      page: 1,
      size: 1000,
    });
    pipes.value = data.data.data;
    search();
  });

  function duan(pipeid) {
    // return pipes.value.find((pipe) => pipe.id == pipeid).pipeName;
    return null;
  }

  const did = ref('');
  const nowdata = ref();
  async function edit(id) {
    did.value = id;
    nowdata.value = new Date();
    dialogVisible.value = true;
  }

  const dialogVisible = ref(false);
  //修改按钮
  async function add() {
    nowdata.value = new Date();
    did.value = '';
    dialogVisible.value = true;
  }

  async function delbyid(id) {
    await del(id);
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    search();
  }

  // 批量删除
  async function dels() {
    let showMessage = true;
    deletids.value.forEach(async (item, index) => {
      let data = await del(item);
      if (data.status === 200) {
        ElMessage({
          message: '保存成功',
          type: 'success',
        });
        showMessage = false;
      }
      search();
    });
  }

  const deletids = reactive([]);
  //多选
  async function handleSelectionChange(val) {
    console.log(JSON.parse(JSON.stringify(val)));
    let aaa = JSON.parse(JSON.stringify(val));
    // console.log(deletids);
    deletids.value = [];
    aaa.forEach((element) => {
      // console.log(element.id)
      deletids.value.push(element.id);
    });
  }
</script>

<template>
  <LeftRightContainer>
    <template #left><PipelineSelecter /></template>
    <template #right>
      <Container class="rightmenu" pagination :params="params" @search="search">
        <template #form>
          <el-form :inline="true" :model="params" class="form">
            <el-row>
              <el-form-item label="关键字">
                <el-input
                  clearable
                  v-model="params.keyword"
                  placeholder="按照补偿器编号、补偿器名称搜索"
                  style="width: 30rem"
                />
              </el-form-item>
              <el-form-item>
                <el-button icon="Search" @click="search">查询</el-button>
              </el-form-item>
            </el-row>
          </el-form>
        </template>

        <template #commands>
          <el-button
            icon="Plus"
            @click="add"
            v-permission="'PipeNetwork.update'"
          >
            添加补偿器
          </el-button>
          <el-button
            icon="Delete"
            v-confirm="() => dels()"
            v-permission="'PipeNetwork.delete'"
          >
            批量删除
          </el-button>
        </template>
        <template #commands_right>
          <el-button icon="Plus">下载数据模板</el-button>
          <el-button icon="Plus" v-permission="'Pipe.ImportOrexport'">
            导入信息
          </el-button>
          <el-button icon="Delete" v-permission="'Pipe.ImportOrexport'">
            导出信息
          </el-button>
        </template>
        <template #table>
          <el-table
            :data="compensators"
            class="table"
            border
            @selection-change="handleSelectionChange"
          >
            <el-table-column align="center" type="selection" width="55" />
            <el-table-column
              align="center"
              type="index"
              label="序号"
              width="55px"
            />
            <el-table-column
              align="center"
              prop="deviceCode"
              width="100px"
              label="补偿器编号"
            >
              <template #default="scope">
                <span v-html="scope.row.deviceCode_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="deviceName"
              min-width="100px"
              label="补偿器名称"
            >
              <template #default="scope">
                <span v-html="scope.row.deviceName_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="deviceType"
              width="100px"
              label="补偿器类型"
            >
              <template #default="scope">
                {{
                  dictStore()
                    .pipeline_compensator_type()
                    .label(scope.row.deviceType)
                }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="pipeInlet"
              width="90px"
              label="入口管段"
            >
              <template #default="scope">
                {{ duan(scope.row.pipeInlet) }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="pipeExit"
              width="90px"
              label="出口管段"
            >
              <template #default="scope">
                {{ duan(scope.row.pipeExit) }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="workYears"
              width="150px"
              label="工作年限（年）"
            />
            <el-table-column
              align="center"
              prop="useTime"
              width="150px"
              :formatter="date"
              label="投用日期"
            />
            <el-table-column
              align="center"
              prop="usageDuration"
              width="90px"
              label="使用年限"
            />
            <el-table-column
              align="center"
              prop="checkPeriod"
              width="150px"
              label="检查周期（月）"
            />
            <el-table-column
              align="center"
              prop="qcDate"
              width="150px"
              :formatter="date"
              label="质检日期"
            />
            <el-table-column
              align="center"
              fixed="right"
              width="150px"
              label="操作[自定义列]"
            >
              <template #default="scope">
                <el-button
                  icon="Edit"
                  text
                  type="primary"
                  @click="edit(scope.row.id)"
                  v-permission="'Pipe.update'"
                >
                  修改
                </el-button>

                <el-button
                  icon="Delete"
                  type="danger"
                  v-confirm="() => delbyid(scope.row.id)"
                  text
                  v-permission="'Pipe.update'"
                >
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </Container>
    </template>
  </LeftRightContainer>
  <el-dialog
    v-model="dialogVisible"
    title="编辑补偿器"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <CompensatorManagementManage
      :did="did"
      :nowdata="nowdata"
      @okok="(dialogVisible = false), search()"
    />
  </el-dialog>
</template>

<style scoped lang="scss"></style>
