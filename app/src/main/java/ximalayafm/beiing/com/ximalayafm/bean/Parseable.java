package ximalayafm.beiing.com.ximalayafm.bean;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用于描述可以通过JSON/XML ，Cursor来解析的数据
 */
public interface Parseable {
    /**
     * 实体类解析JSON，更新内部数据
     * @param jsonObject
     * @throws JSONException
     */
    void parseJSON(JSONObject jsonObject) throws JSONException;
}
