<template>
  <div class="page">
    <div class="content">
      <div class="img js_timeCoin" :style="backImg" @click="toTimeCoin"></div>
      <p class="title">人的回忆是由过去构成的</p>
    </div>


  </div>
</template>

<script>
  export default {
    name: "HomePage",
    data() {
      return {
        backImg: {
          backgroundImage: "url(" + require("../../../assets/images/jinbi-index2.png") + ") ",
          backgroundSize: "219px auto",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "-6px -2px"
        }
      }
    },
    methods:{
      toTimeCoin() {
        console.log(this.$router)
        this.$router.push('/TimeCoin');
       /* this.router.push('/TimeCoin');*/
      }
    }

  }
</script>

<style scoped>
  @keyframes shine {
    0% {
      background-color: transparent;
    }
    50% {
      background-color: #F4E0AD;
      box-shadow: 0 0 30px #FAEAC0;
    }
    100% {
      background-color: transparent;
    }
  }

  .page {
    display: flex;
    justify-content: center;
    align-content: center;
    width: 100%;
    height: 100%;
  }

  .img {
    width: 200px;
    height: 200px;
    margin-top: 80%;
    border-radius: 50%;
    animation: shine 1s ease-in-out 0s infinite;
  }

  .title {
    font-family: "楷体";
    text-align: center;
    line-height: 68px;
    height: 68px;
    color: lightgray;
    font-size: 16px;
  }

</style>
