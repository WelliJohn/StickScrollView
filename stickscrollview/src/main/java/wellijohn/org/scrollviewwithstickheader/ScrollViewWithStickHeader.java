package wellijohn.org.scrollviewwithstickheader;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Arrays;

import wellijohn.org.scrollviewwithstickheader.utils.UIUtil;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-16:57
 * @email:
 * @desc:
 */
public class ScrollViewWithStickHeader extends ScrollView {

    private final int minPageSlop;
    private boolean isChildToBottom;

    private View mAutoFillView;

    private Runnable scrollerTask;


    private static final String TAG = "StickViewScrollView";

    private boolean mIsAutoScrollChild;

    private boolean mIsNeedAutoScroll;

    private int initialPosition;
    private int newCheck = 50;
    private boolean mIsVisible;

    private Rect rect = new Rect();
    private ArrayList<View> mSuspensionViews = new ArrayList<>();

    private ChildRecyclerView mChildRecyclerView;

    private boolean mIsDragging;
    private float mDownY;
    private int mScaledTouchSlop;
    private float mCurrY;


    public ScrollViewWithStickHeader(Context context) {
        this(context, null);
    }

    public ScrollViewWithStickHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollViewWithStickHeader(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        minPageSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
        TypedArray ta = context.getResources().obtainAttributes(attrs, R.styleable.ScrollViewWithStickHeader);
        mIsNeedAutoScroll = ta.getBoolean(R.styleable.ScrollViewWithStickHeader_autoscroll, false);
        ta.recycle();

        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        scrollerTask = new Runnable() {

            public void run() {
                if (mAutoFillView == null) return;
                int newPosition = getScrollY();
                if (initialPosition - newPosition == 0) {//has stopped
                    if (!mIsVisible) return;

                    if (mIsAutoScrollChild) {
                        ObjectAnimator.ofInt(ScrollViewWithStickHeader.this, "scrollY",
                                getChildAt(0).getHeight() - mAutoFillView.getHeight()).setDuration(100).start();
                    } else {
                        ObjectAnimator.ofInt(ScrollViewWithStickHeader.this, "scrollY",
                                (getChildAt(0).getHeight() - mAutoFillView.getHeight() * 2)).setDuration(100).setDuration(200).start();

                    }
                } else {
                    initialPosition = getScrollY();
                    ScrollViewWithStickHeader.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };


        post(new Runnable() {
            @Override
            public void run() {
                checkNotNull();
                ViewGroup.LayoutParams lp = mAutoFillView.getLayoutParams();
                int[] stickViewScrollViewCoor = new int[2];
                ScrollViewWithStickHeader.this.getLocationOnScreen(stickViewScrollViewCoor);

                int contentHeight = UIUtil.getScreenHeight(getContext()) - stickViewScrollViewCoor[1];
                lp.height = contentHeight;
                mAutoFillView.setLayoutParams(lp);

                int[] viewPageCoor = new int[2];
                int[] autoFillCoor = new int[2];
                View tempViewPager = getView();

                tempViewPager.getLocationOnScreen(viewPageCoor);
                mAutoFillView.getLocationOnScreen(autoFillCoor);
                int tempStickHeight = viewPageCoor[1] - autoFillCoor[1];

                int vpHeight = contentHeight - tempStickHeight - getSuspensionHeight();
                ViewGroup.LayoutParams vpLp = tempViewPager.getLayoutParams();
                vpLp.height = vpHeight;
                tempViewPager.setLayoutParams(vpLp);

                mChildRecyclerView = findChildView(ScrollViewWithStickHeader.this, ChildRecyclerView.class);

            }
        });
    }


    public boolean isScroll() {
        return Math.abs(mCurrY - mDownY) > mScaledTouchSlop;
    }


    private void checkNotNull() {
        if (mAutoFillView == null)
            throw new IllegalStateException("StickView can not be null,Please check you have set");
    }

    private View getView() {
        View tempViewPager = findChildView(ScrollViewWithStickHeader.this, ViewPager.class);

        if (tempViewPager == null) {
            tempViewPager = findChildView(ScrollViewWithStickHeader.this, RecyclerView.class);
        }

        if (tempViewPager == null) {
            tempViewPager = findChildView(ScrollViewWithStickHeader.this, ScrollView.class);
        }

        if (tempViewPager == null) {
            tempViewPager = findChildView(ScrollViewWithStickHeader.this, WebView.class);
        }

        if (tempViewPager == null)
            throw new IllegalStateException("ScrollViewWithStickHeader must" +
                    "use with ViewPager||ChildScrollView||ChildRecyclerView||ChildWebView");
        return tempViewPager;
    }


    public void setContentView(View paramAutoFillView) {
        this.mAutoFillView = paramAutoFillView;
    }

    public void setSuspensionView(View... paramView) {
        mSuspensionViews.addAll(Arrays.asList(paramView));
    }


    public void startScrollerTask() {
        initialPosition = getScrollY();
        ScrollViewWithStickHeader.this.postDelayed(scrollerTask, newCheck);
    }

    private <T extends View> T findChildView(View paramView, Class<T> t) {
        View childView;
        if (paramView instanceof ViewGroup) {
            ViewGroup tempVg = (ViewGroup) paramView;
            int count = tempVg.getChildCount();
            for (int index = 0; index < count; index++) {
                View tempView = tempVg.getChildAt(index);
                if (t.isInstance(tempView)) {
                    childView = tempView;
                    return (T) childView;
                } else if (tempView instanceof ViewGroup) {
                    View view = findChildView(tempView, t);
                    if (view != null) {
                        return (T) view;
                    }
                }
            }
        }
        return null;
    }

    public boolean isBottom() {
        return isChildToBottom;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mAutoFillView == null) return;
        isChildToBottom = t + getHeight() >= getChildAt(0).getMeasuredHeight();

        mIsVisible = mAutoFillView.getGlobalVisibleRect(rect);

        if (mIsVisible) {
            mIsAutoScrollChild = rect.height() > (mAutoFillView.getHeight() / 3);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (mIsNeedAutoScroll)
                    startScrollerTask();
                break;
        }
        return super.onTouchEvent(ev);

    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                if (mChildRecyclerView != null && mChildRecyclerView.isScrolledToTop() && !isBottom()) {
//                    return true;
//                }
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    public boolean isNeedAutoScroll() {
        return mIsNeedAutoScroll;
    }

    public int getSuspensionHeight() {
        int height = 0;
        for (View view : mSuspensionViews) {
            height += view.getHeight();
        }
        return height;
    }

}
