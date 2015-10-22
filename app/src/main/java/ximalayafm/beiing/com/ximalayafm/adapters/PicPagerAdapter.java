package ximalayafm.beiing.com.ximalayafm.adapters;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.utils.DimensionUtil;

/**
 * 轮播海报的ViewPagerAdapter
 */
public class PicPagerAdapter extends PagerAdapter{
    private List<ImageView> picData;

    public PicPagerAdapter(List<ImageView> picData) {
        this.picData = picData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (picData != null) {
            if (!picData .isEmpty()){
                /**
                 *
                 */
                ret = Integer.MAX_VALUE;// 使用整型最大值来描述假的循环
            }

        }
        return ret;
    }

    /**
     * 判断View是否是通过或者由Object创建出来的；
     * 通常对于只返回View的PagerAdapter，那么可以简写成View==Object
     *
     * @param view ：ViewPager 内部管理的View
     * @param object ：由instantiateItem返回的对象
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    /**
     * 根据指定的位置创建对象，这个对象可以就是View，也可以是管理View的对象
     * 例如Fragment
     * 最终在方法返回之前，一定要将实际的View添加到container
     * 并且永远不要调用super
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        // TODO 因为getCount返回了整型最大值，实际的数据个数有限
        //  利用position % 数据个数，从而映射城市及数据索引
        int index = position % picData.size();

        // TODO 根据index获取点击位置，以及数据

        ImageView ret = picData.get(index);
        Log.d("PicPagerAdapter" ," " + picData.size());


//        ret.setImageResource(R.mipmap.ic_launcher);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensionUtil.dp2px(context,100)
        );
        ret.setLayoutParams(lp);
        container.addView(ret);
        return ret;
    }

    /**
     * 销毁对象，从容器删除视图
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
