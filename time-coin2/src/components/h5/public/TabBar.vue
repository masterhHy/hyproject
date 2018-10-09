<template>
  <div>
    <tabbar>
      <tabbar-item selected link="/component/demo">
        <i class="fa fa-camera-retro  fa-2x" slot="icon"></i>
        <!--<span class="fa fa-plus inline-block" slot="icon"></span>-->
        <!-- <img slot="icon" src="../../../assets/images/icon.png">-->
        <i slot="icon-active" class="fa fa-camera-retro fa-2x"></i>
        <span slot="label">1</span>
      </tabbar-item>
      <tabbar-item>
        <img slot="icon" src="../../../assets/images/week.png">
        <img slot="icon-active" src="../../../assets/logo.png">
        <span slot="label">2</span>
      </tabbar-item>
      <tabbar-item>
        <img slot="icon" src="../../../assets/images/icons8-user-24.png">
        <img slot="icon-active" src="../../../assets/logo.png">
        <span slot="label">3</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script>
  import {Tabbar, TabbarItem} from 'vux'

  export default {
    name: "TabBar",
    components: {
      Tabbar,
      TabbarItem
    }
  }
</script>
<style scoped>

  .blue {
    color: red;
  }
</style>
