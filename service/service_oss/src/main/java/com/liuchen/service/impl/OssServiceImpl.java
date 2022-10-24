package com.liuchen.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.liuchen.service.OssService;
import com.liuchen.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: OssServiceImpl
 * @Description:
 * @date: 2022/10/23 19:49
 */
@Service
public class OssServiceImpl implements OssService {

    // 上传头像到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        // 获取文件名称
        String fileName = file.getOriginalFilename();

        // 在文件名称里面添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        fileName = uuid + fileName;

        // 把文件按照日期进行分类
        String datePath = new DateTime().toString("yyyy/MM/dd");
        fileName = datePath + "/" + fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流
        try {
            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            // 调用oss方法实现上传
            // 第一个参数 Bucket名称
            // 第二个参数 上传到oss文件路径和名称
            // 第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 把上传之后文件路径返回
            // 需要把上传到阿里云oss路径手动拼接出来
            // https://edu-liujiachen.oss-cn-hongkong.aliyuncs.com/%E6%A1%8C%E9%9D%A2.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
