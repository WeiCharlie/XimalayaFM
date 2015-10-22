package ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class DiscoverRecommendSpecial extends DiscoverRecommenItem {
    private List<SpecialItem> specialItems;

    @Override
    public void parseJSON(JSONObject json) throws JSONException {
        super.parseJSON(json);
        if (json != null) {

            // TODO 解析list
            JSONArray array = json.getJSONArray("list");
            int len = array.length();
            if (len > 0) {
                //
                specialItems = new LinkedList<>();
                for (int i = 0; i < len; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    SpecialItem specialItem = new SpecialItem();
                    specialItem.parseJSON(jsonObject);
                    specialItems.add(specialItem);
                }
            }
        }
    }

    public List<SpecialItem> getSpecialItems() {
        return specialItems;
    }
}