<script setup>
import { ref, onMounted, watch } from "vue";
const props = defineProps(["pagination", "params"]);
const emits = defineEmits(["search"]);

function handleSizeChange(size) {
  console.log(size);
  props.params.size = size;
  emits("search");
}

function handleCurrentChange(page) {
  props.params.page = page;
  emits("search");
}
</script>

<template>
  <div class="flex-container">
    <Card>
      <div class="flex-container">
        <div class="form">
          <slot name="form"> </slot>
        </div>
        <div class="commands">
          <div class="commands-left">
            <slot name="commands"></slot>
          </div>
          <div class="commands-right">
            <slot name="commands_right"></slot>
          </div>
        </div>
        <div class="table">
          <slot name="table"></slot>
        </div>
        <div
          class="pagination"
          v-if="pagination !== undefined && params.total >= 0"
        >
          <el-pagination
            :current-page="params.page"
            :page-size="params.size"
            :page-sizes="[5, 10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="params.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>
    </Card>
  </div>
</template>

<style scoped lang="scss">
.form {
}
.commands {
  margin-bottom: 0.5rem;
  display: flex;
  &-left {
    flex: 1 0 0;
    width: 0;
  }
  &-right {
  }
}
.table {
  flex: 1 0 0;
  height: 0;
}
.pagination {
  display: flex;
  margin-top: 0.5rem;
  justify-content: end;
}
</style>
