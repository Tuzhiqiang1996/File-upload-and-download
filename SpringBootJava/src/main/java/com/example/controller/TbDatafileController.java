package com.example.controller;


import com.example.common.lang.Result;
import com.example.entity.TbDatafile;
import com.example.service.TbDatafileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tu
 * @since 2021-05-12
 */
@RestController
public class TbDatafileController {
    @Autowired
    TbDatafileService tbDatafileService;

    @PostMapping("/upfile")
    public Result upfile(@RequestBody List<TbDatafile> lists) {
        if (lists.size() == 0 || lists == null) {
            return Result.fail("插入数据为空！");
        }
        TbDatafile tbDatafile = new TbDatafile();
        List<TbDatafile> listfile = new ArrayList<>(lists);
        tbDatafileService.saveBatch(listfile);
        return Result.succ("导入成功！", tbDatafile);
    }
}
