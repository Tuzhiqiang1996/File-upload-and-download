<!--  -->
<template>
  <div class="box">
    <div v-if="num == 1">
      <HelloWorld />
    </div>
    <div v-if="num == 2"><Upfile /></div>
    <div v-if="num == 3"><Upimg /></div>
    <div v-if="num == 4"><UpfileEx /></div>
    <div v-if="num == 5"><UpdataV /></div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import HelloWorld from "../components/HelloWorld";
import Upfile from "../components/Upfile";
import Upimg from "../components/Upimg";
import UpfileEx from "../components/UpfileEx";
import UpdataV from "../components/updataV";
export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: { HelloWorld, Upfile, Upimg, UpfileEx, UpdataV },
  props: ["num"],
  data() {
    //这里存放数据
    return {};
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {},
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
.box {
  height: 100%;
  display: flex;
  justify-content: center;
  text-align: center;
  div {
    width: 100%;
  }
}
</style>
