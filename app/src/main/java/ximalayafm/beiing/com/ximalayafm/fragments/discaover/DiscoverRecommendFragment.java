package ximalayafm.beiing.com.ximalayafm.fragments.discaover;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.adapters.DiscoverRecommendAdapter;
import ximalayafm.beiing.com.ximalayafm.adapters.PicPagerAdapter;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.AlbumRecommend;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommenItem;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommendAlbums;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;
import ximalayafm.beiing.com.ximalayafm.tasks.DiscoverRecommendTask;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskCallBack;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskResult;
import ximalayafm.beiing.com.ximalayafm.utils.DimensionUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverRecommendFragment extends BaseFragment implements TaskCallBack, View.OnClickListener {

    private DiscoverRecommendAdapter adapter;
    private List<DiscoverRecommenItem> items;
    /**
     * 轮播海报
     */
    private ViewPager focusImagesPager;
    private PicPagerAdapter picPagerAdapter;

    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new LinkedList<DiscoverRecommenItem>();
        adapter = new DiscoverRecommendAdapter(getActivity(), items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_recommend, container, false);

        ListView listView = (ListView) ret.findViewById(R.id.discover_recommend_list);
        listView.setDividerHeight(40);

        focusImagesPager = new ViewPager(getActivity());
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensionUtil.dp2px(getActivity(), 100)
        );

        focusImagesPager.setLayoutParams(lp);
        LinkedList picData = new LinkedList();
        // TODO 假数据
        for (int i = 0; i < 5; i++) {
            picData.add("String" + i);
        }
        picPagerAdapter = new PicPagerAdapter(picData);
        focusImagesPager.setAdapter(picPagerAdapter);
        listView.addHeaderView(focusImagesPager);

        // ----------------------------------------
        // 设置点击专辑推荐点击事件
        adapter.setOnRecommendAlbumClickListener(this);

        listView.setAdapter(adapter);
        focusImagesPager.setCurrentItem(Integer.MAX_VALUE >> 1);

        return ret;
    }


    @Override
    public String getFragmentTitle() {
        return "推荐";
    }

    /**
     * 因为每次进入推荐的时候，都会触发内容的刷新，因此网络加载放到onResume中
     */
    @Override
    public void onResume() {
        super.onResume();
        DiscoverRecommendTask task = new DiscoverRecommendTask(this);
        task.execute();
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            // TODO 处理推荐列表数据
            if (result.action == Constants.TASK_ACTION_DISCOVER_RECOMMEND) {

                if (result.resultCode == Constants.TASK_RESULT_OK) {
                    Object data = result.data;
                    if (data != null && data instanceof List) {
                        List list = (List) data;
                        // TODO 更新ListView Adapter
                        // 只要数据来了，就清除ListView Adapter
                        items.clear();
                        for (Object l : list) {
                            if (l instanceof DiscoverRecommenItem) {
                                items.add((DiscoverRecommenItem) l);
                            }
                        }

                        adapter.notifyDataSetChanged();

                    } else if (data instanceof JSONObject) {
                        // focusImages
                        // 1 解析 轮播海报
                        JSONObject jsonObject = (JSONObject) data;
                        try {
                            jsonObject.getJSONObject("focusImages");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // TODO 解析海报

                        // 2 解析列表
                    }
                }

            } else {
                // TODO 提示错误信息
            }

        }
    }

    @Override
    public void onClick(View v) {

        Object tag = v.getTag();
        if (tag != null) {
            if (tag instanceof String) {
                String str = (String) tag;
                if (str.startsWith(Constants.TAG_DISCOVER_RECOMMEND_ALBUM)) {
                    // TODO 需要处理，专辑推荐的图标点击
                    str = str.substring(Constants.TAG_DISCOVER_RECOMMEND_ALBUM.length());
                    int index = str.indexOf(":");
                    if (index > -1) {
                        String s1 = str.substring(0, index);
                        String s2 = str.substring(index + 1);

                        int position = Integer.parseInt(s1);
                        int child = Integer.parseInt(s2);
                        // 获取Tag代表的专辑信息
                        DiscoverRecommendAlbums albums = (DiscoverRecommendAlbums) items.get(position);
                        List<AlbumRecommend> recommends = albums.getAlbumRecommends();
                        if (recommends != null) {
                            AlbumRecommend albumRecommend = recommends.get(child);
                            // 专辑Id
                            albumRecommend.getAlbumId();
                            // 曲目ID
                            albumRecommend.getTrackId();

                        }
                    }
                }
            }else if (tag instanceof AlbumRecommend){
                // 也是专辑推荐
                AlbumRecommend albumRecommend = (AlbumRecommend) tag;

                // 专辑Id
                // 接口17 获取专辑详情和曲目
                // 专辑id
                long albumId = albumRecommend.getAlbumId();
                // 曲目ID  接口20
                long trackId = albumRecommend.getTrackId();
            }
        }

    }
}
