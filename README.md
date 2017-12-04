# StickScrollView
![image](https://github.com/WelliJohn/StickScrollView/blob/master/imgs/scrollsnap.gif)
## 使用说明
compile 'wellijohn.org:stickscrollview:0.0.5'<br>
另外界面的布局如下所示
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    tools:context="wellijohn.org.scrollviewwithstickview.MainActivity">

    //这个view是用来最外层滚动的view
    <wellijohn.org.stickscrollview.StickViewScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:focusableInTouchMode="true"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="400dp"
                android:background="@color/colorPrimaryDark" />

            //这个view是用来铺满屏幕的，外层的View滑动到该View的位置就会停止滑动
            //如果你的Activity的不是继承AppCompactActivity的话，那么需要对这个view的高度进行重新设置。
            <wellijohn.org.stickscrollview.AutoFillView
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <LinearLayout
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


                    //一般我们用ViewPager中嵌套Fragment，Fragment中在嵌套ScrollView，这个时候，如果你想Fragment的内容滚动的话，
                    //需要在Fragment的根布局中添加ChildScrollView，或者是ChildWebView,或者是ChildRecyclerView
                    <android.support.v4.view.ViewPager
                        android:id="@+id/vp"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content" />


                </LinearLayout>
            </wellijohn.org.stickscrollview.AutoFillView>
        </LinearLayout>

    </wellijohn.org.stickscrollview.StickViewScrollView>


</LinearLayout>

```

## 如下所示，是一个嵌套在viewpager的fragment的布局

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/ll"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    //就看你再childView中嵌套的是哪种控件的view，如果是ScrollView的话，那就ChildScrollView
    <wellijohn.org.stickscrollview.ChildScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:focusableInTouchMode="true"
            android:orientation="vertical">

        </LinearLayout>

    </wellijohn.org.stickscrollview.ChildScrollView>

    //如果是WebView的话，ChildWebView
    <wellijohn.org.stickscrollview.ChildWebView
                android:id="@+id/webview"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>

    //如果是列表的话，那就ChildRecyclerView，看情况使用
    <wellijohn.org.stickscrollview.ChildRecyclerView
        android:id="@+id/child_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>
```