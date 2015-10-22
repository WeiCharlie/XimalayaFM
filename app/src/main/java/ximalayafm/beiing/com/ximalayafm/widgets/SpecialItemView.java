package ximalayafm.beiing.com.ximalayafm.widgets;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/22.
 * Email: charlie_net@163.com
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.utils.DimensionUtil;

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
     * 听单中的专辑数，或者曲目数
     */
    private TextView txtNumber;
    /**
     * 最右侧箭头
     */
    private ImageView imgArrow;
    private ImageView imgLine;

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
                DimensionUtil.dp2px(context,120),
                DimensionUtil.dp2px(context,120));
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
        lp.leftMargin = DimensionUtil.dp2px(context,10); // 10dp 自定义DimensionUtil方法

        txtTitle.setLayoutParams(lp);
        txtTitle.setText("标题");
        // 设置字体尺寸 20sp
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setId(R.id.special_item_title);
        txtTitle.setSingleLine();
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
        txtSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        txtSubtitle.setId(R.id.sprcial_item_subtiel);
        // 单行
        txtSubtitle.setSingleLine();
        addView(txtSubtitle);

        // -------------------------------------------
        txtNumber = new TextView(context);
        lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        // 设置对齐与图片底部对齐，与标题左对齐
        lp.addRule(ALIGN_BOTTOM, R.id.special_item_icon);
        lp.addRule(ALIGN_LEFT, R.id.special_item_title);

        txtNumber.setLayoutParams(lp);
        txtNumber.setId(R.id.special_item_number);
        txtNumber.setText("10首声音");
        txtNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        // 给textView设置左面的图标
        // 这个方法可以同时设置左上右下图标,图标必须经过setBounds
        // 使用V4包获取Drawable方式，
        Drawable drawableLeft = ContextCompat.getDrawable(context, R.mipmap.finding_album_img);
        drawableLeft.setBounds(0, 0, 20, 20);
        txtNumber.setCompoundDrawables(drawableLeft, null, null, null);
        addView(txtNumber);

        //------------------------------------
        imgArrow = new
                ImageView(context);
        lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        imgArrow.setImageResource(R.mipmap.arrow_right);
        // 容器右对齐
        lp.addRule(ALIGN_PARENT_RIGHT);
        lp.addRule(CENTER_VERTICAL);
        imgArrow.setLayoutParams(lp);
        addView(imgArrow);

        // -------------------------------------------------
// 底部的线段
        imgLine = new ImageView(context);
        lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 1// 一个像素高度的图片
        );
        // 与title左对齐
        lp.addRule(ALIGN_LEFT, R.id.special_item_title);
        // 在父容器右侧
        lp.addRule(ALIGN_PARENT_RIGHT);
        // 在数量下面
        lp.addRule(BELOW, R.id.special_item_number);
        // 指定线与数量的间距,左上右下
        int top = DimensionUtil.dp2px(context, 5);
        lp.setMargins(0, top, 0, 0);
        imgLine.setLayoutParams(lp);
        imgLine.setImageResource(R.drawable.shape_line);
        addView(imgLine);

    }

    /**
     * 设置标题
     *
     * @param str
     */
    public void setTitle(String str) {
        /*
        if (str == null) {
            str = "";
        }*/

        txtTitle.setText(str);
    }

    /**
     * 设置描述
     *
     * @param str
     */
    public void setTxtSubtitle(String str) {
        txtSubtitle.setText(str);
    }

    public void setTxtNumber(String str) {
        txtNumber.setText(str);
    }

    /**
     * 设置底部的线是否显示
     *
     * @param show
     */
    public void setBottomLine(boolean show) {
        if (show) {
            imgLine.setVisibility(VISIBLE);
        } else {
            // TODO 尝试 gone 的情况
            imgLine.setVisibility(INVISIBLE);
        }
    }

    /**
     * @param resId
     */
    public void setRightArrowResource(int resId) {
        imgArrow.setImageResource(resId);
    }
    public ImageView getImgIcon(){
        return imgIcon;
    }

}
