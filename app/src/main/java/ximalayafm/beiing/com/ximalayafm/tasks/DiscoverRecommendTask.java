package ximalayafm.beiing.com.ximalayafm.tasks;

import android.os.AsyncTask;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */
public class DiscoverRecommendTask extends BaseTask{

    private TaskCallBack callBack;

    public DiscoverRecommendTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        return null;
    }


}
