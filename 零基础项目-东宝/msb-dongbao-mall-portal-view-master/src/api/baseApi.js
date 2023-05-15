import {post, get} from '@/utils/http';

export const login = (data) => {
  return post({
    url: '/admin/login',
    method: 'POST',
    data: data
  });
};

// 退出
export const logout = (data) => {
  return post({
    url: '/admin/logout',
    method: 'POST',
    data: data
  });
};
// 注册
export const signUp = (user) => {
  return post({
    url: '/admin/register',
    method: 'POST',
    data: user
  });
};

// 判断用户手机是否注册过
export const isExist = (phone) => {
  return post({
    url: '/admin/checkPhone',
    method: 'POST',
    data: {
      phone: phone
    }
  });
};
// 获得验证码图片
export const getPicBase64 = () => {
  return get({
    url: '/admin/getPic?t=' + Math.random()
  });
};

// 查找商品
export const goodsList = (data, pageNum, pageSize) => {
  let param = '';
  var page = data.pageNum || 0;
  var num = data.pageSize || 30;
  if(data.pageNum) page = data.pageNum
  // if(data.key) param += `key=${data.key}&`;
  // if (data.value) param += `value=${data.value}&`;
  param += `page=${page}&size=${num}`;
  console.log(param);
  return post({
    url: `/pms/product/findByESCondition?${param}`,
    method: 'POST',
    data: data
  });
};

export const searchProduct = (data, pageNum, pageSize) =>{
  let param = '';
  var page = pageNum  || 0;
  var num = pageSize || 16;
  if(data.pageNum) page = data.pageNum
  if(data.key) param += `key=${data.key}&`;
  if (data.value) param += `value=${data.value}&`;
  param += `page=${page}&pageSize=${num}`;
  console.log(param);
  return post({
    url: `/pms/product/findByCondition?${param}`,
    method: 'POST',
    data: {
      productName: 'Fred',
      relCategory1Id: 1
    }
  })
}

// 根据商品ID 查询商品
export const getOneGoods = (id) => {
  return get({
    url: `/pms/product/detail`,
    method: 'GET',
    params: {
      id: id
    }
  });
};

// 获取规格树
export const categoryList = () => {
  return post({
    url: '/pms/category/list',
    method: 'POST'
  });
};

// 根据条件查询SKU商品
export const getSkuGoods = (data) => {
  return post({
    url: `/pms/skuStock/detail`,
    method: 'POST',
    data: data
  });
};

// 查找商品
export const getSearchCondition = (data) => {
  return post({
    url: `/pms/product/findSearchCondition/`,
    method: 'POST',
    data: data
  });
};
