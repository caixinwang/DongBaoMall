import {get} from '@/utils/http';

// 首页数据查询
export const getHomeData = (data) => {
  return get({
    url: `/home/index`
  });
};
