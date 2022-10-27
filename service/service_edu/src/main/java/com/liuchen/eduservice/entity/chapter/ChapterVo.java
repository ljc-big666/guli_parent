package com.liuchen.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ChapterVo
 * @Description:
 * @date: 2022/10/27 10:43
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    // 表示小节
    private List<VideoVo> children = new ArrayList<>();
}
