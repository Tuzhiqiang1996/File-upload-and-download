package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Tu
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbDatafile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 序号
     */
    private String sn;

    /**
     * 名字
     */
    private String name;

    /**
     * 性别
     */
    private String age;

    /**
     * 地区
     */
    private String city;

    /**
     * 年级
     */
    private String grade;

    /**
     * 就读学校
     */
    private String school;

    /**
     * 招生老师
     */
    private String teacher;

    /**
     * 缴费时间
     */
    private String time;

    /**
     * 学费
     */
    private String tuitionfees;

    /**
     * 班型
     */
    private String classtype;

    /**
     * 学生类型
     */
    private String studenttype;

    /**
     * 转介绍或合作生
     */
    private String relationship;

    /**
     * 备注
     */
    private String note;

    /**
     * 备注
     */
    private String subnote;


}
