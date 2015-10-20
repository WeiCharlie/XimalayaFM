package ximalayafm.beiing.com.ximalayafm.tasks;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */

/**
 * 异步任务回调接口
 * @see TaskResult
 */
public interface TaskCallBack {

    /**
     * 当异步任务执行完成时，会回调这个方法，将数据结构传递给相应的实现类
     * @param result 返回的结果
     */
    void onTaskFinished(TaskResult result);

}
