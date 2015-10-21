package ximalayafm.beiing.com.ximalayafm.fragments.discaover;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.adapters.DiscoverRecommendAdapter;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommenItem;
import ximalayafm.beiing.com.ximalayafm.fragments.BaseFragment;
import ximalayafm.beiing.com.ximalayafm.tasks.DiscoverRecommendTask;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskCallBack;
import ximalayafm.beiing.com.ximalayafm.tasks.TaskResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverRecommendFragment extends BaseFragment implements TaskCallBack {

    private DiscoverRecommendAdapter adapter;
    private List<DiscoverRecommenItem> items;

    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

        listView.setAdapter(adapter);

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
        if (result != null){
        // TODO 处理推荐列表数据
            if (result.action == Constants.TASK_ACTION_DISCOVER_RECOMMEND){

                if (result.resultCode == Constants.TASK_RESULT_OK){
                    Object data = result.data;
                    if (data != null && data instanceof List) {
                        List list = (List)data;
                        // TODO 更新ListView Adapter
                        // 只要数据来了，就清除ListView Adapter
                        items.clear();
                        for (Object l : list) {
                            if (l instanceof  DiscoverRecommenItem){
                                items.add((DiscoverRecommenItem)l);
                            }
                        }

                        adapter.notifyDataSetChanged();

                    }
                }else{
                    // TODO 提示错误信息
                }

            }

        }
    }
}
