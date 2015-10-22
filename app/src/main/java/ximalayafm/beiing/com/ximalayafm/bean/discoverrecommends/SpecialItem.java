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
            columnType = jsonObject.optInt("columnType",1);
            footnote = jsonObject.optString("footnote");
            coverPath = jsonObject.optString("coverPath");
            contentType = jsonObject.optString("contentType");
        }
    }
}
