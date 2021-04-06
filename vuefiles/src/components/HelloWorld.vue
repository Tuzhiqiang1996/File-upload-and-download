    <!--  excel表格上传  -->
<template>
  <div>
    <div><p>文件上传预览</p></div>
    <el-upload
      class="upload-demo"
      drag
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      accept=".xlsx"
      :on-exceed="exceed"
      :limit="1"
      :on-remove="remove"
      :http-request="uploadFile"
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
    <!--  上传的excel表格预览  -->
    <div class="preview-excel">
      <el-table
        v-show="listTable.length != 0"
        class="listTable_ele"
        :data="listTable"
        stripe
        height="250"
        style="width: 100%"
      >
        <el-table-column
          prop="sno"
          label="学号"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="age"
          label="年龄"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="city"
          label="城市"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="sex"
          label="性别"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
          width="200"
          align="center"
        ></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import XLSX from "xlsx"; //引入xlsx
export default {
  data() {
    return {
      listTable: [],
    };
  },
  methods: {
    //解析excel
    async uploadFile(params) {
      const _file = params.file;
      const fileReader = new FileReader();
      fileReader.onload = (ev) => {
        try {
          const data = ev.target.result;
          const workbook = XLSX.read(data, {
            type: "binary",
          });
          for (let sheet in workbook.Sheets) {
            //循环读取每个文件
            const sheetArray = XLSX.utils.sheet_to_json(workbook.Sheets[sheet]);
            //若当前sheet没有数据，则continue
            if (sheetArray.length == 0) {
              continue;
            }
            console.log("读取文件");
            console.log(sheetArray);
            for (let item in sheetArray) {
              let rowTable = {};
              //这里的rowTable的属性名注意要与上面表格的prop一致
              //sheetArray的属性名与上传的表格的列名一致
              rowTable.name = sheetArray[item].姓名;
              rowTable.age = sheetArray[item].年龄;
              rowTable.city = sheetArray[item].地址;
              rowTable.sno = sheetArray[item].学号;
              rowTable.sex = sheetArray[item].性别;
              rowTable.email = sheetArray[item].邮箱;
              rowTable.phone = sheetArray[item].电话;
              this.listTable.push(rowTable);
            }
            console.log(this.listTable);
          }
        } catch (e) {
            this.$message({
              message: "文件类型不正确！",
              showClose: true,
              type: "warning",
            });
        }
      };
      fileReader.readAsBinaryString(_file);
    },
    //上传1个以上文件时弹窗提示错误
    exceed: function () {
        this.$message({
              message: "最多只能上传1个xls文件",
              showClose: true,
              type: "error",
            });
    },
    //删除文件
    remove() {
      this.listTable = [];
    },
  },
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
.upload-demo {
  padding: 20px;
}
</style>