import 'bootstrap-css-only/css/bootstrap.min.css';

import Vue from 'vue';
import VueNotification from 'vue-notification';

import App from './App.vue';
import Loader from './components/ui/Loader';
import Button from './components/ui/Button';
import router from './router';
import store from './store/index';

import veeValidateUse from './plugins/veeValidate.js';
import './registerServiceWorker';

Vue.config.productionTip = false;

Vue.use(VueNotification);
veeValidateUse(Vue);

Vue.component('loader', Loader);
Vue.component('btn', Button);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
