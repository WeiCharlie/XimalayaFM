package ximalayafm.beiing.com.ximalayafm.fragments.discover;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverAnchorFragment extends BaseFragment {


    public DiscoverAnchorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_anchor, container, false);
    }


    @Override
    public String getFragmentTitle() {
        return "主播";
    }
}
