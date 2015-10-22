package ximalayafm.beiing.com.ximalayafm.widgets;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ximalayafm.beiing.com.ximalayafm.R;

/**
 * 自定义View，用于在发现模块中推荐栏目中 “精品听单”的条目展现
 */
public class SpecialItemView extends RelativeLayout {

    // 听单图标
    private ImageView imgIcon;
    // 听单标题
    private TextView txtTitle;
    /**
     * 听单子标题，就是描述textSubtitle
     */
    private TextView txtSubtitle;

    /**
     * 在代码中使用new SpecialItemView的时候，调用这个构造方法
     *
     * @param context
     */
    public SpecialItemView(Context context) {
//        super(context);
        this(context, null);

    }

    /**
     * 在布局xml中，使用控件的时候，使用的构造方法
     *
     * @param context
     * @param attrs
     */
    public SpecialItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 初始化控件，
        imgIcon = new ImageView(context);
        // 手写属性，
        // LayoutParams 代表在布局中的android : layout_xxx 属性
        // 控件要添加到那一个容器中，就用哪一个容器的layoutParams
        RelativeLayout.LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // ImageView垂直居中,相当于layout_centerVertical = true
        lp.addRule(CENTER_VERTICAL);
        imgIcon.setLayoutParams(lp);
        // 设置了imageciew的id
        imgIcon.setId(R.id.special_item_icon);
        imgIcon.setBackgroundResource(R.mipmap.ic_launcher);
        addView(imgIcon);

        //  标题部分------------------------------------------------------------
        txtTitle = new TextView(context);
        lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // 设置textView与ImageView顶部对齐
        lp.addRule(ALIGN_TOP, R.id.special_item_icon);
        lp.addRule(RIGHT_OF, R.id.special_item_icon);
        lp.leftMargin = 20; // 单位像素，需要进行机型适配

        txtTitle.setLayoutParams(lp);
        txtTitle.setText("标题");
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setId(R.id.special_item_title);
        addView(txtTitle);

        //--------------------------------------
        txtSubtitle = new TextView(context);
        lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        // 设置对齐规则
        lp.addRule(CENTER_VERTICAL);
        lp.addRule(ALIGN_LEFT, R.id.special_item_title);
        txtSubtitle.setLayoutParams(lp);
        txtSubtitle.setText("Subtitle");
        txtSubtitle.setId(R.id.sprcial_item_subtiel);
        addView(txtSubtitle);
    }

}
