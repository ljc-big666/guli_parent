package com.liuchen.eduservice.service;

import com.liuchen.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuchen.eduservice.entity.vo.CourseInfoVo;
import com.liuchen.eduservice.entity.vo.CoursePublishVo;

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

    // 根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    // 修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    // 根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);
}
