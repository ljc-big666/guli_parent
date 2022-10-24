package com.liuchen.eduservice.controller;


import com.liuchen.commonutils.R;
import com.liuchen.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author liuchen
 * @since 2022-10-24
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    // 添加课程分类
    // 获取上传过来文件，把文件内容读出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        // 上传过来excel文件
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }
}

