<script setup>
  import { reactive, ref, onMounted, getCurrentInstance } from 'vue';
  import { useRouter } from 'vue-router';
  import { pageList, del } from '@/api/surveillance/PipeNetwork.js';
  import { ElMessage } from 'element-plus';
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import PipeNetworkManage from './PipeNetworkManage.vue';
  import { dictStore } from '@/stores/dict';
  import { useDeviceStore } from '@/stores/device.js';
  const deviceStore = useDeviceStore();
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const router = useRouter();
  const params = reactive({
    keyword: '',
    region: '',
    total: 0,
    page: 1,
    size: 10,
  });
  const dialogFormVisible = ref(false);

  const shipping = ref('');
  onMounted(async () => {
    search();
  });

  const pipes = ref(); //v3
  //查询
  async function search() {
    let data = await pageList(params);
    pipes.value = data.data.data;
    params.total = data.data.total;
    $utils.highlight(
      data.data.data.map((item) => {
        if (item.startPosition === null) {
          item.startPosition = '';
        }
        if (item.endPosition === null) {
          item.endPosition = '';
        }
        return item;
      }),
      ['pipelineName', 'startPosition', 'endPosition'],
      params.keyword
    );

    // console.log(data, '36');
  }

  //路由
  function doCmd(cmd, id) {
    if (cmd) {
      router.push({ name: cmd, query: { id: id } });
    }
  }

  const pipeline = ref();

  const nowdata = ref();
  //修改框
  async function edit(row) {
    shipping.value = '编辑管段信息';
    pipeline.value = { ...row };
    dialogFormVisible.value = true;
    nowdata.value = new Date();
  }

  //单机删除
  async function delbyid(id) {
    let data = await del(id);
    if (data.status === 200) {
      ElMessage({
        message: '保存成功',
        type: 'success',
      });
    }
    search();
    deviceStore.initDevice();
  }

  //添加框
  const add = () => {
    pipeline.value = {};
    nowdata.value = new Date();
    shipping.value = '添加管线信息';
    dialogFormVisible.value = true;
  };

  // 批量删除
  async function dels() {
    let showMessage = true;
    for (const item of deletids.value) {
      const data = await del(item);
      if (data.status === 200 && showMessage) {
        ElMessage({
          message: '保存成功',
          type: 'success',
        });
        showMessage = false;
      }
    }
    search();
  }

  const deletids = ref([]);
  //多选
  async function handleSelectionChange(val) {
    console.log(JSON.parse(JSON.stringify(val)));
    let aaa = JSON.parse(JSON.stringify(val));
    deletids.value = [];
    aaa.forEach((element) => {
      deletids.value.push(element.id);
    });
    console.log('这得删', deletids.value);
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
                  placeholder="按照管线名称、起始位置、结束位置搜索"
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
            添加管线
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
            :data="pipes"
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
              min-width="90px"
              align="center"
              prop="pipelineName"
              label="管线名称"
            >
              <template #default="scope">
                <el-link
                  type="primary"
                  :underline="false"
                  @click="doCmd('pipeNetworkPipework', scope.row.id)"
                >
                  <span v-html="scope.row.pipelineName_highlight"></span>
                </el-link>
              </template>
            </el-table-column>
            <el-table-column
              width="133px"
              prop="pressureRating"
              label="压力等级(MPa)"
              align="center"
            >
              <template #default="scope">
                {{
                  dictStore()
                    .dictData('pipeline_pressure')
                    .label(scope.row.pressureRating)
                }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="startPosition"
              width="90px"
              label="起始位置"
            >
              <template #default="scope">
                <span v-html="scope.row.startPosition_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="endPosition"
              width="90px"
              label="结束位置"
            >
              <template #default="scope">
                <span v-html="scope.row.endPosition_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              prop="pipelineLength"
              width="90px"
              label="长度(m)"
            />
            <el-table-column
              align="center"
              prop="pipeCount"
              width="106px"
              label="管段数量(个)"
            />
            <el-table-column
              align="center"
              prop="elbowCount"
              width="106px"
              label="弯头数量(个)"
            />
            <el-table-column
              align="center"
              prop="compensatorCount"
              width="120px"
              label="补偿器数量(个)"
            />
            <el-table-column
              align="center"
              prop="valveCount"
              width="106px"
              label="阀门数量(个)"
            />
            <el-table-column
              align="center"
              prop="instrumentCount"
              width="106px"
              label="仪表数量(个)"
            />
            <el-table-column
              align="center"
              prop=""
              label="操作"
              fixed="right"
              width="200"
            >
              <template #default="scope">
                <el-button
                  icon="Edit"
                  text
                  type="primary"
                  @click="edit(scope.row)"
                  v-permission="'PipeNetwork.update'"
                >
                  修改
                </el-button>

                <el-button
                  icon="Delete"
                  type="danger"
                  v-confirm="() => delbyid(scope.row.id)"
                  text
                  v-permission="'PipeNetwork.delete'"
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

  <!-- 添加框 -->
  <el-dialog
    v-model="dialogFormVisible"
    :title="shipping"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <PipeNetworkManage
      :pipeline="pipeline"
      :nowdata="nowdata"
      @okok="
        (dialogFormVisible = !dialogFormVisible),
          search(),
          deviceStore.initDevice()
      "
    />
  </el-dialog>
</template>

<style lang="scss" scoped></style>
