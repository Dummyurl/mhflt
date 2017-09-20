package com.changju.fanlitou.util;

import com.google.gson.Gson;

/**
 * Created by chengww on 2017/5/4.
 */

public class ParseUtils {
    /**
     * 通用的Gson解析工具类
     * @param s Json字符串
     * @param clz Bean.class
     * @param <T> 目标类型
     * @return 结果对象
     */
    public static <T> T parseByGson(String s,Class<T> clz) {
        Gson gson = new Gson();
        return gson.fromJson(s,clz);
    }

}
