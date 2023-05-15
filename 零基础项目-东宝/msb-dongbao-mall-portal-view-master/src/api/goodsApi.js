import {get, post} from '@/utils/http';

// 店铺热销商品
export const getMerchantHotsales = (data) => {
  return get({
    url: `/pms/product/hotsales/merchant/` + data
  });
};

// 获取优惠价格信息
export const getPreferentialPrice = (data) => {
  return post({
    url: `/pms/skuStock/detailSKU`,
    data: data
  });
};
