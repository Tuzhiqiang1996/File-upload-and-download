<!-- 上传文件解析 -->
<template>
  <div>
    <div class="">上传文件解析</div>
    <el-upload
      class="upload-demo"
      drag
      ref="upload"
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      :auto-upload="false"
      :file-list="fileList"
      :on-change="handleChange"
      :on-success="success"
      :http-request="uploadFile"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
    <el-button @click="btn">上传文件解析</el-button>
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
      fileList: [],
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    handleChange(file, fileList) {
      //   this.fileList = file.raw;
      this.fileList = fileList.slice(-1);

      this.$message({
        message: "文件已上传！",
        showClose: true,
        type: "success",
      });
      console.log(file);
      // if (file.raw) {
      //   let reader = new FileReader();
      //   reader.onload = function (e) {
      //     let contentHtml = e.target.result;
      //     console.log("ee", contentHtml);
      //     this.datadevlist = e.target.result;
      //   };
      //   reader.readAsText(file.raw, "gb2312");
      // }
    },
    success(response, file, fileList) {
      console.log("success", response, file, fileList);
    },
    uploadFile(params) {
      let reader = new FileReader();
      reader.readAsText(params.file, "gb2312");
      reader.onload = (e) => {
        this.ee = e.target.result;
        console.log(e.target.result);
        this.datadevlist = e.target.result;
        this.morearrylists();
      };
    },
    morearrylists() {
      let datalist = this.datadevlist;
      let list = datalist.replace(/([.\n\r]+)/g, "#*#");
      let arr = new Array();
      arr = list.split(",");
      if (arr.length == 1) {
        this.$message({
          message: "请上传多列数据文件！",
          showClose: true,
          type: "error",
        });

        return false;
      }
      let listsplit = list.split("#*#");
      console.log("list", listsplit);
      let listpush = [];
      for (let index = 0; index < listsplit.length; index++) {
        let listarry = listsplit[index].split(",");
        listpush.push(listarry);
      }
      this.morearrylist(listpush);
    },
    morearrylist(data) {
      let datalist = {};
      let datalist2 = [];
      for (let index = 0; index < data.length; index++) {
        for (let indexs = 0; indexs < data[index].length; indexs++) {
          datalist = {
            sn: data[index][0],
            name: data[index][1],
            age: data[index][2],
            city: data[index][3],
            grade: data[index][4],
            school: data[index][5],
            teacher: data[index][6],
            time: data[index][7],
            tuitionfees: data[index][8],
            classtype: data[index][9],
            studenttype: data[index][10],
            relationship: data[index][11],
            note: data[index][12],
            subnote: data[index][13],
          };
        }
        datalist2.push(datalist);
      }
      console.log("morearry", datalist2);

      this.$message({
        message: "多列数据准备就绪！",
        showClose: true,
        type: "success",
      });
      // this.refile(datalist2);
      var map = {},
        dest = [];
      for (var i = 0; i < datalist2.length; i++) {
        //datalist2 为需要合并的数组
        var ai = datalist2[i];
        if (!map[ai.name]) {
          // 如果 map 对象中不存在数组中的key---sn
          dest.push({
            name: ai.name, //姓名
            city: ai.city, //地区
            // age: ai.age,
            school: ai.school, //学校
            grade: ai.grade, //年级
            teacher: [ai.teacher], //老师
            studenttype: [ai.studenttype], //教学点
            relationship: [ai.relationship], //学生类型
            //春季班  时间 金额 “”  “” 班型   暑假班  秋季班 寒假班 集训
            // c1: [""],
            // c2: [""],
            // c3: [""],
            // c4: [""],
            // c5: [""],
            // c6: [""],

            //春季班  时间 金额 “”  “” 班型
            time: [ai.time], //时间
            tuitionfees: [ai.tuitionfees], //金额
            classtype: [ai.classtype] , //班型
            note: [ai.note], //备注1
            subnote: [ai.subnote], //备注2
          });
          map[ai.name] = ai.teacher;
        } else {
          for (var j = 0; j < dest.length; j++) {
            var dj = dest[j];
            if (dj.name === ai.name) {
              // if (dj.classtype == "春季班") {
              //   dj.c1.push(ai.time);
              //   dj.c1.push(ai.tuitionfees);
              //   dj.c1.push(ai.classtype);
              // } else if (dj.classtype == "暑假班") {
              //   dj.c2.push(ai.time);
              //   dj.c2.push(ai.tuitionfees);
              //   dj.c2.push(ai.classtype);
              // } else if (dj.classtype == "秋季班") {
              //   dj.c3.push(ai.time);
              //   dj.c3.push(ai.tuitionfees);
              //   dj.c3.push(ai.classtype);
              // } else if (dj.classtype == "寒假班") {
              //   dj.c4.push(ai.time);
              //   dj.c4.push(ai.tuitionfees);
              //   dj.c4.push(ai.classtype);
              // } else if (dj.classtype == "集训") {
              //   dj.c5.push(ai.time);
              //   dj.c5.push(ai.tuitionfees);
              //   dj.c5.push(ai.classtype);
              // }else if (dj.classtype == "课时班") {
              //   dj.c6.push(ai.time);
              //   dj.c6.push(ai.tuitionfees);
              //   dj.c6.push(ai.classtype);
              // }
              dj.time.push(ai.time);
              dj.tuitionfees.push(ai.tuitionfees);
              dj.classtype.push(ai.classtype);
              dj.teacher.push(ai.teacher);
              dj.studenttype.push(ai.studenttype);
              dj.relationship.push(ai.relationship);
              dj.note.push(ai.note);
              dj.subnote.push(ai.subnote);
              break;
            }
          }
        }
      }
      console.log("dest", dest);
      this.Arraylist(dest);
    },
    //上传
    refile(e) {
      this.$message({
        message: "数据插入准备，数量为" + e.length,
        showClose: true,
        type: "success",
      });
      let time = new Date();
      let timeold = time.getTime();
      let url = "http://localhost:8081/upfile";

      this.$axios
        .post(url, e, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.code == 200) {
            let timenew = new Date();
            let timenew1 = timenew.getTime();
            let timenew2 = timenew1 - timeold;

            this.$message({
              message: res.data.msg + "耗时：" + timenew2 + "ms",
              showClose: true,
              type: "success",
            });
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
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },
    //
    btn() {
      this.$refs.upload.submit();
    },
    /**
 * ,
          list[key].age,
          list[key].city,
          list[key].grade,
          list[key].school,
          list[key].teacher,
          list[key].time,
          list[key].tuitionfees,
          list[key].classtype,
          list[key].studenttype,
          list[key].relationship,
          list[key].note,
          list[key].subnote
 */
    //danlie
    Arraylist(list) {
      // let list = this.list;
      let str = "";
      for (const key in list) {
        // str += `${list[key] + "\t\n"}`;
        str += `${
          list[key].name +
          "," +
          list[key].city +
          "," +
          list[key].grade +
          "," +
          list[key].school +
          "," +
          list[key].teacher +
          "," +
          list[key].time +
          "," +
          list[key].tuitionfees +
          "," +
          list[key].classtype +
          "," +
          list[key].studenttype +
          "," +
          list[key].relationship +
          "," +
          list[key].note +
          "," +
          list[key].subnote +
          "\n"
        }`;
      }
      console.log("e", str);
      this.strdata = str;

      this.files(str);
    },
    //下载到本地
    files(str) {
      const uri =
        "data:text/csv;charset=utf-8,\ufeff" + encodeURIComponent(str);
      // 通过创建a标签实现
      const link = document.createElement("a");
      link.href = uri;
      // 对下载的文件命名
      // link.download = `${this.formInline.filename}${this.formInline.sufname}`;
      link.download = "文件.xlsx";
      link.click();
      this.$message.success("操作成功！");
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
</style>
