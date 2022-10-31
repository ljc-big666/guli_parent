package com.liuchen.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuchen.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuchen.eduservice.entity.EduCourse;
import com.liuchen.eduservice.entity.vo.CourseInfoVo;
import com.liuchen.eduservice.entity.vo.CoursePublishVo;
import com.liuchen.eduservice.entity.vo.CourseQuery;
import com.liuchen.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liuchen
 * @since 2022-10-25
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    // 课程列表 基本实现
    // 查询课程表所有数据
    @GetMapping("getCourseList")
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    // TODO 完善成条件查询带分页
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageTeacherCondition(@RequestBody(required = false) CourseQuery courseQuery,
                                  @PathVariable long current,
                                  @PathVariable long limit){
        // 创建page对象
        Page<EduCourse> pageCourse = new Page<>(current, limit);

        // 构造条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String teacherId = courseQuery.getTeacherId();
        String gmtCreate = courseQuery.getGmtCreate();
        String gmtModified = courseQuery.getGmtModified();
        // 判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }
        if (!StringUtils.isEmpty(gmtCreate)){
            wrapper.ge("gmt_create",gmtCreate);
        }
        if (!StringUtils.isEmpty(gmtModified)){
            wrapper.le("gmt_modified",gmtModified);
        }

        // 根据修改时间排序
        wrapper.orderByDesc("gmt_modified");

        // 调用方法实现条件查询分页
        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal(); // 总记录数
        List<EduCourse> records = pageCourse.getRecords(); // 数据list集合

        return R.ok().data("total",total).data("rows",records);
    }

    // 删除课程
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        //courseService.removeById(courseId);
        return R.ok();
    }

    // 添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        // 返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    // 根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    // 修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    // 根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo courseInfoVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",courseInfoVo);
    }
    
    // 课程最终发布
    // 修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal"); // 设置课程发布状态
        courseService.updateById(eduCourse);
        return R.ok();
    }
}

