package ximalayafm.beiing.com.ximalayafm.adapters;

/**
 * Created by  :
 * Author: Charlie Wei
 * Date: 2015/10/21.
 * Email: charlie_net@163.com
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.List;

import ximalayafm.beiing.com.ximalayafm.R;
import ximalayafm.beiing.com.ximalayafm.bean.AlbumBasic;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.AlbumRecommend;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommenColumns;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommenItem;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommendAlbums;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.DiscoverRecommendSpecial;
import ximalayafm.beiing.com.ximalayafm.bean.discoverrecommends.SpecialItem;
import ximalayafm.beiing.com.ximalayafm.widgets.SpecialItemView;

/**
 * 发现推荐部分的ListView Adapter，支持多布局复用
 */
public class DiscoverRecommendAdapter extends BaseAdapter {

    private Context context;
    private List<DiscoverRecommenItem> items;

    // ---------------------------------------点击事件处理器
    /**
     * 推荐专辑点击事件
     */
    private View.OnClickListener onRecommendAlbumClickListener;

    // -------------------------------------
    public DiscoverRecommendAdapter(Context context, List<DiscoverRecommenItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setOnRecommendAlbumClickListener(View.OnClickListener onRecommendAlbumClickListener) {
        this.onRecommendAlbumClickListener = onRecommendAlbumClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (items != null) {
            ret = items.size();

        }
        return ret;
    }

    @Override
    public int getViewTypeCount() {
        return 3; // 当前的listView可以显示3中类型，小编推荐/热门推荐、精品听单、发现新奇三种。
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;// 索引位置
        DiscoverRecommenItem item = items.get(position);

        if (item instanceof DiscoverRecommendAlbums) {// 小编推荐，热门推荐
            ret = 0;
        } else if (item instanceof DiscoverRecommenColumns) {// 发现新奇
            ret = 1;
        } else if (item instanceof DiscoverRecommendSpecial) {// 精品听单
            ret = 2;
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        // 1 获取数据
        DiscoverRecommenItem item = items.get(position);

        if (item instanceof DiscoverRecommendAlbums) {// 小编推荐，热门推荐
            ret = bindAlbumsView(position, convertView, parent);
        } else if (item instanceof DiscoverRecommenColumns) {// 发现新奇
            ret = bindColumnView(position, convertView, parent);
        } else if (item instanceof DiscoverRecommendSpecial) {// 精品听单
            ret = bindSpecialView(position, convertView, parent);
        }

        return ret;
    }

    /**
     * 加载专辑推荐的item，小编推荐和热门推荐
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View bindAlbumsView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        // 1 复用
        if (convertView != null) {
            ret = convertView;
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            ret = inflater.inflate(R.layout.discover_recommend_album_item, parent, false);

        }

        // 2 ViewHolder
        AlbumViewHolder holder = (AlbumViewHolder) ret.getTag();
        if (holder == null) {

            holder = new AlbumViewHolder();
            holder.txtTitle = (TextView) ret.findViewById(R.id.recommend_album_title);
            holder.txtMore = (TextView) ret.findViewById(R.id.recommend_album_more);

            // TODO 点击more进入推荐列表


            holder.albumIcons = new ImageView[3];
            holder.albumNames = new TextView[3];
            holder.trackNames = new TextView[3];

            for (int i = 0; i < 3; i++) {
                // 给holder设置数组的内容
                holder.albumIcons[i] = (ImageView) findView(ret, "recommend_album_icon_" + i);

                // TODO 点击专辑图片进入专辑详情
                holder.albumIcons[i].setOnClickListener(onRecommendAlbumClickListener);


                holder.albumNames[i] = (TextView) findView(ret, "recommend_album_name_" + i);
                holder.trackNames[i] = (TextView) findView(ret, "recommend_album_track_name_" + i);

            }

            ret.setTag(holder);
        }

        // 3 获取数据,设置内容
        DiscoverRecommendAlbums albums = (DiscoverRecommendAlbums) items.get(position);
        String title = albums.getTitle();
        holder.txtTitle.setText(title);

        // 处理 “更多”
        if (albums.isHasMore()) {
            holder.txtMore.setVisibility(View.VISIBLE);
        } else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }

        // 处理专辑标题
        List<AlbumRecommend> albumRecommends = albums.getAlbumRecommends();
        int len = holder.albumIcons.length;

        if (albumRecommends != null) {
            for (int i = 0; i < len; i++) {
                AlbumRecommend albumRecommend = albumRecommends.get(i);
                title = albumRecommend.getTitle();// 获取专辑名称
                holder.albumNames[i].setText(title);
                title = albumRecommend.getTrackTitle(); // 获取推荐曲目的名称
                holder.trackNames[i].setText(title);

                // 使用picasso加载图片
                String coverLarge = albumRecommend.getCoverLarge();
                // 创建实例，设置加载网址，设置居中裁剪，设置ImageView

                // 设置Tag
                ImageView imageView = holder.albumIcons[i];
//                imageView.setTag("albumRecommend:" + position+":" +i);
                imageView.setTag(albumRecommend);
                Picasso.with(context).load(coverLarge)
//                        .resize(128,128)
//                        .centerCrop()
                        .into(imageView);
            }
        }

//        //  为设置图片和标签做铺垫
//        BitmapUtils bitmapUtils = new BitmapUtils(context);
//        List<AlbumBasic> list = albums.albumBasics;
//
//        for (int i = 0; i < 3; i++) {// 遍历item设置各个推荐图片和标题
//            bitmapUtils.display(holder.albumIcons[i],list.get(i).getCoverLarge());
//            holder.albumNames[i].setText(list.get(i).getTitle());
//            holder.trackNames[i].setText(list.get(i).getTrackTitle());
//        }

        return ret;
    }

    /**
     * 发现新奇
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View bindColumnView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /**
     * 加载精品听单
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View bindSpecialView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        // 1 复用
        if (convertView != null) {
            ret = convertView;

        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            ret = inflater.inflate(R.layout.discover_recommend_special_item, parent, false);

        }
        // 2 ViewHolder 处理
        SpecialViewHolder holder = (SpecialViewHolder) ret.getTag();
        if (holder == null) {
            holder = new SpecialViewHolder();
            holder.txtTile = (TextView) ret.findViewById(R.id.recommend_special_title);
            holder.txtMore = (TextView) ret.findViewById(R.id.recommend_special_more);
            holder.itemContainer = (LinearLayout) ret.findViewById(R.id.recommend_special_container);

            ret.setTag(holder);
        }
        // 3 获取数据，处理数据，显示数据
        DiscoverRecommendSpecial special = (DiscoverRecommendSpecial) items.get(position);
        holder.txtTile.setText(special.getTitle());
        if (special.isHasMore()) {
            holder.txtMore.setVisibility(View.VISIBLE);
        } else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }
        // 3.1 清空旧数据linearlyout再根据听单的item来添加
        holder.itemContainer.removeAllViews();
        List<SpecialItem> specialItems = special.getSpecialItems();
        if (specialItems != null) {

            int index = -1;
            int itemtCount = specialItems.size();
            for (SpecialItem s : specialItems) {
                index++;
                // 创建自定义View，用来显示听单信息
                SpecialItemView itemView = new SpecialItemView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                itemView.setLayoutParams(lp);
                holder.itemContainer.addView(itemView);

                Log.d("Add", " --" + s);
                // 设置听单内容
                String title = s.getTitle();
                itemView.setTitle(title);
                itemView.setTxtSubtitle(s.getSubtitle());
                itemView.setTxtNumber(s.getFootnote());

                // 获取听单的图标ImageView，加载图片
                ImageView imageView = itemView.getImgIcon();
                String coverPath = s.getCoverPath();
                Picasso.with(context).load(coverPath).into(imageView);

                // 判断是否是最后一个
                if (index == itemtCount - 1) {
                    itemView.setBottomLine(false);
                }
            }
        }

        return ret;
    }


    /**
     * 精品听单ViewHolder
     */
    private static class SpecialViewHolder {
        public TextView txtTile;
        public TextView txtMore;
        public LinearLayout itemContainer;//存储SpecailItemView
    }

    /**
     * 利用类的反射，来获取指定R,id.XXXX代表的View
     *
     * @param container
     * @param fieldName
     * @return
     */
    public static View findView(View container, String fieldName) {
        View ret = null;
        if (container != null && fieldName != null) {
            Class<R.id> idClass = R.id.class;
            try {
                Field field = idClass.getDeclaredField(fieldName);
                int id = field.getInt(idClass);

                // 通过静态常量获取整型 int值来查找View
                ret = container.findViewById(id);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 小编推荐的ViewHolder
     */
    private static class AlbumViewHolder {

        public TextView txtTitle;
        public TextView txtMore;

        public ImageView[] albumIcons;// 3张图片，在不同的relativeLayout里
        public TextView[] albumNames;// 3个专辑标题
        public TextView[] trackNames;// 3个曲目名称


    }


}
