import {get} from '@/utils/http';

// 获取优惠券列表
export const getCouponListByCategoryIds = (data) => {
  return get({
    url: `/umsAndSms/list/productCategoryIds`,
    params: {
      productCategoryIdsS: data
    }
  });
};
