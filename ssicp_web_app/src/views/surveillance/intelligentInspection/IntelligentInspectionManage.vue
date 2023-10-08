<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { ElMessage } from 'element-plus';
  import { pressurePipelineGroupingV2 as pressurePipelineGrouping } from '@/api/surveillance/PipeNetwork.js';
  import {
    addPlans,
    updatePlans,
  } from '@/api/surveillance/intelligentInspection.js';
  import { dictStore } from '@/stores/dict';
  import PipeTreeSelecter from '@/views/common/PipeTreeSelecter.vue';

  //自定义属性
  const props = defineProps(['oldPlan', 'uuu']);

  //自定义属性
  const users = ref([]);
  const newPlan = ref({});
  //管线/段初始化枚举
  const pipes = ref([]);
  onMounted(() => {
    echo();
    //初始化数据
    initialize();
  });

  watch(
    () => props.oldPlan,
    (newValue, oldValue) => {
      echo();
    }
  );

  async function chongzhi() {
    dictStore()
      .dictData('inspection_device_type')
      .all.forEach((key, value) => {
        dtype.value[key.dictValue + '_index'] = false;
      });
    ptype.value = {};
    duan.value = [];
    pipeIds.value = [];
    pipelineId.value = '';
    isIndeterminate.value = {};
  }

  const alarms = ref([]);
  const times = ref([]);
  const pipeIds = ref([]);
  async function echo() {
    await chongzhi();
    let ppids = [];
    newPlan.value = { ...props.oldPlan };
    if (newPlan.value.planCode) {
      // let data = await getIndicatortemplate(newPlan.value.planCode);
      // ptype.value = data.data;

      if (newPlan.value.item) {
        ptype.value = JSON.parse(newPlan.value.item);
      }
      Object.keys(ptype.value).forEach((key) => {
        // 判断 ptype 对应属性是否有值，即非空
        if (ptype.value[key]) {
          // 设置 dtype 对应属性为 true
          dtype.value[key] = true;
        } else {
          // 设置 dtype 对应属性为 false
          dtype.value[key] = false;
        }
      });
    }
    if (newPlan.value.inspectionTimes) {
      times.value = JSON.parse(newPlan.value.inspectionTimes);
    } else {
      times.value = [];
    }
    if (newPlan.value.pipeIds) {
      ppids.push(...JSON.parse(newPlan.value.pipeIds));
      duan.value.push(...JSON.parse(newPlan.value.pipeIds));
    }
    if (newPlan.value.deviceAlarmIds) {
      alarms.value = JSON.parse(newPlan.value.deviceAlarmIds);
    } else {
      alarms.value = [];
    }
    pipeIds.value = JSON.parse(newPlan.value.pipeIds || '[]');
    pipelineId.value = newPlan.value.pipelineIds;
  }

  const regions = ref([]);
  watch(
    () => regions.value,
    async (newValue, oldValue) => {
      let responsepipe = await pressurePipelineGrouping(regions.value);
      pipes.value = responsepipe.data;
    }
  );

  async function initialize() {
    let responsepipe = await pressurePipelineGrouping(regions.value);
    pipes.value = responsepipe.data;
    users.value = props.uuu;
  }

  // 添加
  async function add() {
    if (times.value.length > 0) {
      let aaa = times.value.filter(
        (value) => value !== null && value !== undefined && value !== ''
      );
      console.log(aaa);
      newPlan.value.inspectionTimes = JSON.stringify(aaa); //巡检时间集合
    }
    newPlan.value.pipeIds = ref(Array.from(new Set(pipeIds.value))); //管段id集合
    newPlan.value.pipelineIds = pipelineId.value; //管线
    newPlan.value.metricTemplateDTOS = ptype.value; //指标模板集合
    await addPlans(newPlan.value);
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    await chongzhi();
    emits('save');
  }

  // 修改
  async function update() {
    console.log(times.value);
    if (times.value.length > 0) {
      let aaa = times.value.filter(
        (value) => value !== null && value !== undefined && value !== ''
      );
      console.log(aaa);
      newPlan.value.inspectionTimes = JSON.stringify(aaa); //巡检时间集合
    }
    newPlan.value.pipeIds = ref(Array.from(new Set(pipeIds.value))); //管段id集合
    newPlan.value.pipelineIds = pipelineId.value; //管线id集合
    newPlan.value.metricTemplateDTOS = Object.fromEntries(
      Object.entries(ptype.value).filter(([key, obj]) => obj && obj.length > 0)
    );
    //指标模板集合
    await updatePlans(newPlan.value);
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    await chongzhi();
    emits('save');
  }

  async function addTime() {
    times.value.push(null); // 添加一个新的时间节点
  }
  async function removeTime(index) {
    times.value.splice(index, 1); // 移除指定位置的时间节点
  }

  const duan = ref([]);

  const defaultProps = {
    children: 'children',
    label: 'title',
    disabled(data, node) {
      return data.id === null && data.children === null;
    },
  };

  //自定义事件
  const emits = defineEmits(['save']);

  const pickerOptions = {
    selectableRange: [
      // 只保留 9:00 到 18:00 的时间选项
      '09:00 - 18:00',
      // 可选添加其他时间范围，比如:
      // '20:00:00 - 23:59:59',
      // '00:00:00 - 07:00:00',
    ],
  };

  const ptype = ref({});
  const dtype = ref({});
  const isIndeterminate = ref({});
  async function handleCheckAllChange(val, py) {
    py = py + '_index';
    ptype.value[py] = val
      ? dictStore()
          .dictData(py)
          .all.map((item) => item.dictValue)
      : [];
    console.log('1111', isIndeterminate.value[py]);
    isIndeterminate.value[py] = false;
  }

  async function handleCheckedCitiesChange(value, py) {
    py = py + '_index';
    const checkedCount = value.length;
    dtype.value[py] = checkedCount === dictStore().dictData(py).all.length;
    isIndeterminate.value[py] =
      checkedCount > 0 && checkedCount < dictStore().dictData(py).all.length;

    console.log(isIndeterminate.value[py]);
  }

  const pipelineId = ref();
</script>

<template>
  <el-form label-width="80px">
    <FormCard title="巡检计划基本信息">
      <el-row :gutter="16">
        <el-col :span="12">
          <div class="ping1">
            <el-row>
              <el-col :span="24">
                <el-form-item label="计划名称" required>
                  <el-input
                    minlength="3"
                    maxlength="10"
                    show-word-limit
                    v-model="newPlan.planName"
                    clearable
                    placeholder="请输入"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="负责人" required>
                  <el-select
                    clearable
                    v-model="newPlan.responsibleName"
                    class="m-2"
                    placeholder="选择负责人"
                  >
                    <el-option
                      v-for="item in users"
                      :key="item.name"
                      :label="item.nickName"
                      :value="item.name"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="开始时间" required>
                  <el-date-picker
                    v-model="newPlan.startTime"
                    type="date"
                    placeholder="开始时间"
                  />
                </el-form-item>
              </el-col>
              <!-- <el-col :span="24">
              <el-form-item label="巡检周期" required>
                <el-input clearable type="number" v-model="newPlan.cycle">
                  <template #append>
                    <el-select
                      v-model="newPlan.cycleUnit"
                      placeholder="天"
                      style="width: 60px"
                    >
                      <el-option
                        :key="item.dictValue"
                        :label="item.dictLabel"
                        :value="item.dictValue"
                        v-for="item in dictStore().dictData('time_unit').all"
                      />
                    </el-select>
                  </template>
                </el-input>
              </el-form-item>
            </el-col> -->
            </el-row>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="ping1">
            <el-form-item label="时间段" required>
              <el-button
                v-if="times.length === 0"
                @click="addTime"
                type="primary"
                icon="plus"
              ></el-button>
              <el-scrollbar style="width: 100%" max-height="110px">
                <div
                  v-for="(time, index) in times"
                  :key="index"
                  style="width: 100%"
                  class="time_wrap"
                >
                  <div class="timebox1">
                    <el-time-picker
                      class="time"
                      v-model="times[index]"
                      format="HH:mm"
                      style="width: 100%"
                      value-format="HH:mm"
                      placeholder="任务执行时间"
                      step="00:15"
                    />
                  </div>
                  <div class="timebox2">
                    <el-button
                      @click="removeTime(index)"
                      type="danger"
                      icon="delete"
                    ></el-button>
                    <el-button
                      v-if="index === times.length - 1"
                      @click="addTime"
                      type="primary"
                      icon="plus"
                    ></el-button>
                  </div>
                </div>
                <div></div>
              </el-scrollbar>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
    </FormCard>
    <FormCard title="计划巡检内容">
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
            /> -->
          </el-form-item>
        </el-col>
      </el-row>
    </FormCard>
  </el-form>
  <el-table height="250px" :data="dictStore().inspection_device_type().all">
    <el-table-column type="index" label="序号" align="center" width="50px" />
    <el-table-column label="" align="center" width="180px" />
    <el-table-column label="巡检对象" align="left">
      <template #default="scope">
        <el-checkbox
          v-model="dtype[scope.row.dictValue + '_index']"
          :indeterminate="isIndeterminate[scope.row.dictValue + '_index']"
          @change="(value) => handleCheckAllChange(value, scope.row.dictValue)"
        >
          {{ scope.row.dictLabel }}
        </el-checkbox>
      </template>
    </el-table-column>
    <el-table-column label="智能巡检指标内容(自定义)" align="left">
      <template #default="scope">
        <el-checkbox-group
          v-model="ptype[scope.row.dictValue + '_index']"
          @change="
            (value) => handleCheckedCitiesChange(value, scope.row.dictValue)
          "
        >
          <el-checkbox
            v-for="index in dictStore().dictData(scope.row.dictValue + '_index')
              .all"
            :label="index.dictValue"
          >
            {{ index.dictLabel }}
          </el-checkbox>
        </el-checkbox-group>
      </template>
    </el-table-column>
  </el-table>

  <Footer>
    <el-button size="large" @click="update" v-if="newPlan.planCode">
      修改
    </el-button>
    <el-button size="large" @click="add" v-else>新增</el-button>
  </Footer>
</template>

<style scoped lang="scss">
  .time_wrap {
    display: flex;
    width: 100%;
    & + & {
      margin-top: 0.5rem;
    }
    &:deep() .el-date-editor.el-input {
      width: var(--el-date-editor-width);
    }
  }

  .timebox1 {
    flex: 1; /* 自动分配剩余宽度 */
    width: 0; /* 必须设置为 0，以便 flex 属性起作用 */
  }

  .timebox2 {
    width: 100px;
  }

  .ping1 {
    height: 115px;
  }
</style>
