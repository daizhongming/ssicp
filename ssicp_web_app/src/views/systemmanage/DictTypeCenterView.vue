<script setup>
  import { reactive, ref, onMounted, getCurrentInstance, watch } from 'vue';
  import {
    pageListType as pageList,
    loadType as load,
    delType as del,
    insertType as insert,
    updateType as update,
  } from '@/api/dict.js';
  import { useRouter } from 'vue-router';
  import { datetime as date } from '@/utils/formatter.js';
  import { ElMessage } from 'element-plus';
  import { dictStore as useDictStore } from '@/stores/dict';
  const dictStore = useDictStore();
  const { $utils } = getCurrentInstance().appContext.config.globalProperties;
  import { useUserStore } from '@/stores/user';
  const userStore = useUserStore();

  const router = useRouter();

  const ruleFormRef = ref();

  const isDisabled = ref();

  // 查询参数
  const params = reactive({
    dictName: '',
    page: 1,
    size: 10,
    total: 0,
  });

  // 弹出层标题
  const shipping = ref('');

  // 字典表格数据
  const typeList = ref([]);

  // 字典表格数据
  const dialogFormVisible = ref(false);

  //添加表单
  const sicpDictType = reactive({
    id: '',
    dictName: '',
    code: '',
    status: '0',
    createBy: '',
    updateBy: '',
    remark: '',
    privileges: [],
  });

  //校验规则
  const rules1 = reactive({
    dictName: [
      { required: true, message: '字典名称不能为空', trigger: 'blur' },
    ],
    code: [{ required: true, message: '字典编码不能为空', trigger: 'blur' }],
  });

  //路由
  function doCmd(cmd, code) {
    console.log(code);
    if (code) {
      router.push({ name: cmd, params: { code: code } });
    }
  }

  //重置
  async function chongzhi() {
    Object.assign(sicpDictType, {
      id: '',
      dictName: '',
      code: '',
      status: '0',
      createBy: '',
      updateBy: '',
      remark: '',
      privileges: [],
    });
  }

  /** 新增按钮操作 */
  async function handleAdd() {
    chongzhi();
    isDisabled.value = false;
    dialogFormVisible.value = true;
    shipping.value = '添加字典类型';
  }

  //修改框
  async function edit(code) {
    chongzhi();
    shipping.value = '编辑字典类型';
    isDisabled.value = true;
    let { data } = await load(code);
    //回显
    Echo(data);
    dialogFormVisible.value = true;
  }

  //回显
  async function Echo(data) {
    Object.assign(sicpDictType, data, {
      privileges: data.privileges ? JSON.parse(data.privileges) : [],
    });
  }

  //新增/修改确认
  async function submitForm(formEl) {
    await $utils.formValidation(formEl);
    tijiao();
  }

  //新增/修改接口
  async function tijiao() {
    let data = {};
    if (shipping.value === '编辑字典类型') {
      data = await update(sicpDictType);
    } else {
      data = await insert(sicpDictType);
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
  async function delbyid(code) {
    let data = await del(code);
    if (data.status === 200) {
      ElMessage({
        message: '保存成功',
        type: 'success',
      });
    }
    search();
    dictStore.queryAllDicts();
  }

  async function search() {
    router.replace({ name: router.currentRoute.name, query: params });
    typeList.value = [];
    let data = await pageList(params);
    console.log(data);
    typeList.value = data.data.data;
    params.total = data.data.total;
  }

  async function initParams() {
    const { dictName, page, size } = router.currentRoute.value.query;
    params.dictName = dictName || '';
    params.page = +page || 1;
    params.size = +size || 10;
  }

  onMounted(() => {
    initParams();
    search();
  });
</script>

<template name="dictTypeCenter">
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form @submit.prevent :inline="true" :model="params" class="form">
        <el-form-item label="字典名称" prop="dictName">
          <el-input
            v-model="params.dictName"
            placeholder="请输入字典名称"
            clearable
            style="width: 240px"
          />
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">搜索</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="handleAdd" v-if="userStore.isRoot">
        添加字典类型
      </el-button>
    </template>

    <template #table>
      <el-table :data="typeList" border>
        <el-table-column type="index" label="序号" align="center" width="60" />
        <!-- <el-table-column label="编号" align="center" prop="code" /> -->
        <el-table-column
          label="字典名称"
          align="center"
          prop="dictName"
          width="200"
        >
          <template #default="scope">
            <el-link
              type="primary"
              :underline="false"
              @click="doCmd('dictData', scope.row.code)"
            >
              {{ scope.row.dictName }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          :formatter="date"
          width="180"
        />
        <el-table-column
          label="备注"
          align="center"
          prop="remark"
          min-width="200"
          :show-overflow-tooltip="true"
        />
        <el-table-column
          label="扩展1(名称/类型)"
          align="center"
          prop="expand1"
          width="200"
        >
          <template #default="{ row }">
            <div v-if="row.expand1">
              {{ row.expand1 }}/{{
                dictStore.expandTypes().label(row.expandType1)
              }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="扩展2(名称/类型)"
          align="center"
          prop="expand2"
          width="200"
        >
          <template #default="{ row }">
            <div v-if="row.expand2">
              {{ row.expand2 }}/{{
                dictStore.expandTypes().label(row.expandType2)
              }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="扩展3(名称/类型)"
          align="center"
          prop="expand3"
          width="200"
        >
          <template #default="{ row }">
            <div v-if="row.expand3">
              {{ row.expand3 }}/{{
                dictStore.expandTypes().label(row.expandType3)
              }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="权限"
          align="left"
          width="300"
          :show-overflow-tooltip="true"
          prop="privileges"
        />
        <el-table-column
          v-if="userStore.isRoot"
          label="操作"
          fixed="right"
          align="center"
          width="200"
        >
          <template #default="scope">
            <el-button
              icon="Edit"
              text
              type="primary"
              @click="edit(scope.row.code)"
            >
              修改
            </el-button>

            <el-button
              icon="Delete"
              type="danger"
              v-confirm="() => delbyid(scope.row.code)"
              text
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
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
        :show-message="false"
        ref="ruleFormRef"
        :model="sicpDictType"
        :rules="rules1"
        label-width="80px"
      >
        <el-form-item label="类型名称" prop="dictName">
          <el-input
            v-model="sicpDictType.dictName"
            placeholder="请输入字典名称"
          />
        </el-form-item>
        <el-form-item label="类型编码" prop="code">
          <el-input
            v-model="sicpDictType.code"
            :readonly="isDisabled"
            placeholder="请输入字典名称"
          />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="sicpDictType.remark"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>
        <el-form-item label="扩展1描述" prop="expand1">
          <el-input
            v-model="sicpDictType.expand1"
            placeholder="扩展1描述"
          ></el-input>
        </el-form-item>
        <el-form-item label="扩展1类型" prop="expandType1">
          <el-select placeholder="扩展1类型" v-model="sicpDictType.expandType1">
            <el-option
              v-for="expandType in dictStore.expandTypes().all"
              :label="expandType.name"
              :value="expandType.value"
              :key="expandType.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="扩展2描述" prop="expand2">
          <el-input
            v-model="sicpDictType.expand2"
            placeholder="扩展2描述"
          ></el-input>
        </el-form-item>

        <el-form-item label="扩展2类型" prop="expandType2">
          <el-select placeholder="扩展2类型" v-model="sicpDictType.expandType2">
            <el-option
              v-for="expandType in dictStore.expandTypes().all"
              :label="expandType.name"
              :value="expandType.value"
              :key="expandType.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="扩展3描述" prop="expand3">
          <el-input
            v-model="sicpDictType.expand3"
            placeholder="扩展3描述"
          ></el-input>
        </el-form-item>

        <el-form-item label="扩展3类型" prop="expandType3">
          <el-select placeholder="扩展3类型" v-model="sicpDictType.expandType3">
            <el-option
              v-for="expandType in dictStore.expandTypes().all"
              :label="expandType.name"
              :value="expandType.value"
              :key="expandType.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="权限设置" prop="privileges">
          <el-checkbox-group v-model="sicpDictType.privileges">
            <el-checkbox label="add">新增</el-checkbox>
            <el-checkbox label="delete">删除</el-checkbox>
            <el-checkbox label="update">修改</el-checkbox>
            <br />
            <el-checkbox label="dictValue">字典编码</el-checkbox>
            <el-checkbox label="dictLabel">字典名称</el-checkbox>
            <el-checkbox label="dictSort">字典排序</el-checkbox>
            <el-checkbox label="status">启用/禁用</el-checkbox>
            <el-checkbox label="remark">备注</el-checkbox>
            <br />
            <el-checkbox label="expand1">扩展字段1</el-checkbox>
            <el-checkbox label="expand2">扩展字段2</el-checkbox>
            <el-checkbox label="expand3">扩展字段3</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
    </FormCard>

    <Footer>
      <el-button size="large" @click="submitForm(ruleFormRef)">确 定</el-button>
    </Footer>
  </el-dialog>
</template>

<style scoped lang="scss"></style>
