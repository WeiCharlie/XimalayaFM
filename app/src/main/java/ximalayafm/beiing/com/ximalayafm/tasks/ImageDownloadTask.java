package ximalayafm.beiing.com.ximalayafm.tasks;

import ximalayafm.beiing.com.ximalayafm.Constants;
import ximalayafm.beiing.com.ximalayafm.clients.ClientAPI;
import ximalayafm.beiing.com.ximalayafm.utils.HttpTools;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */

/**
 * 自己测试用 图片下载任务
 */
public class ImageDownloadTask extends BaseTask {

    public ImageDownloadTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();

            ret.action = Constants.TASK_ACTION_DISCOVER_IMAGE;
            ret.data = HttpTools.doGet(ClientAPI.AD_IMAGE);

        
        return ret;
    }
}
