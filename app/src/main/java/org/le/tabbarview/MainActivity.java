package org.le.tabbarview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.le.tabbar.TabBarView;
import org.le.tabbar.interfaces.OnTabBarViewListener;
import org.le.tabbar.javaBean.ItemData;

public class MainActivity extends AppCompatActivity implements OnTabBarViewListener {

    private Toast toast;
    private TabBarView tabBarView,tabBarView1,tabBarView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        tabBarView = findViewById(R.id.tabBarView);
        tabBarView.addTabItem("主页",R.drawable.item,R.drawable.item_blue)
                .addTabItem("发现",R.drawable.item,R.drawable.item_blue)
                .setSelectItem(0)
                .setOnTabBarViewListener(this)
                .show();

        tabBarView1 = findViewById(R.id.tabBarView1);
        tabBarView1.addTabItem("商城",R.drawable.item,R.drawable.item_blue)
                .addTabItem("主页",R.drawable.item,R.drawable.item_blue)
                .addTabItem("发现",R.drawable.item,R.drawable.item_blue)
//                .addTabItem("我的",R.drawable.item,R.drawable.item_blue)
                .setSelectItem(1)
                .setOnTabBarViewListener(this)
                .show();

        tabBarView2 = findViewById(R.id.tabBarView2);
        tabBarView2.setTextChangeColor(Color.GRAY,Color.YELLOW)
                .setImgSize(28,28)
                .setMargins(5,2,5)
                .setTextSize(12)
                .setTabBarBackgroundColor(Color.RED)
                .setDividerColor(Color.BLACK)
                .setDividerHeight(1)
                .isShowDivider(true)
                .addTabItem("商城",R.drawable.item,R.drawable.item_blue)
                .addTabItem("主页",R.drawable.item,R.drawable.item_blue)
                .addTabItem("发现",R.drawable.item,R.drawable.item_blue)
                .addTabItem("我的",R.drawable.item,R.drawable.item_blue)
                .setSelectItem(2)
                .setOnTabBarViewListener(this)
                .show();
    }

    @Override
    public void OnTabBarClickListener(int position, TabBarView tabBarView,ItemData itemData, View itemView) {
        switch (tabBarView.getId()){
            case R.id.tabBarView:
                toast.setText("TabBarView-点击了第"+(position+1)+"个item");
                toast.show();
                break;
            case R.id.tabBarView1:
                toast.setText("TabBarView1-点击了第"+(position+1)+"个item");
                toast.show();
                break;
            case R.id.tabBarView2:
                toast.setText("TabBarView2-点击了第"+(position+1)+"个item");
                toast.show();
                break;
        }
    }
}
