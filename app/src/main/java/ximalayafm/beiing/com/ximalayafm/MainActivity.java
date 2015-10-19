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

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    /**
     * 主界面中第一层 Fragment ，发现，定制听，下载听，我
     */
    private Fragment[] fragments;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new Fragment[4];
        fragments[0] = new DiscoverFragment();
        fragments[1] = new CustomTingFragment();
        fragments[2] = new DownloadTingFragment();
        fragments[3] = new PersonalFragment();

        /**
         * 采用hide 和 show 的形式，进行处理
         */
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        int len = fragments.length;
        for (int i = 0; i < len; i++) {
            tx.add(R.id.main_fragment_container, fragments[i]);
            tx.hide(fragments[i]);
        }
        tx.show(fragments[0]);

        tx.commit();


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_tab_bar);

        radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //show ， hide
        int index = 0;
        switch (checkedId){
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
            if(i == index){
                tx.show(fragments[i]);
            } else{
                tx.hide(fragments[i]);
            }
        }
        tx.commit();

    }
}





