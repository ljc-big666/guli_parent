package com.liuchen.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: 一级分类
 * @Description:
 * @date: 2022/10/24 21:01
 */
@Data
public class OneSubject {

    private String id;
    private String title;

    // 一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

}
