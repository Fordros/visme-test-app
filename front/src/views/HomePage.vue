<template>
  <div id="home">
    <router-link to="/users"><el-button type="primary" size="large" round>LOGIN</el-button></router-link>
  </div>
</template>

<script>

export default {

  components: {

  }
}
</script>

<style>
  #home{
    width: 100px;
    height: 100px;

    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;

    margin: auto;
  }
</style>
