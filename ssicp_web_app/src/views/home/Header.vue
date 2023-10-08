<script setup>
  import { ref, onMounted, onUnmounted, reactive, watch, computed } from 'vue';
  import { useRouter } from 'vue-router';
  import PasswordChangeComponent from './PasswordChangeComponent.vue';

  import { useUserStore } from '@/stores/user.js';
  const userStore = useUserStore();
  import { useMenuStore } from '@/stores/menu.js';
  const menuStore = useMenuStore();

  const router = useRouter();

  function gotoMenu(menu) {
    if (!menu.children && menu.code) {
      router.push({ name: menu.code });
    }
  }

  function signIn() {
    router.replace({
      name: 'login',
      query: {
        redirect: router.currentRoute.value.fullPath,
      },
    });
  }
  function signOut() {
    userStore.logout();
    router.push({ name: 'index' });
  }

  const changedialog = ref(false);

  function change() {
    changedialog.value = !changedialog.value;
  }

  const date = ref(new Date());
  let timer = null;
  function changeDate() {
    timer = setInterval(() => {
      date.value = new Date();
    }, 1000);
  }
  onMounted(() => {
    changeDate();
  });
  onUnmounted(() => {
    clearInterval(timer);
  });

  const path = computed(() => {
    let matched = [...router.currentRoute.value.matched]
      .reverse()
      .find((route) => menuStore.menusMap.has(route.name));
    let temp = menuStore.menusMap.get(matched.name);
    let set = new Set();
    while (temp) {
      set.add(temp.code);
      temp = menuStore.menusMap.get(temp.parentCode);
    }
    return set;
  });
</script>

<template>
  <div class="header">
    <div class="logo">
      <img src="" alt="" />
    </div>
    <div class="menus">
      <div
        v-for="level1Menu in menuStore.headMenuTree"
        :key="level1Menu.code"
        class="menu drop_controller"
        @click="gotoMenu(level1Menu)"
        :class="{
          menu_active: path.has(level1Menu.code),
          cursor: !level1Menu.children,
        }"
      >
        <div class="menu_bg"></div>
        <div>
          <span class="name">{{ level1Menu.title }}</span>
          <span class="underline" v-if="level1Menu.children"></span>
        </div>
        <div class="drop" v-if="level1Menu.children">
          <div
            class="cmd square"
            v-for="level2Menu in level1Menu.children"
            :key="level2Menu.code"
            @click="gotoMenu(level2Menu)"
            :class="{
              cursor: !level2Menu.children,
              square_active: path.has(level2Menu.code),
            }"
          >
            <span class="txt">
              {{ level2Menu.title }}
            </span>
            <div class="cmd-child-wrap" v-if="level2Menu.children">
              <div
                class="cmd-child square"
                :class="{
                  cursor: !level3Menu.children,
                  square_active: path.has(level3Menu.code),
                }"
                v-for="level3Menu in level2Menu.children"
                :key="level3Menu.code"
                @click="gotoMenu(level3Menu)"
              >
                <span class="txt">{{ level3Menu.title }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="user">
      <div class="name drop_controller">
        <span>
          {{ userStore.user ? userStore.user.nickName : '未登录' }}
        </span>
        <div class="drop">
          <div class="cmd square cursor" @click="signOut" v-if="userStore.user">
            <span class="txt">注销</span>
          </div>
          <div class="cmd square cursor" @click="change" v-if="userStore.user">
            <span class="txt">更改密码</span>
          </div>
          <div class="cmd square cursor" @click="signIn" v-else>
            <span class="txt">登录</span>
          </div>
        </div>
      </div>
    </div>
    <div class="datetime">
      <span class="time">
        <span
          v-for="(item, $index) in $date.time(date).split(':')"
          :key="$index"
        >
          <span v-if="$index !== 0">:</span>
          <span class="letter" v-for="(letter, $index2) in item" :key="$index2">
            {{ letter }}
          </span>
        </span>
      </span>
      <span class="date">{{ $date.date(date) }}</span>
    </div>
    <el-dialog v-model="changedialog" title="更改密码" width="20%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
      <PasswordChangeComponent
        v-if="userStore.user"
        :userName="userStore.user.name"
        @save="change"
      />
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">
  .cursor {
    cursor: pointer;
  }
  .square {
    height: 3rem;
    // cursor: pointer;
    width: 10rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 1px solid rgba(255, 255, 255, 0.1);
    background-color: rgba(12, 22, 40, 0.9);
    box-shadow: inset 0 0 3px rgba(255, 255, 255, 0.2);
    & > .txt {
      color: #c4c4c4;
    }
    &_active {
      // background-color: gray;
      background: linear-gradient(
        to bottom,
        rgba(49, 81, 160, 0.9),
        rgba(29, 47, 89, 0.9)
      );
      box-shadow: none;
      border-top-color: #6187e4;
      & > .txt {
        color: #80e3f2;
      }
    }
    &:hover {
      @extend .square_active;
    }
  }

  .header {
    display: flex;
    height: 100%;
    color: #94abcb;
    font-family: 'YouSheBiaoTiHei';

    & > div {
      // border: 1px solid yellow;
    }

    .logo {
      width: 31rem;
    }

    .drop_controller {
      &:hover {
        & > .drop {
          display: block;
        }
      }

      position: relative;

      .drop {
        display: none;
        position: absolute;
        top: 100%;
        width: 100%;
        font-size: 1.2rem;
        z-index: 4;

        & > .cmd {
          position: relative;
          &:hover {
            & > .cmd-child-wrap {
              display: block;
            }
          }
          & > .cmd-child-wrap {
            display: none;
            position: absolute;
            left: 100%;
            top: -1px;
            & > .cmd-child {
              // border: 1px solid red;
            }
          }
        }
      }
    }

    .menus {
      flex: 1 0 0;
      display: flex;
      height: 100%;
      align-items: center;

      .menu {
        width: 10rem;
        height: 3rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 1.5rem;
        position: relative;

        &:not(:first-child) {
          margin-left: 0.5rem;
        }

        & > .menu_bg {
          position: absolute;
          top: 0px;
          right: 0px;
          left: 0px;
          bottom: 0px;
          border: 1px solid #204270;
          background: linear-gradient(
            to bottom,
            rgba(6, 29, 54, 0.5),
            rgba(32, 66, 112, 0.5)
          );
          transform: skew(-10deg, 0deg);
          transform-origin: bottom;
          z-index: -1;
        }

        & > div {
          .name {
            margin-left: 0.4rem;
          }

          .underline {
            display: block;
            margin-left: 0.4rem;
            border-top: 2px solid #617899;
            width: 2.5rem;
            align-self: flex-start;
          }
        }

        &_active {
          & > .menu_bg {
            background: linear-gradient(
              to bottom,
              rgba(28, 82, 164, 0.5),
              rgba(33, 121, 209, 0.5)
            );
          }

          & > div {
            .name {
              color: white;
            }

            & .underline {
              border-color: #10f8fd;
            }
          }
        }

        &:hover {
          @extend .menu_active;
          & > .drop {
            display: block;
          }
        }
      }
    }

    .user {
      width: 10rem;
      display: flex;
      justify-content: center;

      align-items: center;

      & > .name {
        font-size: 1.2rem;
        height: 3rem;

        & > span {
          line-height: 3rem;
          margin-left: 4rem;
        }

        width: 100%;
        // text-align: center;
      }
    }

    .datetime {
      width: 11rem;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: flex-end;

      & > span {
        margin-right: 2.5rem;
      }

      .time {
        font-size: 1.3rem;
        & > span > span.letter {
          width: 1rem;
          display: inline-block;
          text-align: right;
        }
      }

      .date {
        font-size: 0.8rem;
      }
    }
  }
</style>
@/stores/button
