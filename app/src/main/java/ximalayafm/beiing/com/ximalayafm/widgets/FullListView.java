package ximalayafm.beiing.com.ximalayafm.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/23.
 * Email: charlie_net@163.com
 */

/**
 * 自动扩充尺寸的ListView，适用于ScrollView+ListView，
 */
public class FullListView extends ListView {
    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
          Integer.MAX_VALUE >> 2,// size控件可以显示的最大高度；
                MeasureSpec.AT_MOST
        );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
