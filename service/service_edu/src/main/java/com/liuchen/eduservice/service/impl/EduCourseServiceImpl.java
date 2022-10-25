package com.liuchen.eduservice.service.impl;

import com.liuchen.eduservice.entity.EduCourse;
import com.liuchen.eduservice.entity.EduCourseDescription;
import com.liuchen.eduservice.entity.vo.CourseInfoVo;
import com.liuchen.eduservice.mapper.EduCourseMapper;
import com.liuchen.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuchen.servicebase.exceptionhandler.GuLiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liuchen
 * @since 2022-10-25
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    // 课程描述注入
    @Autowired
    EduCourseDescriptionServiceImpl courseDescriptionService;

    // 添加课程基本信息的方法
    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 1、向课程表添加课程基本信息
        // CourseInfoVo对象转换成eduCourse();
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0){
            throw new GuLiException(20001,"添加课程信息失败");
        }

        // 2、向课程简介表添加课程简介
        // edu_course_description
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        // 设置描述id就是课程id
        eduCourseDescription.setId(eduCourse.getId());
        courseDescriptionService.save(eduCourseDescription);
    }
}
