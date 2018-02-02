package org.le.tabbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.le.tabbar.interfaces.OnTabBarViewListener;
import org.le.tabbar.interfaces.TabBarFactory;
import org.le.tabbar.javaBean.ItemData;
import org.le.tabbar.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by le on 2018/1/29.
 * 提醒一下关于设置size的问题
 *
 * 1、XML文件里面设置的支持px dp sp
 * 2、set方法设置的只支持dp（文字是sp），我这边自动帮用户转换了（方便适配嘛）
 * 3、文字只支持sp
 * 4、默认size我全部转换成了dp（也是方便适配）
 *
 * 以上有特殊需求可查看相关源码更改
 */

public class TabBarView extends LinearLayout implements TabBarFactory, View.OnClickListener {

    public Context context;//上下文
    public OnTabBarViewListener tabBarViewListener;//点击事件

    public LinearLayout mLinearLayout;//存放菜单的水平布局
    public List<ItemData> listItem;//存放每个item的数据信息，便于处理点击事件的时候设置itemview的选中状态

    public int tabBgColor;//tabbarview的背景颜色
    public int dividerColor;//分割线的颜色
    public int unSelectColor, selectColor;//文字的改变颜色
    public boolean isShowDivider;//是否显示分割线，默认true:显示
    public float dividerHeight;//分割线的高度
    public float textSize;//字体大小
    public int imgWidth, imgHeight;//图片的尺寸
    public int top;//img距离上面的距离
    public int bottom;//文字距离下面的距离
    public int middle;//img和文字的距离

    public int screenWidth;//屏幕宽度,用来计算每个itemview的宽度

    public TabBarView(Context context) {
        super(context);
        init(context);
    }

    public TabBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TabBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context) {
        this.context = context;
        setOrientation(VERTICAL);//强制竖直布局
        setGravity(Gravity.CENTER);//内容居中
        screenWidth = SizeUtils.getScreenWidth(context);
    }

    /**
     * getDimension和getDimensionPixelOffset的功能类似，
     都是获取某个dimen的值，但是如果单位是dp或sp，则需要将其乘以density
     如果是px，则不乘。并且getDimension返回float，getDimensionPixelOffset返回int.
     而getDimensionPixelSize则不管写的是dp还是sp还是px,都会乘以denstiy.
     * @param context
     * @param attrs
     */
    private void init(Context context,@Nullable AttributeSet attrs) {
        this.context = context;
        setOrientation(VERTICAL);//强制竖直布局
        setGravity(Gravity.CENTER);//内容居中
        screenWidth = SizeUtils.getScreenWidth(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tabBarView);
        this.imgWidth = typedArray.getDimensionPixelOffset(R.styleable.tabBarView_imgWidth,SizeUtils.dp2px(context,28));
        this.imgHeight = typedArray.getDimensionPixelOffset(R.styleable.tabBarView_imgHeight,SizeUtils.dp2px(context,28));
        //文字大小的处理比较特殊，由于调用系统的方法会扩大两倍左右，和我们常用的SP单位不符，这里做了特殊处理
        this.textSize = SizeUtils.px2dp(context,typedArray.getDimension(R.styleable.tabBarView_textSize,24));
        this.top = typedArray.getDimensionPixelOffset(R.styleable.tabBarView_marginTop,SizeUtils.dp2px(context,5));
        this.middle = typedArray.getDimensionPixelOffset(R.styleable.tabBarView_middleSize,SizeUtils.dp2px(context,2));
        this.bottom = typedArray.getDimensionPixelOffset(R.styleable.tabBarView_marginBottom,SizeUtils.dp2px(context,5));
        this.unSelectColor = typedArray.getInt(R.styleable.tabBarView_textUnselectColor, Color.GRAY);
        this.selectColor = typedArray.getInt(R.styleable.tabBarView_textSelectColor,Color.BLUE);
        this.tabBgColor = typedArray.getInt(R.styleable.tabBarView_tabBgColor,Color.WHITE);
        this.isShowDivider = typedArray.getBoolean(R.styleable.tabBarView_isShowDivider,true);
        this.dividerHeight = typedArray.getDimension(R.styleable.tabBarView_dividerHeight,SizeUtils.dp2px(context,1));
        this.dividerColor = typedArray.getInt(R.styleable.tabBarView_dividerColor,Color.GRAY);
    }

    private void addView() {
        setBackgroundColor(tabBgColor);//设置背景颜色
        if (isShowDivider) {//显示分割线
            View divderView = new View(context);
            divderView.setBackgroundColor(dividerColor);
            LinearLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) dividerHeight);
            addView(divderView, lp);
        }
        if (mLinearLayout == null) {
            mLinearLayout = new LinearLayout(context);
            mLinearLayout.setOrientation(HORIZONTAL);
        }
        if (listItem == null || listItem.size() == 0) {
            return;
        }
        for (ItemData itemData : listItem) {
            LayoutParams lp = new LayoutParams(screenWidth / listItem.size(), ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(0,top,0,bottom);
            itemData.lin_item.setLayoutParams(lp);
            mLinearLayout.addView(itemData.itemView);
        }
        addView(mLinearLayout);
//        setPadding(0,0,0,SizeUtils.dp2px(context,paddingBottom));
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    /**
     * 将item添加进去 view重新绘制
     */
    public void show(){
        addView();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public TabBarView setImgSize(int width, int height) {
        this.imgWidth = SizeUtils.dp2px(context,width);
        this.imgHeight = SizeUtils.dp2px(context,height);
        return this;
    }

    @Override
    public TabBarView setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    @Override
    public TabBarView setMargins(int top, int middle, int bottom) {
        this.top = SizeUtils.dp2px(context,top);
        this.middle = SizeUtils.dp2px(context,middle);
        this.bottom = SizeUtils.dp2px(context,bottom);
        return this;
    }

    @Override
    public TabBarView setTextChangeColor(int unSelectColor, int selectColor) {
        this.unSelectColor = unSelectColor;
        this.selectColor = selectColor;
        return this;
    }

    @Override
    public TabBarView setTabBarBackgroundColor(int color) {
        this.tabBgColor = color;
        return this;
    }

    @Override
    public TabBarView isShowDivider(boolean isShowDivider) {
        this.isShowDivider = isShowDivider;
        return this;
    }

    @Override
    public TabBarView setDividerHeight(float height) {
        this.dividerHeight = SizeUtils.dp2px(context,height);
        return this;
    }

    @Override
    public TabBarView setDividerColor(int color) {
        this.dividerColor = color;
        return this;
    }

    @Override
    public TabBarView addTabItem(String name, int unSelectImgRes, int selectImgRes) {
        addTabItemView(name, unSelectImgRes, selectImgRes);
        return this;
    }

    /**
     * 处理添加itemview的逻辑
     *
     * @param name
     * @param unSelectImgRes
     * @param selectImgRes
     */
    private void addTabItemView(String name, int unSelectImgRes, int selectImgRes) {
        if (listItem == null) {
            listItem = new ArrayList<>();
        }
        View v = View.inflate(context, R.layout.item_layout, null);
        LinearLayout lin_item = v.findViewById(R.id.lin_item);
        ImageView iv_item = v.findViewById(R.id.iv_item);
        TextView tv_item = v.findViewById(R.id.tv_item);
        listItem.add(new ItemData(name, unSelectColor, selectColor, unSelectImgRes, selectImgRes, iv_item, tv_item, lin_item, v));
        iv_item.setImageResource(unSelectImgRes);
        LayoutParams lp = new LayoutParams(imgWidth, imgHeight);
        lp.setMargins(0,0,0,middle);
        iv_item.setLayoutParams(lp);
        tv_item.setText(name);
        tv_item.setTextSize(textSize);
        tv_item.setTextColor(unSelectColor);
        lin_item.setOnClickListener(this);
    }

    @Override
    public TabBarView setSelectItem(int position) {
        if (listItem == null){
            return this;
        }
        ItemData itemData = listItem.get(position);
        itemData.setSelect(true);
        for (int i = 0; i < listItem.size(); i++) {
            if (position != i) {
                listItem.get(i).setSelect(false);
            }
        }
        return this;
    }

    @Override
    public TabBarView setOnTabBarViewListener(OnTabBarViewListener tabBarViewListener) {
        if (tabBarViewListener != null) {
            this.tabBarViewListener = tabBarViewListener;
        }
        return this;
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < listItem.size(); i++) {
            ItemData itemData = listItem.get(i);
            if (view == itemData.lin_item) {//设置itemView选中，并触发点击事件
                itemData.setSelect(true);
                if (tabBarViewListener != null){
                    tabBarViewListener.OnTabBarClickListener(i, this,itemData, view);
                }
            } else {
                itemData.setSelect(false);
            }
        }
    }
}
