package ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.bean.AlbumBasic;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/21.
 * Email: charlie_net@163.com
 */

/**
 * 发现推荐内部的一个专辑推荐，最终可以组合一个”小编推荐“
 */
public class AlbumRecommend extends AlbumBasic {
    /*
     *  {
     "albumId": 344497,
     "coverLarge": "http://fdfs.xmcdn.com/group6/M02/35/45/wKgDhFTg4w_SDkc9AAT-fXngGBY184_mobile_large.jpg",
     "title": "黑先生在麦田咖啡馆",
     "tags": "民谣,80后,文艺",
     "tracks": 117,



     "playsCounts": 917714,
     "isFinished": 0,
     "trackId": 7898099,
     "trackTitle": "几米：音乐与绘本的美好邂逅"
     }
     */
    private long playsCounts;
    private int isFinished;
    private long trackId;
    private String trackTitle;

    public long getPlaysCounts() {
        return playsCounts;
    }

    public void setPlaysCounts(long playsCounts) {
        this.playsCounts = playsCounts;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        super.parseJSON(jsonObject);

        if (jsonObject != null) {
            playsCounts = jsonObject.optLong("playsCounts",0);
            isFinished = jsonObject.optInt("isFinished", 0);
            trackId = jsonObject.getLong("trackId");
            trackTitle = jsonObject.getString("trackTitle");
        }
    }

}
