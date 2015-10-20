package ximalayafm.beiing.com.ximalayafm.fragments.discaover;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;
import ximalayafm.beiing.com.ximalayafm.tasks.DiscoverCategoryTask;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskCallBack;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskResult;


public class DiscoverCategoryFragment extends BaseFragment implements TaskCallBack {


    public DiscoverCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiscoverCategoryTask task = new DiscoverCategoryTask(this);
        task.execute();
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


    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int action = result.action;
            if(action == Constants.TASK_ACTION_DISCOVER_CATEGORIES){
                // TODO 结果从发现- 分类，任务中返回的，获取的是分类
                if(result.resultCode == Constants.TASK_RESULT_OK){
                    // TODO 加载成功
                    Object data = result.data;
                    if(data != null && data instanceof List){
                        List list = (List) data;
                        Log.d("List"," list = " + list);
                        Log.d("List"," list = " + list.toString());
                    }

                }else {
                    // TODO 加载失败
                }
            }
        }
    }
}
