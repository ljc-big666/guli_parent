package com.liuchen.eduservice.entity.vo;

import lombok.Data;

/**
 * @ClassName: CoursePublishVo
 * @Description:
 * @date: 2022/10/30 19:28
 */

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price; // 只用于显示
}
