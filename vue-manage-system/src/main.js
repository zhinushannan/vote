import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import VueI18n from 'vue-i18n';
import {messages} from './components/common/i18n';
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import './assets/css/theme-green/index.css'; // 浅绿色主题
import './assets/css/icon.css';
import './components/common/directives';
import 'babel-polyfill';
import Axios from "axios";
import VueClipboard from 'vue-clipboard2'

Vue.config.productionTip = false;
Vue.use(VueI18n);
Vue.use(ElementUI, {
    size: 'small'
});

Vue.use(VueClipboard)

const i18n = new VueI18n({
    locale: 'zh',
    messages
});

Vue.prototype.$axios = Axios
Axios.defaults.baseURL = 'http://127.0.0.1:8001'
Axios.defaults.withCredentials = true
Axios.interceptors.request.use(
    config => {
        let authorization = localStorage.getItem('authorization')
        if (authorization) {
            config.headers.common['authorization'] = authorization
        } else {
            let path = config.url
            if (path !== "common/login" && path !== "common/logout" && path !== "common/register/do") {
                ElementUI.Message.error("请先登录！")
                router.push("/login")
            }
        }
        return config
    }
)

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    let excludePath = ["/dashboard"]
    let userPath = ["/vote/page", "/vote/mine"]
    let adminPath = ["/candidate/save", "/candidate/list", "/vote/save", "/vote/list/publish", "/vote/list/finished"]

    document.title = `${to.meta.title} | vue-manage-system`;

    const role = localStorage.getItem('role');
    if (!role && to.path !== '/login' && to.path !== '/register') {
        next('/login');
    } else if (excludePath.indexOf(to.path) !== -1 && role) {
        next()
    } else if (userPath.indexOf(to.path) !== -1 && role.indexOf("USER") !== -1) {
        next()
    } else if (adminPath.indexOf(to.path) !== -1 && role.indexOf("ADMIN") !== -1) {
        next()
    } else {
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {
            next();
        }
    }
});




new Vue({
    router,
    i18n,
    render: h => h(App)
}).$mount('#app');
