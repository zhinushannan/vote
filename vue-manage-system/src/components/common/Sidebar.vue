<template>
  <div class="sidebar">
    <el-menu
        class="sidebar-el-menu"
        :default-active="onRoutes"
        :collapse="collapse"
        background-color="#324157"
        text-color="#bfcbd9"
        active-text-color="#20a0ff"
        unique-opened
        router
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu
                  v-if="subItem.subs"
                  :index="subItem.index"
                  :key="subItem.index"
              >
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item
                    v-for="(threeItem,i) in subItem.subs"
                    :key="i"
                    :index="threeItem.index"
                >{{ threeItem.title }}
                </el-menu-item>
              </el-submenu>
              <el-menu-item
                  v-else
                  :index="subItem.index"
                  :key="subItem.index"
              >{{ subItem.title }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from '../common/bus';

export default {
  data() {
    return {
      collapse: false,
      adminItems: [
        {
          icon: 'el-icon-lx-home',
          index: '/dashboard',
          title: '系统首页'
        },
        {
          icon: 'el-icon-lx-calendar',
          index: '13',
          title: '候选人管理',
          subs: [
            {
              index: '/candidate/save',
              title: '新建候选人'
            },
            {
              index: '/candidate/list',
              title: '查看候选人'
            }
          ]
        },
        {
          icon: 'el-icon-lx-calendar',
          index: '14',
          title: '投票管理',
          subs: [
            {
              index: '/vote/save',
              title: '新建投票'
            },
            {
              index: '/vote/list/publish',
              title: '查看投票'
            },
            {
              index: '/vote/list/finished',
              title: '查看结果'
            },
          ],
        }],
      userItems: [
        {
          icon: 'el-icon-lx-home',
          index: '/dashboard',
          title: '系统首页'
        },
        {
          icon: 'el-icon-lx-calendar',
          index: '14',
          title: '投票管理',
          subs: [
            {
              index: '/vote/mine',
              title: '我的投票'
            },
          ],

        }
      ],
      allItems: [
        {
          icon: 'el-icon-lx-home',
          index: '/dashboard',
          title: '系统首页'
        },
        {
          icon: 'el-icon-lx-calendar',
          index: '13',
          title: '候选人管理',
          subs: [
            {
              index: '/candidate/save',
              title: '新建候选人'
            },
            {
              index: '/candidate/list',
              title: '查看候选人'
            }
          ]
        },
        {
          icon: 'el-icon-lx-calendar',
          index: '14',
          title: '投票管理',
          subs: [
            {
              index: '/vote/save',
              title: '新建投票'
            },
            {
              index: '/vote/list/publish',
              title: '查看投票'
            },
            {
              index: '/vote/list/finished',
              title: '查看结果'
            },
            {
              index: '/vote/mine',
              title: '我的投票'
            },
          ],
        }],
      items: []

    };
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace('/', '');
    }
  },
  created() {
    const role = localStorage.getItem('role');
    if (role.indexOf("ADMIN") !== -1 && role.indexOf("USER") !== -1) {
      this.items = this.allItems;
    } else if (role.indexOf("ADMIN") !== -1) {
      this.items = this.adminItems;
    } else if (role.indexOf("USER") !== -1) {
      this.items = this.userItems;
    }

    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on('collapse', msg => {
      this.collapse = msg;
      bus.$emit('collapse-content', msg);
    });
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}

.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}

.sidebar > ul {
  height: 100%;
}
</style>
