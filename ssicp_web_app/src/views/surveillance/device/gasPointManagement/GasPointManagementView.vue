<script setup>
  import { reactive, ref, onMounted, getCurrentInstance } from 'vue';
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import { pageList, del } from '@/api/surveillance/device.js';
  import GasPointManagementManage from './GasPointManagementManage.vue';
  import { dictStore } from '@/stores/dict';
  import { pageList as pageListPipe } from '@/api/surveillance/Pipework.js';
  import { ElMessage } from 'element-plus';
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const params = reactive({
    keyword: '',
    parentDeviceType: '10',
    total: 0,
    page: 1,
    size: 10,
  });

  const instruments = ref([]);

  //查询
  async function search() {
    let data = await pageList(params);
    instruments.value = data.data.data;
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
    return pipes.value.find((pipe) => pipe.id == pipeid).pipeName;
  }

  const did = ref('');
  const nowdata = ref();
  async function edit(id) {
    did.value = id;
    nowdata.value = new Date()
    dialogVisible.value = true;
  }

  const dialogVisible = ref(false);
  //修改按钮
  async function add() {
    nowdata.value = new Date()
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
    let aaa = JSON.parse(JSON.stringify(val));
    deletids.value = [];
    aaa.forEach((element) => {
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
                  v-model="params.keyword"
                  placeholder="按照编号、产用汽名称搜索"
                  clearable
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
            添加产用汽点
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
            :data="instruments"
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
              width="90px"
              label="编号"
            >
              <template #default="scope">
                <span v-html="scope.row.deviceCode_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="deviceName"
              min-width="120px"
              label="产用汽名称"
            >
              <template #default="scope">
                <span v-html="scope.row.deviceName_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="parentDeviceType"
              width="90px"
              label="类型"
            >
              <template #default="scope">
                {{
                  dictStore().device_type().label(scope.row.parentDeviceType)
                }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="region"
              width="120px"
              label="所属区域"
            >
              <template #default="scope">
                {{ dictStore().regional_division().label(scope.row.region) }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="pid"
              width="90px"
              label="入口管段"
            >
              <template #default="scope">
                {{ duan(scope.row.pid) }}
              </template>
            </el-table-column>

            <el-table-column
              align="center"
              prop=""
              label="操作[自定义列]"
              width="150px"
              fixed="right"
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
    title="编辑产用汽信息"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <GasPointManagementManage
      :did="did"
      :nowdata="nowdata"
      @okok="(dialogVisible = false), search()"
    />
  </el-dialog>
</template>

<style scoped lang="scss"></style>
