import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/vote/page/:id',
            component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/vote/page.vue'),
            meta: {title: '投票'}
        },
        {
            path: '/404',
            component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
            meta: {title: '404'}
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: {title: '自述文件'},
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: {title: '系统首页'}
                },
                {
                    path: '/candidate/save',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/candidate/save.vue'),
                    meta: {title: '新建候选人'}
                },
                {
                    path: '/candidate/list',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/candidate/list.vue'),
                    meta: {title: '查看候选人'}
                },
                {
                    path: '/vote/save',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/vote/save.vue'),
                    meta: {title: '新建投票'}
                },
                {
                    path: '/vote/list/publish',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/vote/publish.vue'),
                    meta: {title: '查看投票'}
                },
                {
                    path: '/vote/list/finished',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/vote/finished.vue'),
                    meta: {title: '查看结果'}
                },
                {
                    path: '/vote/mine',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/vote/mine.vue'),
                    meta: {title: '我的投票'}
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: () => import(/* webpackChunkName: "permission" */ '../components/page/Permission.vue'),
                    meta: {title: '权限测试', permission: true}
                },

                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: {title: '403'}
                },
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: {title: '登录'}
        },
        {
            path: '/register',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Register.vue'),
            meta: {title: '注册'}
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
