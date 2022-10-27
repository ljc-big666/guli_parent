package com.liuchen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuchen.eduservice.entity.EduChapter;
import com.liuchen.eduservice.entity.EduVideo;
import com.liuchen.eduservice.entity.chapter.ChapterVo;
import com.liuchen.eduservice.mapper.EduChapterMapper;
import com.liuchen.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuchen.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liuchen
 * @since 2022-10-25
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    // 课程大纲列表，根据课程id进行查询
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        // 1、根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        // 2、根据章节id查询章节里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduVideo> videoList = videoService.list(wrapperVideo);

        // 创建list集合，用于最终封装数据


        return null;
    }
}
