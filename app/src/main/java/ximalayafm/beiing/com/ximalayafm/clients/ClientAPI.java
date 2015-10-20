package ximalayafm.beiing.com.ximalayafm.clients;

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
    private ClientAPI(){

    }
    // 接口12  -----------------------

    /**
     * 获取发现分类<br/>
     * API地址：/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2
     * @return :JsonObject
     */
    public static JSONObject getDiscoverCategories(){
        JSONObject ret  = null;
        byte[] data = HttpTools.doGet(
                     API_POINT +
                            "/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2");

        if (data != null) {
            try {
                String str = new String(data,"utf-8");
                ret = new JSONObject(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------
    // -----------------------


    // 自己测试使用图片网址
    public static final String AD_IMAGE = "http://img1.mydrivers.com/img/20150701/05e6b44457ad48549b09efbb03f43f6a.jpg";
}
