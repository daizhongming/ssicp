<script setup>
  import { reactive, ref, onMounted, watch } from 'vue';
  import {
    pagePlansList,
    getPushRecords,
    readIncreasing,
  } from '@/api/surveillance/knowledgeBase.js';
  import { useRouter } from 'vue-router';
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    total: 0,
  });

  const types = reactive([
    { dictValue: 'alarm0', dictLabel: '低报' },
    { dictValue: 'alarm1', dictLabel: '高报' },
    { dictValue: 'alarm2', dictLabel: '高高报' },
    { dictValue: 'inspection', dictLabel: '异常' },
  ]);

  const appraises = reactive([
    { dictValue: '0', dictLabel: '有效' },
    { dictValue: '1', dictLabel: '无效' },
    { dictValue: '2', dictLabel: '待评价' },
  ]);

  const bases = ref([]);
  const router = useRouter();
  async function search() {
    let data = await pagePlansList(params);
    bases.value = data.data.data;
    params.total = data.data.total;
    if (router.currentRoute.value.query.id) {
      let found = bases.value.find(
        (base) => base.id === router.currentRoute.value.query.id
      );
      handleCurrentChange(found);
    }
  }

  const base = ref({});
  const pushRecords = ref([]);
  async function handleCurrentChange(row) {
    base.value = row;
    readIncreasing(row.id);
    let data = await getPushRecords(row.id);
    pushRecords.value = data.data;
  }

  onMounted(search);
</script>

<template>
  <el-row class="all" :gutter="10">
    <el-col class="left" :span="5">
      <Card>
        <el-table
          :data="bases"
          highlight-current-row
          @current-change="handleCurrentChange"
          border
        >
          <el-table-column
            type="index"
            label="序号"
            align="center"
            width="60"
          />
          <el-table-column label="标题" align="center" prop="title" />
        </el-table>
        <div>
          <el-pagination
            :page-size="20"
            :pager-count="11"
            layout="prev, pager, next"
            :total="1000"
          />
        </div>
      </Card>
    </el-col>
    <el-col class="left" :span="13">
      <Card>
        <el-card class="cards" shadow="always">
          <div class="card-content">
            <span class="title">标题:{{ base.title }}</span>
            <span class="read-count">查阅次数:{{ base.readCount }}</span>
          </div>
        </el-card>
        <FormCard title="问题现象">
          <span>{{ base.question }}</span>
        </FormCard>
        <FormCard title="解决方案">
          {{ base.scheme }}
        </FormCard>
        <FormCard title="应用配置设置">
          <p>1.装置范围</p>
          <p>管段|阀门</p>
          <p>2.指标范围</p>
          <p>温度|阀体前温度|阀体后温度</p>
        </FormCard>
      </Card>
    </el-col>
    <el-col class="left" :span="6">
      <Card>
        <el-card class="cards" shadow="always">
          <div class="card-content">
            <span class="">知识推送</span>
          </div>
        </el-card>
        <el-table :data="pushRecords">
          <el-table-column label="类型" align="center" prop="type">
            <template #default="scope">
              {{
                types.find((dict) => dict.dictValue == scope.row.type).dictLabel
              }}
            </template>
          </el-table-column>
          <el-table-column label="装置名称" align="center" prop="deviceName" />
          <el-table-column label="触发指标" align="center" prop="indexValue" />
          <el-table-column label="评价" align="center" prop="appraise">
            <template #default="scope">
              {{
                appraises.find((dict) => dict.dictValue == scope.row.appraise)
                  .dictLabel
              }}
            </template>
          </el-table-column>
        </el-table>
      </Card>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
  .all {
    height: 100%;
  }
  .cards {
    height: 4.6%;
    background-color: #12266e;
  }
  .title {
    text-align: center;
    flex: 1; /* 让标题占据剩余的空间 */
  }

  .read-count {
    margin-left: 10px; /* 适当调整距离 */
  }
  .card-content {
    line-height: 0px;
    display: flex;
    height: 100%; /* 设置高度为100%以撑开el-card */
    font-size: 15px;
  }
</style>
