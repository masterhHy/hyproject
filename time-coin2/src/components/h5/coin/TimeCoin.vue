<template>
  <div class="coin-all">
    <!-- <grid :show-lr-borders="false" :show-vertical-dividers="false" v-for="i in 6" :key="i">
       <grid-item  v-for="i in 4" :key="i" label="金币">
         <img slot="icon" src="@/components/h5/coin/assets/images/gold-coin.png" class="coin-img">
       </grid-item>
     </grid>-->

    <flexbox v-for="j in 6" :key="j">
      <flexbox-item v-for="i in 4" :key="i">
        <div class="flex-coin" @click="toTimeCoinEdit(j,i)"><img class="coins-img"
                                                                 src="@/components/h5/coin/assets/images/gold-coin.png">
        </div>
      </flexbox-item>
    </flexbox>

    <div v-transfer-dom>
      <confirm v-model="show"
               title="请选择金币类型"
               @on-cancel="onCancel"
               @on-confirm="onConfirm"
               @on-show="onShow"
               @on-hide="onHide"
               closeOnConfirm="false">
        <label class="coin-type blue"><input type="radio"  name="type" value="工作" />工作</label>
        <label class="coin-type green"> <input type="radio" name="type" value="学习"/>学习</label>
        <label class="coin-type red"><input type="radio" name="type" value="娱乐"/>娱乐</label>
      </confirm>
    </div>


  </div>
</template>

<script>
  import {
    Grid,
    GridItem,
    GroupTitle,
    Flexbox,
    FlexboxItem,
    XDialog,
    Confirm,
    TransferDomDirective as TransferDom
  } from 'vux'

  export default {
    directives: {
      TransferDom
    },
    name: "TimeCoin",
    data() {
      return {
        show: false,
        coinType: ''
      }
    },
    components: {
      Grid,
      GridItem,
      GroupTitle,
      Flexbox,
      FlexboxItem,
      XDialog,
      Confirm
    },
    methods: {
      toTimeCoinEdit(col, row) {
        this.show = true;
        /*this.$router.push('/TimeCoinEdit');*/
      },
      doShowToast() {
        this.$vux.toast.show({
          text: 'toast'
        })
      },
      onCancel() {
        console.log('on cancel')
      },
      onConfirm(msg) {
        console.log('on confirm')
        if (msg) {
          alert(msg)
        }
      },
      onHide() {
        console.log('on hide')
      },
      onShow() {
        console.log('on show')
      },
    }
  }
</script>

<style scoped>
  .coin-all {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    overflow: hidden;
    padding: 10px;
  }

  .coins-img {
    width: 60px;
  }
  .coin-type-font{
    letter-spacing: 12px;
  }


  .flex-coin {
    margin: 5px 0;
    position: relative;
    border: 1px solid #4E4B41;
    background-color: #615D4F;
    height: 90px;
  }
  .blue{
    color:blue;
  }
  .green{
    color:green;
  }
  .red{
    color: red;
  }

  .coins-img {
    width: 70px;
    position: absolute;
    left: 50%;
    margin-left: -35px;
    top: 50%;
    margin-top: -35px;
  }
  .coin-type{
    display: block;
    margin:10px;
    width: 150px;
    letter-spacing: 12px;
    padding-left: 60px;
  }
  .coin-type input{
    margin-right: 20px;
  }

</style>
