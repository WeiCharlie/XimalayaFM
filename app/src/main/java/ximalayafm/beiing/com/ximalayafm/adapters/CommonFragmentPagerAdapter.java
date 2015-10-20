package ximalayafm.beiing.com.ximalayafm.adapters;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;

/**
 * 通用的Fragment适配器
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    /**
     *
     * @param fm
     * @param fragments
     */
    public CommonFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (fragments != null) {
            ret = fragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String ret = null;

        BaseFragment baseFragment = fragments.get(position);
        ret = baseFragment.getFragmentTitle();

        return ret;
    }
}
