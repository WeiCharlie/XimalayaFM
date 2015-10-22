package ximalayafm.beiing.com.ximalayafm.bean;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/21.
 * Email: charlie_net@163.com
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 专辑的基本信息
 * @see ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.AlbumRecommend
 */
public class AlbumBasic {
    /*
     {
        "albumId": 344497,
        "coverLarge": "http://fdfs.xmcdn.com/group6/M02/35/45/wKgDhFTg4w_SDkc9AAT-fXngGBY184_mobile_large.jpg",
        "title": "黑先生在麦田咖啡馆",
        "tags": "民谣,80后,文艺",
        "tracks": 117,

      }
     */

    private long albumId;
    private String coverLarge;
    private String title;
    private String tags;
//    private String trackTitle;

//    public String getTrackTitle() {
//        return trackTitle;
//    }
//
//    public void setTrackTitle(String trackTitle) {
//        this.trackTitle = trackTitle;
//    }

    /**
     * 曲目数
     */

    private long tracks;

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            albumId = jsonObject.getLong("albumId");
            coverLarge = jsonObject.getString("coverLarge");
            title = jsonObject.getString("title");
            tags = jsonObject.getString("tags");
            tracks = jsonObject.getLong("tracks");
//            trackTitle = jsonObject.optString("trackTitle");
        }
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getTracks() {
        return tracks;
    }

    public void setTracks(long tracks) {
        this.tracks = tracks;
    }
}
