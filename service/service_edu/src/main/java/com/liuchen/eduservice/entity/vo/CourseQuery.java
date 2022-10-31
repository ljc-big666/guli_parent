package com.liuchen.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: CourseQuery
 * @Description:
 * @date: 2022/10/31 10:40
 */
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程标题，模糊查询")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 10:10:10")
    private String gmtCreate;// 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "更新时间",example = "2019-12-01 10:10:10")
    private String gmtModified;
}
