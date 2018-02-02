package org.le.tabbar.interfaces;

import android.support.annotation.ColorInt;

import org.le.tabbar.TabBarView;

/**
 * Created by le on 2018/1/29.
 */

public interface TabBarFactory {

    /**
     * 设置图片的尺寸
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param width  宽度 dp
     * @param height 高度 dp
     * @return
     */
    TabBarView setImgSize(int width, int height);

    /**
     * 设置文字的尺寸
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param textSize 文字的尺寸 sp
     * @return
     */
    TabBarView setTextSize(float textSize);

    /**
     * 设置Tab的padding值
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param top    上边距 dp
     * @param middle 文字图片的距离 dp
     * @param bottom 下边距 dp
     * @return
     */
    TabBarView setMargins(int top, int middle, int bottom);

    /**
     * 设置选中未选中的颜色
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param unSelectColor 未选中的颜色
     * @param selectColor   选中的颜色
     * @return
     */
    TabBarView setTextChangeColor(@ColorInt int unSelectColor, @ColorInt int selectColor);

    /**
     * 设置BottomTabBar的整体背景
     *
     * @param color 背景颜色
     * @return
     */
    TabBarView setTabBarBackgroundColor(@ColorInt int color);

    /**
     * 是否显示分割线
     *
     * @param isShowDivider
     * @return
     */
    TabBarView isShowDivider(boolean isShowDivider);

    /**
     * 设置分割线的高度
     *
     * @param height
     * @return
     */
    TabBarView setDividerHeight(float height);

    /**
     * 设置分割线的颜色
     *
     * @param color
     * @return
     */
    TabBarView setDividerColor(@ColorInt int color);


    /**
     * 添加TabItem
     *
     * @param name
     * @param unSelectImgRes
     * @param selectImgRes
     */
    TabBarView addTabItem(String name, int unSelectImgRes, int selectImgRes);

    /**
     * 设置选中项
     * 此方法必须在addTabItem方法调用结束之后调用
     * @param position
     * @return
     */
    TabBarView setSelectItem(int position);

    /**
     * 设置点击事件
     *
     * @param tabBarViewListener
     */
    TabBarView setOnTabBarViewListener(OnTabBarViewListener tabBarViewListener);

}
