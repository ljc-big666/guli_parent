package com.liuchen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuchen.eduservice.entity.EduChapter;
import com.liuchen.eduservice.entity.EduVideo;
import com.liuchen.eduservice.entity.chapter.ChapterVo;
import com.liuchen.eduservice.entity.chapter.VideoVo;
import com.liuchen.eduservice.mapper.EduChapterMapper;
import com.liuchen.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuchen.eduservice.service.EduVideoService;
import com.liuchen.servicebase.exceptionhandler.GuLiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<ChapterVo> finalList = new ArrayList<>();

        // 3、遍历查询章节list集合进行封装
        // 遍历查询章节list集合
        for (int i = 0; i < chapterList.size(); i++){
            // 每个章节
            EduChapter eduChapters = chapterList.get(i);
            // eduChapters对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapters, chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();
            // 4、遍历查询小节list集合，进行封装
            for (int m = 0; m < videoList.size(); m++){
                // 遍历每个小节
                EduVideo eduVideo = videoList.get(m);
                if (eduVideo.getChapterId().equals(eduChapters.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
            finalList.add(chapterVo);
        }

        return finalList;
    }

    // 删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据chapterId章节id查询小节表，如果查询有数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if (count > 0){
            throw new GuLiException(20001, "不能删除");
        } else {
            int result = baseMapper.deleteById(chapterId);
            // 成功 1>0 true  失败 0>0 false
            return result>0;
        }
    }
}
