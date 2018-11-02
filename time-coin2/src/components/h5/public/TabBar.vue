<template>
  <div>
    <tabbar>
      <tabbar-item selected link="/HomePage">
        <i slot="icon" class="fa fa-camera-retro  fa-2x" ></i>
        <i slot="icon-active" class="fa fa-camera-retro fa-2x"></i>
        <span slot="label">时间金币</span>
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

</style>
