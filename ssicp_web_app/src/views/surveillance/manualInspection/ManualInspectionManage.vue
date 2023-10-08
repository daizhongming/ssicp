<script setup>
  import { ref, onMounted, watch, reactive } from 'vue';
  import { dictStore } from '@/stores/dict';
  import { ElMessage } from 'element-plus';
  import { pressurePipelineGroupingV2 as pressurePipelineGrouping } from '@/api/surveillance/PipeNetwork.js';
  import {
    addPlan as add,
    updatePlan as update,
  } from '@/api/surveillance/manualInspection.js';

  import PipeTreeSelecter from '@/views/common/PipeTreeSelecter.vue';

  const item = reactive([
    {
      Label: '数据收集项',
      Value: '_index',
    },
    {
      Label: '观察判定项',
      Value: '_decide',
    },
  ]);
  const newPlan = ref({
    cycleUnit: 'DAY',
  });
  const ptype = ref({});
  //自定义属性
  const props = defineProps(['users', 'oldPlan']);
  //自定义事件
  const emits = defineEmits(['save']);

  //管线/段初始化枚举
  const pipes = ref([]);
  const regions = ref([]);
  watch(
    () => regions.value,
    async (newValue, oldValue) => {
      let responsepipe = await pressurePipelineGrouping(regions.value);
      pipes.value = responsepipe.data;
    }
  );

  watch(
    () => props.oldPlan,
    (newValue, oldValue) => {
      newPlan.value = { ...newValue };
      echo();
    }
  );

  async function initialize() {
    let responsepipe = await pressurePipelineGrouping(regions.value);
    pipes.value = responsepipe.data;
  }

  const pipelineId = ref();
  const duan = ref([]);
  const xian = ref([]);
  const treeRef = ref();
  const pipeIds = ref([]);

  async function chongzhi() {
    newPlan.value = {};
    newPlan.value.cycleUnit = 'DAY';
    ptype.value = {};
    xian.value = [];
    duan.value = [];
  }

  async function echo() {
    await chongzhi();
    newPlan.value = { ...props.oldPlan };
    let ppids = [];
    if (newPlan.value.pipelineIds) {
      pipelineId.value = newPlan.value.pipelineIds;
    }
    if (newPlan.value.pipeIds) {
      ppids.push(...JSON.parse(newPlan.value.pipeIds));
      duan.value.push(...JSON.parse(newPlan.value.pipeIds));
    }
    pipeIds.value = ppids;
    if (newPlan.value.item) {
      ptype.value = JSON.parse(newPlan.value.item);
    }
    pipeIds.value = ppids;
  }

  async function save() {
    newPlan.value.item = JSON.stringify(ptype.value);
    newPlan.value.pipeIds = JSON.stringify(Array.from(new Set(pipeIds.value))); //管段id集合
    newPlan.value.pipelineIds = pipelineId.value; //管线id集合
    console.log('整合', newPlan.value);
    if (newPlan.value.planCode) {
      await update(newPlan.value);
    } else {
      await add(newPlan.value);
      chongzhi();
    }
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    emits('save');
  }

  async function watchType(value) {
    if (newPlan.value.inspectionType == '1') {
      newPlan.value.cycleUnit = 'YEAR';
    } else if (newPlan.value.inspectionType == '0') {
      newPlan.value.cycleUnit = 'DAY';
    }
  }

  async function watchUnit(value) {
    if (newPlan.value.cycleUnit == 'YEAR') {
      newPlan.value.inspectionType = '1';
    } else {
      newPlan.value.inspectionType = '0';
    }
  }

  onMounted(() => {
    //初始化数据
    initialize();
    echo();
  });
</script>

<template>
  <el-form label-width="80px">
    <FormCard title="人工巡检基本信息">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="计划名称" required>
            <el-input
              v-model="newPlan.planName"
              clearable
              placeholder="(必填项)"
              minlength="3"
              maxlength="10"
              show-word-limit
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="巡检类型" required>
            <el-select
              style="width: 15rem"
              v-model="newPlan.inspectionType"
              placeholder="请选择(单选)"
              clearable
              @change="watchType"
            >
              <el-option
                v-for="item in dictStore().manual_inspection_type().all"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="负责人" required>
            <el-select
              clearable
              style="width: 15rem"
              v-model="newPlan.responsibleName"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="item in props.users"
                :key="item.name"
                :label="item.nickName"
                :value="item.name"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item clearable label="开始时间" required>
            <el-date-picker
              v-model="newPlan.startTime"
              type="date"
              placeholder="开始时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="巡检周期" required>
            <el-input
              clearable
              v-model="newPlan.cycle"
              oninput="value=value.replace(/[^\d]/g,'')"
              placeholder="单位"
            >
              <template #append>
                <el-select
                  v-model="newPlan.cycleUnit"
                  style="width: 60px"
                  @change="watchUnit"
                >
                  <el-option
                    :key="item.dictValue"
                    :label="item.dictLabel"
                    :value="item.dictValue"
                    v-for="item in dictStore().time_unit().all"
                  />
                </el-select>
              </template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </FormCard>
    <FormCard title="人工巡检范围、内容规则">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="巡检区域">
            <el-select v-model="regions" multiple placeholder="请选择区域">
              <el-option
                v-for="item in dictStore().regional_division().all"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="巡检管段">
            <PipeTreeSelecter
              v-model:pipeIds="pipeIds"
              v-model:pipelineId="pipelineId"
              :regions="regions"
            />
          </el-form-item>
          <!-- <el-tree-select
            ref="treeRef"
            v-model="pipeIds"
            :data="pipes"
            node-key="id"
            multiple
            :render-after-expand="false"
            show-checkbox
            :props="defaultProps"
            @check-change="handleNodeClick"
          />
        </el-form-item> -->
        </el-col>
      </el-row>
    </FormCard>
  </el-form>
  <el-scrollbar height="350px">
    <el-table
      v-for="value in dictStore().inspection_device_type().all"
      :data="item"
    >
      <el-table-column
        width="80px"
        type="index"
        :label="value.dictLabel"
        align="center"
      />
      <el-table-column align="center" width="300px" prop="Label" />
      <el-table-column align="left">
        <template #default="scope">
          <el-checkbox-group v-model="ptype[value.dictValue + scope.row.Value]">
            <el-checkbox
              v-for="index in dictStore().dictData(
                value.dictValue + scope.row.Value
              ).all"
              :label="index.dictValue"
            >
              {{ index.dictLabel }}
            </el-checkbox>
          </el-checkbox-group>
        </template>
      </el-table-column>
    </el-table>
  </el-scrollbar>
  <Footer>
    <el-button size="large" @click="save">保存</el-button>
  </Footer>
</template>

<style scoped></style>
