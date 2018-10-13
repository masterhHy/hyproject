<template>
  <div id="app">
    <router-view></router-view>
    <tab-bar></tab-bar>
  </div>
</template>

<script>
  import TabBar from '@/components/h5/public/TabBar'

  export default {
    name: 'app',
    components: {TabBar}
  }
</script>

<style lang="less">
  @import '~vux/src/styles/reset.less';

  #app {
    background-color: #363636;
    height: 100%;
  }
</style>
