package ximalayafm.beiing.com.ximalayafm.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */
public class DiscoverCategory {


    /**
     * id : 2
     * name : music
     * title : 音乐
     * isChecked : false
     * orderNum : 2
     * coverPath : http://fdfs.xmcdn.com/group12/M08/17/A0/wKgDXFVxM-LyPrvZAAAGGBNsGas270.png
     * selectedSwitch : false
     * isFinished : false
     * contentType : album
     */

    private int id;
    private String name;
    private String title;
    private boolean isChecked;
    private int orderNum;
    private String coverPath;
    private boolean selectedSwitch;
    private boolean isFinished;
    private String contentType;

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {

            id = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            title = jsonObject.getString("title");
            orderNum = jsonObject.getInt("orderNum");// TODO 需要考虑是否必选

            // optXXXX代表的是可选的,如果没有字段返回null或者默认值，而getXXXX必须有，没有则抛异常
            isChecked = jsonObject.optBoolean("isChecked");
            coverPath = jsonObject.optString("coverPath");
            selectedSwitch = jsonObject.optBoolean("selectedSwitch");
            isFinished = jsonObject.optBoolean("isFinished");
            contentType = jsonObject.optString("contentType");

        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public void setSelectedSwitch(boolean selectedSwitch) {
        this.selectedSwitch = selectedSwitch;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean getSelectedSwitch() {
        return selectedSwitch;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public String getContentType() {
        return contentType;
    }
}
