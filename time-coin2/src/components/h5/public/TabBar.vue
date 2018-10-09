<template>
  <div>
    <tabbar>
      <tabbar-item selected link="/component/demo">
        <img slot="icon" src="../../../assets/logo.png">
        <span slot="label">时间金币</span>
      </tabbar-item>
      <tabbar-item show-dot>
        <img slot="icon" src="../../../assets/logo.png">
        <span slot="label">愿望清单</span>
      </tabbar-item>
      <tabbar-item badge="2">
        <img slot="icon" src="../../../assets/logo.png">
        <span slot="label">我的</span>
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
