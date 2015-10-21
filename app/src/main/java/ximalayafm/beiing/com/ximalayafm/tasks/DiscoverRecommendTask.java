package ximalayafm.beiing.com.ximalayafm.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.clients.ClientAPI;
import ximalayafm.beiing.com.ximalayafm.utils.EntityParseUtil;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */
public class DiscoverRecommendTask extends BaseTask{


    public DiscoverRecommendTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();

        ret.action = Constants.TASK_ACTION_DISCOVER_RECOMMEND;
        JSONObject jsonObject = ClientAPI.getDiscoverRecommend("and-f6", true, true);

        if (jsonObject != null) {
            try {
                ret.resultCode = jsonObject.getInt("ret");


                // 解析数据
                ret.data = EntityParseUtil.parseDiscoverRecommend(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return ret;
    }


}
