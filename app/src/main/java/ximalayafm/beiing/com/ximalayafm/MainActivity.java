package ximalayafm.beiing.com.ximalayafm;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import ximalayafm.beiing.com.ximalayafm.fragments.CustomTingFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.DiscoverFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.DownloadTingFragment;
import ximalayafm.beiing.com.ximalayafm.fragments.PersonalFragment;

/**
 * 主页面中显示具体的内容
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    /**
     * 主界面中第一层 Fragment ，发现，定制听，下载听，我
     */
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 检查横竖屏切换时，这个数组对象和内部元素是否置空
        fragments = new Fragment[4];
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            // 代表Activity 重新创建,内部的Fragment会自动添加
            int len = fragments.length;
            for (int i = 0; i < len; i++) {
                fragments[i] = fragmentManager.findFragmentByTag("f" + i);
            }

        } else {
            //
            fragments[0] = new DiscoverFragment();
            fragments[1] = new CustomTingFragment();
            fragments[2] = new DownloadTingFragment();
            fragments[3] = new PersonalFragment();

            /**
             * 采用hide 和 show 的形式，进行处理
             */

            FragmentTransaction tx = fragmentManager.beginTransaction();

            int len = fragments.length;

            for (int i = 0; i < len; i++) {
                // 添加Fragment 并且指定tag，利用tag在activitychognxin 创建时，恢复查找
                tx.add(R.id.main_fragment_container, fragments[i], "f" + i);
                tx.hide(fragments[i]);
            }
            tx.show(fragments[0]);

            tx.commit();
        }


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_tab_bar);

        radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //show ， hide
        int index = 0;
        switch (checkedId) {
            case R.id.main_tab_item_discover:
                index = 0;
                break;
            case R.id.main_tab_item_custom:
                index = 1;
                break;
            case R.id.main_tab_item_download:
                index = 2;
                break;
            case R.id.main_tab_item_personal:
                index = 3;
                break;
        }

        int len = fragments.length;
        FragmentTransaction tx = fragmentManager.beginTransaction();
        for (int i = 0; i < len; i++) {
            if (i == index) {
                tx.show(fragments[i]);
            } else {
                tx.hide(fragments[i]);
            }
        }
        tx.commit();

    }
}





