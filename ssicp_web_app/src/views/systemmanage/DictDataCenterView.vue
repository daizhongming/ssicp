<script setup>
  import { reactive, ref, onMounted, computed } from 'vue';
  import {
    pageListData as pageList,
    loadData as load,
    loadType,
    delData as del,
    insertData as insert,
    updateData as update,
  } from '@/api/dict.js';
  import { useRoute } from 'vue-router';
  import { datetime as date } from '@/utils/formatter.js';
  import { ElMessage } from 'element-plus';
  import DictExpandShow from './components/DictExpandShow.vue';
  import DictExpandEdit from './components/DictExpandEdit.vue';
  import { dictStore as useDictStore } from '@/stores/dict';
  const dictStore = useDictStore();
  import { useUserStore } from '@/stores/user';
  const userStore = useUserStore();

  const enums = reactive({
    sstatus: undefined,
  });

  const typeCode = ref();

  const total2 = ref(1);

  const ruleFormRef = ref();

  // 查询参数
  const queryParams = reactive({
    page: 1,
    size: 10,
    dictName: '',
    typecode: typeCode.value,
    status: '',
  });

  // 弹出层标题
  const shipping = ref('');

  // 字典表格数据
  const typeList = ref([]);

  const isDisabled = ref();

  // 字典表格数据
  const dialogFormVisible = ref(false);

  //添加表单
  const ssicpDictData = reactive({
    dictSort: 1,
    dictLabel: '',
    dictValue: '',
    status: '0',
    typeCode: typeCode.value,
    parentId: '',
    remark: '',
  });

  //校验规则
  const rules1 = reactive({
    dictValue: [{ required: true, message: '不能为空', trigger: 'blur' }],
    dictLabel: [{ required: true, message: '不能为空', trigger: 'blur' }],
    dictSort: [{ required: true, message: '不能为空', trigger: 'blur' }],
    statuss: [{ required: true, message: '必选项', trigger: 'change' }],
  });

  //点击搜索
  async function handleQuery() {
    queryParams.page = 1;
    search();
  }

  //重置
  async function chongzhi() {
    Object.assign(ssicpDictData, {
      id: '',
      dictSort: 1,
      dictLabel: '',
      dictValue: '',
      status: '0',
      typeCode: typeCode.value,
      parentId: '',
      remark: '',
    });
  }

  async function handleSizeChange(val) {
    console.log(`${val} items per page`);
    queryParams.size = val;
    search();
  }
  async function handleCurrentChange(val) {
    console.log(`current page: ${val}`);
    queryParams.page = val;
    search();
  }

  const resetForm = (formEl) => {
    if (!formEl) return;
    formEl.resetFields();
    chongzhi();
  };

  /** 新增按钮操作 */
  async function handleAdd() {
    chongzhi();
    isDisabled.value = false;
    dialogFormVisible.value = true;
    shipping.value = '添加字典类型';
  }

  //修改框
  async function edit(id) {
    chongzhi();
    shipping.value = '编辑字典类型';
    isDisabled.value = true;
    let data = await load(id);
    // console.log(JSON.parse(JSON.stringify(data)));
    //回显
    Echo(data.data);
    dialogFormVisible.value = true;
  }

  //回显
  async function Echo(data) {
    console.log(JSON.parse(JSON.stringify(data)));
    Object.assign(ssicpDictData, data);
  }

  //新增/修改确认
  const submitForm = async (formEl) => {
    if (!formEl) return;
    await formEl.validate((valid, fields) => {
      if (valid) {
        console.log('submit!');
        tijiao();
      } else {
        console.log('error submit!', fields);
      }
    });
  };

  //新增/修改接口
  async function tijiao() {
    let data = {};
    if (shipping.value === '编辑字典类型') {
      data = await update(ssicpDictData);
    } else {
      data = await insert(ssicpDictData);
    }
    if (data.status === 200) {
      ElMessage({
        message: '保存成功',
        type: 'success',
      });
    }
    dialogFormVisible.value = false;
    search();

    dictStore.queryAllDicts();
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

    dictStore.queryAllDicts();
  }

  //初始化枚举
  async function enumeration() {}

  const dictType = ref({});
  //数据初始化
  async function initialize() {
    const route = useRoute();
    typeCode.value = route.params.code;
    const data = await loadType(route.params.code);
    dictType.value = data.data;
    // typeCode.value = data.data.code;
  }
  const privileges = computed(() => {
    return new Set(JSON.parse(dictType.value.privileges || '[]'));
  });

  onMounted(() => {
    //数据初始化
    initialize();
    enumeration();
    search();
  });

  async function search() {
    queryParams.typecode = typeCode.value;
    typeList.value = [];
    let data = await pageList(queryParams);
    console.log(data);
    typeList.value = data.data.data;
    total2.value = data.data.total;
    console.log(JSON.parse(JSON.stringify(typeList)));
  }
</script>

<template name="dictDataCenter">
  <div class="Center">
    <Card>
      <div class="flex-container">
        <el-form :inline="true" :model="queryParams" class="form">
          <el-form-item label="字典标签" prop="dictType">
            <el-input
              v-model="queryParams.dictName"
              placeholder="请输入字典类型"
              clearable
              style="width: 240px"
            />
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="queryParams.status" placeholder="状态">
              <el-option label="请选择" value="" />
              <el-option label="启用" value="0" />
              <el-option label="停用" value="1" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button icon="Search" @click="handleQuery">搜索</el-button>
          </el-form-item>
        </el-form>

        <div class="commands">
          <el-button
            icon="Plus"
            v-if="userStore.isRoot || privileges.has('add')"
            @click="handleAdd"
          >
            添加字典数据
          </el-button>
        </div>

        <el-table border :data="typeList">
          <el-table-column
            type="index"
            label="序号"
            align="center"
            width="80"
          />
          <el-table-column
            label="字典描述"
            align="center"
            prop="dictLabel"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            label="字典键值"
            align="center"
            prop="dictValue"
            :show-overflow-tooltip="true"
          />
          <el-table-column prop="dictSort" align="center" label="排序" />
          <el-table-column
            prop="expand1"
            align="center"
            :label="dictType.expand1"
            v-if="dictType.expand1"
          >
            <template #default="scope">
              <DictExpandShow
                :type="dictType.expandType1"
                :value="scope.row.expand1"
              />
            </template>
          </el-table-column>
          <el-table-column
            prop="expand2"
            align="center"
            :label="dictType.expand2"
            v-if="dictType.expand2"
          >
            <template #default="scope">
              <DictExpandShow
                :type="dictType.expandType2"
                :value="scope.row.expand2"
              />
            </template>
          </el-table-column>
          <el-table-column
            prop="expand3"
            align="center"
            :label="dictType.expand3"
            v-if="dictType.expand3"
          >
            <template #default="scope">
              <DictExpandShow
                :type="dictType.expandType3"
                :value="scope.row.expand3"
              />
            </template>
          </el-table-column>
          <el-table-column label="状态" align="center" prop="status">
            <template #default="scope">
              <span v-if="scope.row.status == 0" style="color: green">
                启用
              </span>
              <span v-if="scope.row.status == 1" style="color: red">停用</span>
            </template>
          </el-table-column>
          <el-table-column
            label="创建时间"
            align="center"
            prop="createTime"
            :formatter="date"
          />
          <el-table-column
            label="备注"
            align="center"
            prop="remark"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            label="操作"
            v-if="
              userStore.isRoot ||
              privileges.has('update') ||
              privileges.has('delete')
            "
            fixed="right"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template #default="scope">
              <el-button
                icon="Edit"
                type="primary"
                v-if="userStore.isRoot || privileges.has('update')"
                text
                @click="edit(scope.row.id)"
              >
                修改
              </el-button>
              <el-button
                v-if="userStore.isRoot || privileges.has('delete')"
                icon="Delete"
                type="danger"
                v-confirm="() => delbyid(scope.row.id)"
                text
              >
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          class="pagination"
          v-model:current-page="queryParams.page"
          v-model:page-size="queryParams.size"
          :page-sizes="[10, 20, 30, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total2"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </Card>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog
      v-model="dialogFormVisible"
      :title="shipping"
      width="30%"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <FormCard>
        <el-form
          ref="ruleFormRef"
          :model="ssicpDictData"
          :rules="rules1"
          label-width="80px"
        >
          <el-form-item label="数据类型">
            <el-input
              disabled
              v-model="dictType.dictName"
              placeholder="请输入字典类型"
            />
          </el-form-item>
          <el-form-item label="字典名称" prop="dictLabel">
            <el-input
              :disabled="!userStore.isRoot && !privileges.has('dictLabel')"
              v-model="ssicpDictData.dictLabel"
              placeholder="请输入数据标签"
            />
          </el-form-item>
          <el-form-item label="数据键值" prop="dictValue">
            <el-input
              :disabled="!userStore.isRoot && !privileges.has('dictValue')"
              v-model="ssicpDictData.dictValue"
              placeholder="请输入数据标签"
            />
          </el-form-item>
          <el-form-item label="显示排序" prop="dictSort">
            <el-input-number
              :disabled="!userStore.isRoot && !privileges.has('dictSort')"
              v-model="ssicpDictData.dictSort"
              :min="1"
            />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-switch
              :disabled="!userStore.isRoot && !privileges.has('status')"
              active-value="0"
              inactive-value="1"
              active-text="启用"
              inactive-text="停用"
              inline-prompt
              v-model="ssicpDictData.status"
            />
          </el-form-item>
          <el-form-item
            :label="dictType.expand1"
            prop="expand1"
            v-if="dictType.expand1"
          >
            <DictExpandEdit
              :disabled="!userStore.isRoot && !privileges.has('expand1')"
              v-model="ssicpDictData.expand1"
              :type="dictType.expandType1"
            ></DictExpandEdit>
          </el-form-item>
          <el-form-item
            :label="dictType.expand2"
            prop="expand2"
            v-if="dictType.expand2"
          >
            <DictExpandEdit
              :disabled="!userStore.isRoot && !privileges.has('expand2')"
              v-model="ssicpDictData.expand2"
              :type="dictType.expandType2"
            ></DictExpandEdit>
          </el-form-item>
          <el-form-item
            :label="dictType.expand3"
            prop="expand3"
            v-if="dictType.expand3"
          >
            <DictExpandEdit
              :disabled="!userStore.isRoot && !privileges.has('expand3')"
              v-model="ssicpDictData.expand3"
              :type="dictType.expandType3"
            ></DictExpandEdit>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input
              :disabled="!userStore.isRoot && !privileges.has('remark')"
              v-model="ssicpDictData.remark"
              placeholder="请输入内容"
            ></el-input>
          </el-form-item>
        </el-form>
      </FormCard>
      <Footer>
        <el-button size="large" @click="submitForm(ruleFormRef)">
          确 定
        </el-button>
      </Footer>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">
  .Center {
    height: 100%;

    .commands {
      margin-bottom: 0.5rem;
    }

    .table {
      flex: 1 0 0;
      height: 0;
    }

    .pagination {
      margin-top: 1rem;
      justify-content: end;
    }
  }
</style>
