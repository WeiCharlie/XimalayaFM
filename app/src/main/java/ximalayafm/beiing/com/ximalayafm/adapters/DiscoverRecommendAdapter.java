package ximalayafm.beiing.com.ximalayafm.adapters;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/21.
 * Email: charlie_net@163.com
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommenItem;

/**
 * 发现推荐部分的ListView Adapter，支持多布局复用
 */
public class DiscoverRecommendAdapter extends BaseAdapter {

    private Context context;
    private List<DiscoverRecommenItem> items;

    public DiscoverRecommendAdapter(Context context, List<DiscoverRecommenItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (items != null) {
        ret = items.size();

        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        // 1 获取数据
        DiscoverRecommenItem item = items.get(position);

        return ret;
    }

}
