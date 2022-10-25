package com.liuchen.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuchen.eduservice.entity.EduSubject;
import com.liuchen.eduservice.entity.excel.SubjectData;
import com.liuchen.eduservice.entity.subject.OneSubject;
import com.liuchen.eduservice.entity.subject.TwoSubject;
import com.liuchen.eduservice.listener.SubjectExcelListener;
import com.liuchen.eduservice.mapper.EduSubjectMapper;
import com.liuchen.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
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

        // 1、查询所有一级分类 parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        // 2、查询所有二级分类 parent_id != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 创建List集合，用于存储最终封装的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        // 3、封装一级分类
        // 查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        // 封装到要求的list集合里面 List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {
            // 得到oneSubjectList每个eduSubject对象
            EduSubject eduSubject1 = oneSubjectList.get(i);

            // 把每个eduSubject里面的值获取出来，放到OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            /*oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());*/
            // 把eduSubject值复制到对应oneSubject对象里面
            BeanUtils.copyProperties(eduSubject1,oneSubject);

            // 在一级分类循环遍历查询所有的二级分类
            // 创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectsList = new ArrayList<>();
            // 遍历二级分类list集合
            for (int m = 0; m < twoSubjectList.size(); m++) {
                EduSubject eduSubject2 = twoSubjectList.get(m);
                if(eduSubject2.getParentId().equals(eduSubject1.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    twoFinalSubjectsList.add(twoSubject);
                }
            }

            // 把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectsList);

            // 多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);
        }

        return finalSubjectList;
    }
}
