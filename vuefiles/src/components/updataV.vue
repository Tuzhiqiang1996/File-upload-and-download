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
      datadevlist: [],
      listpush: [],
      dest: [],
      strdata: [],
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

      this.listpush = listpush;
      this.morearrylist(listpush);
    },
    morearrylist() {
      let data = this.listpush;
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
            teacher: ai.teacher, //老师
            studenttype: ai.studenttype, //教学点
            relationship: ai.relationship, //学生类型
            //春季班  时间 金额 “”  “” 班型   暑假班  秋季班 寒假班 集训
            // c1: [],
            // c2: [],
            // c3: [],
            // c4: [],
            // c5: [],
            // c6: [],
            // c1: [ai.time, ai.tuitionfees, ai.classtype],
            // c2: [ai.time, ai.tuitionfees, ai.classtype],
            // c3: [ai.time, ai.tuitionfees, ai.classtype],
            // c4: [ai.time, ai.tuitionfees, ai.classtype],
            // c5: [ai.time, ai.tuitionfees, ai.classtype],
            // c6: [ai.time, ai.tuitionfees, ai.classtype],
            c7: [ai.time, ai.tuitionfees, ai.classtype],

            //春季班  时间 金额 “”  “” 班型
            time: [], //时间
            tuitionfees: [], //金额
            classtype: [], //班型
            note: [ai.note], //备注1
            subnote: [ai.subnote], //备注2
          });
          map[ai.name] = ai.name;
          // console.log("map", map);
        } else {
          for (var j = 0; j < dest.length; j++) {
            var dj = dest[j];

            if (dj.name === ai.name) {
              // if (dj.classtype.indexOf("春季班") != -1) {
              //   dj.c1.push(ai.time);
              //   dj.c1.push(ai.tuitionfees);
              //   dj.c1.push(ai.classtype);
              // } else if (dj.classtype.indexOf("暑假班") != -1) {
              //   dj.c2.push(ai.time);
              //   dj.c2.push(ai.tuitionfees);
              //   dj.c2.push(ai.classtype);
              // } else if (dj.classtype.indexOf("秋季班") != -1) {
              //   dj.c3.push(ai.time);
              //   dj.c3.push(ai.tuitionfees);
              //   dj.c3.push(ai.classtype);
              // } else if (dj.classtype.indexOf("寒假班") != -1) {
              //   dj.c4.push(ai.time);
              //   dj.c4.push(ai.tuitionfees);
              //   dj.c4.push(ai.classtype);
              // } else if (dj.classtype.indexOf("集训") != -1) {
              //   dj.c5.push(ai.time);
              //   dj.c5.push(ai.tuitionfees);
              //   dj.c5.push(ai.classtype);
              // } else if (dj.classtype.indexOf("课时班") != -1) {
              //   dj.c6.push(ai.time);
              //   dj.c6.push(ai.tuitionfees);
              //   dj.c6.push(ai.classtype);
              // }

              // console.log("bab", dj.classtype);
              dj.time.push(ai.time);
              dj.tuitionfees.push(ai.tuitionfees);
              dj.classtype.push(ai.classtype);
              dj.teacher += "、" + ai.teacher;
              dj.studenttype += "、" + ai.studenttype;
              dj.relationship += "、" + ai.relationship;
              dj.note.push(ai.note);
              dj.subnote.push(ai.subnote);
              // for (let index = 0; index < dj.classtype.length; index++) {
              //   }
              dj.c7.push(ai.time);
              dj.c7.push(ai.tuitionfees);
              dj.c7.push(ai.classtype);
              // dj.c6.push(dj.time[j]);
              // dj.c6.push(dj.tuitionfees[j]);
              // dj.c6.push(dj.classtype[j]);
              console.log("ee6", dj.c6);
              let result = dj.c7.findIndex((item) => {
                return item == "暑假班";
              });
              // console.log("dfasdf", result);
// buxinga
              // if (result != -1) {
              //   dj.c1.push(ai.time);
              //   dj.c1.push(ai.tuitionfees);
              //   dj.c1.push(ai.classtype);
              // }
              // if (dj.classtype.indexOf("春季班") != -1) {
              //   dj.c1.push(ai.time);
              //   dj.c1.push(ai.tuitionfees);
              //   dj.c1.push(ai.classtype);
              // } else if (dj.classtype.indexOf("暑假班") != -1) {
              //   dj.c2.push(ai.time);
              //   dj.c2.push(ai.tuitionfees);
              //   dj.c2.push(ai.classtype);
              // } else if (dj.classtype.indexOf("秋季班") != -1) {
              //   dj.c3.push(ai.time);
              //   dj.c3.push(ai.tuitionfees);
              //   dj.c3.push(ai.classtype);
              // } else if (dj.classtype.indexOf("寒假班") != -1) {
              //   dj.c4.push(ai.time);
              //   dj.c4.push(ai.tuitionfees);
              //   dj.c4.push(ai.classtype);
              // } else if (dj.classtype.indexOf("集训") != -1) {
              //   dj.c5.push(ai.time);
              //   dj.c5.push(ai.tuitionfees);
              //   dj.c5.push(ai.classtype);
              // } else if (dj.classtype.indexOf("课时班") != -1) {
              //   dj.c6.push(ai.time);
              //   dj.c6.push(ai.tuitionfees);
              //   dj.c6.push(ai.classtype);
              // }
              break;
            }
          }
        }
      }
      console.log("dest", dest);
      this.dest = dest;
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
    Arraylist() {
      let list = this.dest;
      let str = "";
      for (const key in list) {
        // str += `${list[key] + "\t\n"}`;
        str += `${
          list[key].name +
          "," +
          list[key].city +
          "," +
          list[key].school +
          "," +
          list[key].grade +
          "," +
          list[key].teacher +
          // "," +
          // list[key].c1 +
          // "," +
          // list[key].c2 +
          // "," +
          // list[key].c3 +
          // "," +
          // list[key].c4 +
          "," +
          list[key].studenttype +
          "," +
          list[key].relationship +
          "," +
          list[key].c6 +
          // "," +
          // list[key].time +
          // "," +
          // list[key].tuitionfees +
          // "," +
          // list[key].classtype +
          "," +
          list[key].note +
          "," +
          list[key].subnote +
          "\n"
        }`;
      }
      // console.log("e", str);
      this.strdata = str;

      this.files(str);
    },
    //下载到本地
    files(str) {
      const uri =
        "data:text/csv;charset=utf-8,\ufeff" + encodeURIComponent(this.strdata);
      // 通过创建a标签实现
      const link = document.createElement("a");
      link.href = uri;
      // 对下载的文件命名
      // link.download = `${this.formInline.filename}${this.formInline.sufname}`;
      link.download = "文件.csv";
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
