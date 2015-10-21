package ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.bean.AlbumBasic;

public class DiscoverRecommendAlbums extends DiscoverRecommenItem {
    /**
     * "ret": 0,
     * "title": "小编推荐",
     * "hasMore": true,
     * "list": []
     */
    private int ret;
    private List<AlbumRecommend> albumRecommends;
    public List<AlbumBasic> albumBasics;

    public void parseJSON(JSONObject json) throws JSONException {
        // 调用父类的解析，解析父类需要使用的数据
        // 因为super中包含了title  和hasMore，
        super.parseJSON(json);
        ret = json.optInt("ret", 0);

        JSONArray jsonArray = json.getJSONArray("list");
        int len = jsonArray.length();
        albumRecommends = new LinkedList<AlbumRecommend>();
        albumBasics = new LinkedList<>();// 设置图片和标题
        if (len > 0) {
            for (int i = 0; i < len; i++) {

                // 解析内部的专辑推荐
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //  TODO 推荐上显示的图片，标题内容等信息
                AlbumBasic basic = new AlbumBasic();
                basic.parseJSON(jsonObject);
                albumBasics.add(basic);

                AlbumRecommend albumRecommend = new AlbumRecommend();
                albumRecommend.parseJSON(jsonObject);
                albumRecommends.add(albumRecommend);
            }
        }

    }
}