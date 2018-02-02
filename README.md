# TabBarView
❤❀自定义View之tabbar，动态添加item，告别布局烦恼❀❤

不墨迹 直接上效果图

![image](https://github.com/Android-xiaole/TabBarView/blob/master/tabbarview.gif)

PS：应用宝截的GIF画质有点不忍直视。。。下面简单介绍一下用法（后面会写一片博客详细讲解一下）

# 引用方式：

在Android Studio里面配置Jitpack插件

 在项目的根目录下的 build.gradle 文件里面添加
 
     1： 在 dependencies 标签下面添加 

         classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'

     2： 在repositories 标签下面添加

         maven { url "https://jitpack.io" }
         
 最后在app或者你要使用的库的 build.gradle 文件里面的dependencies标签下面添加
 
     compile 'com.github.Android-xiaole:TabBarView:1.0.1'

## 设置数据的方法我全部都抽成了接口

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
    
#  Demo里面写了三个用法实例
   
   1、默认布局 
   
        <org.le.tabbar.TabBarView
        android:id="@+id/tabBarView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"/>
        
   2、XML布局
   
        <org.le.tabbar.TabBarView
        android:id="@+id/tabBarView1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:textSize = "15sp"
        app:imgWidth = "38dp"
        app:imgHeight = "38dp"
        app:marginTop = "10dp"
        app:middleSize = "5dp"
        app:marginBottom = "10dp"
        app:textUnselectColor = "#ff00ff"
        app:textSelectColor = "#ffff00"
        app:tabBgColor = "#0ffff0"
        app:isShowDivider = "true"
        app:dividerHeight = "1px"
        app:dividerColor = "#0f0f0f"
        ></org.le.tabbar.TabBarView>
        
  3、代码设置
  
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
                
## 最后注意： * 提醒一下关于设置size的问题
 
 * 1、XML文件里面设置的支持px dp sp
 * 2、set方法设置的只支持dp（文字是sp），我这边自动帮用户转换了（方便适配嘛）
 * 3、文字只支持sp
 * 4、默认size我全部转换成了dp（也是方便适配）
 *
 * 以上有特殊需求可查看相关源码更改
