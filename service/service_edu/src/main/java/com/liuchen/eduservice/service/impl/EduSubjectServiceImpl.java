package com.liuchen.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.liuchen.eduservice.entity.EduSubject;
import com.liuchen.eduservice.entity.excel.SubjectData;
import com.liuchen.eduservice.entity.subject.OneSubject;
import com.liuchen.eduservice.listener.SubjectExcelListener;
import com.liuchen.eduservice.mapper.EduSubjectMapper;
import com.liuchen.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author liuchen
 * @since 2022-10-24
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    // 添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            // 文件输入流
            InputStream in = file.getInputStream();
            // 调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // 课程分类列表(树形)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        return null;
    }
}
