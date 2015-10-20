package ximalayafm.beiing.com.ximalayafm.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.adapters.CommonFragmentPagerAdapter;
import ximalayafm.beiing.com.ximalayafm.fragments.discaover.DiscoverAnchorFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.discaover.DiscoverCategoryFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.discaover.DiscoverLifeFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.discaover.DiscoverRatingFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.discaover.DiscoverRecommendFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements TabLayout.OnTabSelectedListener {


    private ViewPager pager;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_discover, container, false);

        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.discover_tab_bar);

        //创建tab
        TabLayout.Tab tab=tabLayout.newTab().setText("推荐 ");
        tabLayout.addTab(tab);

        tab=tabLayout.newTab().setText("分类 ");
        tabLayout.addTab(tab);

        tab=tabLayout.newTab().setText("直播 ");
        tabLayout.addTab(tab);

        tab=tabLayout.newTab().setText("榜单 ");
        tabLayout.addTab(tab);

        tab=tabLayout.newTab().setText("主播 ");
        tabLayout.addTab(tab);

        // 2 View Pager 加载
        pager = (ViewPager) view.findViewById(R.id.discover_viewPager);

        List<BaseFragment> fragments = new LinkedList<BaseFragment>();
        fragments.add(new DiscoverAnchorFragment());
        fragments.add(new DiscoverCategoryFragment());
        fragments.add(new DiscoverLifeFragment());
        fragments.add(new DiscoverRatingFragment());
        fragments.add(new DiscoverRecommendFragment());

        // 如果在fragment内部使用adapter必须使用getChildFragmentManger
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getChildFragmentManager(),fragments);
        pager.setAdapter(adapter);
        // ViewPager滑动与TabLayout绑定
        pager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));// 支持多个事件同时处理

        // 设置tab的点击Viewpager联动
        tabLayout.setOnTabSelectedListener(this);
        return view;
    }

    /**
     * 实现的方法
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // TODO 切换ViewPager
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
