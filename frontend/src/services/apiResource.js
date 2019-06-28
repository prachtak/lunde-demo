import Vue from 'vue';
import axios from 'axios';

import {API_BASE_URL} from '@/config/api';

const $api = axios.create({
  baseURL: API_BASE_URL,
});

const errorHandlerFactory = doThrow => err => {
  console.error(err);
  Vue.notify({
    title: `Server error (${err.response.status})`,
    text: `${err.response.data.message}`,
    type: 'danger',
    data: {
      icon: 'error_outline',
    },
  });
  if (doThrow) {
    throw err;
  }
};

const handleError = errorHandlerFactory();
const handleErrorAndThrow = errorHandlerFactory(true);

const apiProxy = {
  get(url, config) {
    return $api.get(url, config).then(r => r.data);
  },
  post(url, payload, config) {
    return $api.post(url, payload, config).then(r => r.data);
  },
  put(url, payload, config) {
    return $api.put(url, payload, config).then(r => r.data);
  },
  delete(url, config) {
    return $api.delete(url, config).then(r => r.data);
  },
};

export const formResource = {
  fetchTypes: () => {
    return apiProxy.get('/forms/contact-form-type').catch(handleError)
  },
};

export const contactUs = payload => {
  return apiProxy.post('/forms/contact-us', payload).catch(handleError)
};


