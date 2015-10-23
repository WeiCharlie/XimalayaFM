package ximalayafm.beiing.com.ximalayafm.clients;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ximalayafm.beiing.com.ximalayafm.utils.HttpTools;
import ximalayafm.beiing.com.ximalayafm.utils.StreamUtils;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */
public final class ClientAPI {
    /**
     * 单独提取服务器地址的部分，避免以后更换服务器地址
     */
    private static final String API_POINT = "http://mobile.ximalaya.com";

    private ClientAPI() {

    }
    // 接口12  -----------------------

    /**
     * 获取发现分类<br/>
     * API地址：/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2
     *
     * @return :JsonObject
     */
    public static JSONObject getDiscoverCategories() {
        JSONObject ret = null;
        byte[] data = HttpTools.doGet(
                API_POINT +
                        "/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2");

        if (data != null) {
            try {
                String str = new String(data, "utf-8");
                Log.d("str", str);
                ret = new JSONObject(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
    // 接口11-----------------------

    /**
     * 获取发现部分推荐列表的内容
     * 请求网址是：/mobile/discovery/v1/recommends
     * ?channel=and-f6
     * &device=android
     * &includeActivity=true
     * &includeSpecial=true
     * &scale=2&version=4.1.7.1
     *
     * @param channel                 ：软件发布渠道
     * @param includeActivity：是否包含活动
     * @param includeSpecial：是否包含精品听单
     * @return：JSONObject
     */
    public static JSONObject getDiscoverRecommend(String channel, boolean includeActivity, boolean includeSpecial) {
        JSONObject ret = null;

        if (channel == null) {
            channel = "and-f6";
        }

        String url = API_POINT
                + "/mobile/discovery/v1/recommends"
                + "?channel=" + channel
                + "&device=android"
                + "&includeActivity=" + includeActivity
                + "&includeSpecial=" + includeSpecial
                + "&scale=2&version=4.1.7.1";
Log.d("URL",url);
        byte[] data = HttpTools.doGet(url);
        Log.d("Data","data" + data);
        Log.d("Url", url);
        if (data != null) {
            try {

                String str = new String(data,"utf-8");
                ret = new JSONObject(str);
                Log.d("JSONString"," -- " + str);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    // -----------------------

    //   -------------------------------接口17
    // http://mobile.ximalaya.com/mobile/others/ca/album/track/203355/true/2/20
    // ?device=android&pageSize=20&albumId=203355&isAsc=true

    public static JSONObject getAlbumDetail(String albumId){
        JSONObject ret = null;
        if (albumId != null) {
            StringBuilder sb = new StringBuilder(API_POINT);
            sb.append("/mobile/others/ca/album/track/203355/true/2/20");
        }

        return ret;
    }
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------


    // 自己测试使用图片网址
    public static final String AD_IMAGE = "http://img1.mydrivers.com/img/20150701/05e6b44457ad48549b09efbb03f43f6a.jpg";
}
