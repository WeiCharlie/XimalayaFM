package ximalayafm.beiing.com.ximalayafm.utils;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * 用于进行尺寸计算
 */
public final class DimensionUtil {

    private DimensionUtil() {
    }

    /**
     * 根据当前手机的屏幕密度，进行dp到px单位的换算
     * @param context：获取屏幕信息
     * @param dp：待转换内容
     * @return：px
     */
    public static int dp2px(Context context, int dp) {
        int ret = 0;
        Resources res = context.getResources();
        // 获取屏幕的测量信息
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        // px = dp * (dpi / 160)
        ret = (int)(dp * displayMetrics.density);

        return ret;
    }

}
