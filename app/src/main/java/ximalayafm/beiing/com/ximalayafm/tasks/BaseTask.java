package ximalayafm.beiing.com.ximalayafm.tasks;

import android.os.AsyncTask;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */
public class  BaseTask extends AsyncTask<String,Void,TaskResult> {

    private TaskCallBack callBack;

    public BaseTask(TaskCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(TaskResult result) {
        if (callBack != null) {
            callBack.onTaskFinished(result);
        }
    }
}
