package com.example.controller;

import com.example.common.lang.Result;
import lombok.Data;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/4/2-15:58
 */
@Data
@RestController
public class ActionsfileController {
//    @Autowired
//    ActionfileService actionfileService;

    /**
     * [org.springframework.web.multipart.MultipartFile]
     *
     * @return com.example.common.lang.Result
     * File path = new File(ResourceUtils.getURL("classpath:").getPath());
     * 很长的
     * File path = new File(ResourceUtils.getURL("/").getPath());
     * 当前文件根目录
     * @author Tu
     * @date 2021/4/2 18:08
     * @message 图片上传
     * http://localhost:8081/actonimg/2021/04/06/1617700045160.jpg
     * 文件上传后回显
     * 从文件第二级目录开始访问 用此链接回显 下载等
     */
    @PostMapping("/upfileimg")
    public Result getfiles(@RequestParam(value = "file", required = false) MultipartFile file) {

     return this.file(file,1);
    }
    @PostMapping("/upfiles")
    public Result upfiles(@RequestParam(value = "file", required = false) MultipartFile file){

        return this.file(file,2);
    }
    public Result file(MultipartFile file,Integer num){
        // 文件名
        String fileName = file.getOriginalFilename();
        // 在file文件夹中创建名为fileName的文件
        assert fileName != null;
        int split = fileName.lastIndexOf(".");
        // 文件后缀，用于判断上传的文件是否是合法的
        String suffix = fileName.substring(split + 1, fileName.length());
        //判断文我这边是图片，所以只设置三种合法格式
        //shiann时间
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        String endtime = ft.format(dNow);

        String url = "";
        String urls = "";
        if (!file.isEmpty()) {
            try {
                File path = new File(ResourceUtils.getURL("/").getPath());
                String msg = "upload/actonimg/";
                if(num==2){
                 msg = "upload/actonfile/";
                }
                    File  upload = new File(path.getAbsolutePath(), msg + endtime);

                if (!upload.exists()) {
                    upload.mkdirs();
                }
                String newName = System.currentTimeMillis() + "." + suffix;
                File savedFile = new File(upload + "/" + newName);
                file.transferTo(savedFile);
                //将路径转换为可访问
                url = savedFile.getAbsolutePath().replace("D:\\upload", "");

                urls=  url.replace("D:\\upload", "");

                System.out.println("图片上传完毕，存储地址为：" + urls);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Result.succ("上传成功！", url);
        }
        return Result.fail("上传错误，请重试！");
    }
}
