package com.changju.fanlitou.util;

import com.orhanobut.logger.Logger;

/**
 * Created by mh on 2017/9/19.
 */

public class LogUtils {

    public static void showJson(String json) {
        Logger.json(json);
    }

    public static void showMsg(String msg) {
        Logger.d(msg);
    }
}
