package ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.bean.Parseable;

/**
 * 一个听单数据实体
 */
public class SpecialItem implements Parseable {

    private int columnType;
    private long specialId;
    private String title;
    private String subtitle;
    private String footnote;
    private String coverPath;
    private String contentType;
    @Override

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            specialId = jsonObject.getInt("specialId");
            title = jsonObject.getString("title");

            subtitle = jsonObject.optString("subtitle");
            columnType = jsonObject.optInt("columnType", 1);
            footnote = jsonObject.optString("footnote");
            coverPath = jsonObject.optString("coverPath");
            contentType = jsonObject.optString("contentType");
        }
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(long specialId) {
        this.specialId = specialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
