import Vue from 'vue';
import App from './App.vue';
import router from './router';

import ElementUI from 'element-ui';
import locale from "element-ui/lib/locale/lang/en";
import 'element-ui/lib/theme-chalk/index.css';

import axios from "axios";

import * as math from "mathjs";

Vue.config.productionTip = false;

Vue.use(ElementUI, {locale});

Vue.prototype.$axios = axios;

Vue.prototype.$TP_UAV_URL_PREFIX = "http://localhost:8080/tp/uav/calculator";


new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
