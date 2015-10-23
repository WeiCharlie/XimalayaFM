package ximalayafm.beiing.com.ximalayafm.fragments.discover;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
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
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.RollImage;
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
    private ViewPager viewPager;
    /**
     * 轮播海报
     */
    private ViewPager focusImagesPager;
    private PicPagerAdapter picPagerAdapter;
    private List<ImageView> picData;
    private ListView listView;

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

        listView = (ListView) ret.findViewById(R.id.discover_recommend_list);
        listView.setDividerHeight(40);

        focusImagesPager = new ViewPager(getActivity());
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensionUtil.dp2px(getActivity(), 100)
        );

        focusImagesPager.setLayoutParams(lp);
        picData = new LinkedList<>();
        // TODO 假数据


        Log.d("CreateView","" + picData.size());
        picPagerAdapter = new PicPagerAdapter(picData);
//        focusImagesPager.setAdapter(picPagerAdapter);
        listView.addHeaderView(focusImagesPager);
        Log.d("CreateView", "" + picData.size());
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
                        try {
                            JSONArray imgArray = jsonObject.getJSONArray("list");
                            List<RollImage> rollImages = new LinkedList<>();
                            int length = imgArray.length();
                            for (int i = 0; i < length; i++) {

                                Log.d("JSONArray",""+ length);
                                JSONObject object = imgArray.getJSONObject(i);
                                RollImage rollImage = new RollImage();
                                rollImage.parseJSON(object);
//                                rollImages.add(rollImage);

                                ImageView imageView = new ImageView(getActivity());
                                Picasso.with(getActivity()).load(rollImage.getPic()).into(imageView);

                                picData.add(imageView);
                                Toast.makeText(getActivity(),length+"",Toast.LENGTH_LONG).show();

                            }

                            picPagerAdapter = new PicPagerAdapter(picData);
                            focusImagesPager.setAdapter(picPagerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // TODO 2 解析列表

                    }
                }

            } else {
                // TODO 提示错误信息
                Toast.makeText(getActivity(),"滚动图加载失败",Toast.LENGTH_LONG).show();
            }

        }
    }

    /**
     * 专辑推荐的图标点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        Object tag = v.getTag();
        if (tag != null) {
            if (tag instanceof String) {
                String str = (String) tag;
                if (str.startsWith(Constants.TAG_DISCOVER_RECOMMEND_ALBUM)) {
                    //  高逼格写法，轻易不要尝试，else if中的是常规写法
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
            } else if (tag instanceof AlbumRecommend) {
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
