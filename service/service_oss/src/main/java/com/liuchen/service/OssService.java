package com.liuchen.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: OssService
 * @Description:
 * @date: 2022/10/23 19:48
 */

public interface OssService {
    // 上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
