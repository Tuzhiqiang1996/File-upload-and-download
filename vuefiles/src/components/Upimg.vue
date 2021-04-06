<!--  -->
<template>
  <div class="">
    <div><p>图片上传服务器</p></div>
    <el-upload
      class="upload-demo"
      drag
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      accept=""
      :on-exceed="exceed"
      :limit="limit"
      :on-remove="remove"
      :on-change="hangchange"
      :auto-upload="false"
      :on-success="handsuccess"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
      <div class="el-upload__tip" slot="tip">
        1次只能上传1个图片文件，且不超过500kb
      </div>
    </el-upload>
    <div>
      <el-button @click="btn">上传服务器</el-button>
    </div>
    <div style="padding: 20px">
      <el-avatar
        shape="square"
        :size="150"
        v-show="imgfile"
        :src="imgfile"
      ></el-avatar>
    </div>
    <div style="padding: 20px">
      <el-avatar
        shape="square"
        :size="150" v-show="simg"
        :src="simg"
      ></el-avatar>
    </div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      limit: 1,
      filelist: [],
      imageUrl: "",
      imgfile: "",
      simg:""
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    exceed(files, fileList) {
      this.$message({
        message: `只能选择 ${this.limit} 个文件，当前共选择了 ${
          files.length + fileList.length
        } 个`,
        showClose: true,
        type: "warning",
      });
    },
    remove(file, fileList) {
      console.log(file, fileList);
    },
    hangchange(file, fileList) {
      console.log(file, fileList);
      this.imageUrl = file.raw;
      this.imgfile = URL.createObjectURL(file.raw);
    },
    handsuccess(file, fileList) {
      this.$message({
        message: "文件上传成功！",
        showClose: true,
        type: "success",
      });
    },

    btn() {
      this.upimg();
    },
    upimg(e) {
      let url = "http://localhost:8081/upfileimg";
      let formData = new FormData();

      formData.append("file", this.imageUrl);
      console.log("dd", this.imageUrl);

      this.$axios
        .post(url, formData, {
          headers: {
            "Content-Type": "application/json;charset=utf-8",
          },
        })
        .then((res) => {
          if (res.data.code == 200) {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
            // http://localhost:8081
            this.simg= `/`+res.data.data.replace(/\\/ig,"/")
            let d3=res.data.data
            console.log('s',d3);


          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
          console.log(res);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    dow2(e){

    }
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
.upload-demo {
  padding: 20px;
}
</style>