package com.liuchen.eduservice.service;

import com.liuchen.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuchen.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liuchen
 * @since 2022-10-25
 */
public interface EduCourseService extends IService<EduCourse> {

    // 添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
