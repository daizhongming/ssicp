<script setup>
  import { reactive, ref, onMounted, getCurrentInstance } from 'vue';
  import { pageList, del } from '@/api/surveillance/Pipework.js';
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import PipeManage from './PipeManage.vue';
  import { datetime as date } from '@/utils/formatter.js';
  import { ElMessage } from 'element-plus';
  import { useRouter } from 'vue-router';
  import { dictStore } from '@/stores/dict';
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  const pid = ref();
  const shipping = ref('');
  const dialogFormVisible = ref(false);
  const pipes = ref();
  const params = reactive({
    keyword: '',
    pid: '',
    total: 0,
    page: 1,
    size: 10,
  });

  const router = useRouter();
  onMounted(() => {
    if (router.currentRoute.value.query.id) {
      params.pid = router.currentRoute.value.query.id;
    }
    search();
  });

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
  }

  const nowdata = ref();
  //修改框
  async function edit(id) {
    shipping.value = '编辑管段信息';
    pid.value = id;
    nowdata.value = new Date();
    dialogFormVisible.value = true;
  }

  //查询
  async function search() {
    let response = await pageList(params);
    params.total = response.data.total;
    pipes.value = response.data.data;
    $utils.highlight(
      response.data.data.map((item) => {
        if (item.startPosition === null) {
          item.startPosition = '';
        }
        if (item.endPosition === null) {
          item.endPosition = '';
        }
        return item;
      }),
      ['pipeCode', 'pipeName', 'startPosition', 'endPosition'],
      params.keyword
    );
  }

  //添加管段框
  async function addpipe() {
    nowdata.value = new Date();
    shipping.value = '添加管段信息';
    dialogFormVisible.value = true;
    pid.value = '';
  }

  const deletids = reactive([]);

  //多选
  async function handleSelectionChange(val) {
    console.log(JSON.parse(JSON.stringify(val)));
    let aaa = JSON.parse(JSON.stringify(val));
    console.log(deletids);
    deletids.value = [];
    aaa.forEach((element) => {
      // console.log(element.id)
      deletids.value.push(element.id);
    });
  }
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
            <el-row>
              <el-form-item label="关键字">
                <el-input
                  v-model="params.keyword"
                  clearable
                  placeholder="按照管段编号、管段名称、起始位置、结束位置搜索"
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
          <el-button icon="Plus" @click="addpipe" v-permission="'Pipe.update'">
            添加管段
          </el-button>
          <!-- <el-button icon="Plus" @click="addpoint">添加测点</el-button> -->
          <el-button icon="Delete" @click="dels" v-permission="'Pipe.delete'">
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
            border
            :data="pipes"
            class="table"
            @selection-change="handleSelectionChange"
          >
            <el-table-column align="center" type="selection"></el-table-column>
            <el-table-column
              align="center"
              width="55px"
              type="index"
              label="序号"
            ></el-table-column>
            <el-table-column
              align="center"
              width="90px"
              prop="pipeCode"
              label="管道编号"
            >
              <template #default="scope">
                <span v-html="scope.row.pipeCode_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              min-width="90px"
              prop="pipeName"
              label="管道名称"
            >
              <template #default="scope">
                <span v-html="scope.row.pipeName_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              width="90px"
              prop="startPosition"
              label="起始位置"
            >
              <template #default="scope">
                <span v-html="scope.row.startPosition_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              width="90px"
              prop="endPosition"
              label="结束位置"
            >
              <template #default="scope">
                <span v-html="scope.row.endPosition_highlight"></span>
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              width="90px"
              prop="drawingCode"
              label="图纸编号"
            />
            <el-table-column
              align="center"
              width="85px"
              prop="pipeLength"
              label="长度(m)"
            />
            <el-table-column
              align="center"
              width="110px"
              prop="usageDuration"
              label="使用年限(年)"
            />
            <el-table-column
              align="center"
              width="120px"
              prop="pipeThickness"
              label="管道厚度(mm)"
            />
            <el-table-column
              align="center"
              width="90px"
              prop="safeGrade"
              label="安全等级"
            />
            <el-table-column
              align="center"
              width="165px"
              prop="insulationMaterial"
              label="保温材料及厚度(mm)"
            >
              <template #default="scope">
                {{
                  dictStore()
                    .pipeline_Insulation()
                    .label(scope.row.insulationMaterial)
                }}
                [{{ scope.row.insulationMaterialThickness }}]
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              width="160px"
              prop="useTime"
              label="投用日期"
              :formatter="date"
            />
            <el-table-column
              align="center"
              width="160px"
              prop="qcDate"
              label="质检日期"
              :formatter="date"
            />
            <el-table-column
              align="center"
              width="200px"
              prop=""
              fixed="right"
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
    v-model="dialogFormVisible"
    :title="shipping"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <el-scrollbar height="700px">
      <PipeManage
        :nowdata="nowdata"
        :pid="pid"
        @okok="(dialogFormVisible = !dialogFormVisible), search()"
      />
    </el-scrollbar>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
