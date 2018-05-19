**前言，一天在点外卖的时候，注意到饿了么列表页的滑动效果不错，但是觉得其中的手势滑动还是挺复杂的，正好又碰到了在熟悉Touch事件的理解当中，所以就抽空对着饿了么的列表页面尝试写写这个效果，同时增加了双列表的联动效果**
## APK下载地址
![stickscrollview](https://github.com/WelliJohn/StickScrollView/blob/master/imgs/QR_code_258%20.png?raw=true)
## 1.先贴一个实现的效果图
![image](https://github.com/WelliJohn/StickScrollView/blob/master/imgs/%E4%BB%BF%E9%A5%BF%E4%BA%86%E4%B9%88%E5%88%97%E8%A1%A8%E9%A1%B5.gif?raw=true)


## 2.引入
```
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}

dependencies {
    com.github.WelliJohn:StickScrollView:1.1.6
}
```

## 3.界面的布局说明
```
    <wellijohn.org.scrollviewwithstickheader.ScrollViewWithStickHeader
        android:id="@+id/stick_scroll_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:id="@+id/ll"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:descendantFocusability="blocksDescendants"
            android:focusableInTouchMode="true"
            android:orientation="vertical">
            //这里是header部分，可以随便自定义
            </LinearLayout>

            <LinearLayout
                android:id="@+id/ll_stick_list"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

                <android.support.design.widget.TabLayout
                    android:id="@+id/order_manager_tabs"
                    android:layout_width="match_parent"
                    android:layout_height="50dp"
                    android:background="#FFFFFF"
                    tools:tabGravity="fill"
                    tools:tabMode="fixed" />

                <android.support.v4.view.ViewPager
                    android:id="@+id/vp"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content" />
            </LinearLayout>
        </LinearLayout>
    </wellijohn.org.scrollviewwithstickheader.ScrollViewWithStickHeader>
```
比如我们看到的仿饿了么的列表页界面，我们就需要在ViewPager设置Fragment，fragment中是左右两个列表，看下fragment的xml设置：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/ll"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">

    <wellijohn.org.scrollviewwithstickheader.ChildRecyclerView
        android:id="@+id/child_recyclerview"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:background="#EEEEEE" />

    <wellijohn.org.scrollviewwithstickheader.ChildRecyclerView//LinearLayoutManager需要继承NoSlideLinearLayoutManager,GridLayoutManager需要继承NoSlideGridLayoutManager。
        android:id="@+id/child_recyclerview_right"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="#FFFFFF"
        android:layout_weight="3" />
</LinearLayout>
```

## 4.java代码必要设置
```
mStickScrollView.setContentView(mLLStickList);//这个是必须的，mLLStickList就是你想到这个布局再滑动就是下面列表滑动的部分了。
mStickScrollView.setSuspensionView(mViewBottom);//这个是底部的悬浮布局，如果没有可以不设置
```

## 5.注意事项
* ScrollViewWithStickHeader内部目前支持放置ViewPager，ScrollView，RecyclerView，WebView
* ScrollView，RecyclerView，WebView需要对应使用ChildScrollView，ChildRecyclerView，ChildWebView
* 我们在使用的时候，需要调用mStickScrollView.setContentView(mContentView);mLLStickList就是我们需要StickHeader+列表的部分，如果你没有StickHeader的话，那么直接设置列表进来也可以，总之，你想滑动到哪个位置接下来滑动就是单纯下面的部分滑动，那你就把下面的View整体设置为mContentView。刚刚那个的ContentView是id为ll_stick_list的View。
* 另外在这里ScrollViewWithStickHeader增加autoscroll属性，默认是关闭的，如果autoscroll:true的话，在我们手指放开的时候，contentView会判断是否自动滑动到顶部还是隐藏不见。
* 暂不支持weight的使用，因为weight会进行二次绘制，所以如果要实现底部bottom的话，请用RelativeiLayout
* 如果是用RecyclerView的话，LinearLayoutManager需要继承NoSlideLinearLayoutManager,GridLayoutManager需要继承NoSlideGridLayoutManager，new NoSlideLinearLayoutManager(context)。

![自动滚动的效果图](https://github.com/WelliJohn/StickScrollView/blob/master/imgs/scrollsnap.gif?raw=true)

