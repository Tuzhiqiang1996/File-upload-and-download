<!--  -->
<template>
  <div class="content">
    <el-container>
      <el-aside width="200px">
        <el-menu
          :default-active="num"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
             @select="select"
        >
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">文件上传预览</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">文件上传</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-document"></i>
            <span slot="title">图片上传</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">文件上传预览</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <!-- <el-header> </el-header> -->
        <el-main>
          <div>
            <PagesBox :num="num" />
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import PagesBox from "./pages";
export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: { PagesBox },
  props: [],
  data() {
    //这里存放数据
    return {
      num: "1",
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    select(key, keyPath) {
      this.num = key;
    },
  },
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
.content {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}
.el-container{

  height: 100%;
}
.el-aside {
  height: 100%;
  background: #545c64;
}
.el-menu {
  border: 0;
}
</style>