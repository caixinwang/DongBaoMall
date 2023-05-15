import axios from 'axios';
import store from '@/vuex/store';
import { Message } from 'iview';

const http = axios.create({
  // baseURL: process.env.NODE_ENV === 'development' ? '' : '/api',
  baseURL: '/api',
  // baseURL: '',
  timeout: 5000
});

export const post = (data) => {
  const info = JSON.parse(localStorage.getItem('info'));
  console.log(info);
  data.method = 'POST';
  if (info) {
    data.headers = { 'x-access-token': info.data.token ,
      'Authorization': info.data.jwtTokenHead + info.data.jwtToken};
  }
  return http(data);
};

export const get = (data) => {
  const info = JSON.parse(localStorage.getItem('info'));
  console.log(info);
  data.method = 'GET';
  if (info) {
    data.headers = { 'x-access-token': info.data.token ,
      'Authorization': info.data.jwtTokenHead + info.data.jwtToken};
  }
  return http(data);
};

// 响应拦截器
http.interceptors.request.use(
  config => {
    // do something before request is sent
    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      // config.headers['x-access-token'] = getToken();
    }
    return config;
  },
  error => {
    // do something with request error
    console.log(error);
    return Promise.reject(error);
  }
);

// response interceptor
http.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data;
    // if the custom code is not 20000, it is judged as an error.
    // {"code":{"code":-1,"msg":"库存不足,无法添加购车"},"data":null}
    if (res.code !== 200) {
      Message.error(res.msg || 'Error');
      return Promise.reject(new Error(res.msg || 'Error'));
    } else {
      return res;
    }
  },
  error => {
    // for debug
    console.log('err' + error);
    Message.error(error.msg || 'Error');
    return Promise.reject(error);
  }
);

export default http;
