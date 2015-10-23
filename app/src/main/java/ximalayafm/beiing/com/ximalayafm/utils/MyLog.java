package ximalayafm.beiing.com.ximalayafm.utils;

import android.util.Log;

import ximalayafm.beiing.com.ximalayafm.BuildConfig;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/23.
 * Email: charlie_net@163.com
 */

/**
 * 自定义日志打印工具
 */
public class MyLog {

    private static final boolean LOG_ON = BuildConfig.DEBUG;

    private MyLog() {
    }

    public static void i(String tag, String msg) {
        if (LOG_ON) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_ON) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_ON) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_ON) {
            Log.w(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (LOG_ON) {
            Log.v(tag, msg);
        }
    }

}
