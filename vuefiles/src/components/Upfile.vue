<!--  -->
<template>
  <div class="">
    <div><p>文件上传服务器</p></div>
    <el-upload
      class="upload-demo"
      drag
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      accept=".xlsx"
      :on-exceed="exceed"
      :limit="limit"
      :on-remove="remove"
      :on-change="hangchange"
      :on-success="hangsuccess"
      :auto-upload="false"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
      <div class="el-upload__tip" slot="tip">
        1次只能上传1个xls文件，且不超过500kb
      </div>
    </el-upload>
    <div>
      <el-button @click="btn">上传服务器</el-button>
    </div>
    <div>
      <el-button v-show="sfileUrl" @click="down">下载</el-button>
      <a
        :href="sfileUrl"
        style="display: none"
        ref="downNode"
        target="_blank"
      ></a>
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
      fileUrl: "",
      sfileUrl: "",
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
      this.filelist = file;
      this.fileUrl = file.raw;
    },
    hangsuccess(file, fileList) {
      console.log(file, fileList);
      this.$message({
        message: "文件上传成功！",
        showClose: true,
        type: "success",
      });
    },
    btn() {
      console.log(this.filelist);

      this.upfile();
    },
    upfile(e) {
      let url = "http://localhost:8081/upfiles";
      let formData = new FormData();

      formData.append("file", this.fileUrl);
      console.log("dd", this.fileUrl);
      this.$axios
        .post(url, formData)
        .then((res) => {
          if (res.data.code == 200) {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
            this.sfileUrl ="/"+ res.data.data;
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    UploadUrl: function () {
      // 因为action参数是必填项，我们使用二次确认进行文件上传时，直接填上传文件的url会因为没有参数导致api报404，所以这里将action设置为一个返回为空的方法就行，避免抛错
      return "";
    },
    down() {
      console.log(this.sfileUrl);

      // 下载本地文件
      // window.location.href = this.sfileUrl
      const alink = document.createElement("a");
      const blob = new Blob([this.sfileUrl], { type: "application/xml" });
      // alink.href = URL.createObjectURL(blob);
      alink.href =`http://localhost:8081` +this.sfileUrl
      alink.setAttribute("download", "模板");
      document.body.appendChild(alink);
      alink.click();
      document.body.removeChild(alink);
      window.URL.revokeObjectURL(alink.href);
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
.upload-demo {
  padding: 20px;
}
</style>