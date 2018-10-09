<template>
  <div>
    <group title="测试" title-color="red">
      <cell title="测试一" is-link>
        11111111
      </cell>
      <cell  title="测试一" is-link is-loading>
        2222
      </cell>
    </group>
  </div>
</template>

<script>
  import {Group, Cell} from 'vux'

  export default {
    name: "VuxIndex",
    components: {Group, Cell}
  }
</script>

<style scoped>

</style>
