package com.liuchen.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: GuLiException
 * @Description:
 * @date: 2022/10/17 15:40
 */
@Data
@AllArgsConstructor // 生成有参数的构造方法（全参）
@NoArgsConstructor // 生成无参数的构造法方法
public class GuLiException extends RuntimeException{

    private Integer code; // 状态码
    private String msg; // 异常信息
}
