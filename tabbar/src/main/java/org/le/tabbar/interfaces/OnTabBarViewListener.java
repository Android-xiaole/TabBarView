package org.le.tabbar.interfaces;

import android.view.View;

import org.le.tabbar.TabBarView;
import org.le.tabbar.javaBean.ItemData;

/**
 * Created by le on 2018/1/29.
 */

public interface OnTabBarViewListener {

    void OnTabBarClickListener(int position, TabBarView tabBarView,ItemData itemData, View itemView);
}
