package ximalayafm.beiing.com.ximalayafm.tasks;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/20.
 * Email: charlie_net@163.com
 */

/**
 * 所有异步任务回调接口中定义的数据，其中包含的Action用于代表处理请求的异步任务
 * 这样的目的就是让回调接口的处理类能够检测到这个数据从哪里来。
 */
public class TaskResult {

    /**
     * 异步任务唯一标志识，每一个异步任务标识都不同
     */
    public int action;

    /**
     * 服务器返回的ret值，0代表成功
     */
    public int resultCode = -1;

     /**
     * 任意数据类型，只要接口实现类支持即可，
     */
    public Object data;

}
