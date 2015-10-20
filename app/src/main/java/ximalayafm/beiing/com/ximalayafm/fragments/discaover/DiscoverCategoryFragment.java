package ximalayafm.beiing.com.ximalayafm.fragments.discaover;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;


public class DiscoverCategoryFragment extends BaseFragment {


    public DiscoverCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_category, container, false);
    }


    @Override
    public String getFragmentTitle() {
        return "分类";
    }
}
