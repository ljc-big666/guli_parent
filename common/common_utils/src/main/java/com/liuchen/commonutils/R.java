package com.liuchen.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: R
 * @Description:
 * @date: 2022/10/14 18:00
 */
// 统一返回结果的类
@Data // 生成私有属性的get和set方法
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    // 把构造方法私有，外面的方法不能new此方法，只有本类内的方法可以new
    // 可以让其他类只能调用公开的方法
    private R(){}

    // 成功静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setMessage("成功");
        r.setCode(ResultCode.SUCCESS);

        return r;
    }

    // 失败静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setMessage("失败");
        r.setCode(ResultCode.ERROR);

        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
