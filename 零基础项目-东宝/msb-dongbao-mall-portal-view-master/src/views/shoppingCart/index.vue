<template>
  <div class="page-shop-cart page-nav-bar">
    <nav-bar title="加入购物车"> </nav-bar>
    <Sreach class="hidden-xs hidden-sm"></Sreach>
    <GoodsListNav class="hidden-xs hidden-sm"></GoodsListNav>
    <!-- 添加成功提示 -->
    <div class="add-info-box-container">
      <div class="add-info-box">
        <div class="add-info-detail">
          <div class="add-info-title">
            <p><i class="fa fa-check-circle"></i> 商品已成功加入购物车！</p>
          </div>
          <div class="add-info-box-row">
            <div class="add-info-img">
              <img :src="newShoppingCart.image" alt="" />
            </div>
            <div class="add-info-intro">
              <p>{{ newShoppingCart.title }}...</p>
              <p class="add-info-intro-detail">
                数量：{{ newShoppingCart.amount }}
              </p>
            </div>
          </div>
        </div>
        <div class="car-btn-group">
          <div></div>
          <div class="car-btn-row">
            <router-link
              :to="{
                path: '/goodsDetail',
                query: {
                  goodsId: newShoppingCart.relProductId,
                  merchantId: 1,
                  relCategory3Id: newShoppingCart.categoryId
                }
              }"
            >
              <button class="btn-car btn-car-to-detail">查看商品详情</button>
            </router-link>
            <button class="btn-car btn-car-to-pay" @click="goToOrder()">
              去购物车结算 >
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 推荐商品 您可能还需要 -->
    <div class="other-user-buy-box">
      <div class="other-user-buy-title">
        <p>您可能还需要 ~</p>
      </div>
      <div
        class="other-user-buy-row"
        v-for="(items, index1) in recommend"
        :key="index1"
      >
        <div
          class="other-user-buy-item-box"
          v-for="(item, index2) in items"
          :key="index2"
        >
          <div class="other-user-buy-item-img">
            <router-link
              :to="{
                path: '/goodsDetail',
                query: {
                  goodsId: item.id,
                  merchantId: 1,
                  relCategory3Id: item.relCategory3Id
                }
              }"
            >
              <img :src="item.defaultPic" alt="" />
            </router-link>
          </div>
          <div class="other-buy-detail-box">
            <div class="other-buy-title">
              <router-link
                :to="{
                path: '/goodsDetail',
                query: {
                  goodsId: item.id,
                  merchantId: 1,
                  relCategory3Id: item.relCategory3Id
                }
              }"
              >
                <p>{{ item.productName }}</p>
              </router-link>
            </div>
            <div class="other-buy-price">
              <p>￥{{ item.price }}</p>
            </div>
            <div class="other-buy-btn-box">
              <router-link
                :to="{
                path: '/goodsDetail',
                query: {
                  goodsId: item.id,
                  merchantId: 1,
                  relCategory3Id: item.relCategory3Id
                }
              }"
              >
                <button class="other-buy-btn">
                  <Icon type="ios-cart"></Icon> 商品详情
                </button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Sreach from "@/components/Sreach";
import GoodsListNav from "@/components/nav/GoodsListNav";
import Footer from "@/components/footer/Footer";
import store from "@/vuex/store";
import { mapState, mapActions } from "vuex";
import navBar from "@/components/navBar";
export default {
  name: "ShoppingCart",

  data() {
    return {
      merchantId: 1
    };
  },

  created() {
    if (
      this.newShoppingCart === null ||
      this.newShoppingCart === undefined ||
      this.newShoppingCart.length === 0
    ) {
      return;
    }
    this.loadRecommend(this.newShoppingCart);
    this.loadShoppingCart();
  },
  computed: {
    ...mapState(["newShoppingCart", "recommend"])
  },
  methods: {
    ...mapActions(["loadRecommend", "loadShoppingCart"]),
    goToOrder() {
      const info = JSON.parse(localStorage.getItem("info"));
      if (info === null || info === undefined || info === "") {
        this.$Message.warning({
          content: "下单结算需要先登录,即将跳转登录页 ^-^",
          onClose: () => {
            this.$router.push("/login");
          }
        });
      } else {
        this.$router.push("/ShowShoppingCart");
      }
    },
    goProductDetail() {
      this.$Message.warning("功能开发中,敬请期待 ^-^");
    }
  },
  components: {
    navBar,
    Sreach,
    GoodsListNav,
    Footer
  },
  store
};
</script>

<style scoped>
/****************************加入购物车页面开始*****************************/
.add-info-box-container {
  width: 100%;
  background-color: #f5f5f5;
}
.add-info-box {
  width: 90%;
  margin: 0px auto;
  padding: 15px 0px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.add-info-detail {
  display: flex;
  flex-direction: column;
}
.add-info-title {
  font-size: 25px;
  color: #71b247;
}
.add-info-box-row {
  display: flex;
  flex-direction: row;
  margin-top: 15px;
}
.add-info-img {
  width: 56px;
  height: 56px;
  margin-right: 15px;
}
.add-info-img img {
  width: 100%;
}
.add-info-intro {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.add-info-intro-detail {
  font-size: 12px;
  color: #999999;
}
.car-btn-group {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
/*按钮*/
.btn-car {
  padding: 8px 10px;
  font-size: 16px;
  border-radius: 0px;
  border: none;
  margin-right: 15px;
}
.btn-car-to-detail {
  background-color: #fff;
  color: #e4393c;
  border: 1px solid #fff;
}
.btn-car-to-detail:hover {
  border: 1px solid #e4393c;
}
.btn-car-to-pay {
  background-color: #e4393c;
  color: #fff;
  border: 1px solid #e4393c;
}
.btn-car-to-pay:hover {
  background-color: #c91623;
  border: 1px solid #c91623;
}
/*其他用户购买*/
.other-user-buy-box {
  width: 90%;
  margin: 0px auto;
  display: flex;
  flex-direction: column;
}
.other-user-buy-title {
  margin-top: 25px;
  font-size: 14px;
  color: #666;
  font-weight: bold;
}
.other-user-buy-row {
  margin-top: 25px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.other-user-buy-item-box {
  display: flex;
  flex-direction: row;
}
.other-user-buy-item-img {
  width: 96px;
  height: 96px;
}
.other-user-buy-item-img img {
  width: 100%;
}
.other-buy-detail-box {
  width: 160px;
  margin-left: 15px;
  display: flex;
  flex-direction: column;
}
.other-buy-title {
  font-size: 12px;
}
.other-buy-title a {
  color: #2c2c2c;
  text-decoration: none;
}
.other-buy-price {
  font-size: 12px;
  font-weight: bold;
  color: #e4393c;
}
.other-buy-btn {
  padding: 3px 10px;
  color: #e4393c;
  font-size: 12px;
  border: 1px solid #e4393c;
  border-radius: 0px;
  background-color: #fff;
}
.other-buy-btn:hover {
  color: #c91623;
  border: 1px solid #c91623;
}
/****************************加入购物车页面结束*****************************/
</style>

<style lang="less" scoped>
@media (max-width: 992px) {
  .page-shop-cart {
    background: #fff;
  }
  .add-info-box {
    display: flex;
    flex-wrap: wrap;
    .add-info-detail {
      margin-bottom: 10px;
    }
  }
  .other-user-buy-row {
    flex-wrap: wrap;
    .other-user-buy-item-box {
      padding: 10px 0;
      display: flex;
      flex-shrink: 0;
      width: 100%;
      .other-buy-detail-box {
        flex-grow: 1;
      }
    }
  }
}
</style>
