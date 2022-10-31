package com.liuchen.eduservice.service;

import com.liuchen.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author liuchen
 * @since 2022-10-25
 */
public interface EduVideoService extends IService<EduVideo> {

    // 1、根据课程id删除小节
    void removeVideoByCourseId(String courseId);
}
