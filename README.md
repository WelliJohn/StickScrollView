# StickScrollView
![image](https://github.com/WelliJohn/StickScrollView/blob/master/imgs/snapshot.gif)
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    tools:context="wellijohn.org.scrollviewwithstickview.MainActivity">

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