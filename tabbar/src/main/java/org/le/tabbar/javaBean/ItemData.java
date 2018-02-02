package org.le.tabbar.javaBean;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by le on 2018/1/29.
 * 存放itemview相关的数据及UI信息 方便操作view的选中
 */

public class ItemData {

    public String name;
    public int unSelectColor;
    public int selectColor;
    public int unSelectImgRes;
    public int selectImgRes;
    public ImageView iv_item;
    public TextView tv_item;
    public LinearLayout lin_item;
    public View itemView;

    public ItemData(String name, int unSelectColor, int selectColor, int unSelectImgRes, int selectImgRes, ImageView iv_item, TextView tv_item, LinearLayout lin_item, View itemView) {
        this.name = name;
        this.unSelectColor = unSelectColor;
        this.selectColor = selectColor;
        this.unSelectImgRes = unSelectImgRes;
        this.selectImgRes = selectImgRes;
        this.iv_item = iv_item;
        this.tv_item = tv_item;
        this.lin_item = lin_item;
        this.itemView = itemView;
    }

    public void setSelect(boolean select){
        if (select){
            iv_item.setImageResource(selectImgRes);
            tv_item.setTextColor(selectColor);
        }else{
            iv_item.setImageResource(unSelectImgRes);
            tv_item.setTextColor(unSelectColor);
        }
    }
}
