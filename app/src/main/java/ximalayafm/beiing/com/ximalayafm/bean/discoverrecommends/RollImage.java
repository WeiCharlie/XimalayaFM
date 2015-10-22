package ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */
public class RollImage {


    /**
     * id : 3597
     * shortTitle : 中国每天2亿人乘电梯，安全吗？
     * longTitle : 中国每天2亿人乘电梯，安全吗？
     * pic : http://fdfs.xmcdn.com/group15/M06/52/77/wKgDaFW16vfD9nFSAAHBRSk6xm4459_android_large.jpg
     * type : 2
     * uid : 30495264
     * albumId : 2814299
     * isShare : false
     * is_External_url : false
     */

    private int id;
    private String shortTitle;
    private String longTitle;
    private String pic;
    private int type;
    private int uid;
    private int albumId;
    private boolean isShare;
    private boolean is_External_url;

    public void parseJSON(JSONObject jsonObject) throws JSONException {

        if (jsonObject != null) {
            id = jsonObject.getInt("id");
            pic = jsonObject.getString("pic");

            shortTitle = jsonObject.getString("shortTitle");
            longTitle = jsonObject.getString("longTitle");
            type = jsonObject.getInt("type");
            uid = jsonObject.getInt("uid");
            albumId = jsonObject.getInt("albumId");
            isShare = jsonObject.getBoolean("isShare");
            is_External_url = jsonObject.getBoolean("is_External_url");
        }


    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setIsShare(boolean isShare) {
        this.isShare = isShare;
    }

    public void setIs_External_url(boolean is_External_url) {
        this.is_External_url = is_External_url;
    }

    public int getId() {
        return id;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getPic() {
        return pic;
    }

    public int getType() {
        return type;
    }

    public int getUid() {
        return uid;
    }

    public int getAlbumId() {
        return albumId;
    }

    public boolean getIsShare() {
        return isShare;
    }

    public boolean getIs_External_url() {
        return is_External_url;
    }
}
