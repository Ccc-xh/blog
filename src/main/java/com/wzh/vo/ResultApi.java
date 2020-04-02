package com.wzh.vo;
import com.wzh.command.ResultCodeInfoEnum;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:
 * @description: 返回Json数据前缀通用数据
 * @author: han
 * @create: 2019-10-30 16:35
 * @xgr:
 * @description:
 **/
public class ResultApi {

    public static Map<String, Object > ResultNoAndDesc(ResultCodeInfoEnum apiResponseEnum, boolean flag) {
        Map<String,Object> result = new HashMap<>();
        result.put("no",apiResponseEnum.getNo());
        result.put("msg",apiResponseEnum.getMsg());
        if(flag){
            result.put("data",apiResponseEnum.getDesc());
        }
        return result;
    }
    public static Map<String, Object> ResultAll(ResultCodeInfoEnum apiResponseEnum,Object object) {
        Map<String,Object > result = new HashMap<>();
        result.put("no",apiResponseEnum.getNo()+"");
        result.put("msg",apiResponseEnum.getMsg());
        result.put("data",object);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}
