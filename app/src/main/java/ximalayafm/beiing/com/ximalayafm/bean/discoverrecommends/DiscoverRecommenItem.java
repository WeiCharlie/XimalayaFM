package ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends;


import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverRecommenItem {
	private String title;
	private boolean hasMore;

	public void parseJSON(JSONObject json) throws JSONException {
		if (json != null) {

			title = json.getString("title");
			hasMore = json.getBoolean("hasMore");//  默认值false

		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}


}