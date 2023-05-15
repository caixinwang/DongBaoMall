import {post, get} from '@/utils/http';

// 添加收货地址
export const addAddress = (data) => {
  console.log(data)
  return post({
    url: '/ums/umsAddress/add',
    data: data
  });
};

// 获取收货地址
export const getAddressList = (data) => {
  console.log(data);
  return post({
    url: '/ums/umsAddress/list?username='+data
  });
};

// 删除地址
export const delAddress = (data) => {
  return post({
    url: '/ums/umsAddress/delete?id='+data
  });
};

// 修改地址
export const editAddress = (data) => {
  return post({
    url: '/ums/umsAddress/update',
    data:  data
  });
};

// 生成订单
export const addOrder = (data) => {
  return post({
    url: '/oms/omsOrder/createOrderNew',
    data: data
  });
};

// 获取订单
export const getOrder = (pageDto) => {
  console.log(pageDto);
  return get({
    url: '/oms/omsOrder/list?pageIndex=' + pageDto.pageIndex + "&pageSize=" + pageDto.pageSize
  });
};

// 获取订单
export const getOrderItems = (data) => {
  console.log('订单详情的订单编号' + data);
  return get({
    url: '/oms/omsOrder/detail?orderNumber=' + data.orderNumber
  });
};
// 退款
export const refund = (data) => {
  console.log('退款订单的订单编号');
  console.log(data);
  return post({
    url: '/oms/orderRefund/orderRefund',
    data: data
  });
};
// 促销活动
export const getPromotion = (data) => {
  return get({
    url: '/pms/productFullReduction/detail/'+data
  });
};
// 优惠券活动
export const getDiscount = (data) => {
  return get({
    url: '/umsAndSms/list/spuIdOrPcId?productCategoryId=' + data.productCategoryId + '&spuId=' + data.spuId
  });
};
// 领取优惠券
export const receiveReduction = (data) => {
  console.log("请求请求 ---------------" +data)
  return get({
    url: '/umsAndSms/addCoupon?couponId='+data
  })
};
// 我的优惠券
export const getMyDiscount = () => {
  return get({
    url: '/umsAndSms/list/allCouponsByUser'
  })
}

