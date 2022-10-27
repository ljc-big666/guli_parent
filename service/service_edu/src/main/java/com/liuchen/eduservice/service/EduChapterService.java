package com.liuchen.eduservice.service;

import com.liuchen.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuchen.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liuchen
 * @since 2022-10-25
 */
public interface EduChapterService extends IService<EduChapter> {

    // 课程大纲列表，根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}