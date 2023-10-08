<script setup>
  import { dictStore } from '@/stores/dict';
  import { reactive, onMounted, ref, watch } from 'vue';

  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
  });
</script>

<template>
  <el-row class="all" :gutter="16">
    <el-col class="left" :span="8">
      <Container class="rightmenu" pagination :params="params" @search="search">
        <template #form>
          <el-form :inline="true" :model="params" class="form">
            <el-row>
              <el-col :span="12">
                <el-form-item label="类型">
                  <el-select
                    clearable
                    v-model="params.responsibleName"
                    placeholder="请选择(单选)"
                  >
                    <el-option label="全部" value="0" />
                    <el-option label="日报" value="1" />
                    <el-option label="月报" value="2" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item>
                  <el-button icon="Search" @click="search">查询</el-button>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <Title>
                  <font size="4">运行日报自动生成记录</font>
                </Title>
              </el-col>
            </el-row>
          </el-form>
        </template>
        <template #table>
          <el-table :data="outcomes">
            <el-table-column align="center" type="index" label="序号" />
            <el-table-column align="center" prop="planName" label="名称" />
            <el-table-column align="center" prop="planName" label="生成时间" />
            <el-table-column align="center" prop="planName" label="类型" />
            <el-table-column align="center" prop="planName" label="查看次数" />
          </el-table>
        </template>
      </Container>
    </el-col>
    <el-col :span="16">
      <Card>
        <FormCard title="报告详情">
          <table border="1" class="table">
            <tr>
              <td class="biaoti2">报告类型</td>
              <td>1</td>
              <td>生成日期</td>
              <td>1</td>
            </tr>
          </table>
        </FormCard>
        <FormCard title="一、产用汽量统计">
          <table border="1" class="table">
            <tr>
              <td class="biaoti2">产气量</td>
              <td>1</td>
              <td>用气量</td>
              <td>1</td>
              <td>损失量</td>
              <td>1</td>
            </tr>
            <tr>
              <td>小结</td>
              <td colspan="5">xxxxxxxx</td>
            </tr>
          </table>
        </FormCard>
        <FormCard title="二、报警信息统计">
          <table border="1" class="table">
            <tr>
              <td class="biaoti2">指标报警</td>
              <td>当日报警6/次，低报2/次 | 高报3/次 | 高高报1/次</td>
              <td>处理情况</td>
              <td>当日已处理4/项，未处理2/项，处理完成66%</td>
            </tr>
            <tr>
              <td colspan="4" height="200px">
                <el-tabel :data="outcomes">
                  <el-table-column
                    align="center"
                    width="55px"
                    type="index"
                    label="序号"
                  />
                  <el-table-column
                    align="center"
                    width="90px"
                    prop="planName"
                    label="报警对象"
                  />
                  <el-table-column
                    align="center"
                    width="90px"
                    prop="planName"
                    label="所属管线"
                  />
                  <el-table-column
                    align="center"
                    width="90px"
                    prop="planName"
                    label="名称/型号"
                  />
                  <el-table-column
                    align="center"
                    width="120px"
                    prop="planName"
                    label="监测指标"
                  />
                  <el-table-column
                    align="center"
                    width="90px"
                    prop="planName"
                    label="报警值"
                  />
                  <el-table-column
                    align="center"
                    width="90px"
                    prop="planName"
                    label="报警级别"
                  />
                  <el-table-column
                    align="center"
                    width="90px"
                    prop="planName"
                    label="处理状态"
                  />
                </el-tabel>
              </td>
            </tr>
          </table>
        </FormCard>
        <FormCard title="三、巡检异常信息统计">
          <table border="1" class="table">
            <tr>
              <td class="biaoti2">报告类型</td>
              <td>1</td>
              <td>生成日期</td>
              <td>1</td>
            </tr>
          </table>
        </FormCard>
      </Card>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
  .all {
    height: 100%;
  }
  .table {
    height: 100%;
    width: 100%;
  }
  .biaoti2 {
    background: #262727;
  }
</style>
