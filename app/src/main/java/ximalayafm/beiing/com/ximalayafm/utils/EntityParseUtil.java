package ximalayafm.beiing.com.ximalayafm.utils;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.bean.DiscoverCategory;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommenItem;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommendAlbums;

/**
 * 实体类的解析工具类
 */
public final class EntityParseUtil {
    private EntityParseUtil() {
    }

    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject jsonObject) {
        List<DiscoverCategory> ret = null;
        if (jsonObject != null) {
            try {
                int code = jsonObject.getInt("ret");
                //  获取数据成功
                if (code == Constants.TASK_RESULT_OK) {
                    ret = new LinkedList<DiscoverCategory>();
                    JSONArray array = jsonObject.getJSONArray("list");
                    int len = array.length();

                    if (len > 0) {

                        for (int i = 0; i < len; i++) {

                            JSONObject obj = array.getJSONObject(i);
                            DiscoverCategory category = new DiscoverCategory();
                            // 利用实体类，内部实现json解析，外部代码，就是调用方法即可，代码简洁
                            //
                            category.parseJSON(obj);
                            ret.add(category);

                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;

    }

    /**
     * 解析发现模块中，推荐栏目中的数据结构
     *
     * @param jsonObject
     * @return
     */
    public static List<DiscoverRecommenItem> parseDiscoverRecommend(JSONObject jsonObject) {
        List<DiscoverRecommenItem> ret = null;

        if (jsonObject != null) {
            try {
                int code = jsonObject.getInt("ret");
                if (code == Constants.TASK_RESULT_OK) {
                    ret = new LinkedList<DiscoverRecommenItem>();
                    // 小编推荐内容解析
                    JSONObject obj = jsonObject.getJSONObject("editorRecommendAlbums");
                    DiscoverRecommendAlbums editor = new DiscoverRecommendAlbums();
                    editor.parseJSON(obj);
                    ret.add(editor);

                    // ---------------------------------------

                    // TODO 解析精品听单

                    // TODO 解析发现新奇

                    // 解析热门推荐内部的内容
                    JSONObject object = jsonObject.getJSONObject("hotRecommends");

                    JSONArray array = object.getJSONArray("list");
                    for (int i = 0; i < array.length(); i++) {
                        // 获取热门推荐内部list中每一个推荐信息
                        JSONObject object1 = array.getJSONObject(i);
                        DiscoverRecommendAlbums hotRecommend = new DiscoverRecommendAlbums();
                        hotRecommend.parseJSON(object1);
                        ret.add(hotRecommend);

                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
