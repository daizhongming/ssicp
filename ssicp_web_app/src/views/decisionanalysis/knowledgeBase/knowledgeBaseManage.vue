<script setup>
  import { watch, ref, onMounted } from 'vue';
  import { dictStore } from '@/stores/dict';
  import {
    getdevice,
    add,
    echo as echoBykid,
    update,
  } from '@/api/surveillance/knowledgeBase.js';
  import { ElMessage } from 'element-plus';
  const emits = defineEmits(['save']);
  const props = defineProps(['kid']);

  watch(
    () => props.kid,
    (newValue, oldValue) => {
      initialize();
    }
  );
  //设备集合
  const devices = ref({});

  const basedto = ref({
    deviceType: {},
    indexs: {},
    knowledgeBase: {},
  });

  async function echo() {
    let data = await echoBykid(props.kid);
    basedto.value = data.data;
    basedto.value.parentDeviceType = Object.keys(basedto.value.indexs);
  }

  function chongzhi() {
    basedto.value = {
      deviceType: {},
      indexs: {},
      knowledgeBase: {},
    };
  }

  async function initialize() {
    chongzhi();
    let data = await getdevice();
    devices.value = data.data;
    if (props.kid) {
      echo();
    }
  }
  const activeName = ref();

  async function formatdevice(types) {
    //初始化指标
    if (!props.kid) {
      basedto.value.indexs[types] = [];
    }
  }
  watch(
    () => basedto.value.parentDeviceType,
    (newValue, oldValue) => {
      if (newValue) {
        activeName.value = newValue[0];
      }
    }
  );

  function addRow(types) {
    if (!basedto.value.indexs[types]) {
      basedto.value.indexs[types] = [];
    }
    basedto.value.indexs[types].push({
      parentFittingsTypes: 1,
      index: '',
      unit: '',
      maxIndexValue: '',
      minIndexValue: '',
    });
  }

  async function deleteRow(row, types) {
    let index = basedto.value.indexs[types].indexOf(row);
    if (index > -1) {
      basedto.value.indexs[types].splice(index, 1);
    }
  }

  async function submitForm() {
  // 过滤 deviceType 和 indexs 对象
  const filteredDeviceType = Object.fromEntries(
    Object.entries(basedto.value.deviceType).filter(([key]) =>
      basedto.value.parentDeviceType.includes(key)
    )
  );

  const filteredIndexs = Object.fromEntries(
    Object.entries(basedto.value.indexs).filter(([key]) =>
      basedto.value.parentDeviceType.includes(key)
    )
  );

  // 创建一个新的对象，包含过滤后的 deviceType 和 indexs，以及其他属性
  const filteredBasedtoValue = {
    ...basedto.value,
    deviceType: filteredDeviceType,
    indexs: filteredIndexs,
  };

  if (basedto.value.knowledgeBase.id) {
    //修改
    await update(filteredBasedtoValue);
    ElMessage({
      message: '修改成功',
      type: 'success',
    });
  } else {
    //新增
    await add(filteredBasedtoValue);
    ElMessage({
      message: '新增成功',
      type: 'success',
    });
  }

  emits('save');
}


  async function handlePipeCodeChange(parameter, row, dicts) {
    row.unit = dicts.find((item) => item.dictValue == parameter).expand1;
  }

  watch(
  () => basedto.value.parentDeviceType,
  async (newValue, oldValue) => {
    const newSet = new Set(newValue); // 假设 newValue 是一个数组
    const oldSet = new Set(oldValue); // 假设 oldValue 也是一个数组
    const difference = new Set([...oldSet].filter(x => !newSet.has(x)));
    difference.forEach(element => {
      basedto.value.indexs[element] = {}
    basedto.value.deviceType[element] = {}
    });
  }
);

  onMounted(initialize);
</script>

<template>
  <el-scrollbar height="50rem" class="global-hide-scrollbar">
    <FormCard title="知识内容信息">
      <el-form label-width="90px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="知识标题" required>
              <el-input v-model="basedto.knowledgeBase.title" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="解决方案" required>
              <el-input
                type="textarea"
                :rows="3"
                v-model="basedto.knowledgeBase.scheme"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="问题现象">
              <el-input
                type="textarea"
                :rows="3"
                v-model="basedto.knowledgeBase.question"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="装置范围" required>
              <el-select
                v-model="basedto.parentDeviceType"
                placeholder="请选择(多选)"
                clearable
                multiple
              >
                <el-option
                  v-for="item in dictStore().dictData('inspection_device_type')
                    .all"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                  :format="formatdevice(item.dictValue)"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </FormCard>
    <FormCard title="应用匹配设置">
      <el-tabs
        v-model="activeName"
        type="card"
        v-if="basedto.parentDeviceType && basedto.parentDeviceType.length > 0"
      >
        <el-tab-pane
          v-for="types in basedto.parentDeviceType"
          :label="dictStore().dictData('inspection_device_type').label(types)"
          :name="types"
        >
          <el-form-item required>
            <template #label>
              选择{{
                dictStore().dictData('inspection_device_type').label(types)
              }}
            </template>
            <el-select
              v-model="basedto.deviceType[types]"
              placeholder="请选择(单选)"
              clearable
              multiple
              filterable
              remote
            >
              <el-option
                v-for="item in devices[types]"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
                <span>{{ item.dictLabel }}</span>
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="指标范围" required>
            <el-table
              :data="basedto.indexs[types]"
              height="200px"
              class="el-table_withoutBottomBorder"
            >
              <template #empty>
                <el-button icon="Plus" @click="addRow(types)" />
              </template>
              <el-table-column
                align="center"
                width="50px"
                type="index"
                label="序号"
              />
              <el-table-column align="center" prop="index" label="指标">
                <template #default="scope">
                  <el-select
                    v-model="scope.row.index"
                    placeholder="请选择(单选)"
                    @change="
                      (parameter) =>
                        handlePipeCodeChange(
                          parameter,
                          scope.row,
                          dictStore().dictData(types + '_index').all
                        )
                    "
                  >
                    <el-option
                      v-for="item in dictStore().dictData(types + '_index').all"
                      :key="item.dictValue"
                      :label="item.dictLabel"
                      :value="item.dictValue"
                    />
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column
                align="center"
                prop="maxIndexValue"
                label="低范围"
              >
                <template #default="scope">
                  <el-input
                    v-model="scope.row.maxIndexValue"
                    placeholder=""
                    clearable
                  >
                    <template #append>{{ scope.row.unit }}</template>
                  </el-input>
                </template>
              </el-table-column>
              <el-table-column
                align="center"
                prop="minIndexValue"
                label="高范围"
              >
                <template #default="scope">
                  <el-input
                    v-model="scope.row.minIndexValue"
                    placeholder=""
                    clearable
                  >
                    <template #append>{{ scope.row.unit }}</template>
                  </el-input>
                </template>
              </el-table-column>
              <el-table-column align="center" label="操作">
                <template #default="{ row, $index }">
                  <el-button
                    icon="Delete"
                    type="danger"
                    @click="deleteRow(row, types)"
                    text
                  >
                    删除
                  </el-button>
                  <el-button
                    icon="Plus"
                    v-if="$index === basedto.indexs[types].length - 1"
                    text
                    type="primary"
                    @click="addRow(types)"
                  >
                    添加
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <div v-else>
        <span>请选择装置范围</span>
      </div>
    </FormCard>
  </el-scrollbar>
  <Footer>
    <el-button size="large" @click="submitForm()">确定</el-button>
  </Footer>
</template>

<style lang="scss" scoped></style>
