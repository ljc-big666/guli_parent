package com.liuchen.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuchen.commonutils.R;
import com.liuchen.eduservice.entity.EduTeacher;
import com.liuchen.eduservice.entity.vo.TeacherQuery;
import com.liuchen.eduservice.service.EduTeacherService;
import com.liuchen.servicebase.exceptionhandler.GuLiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author liuchen
 * @since 2022-10-14
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 1、查询讲师表所有数据
    // restful风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        // 调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    // 2、逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 3、分页查询讲师的方法
    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher, null);

/*        try {
            int i = 10/0;
        } catch (Exception e){
            // 执行自定义异常
            throw new GuLiException(20001,"执行了自定义异常处理");
        }*/

        long total = pageTeacher.getTotal();// 总记录数
        List<EduTeacher> records = pageTeacher.getRecords();// 数据list集合

        /*Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);*/

        return R.ok().data("total",total).data("rows",records);
    }

    // 4、条件查询带分页的方法
    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        // 构造条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)){
            // 构造条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified",end);
        }

        // 排序
        wrapper.orderByDesc("gmt_modified");

        // 调用方法实现条件查询分页
        eduTeacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();// 总记录数
        List<EduTeacher> records = pageTeacher.getRecords();// 数据list集合

        return R.ok().data("total",total).data("rows",records);
    }

    // 添加讲师接口的方法
    @ApiOperation(value = "讲师添加")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 根据讲师id进行查询
    @ApiOperation(value = "根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    // 讲师修改功能
    @ApiOperation(value = "讲师修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

