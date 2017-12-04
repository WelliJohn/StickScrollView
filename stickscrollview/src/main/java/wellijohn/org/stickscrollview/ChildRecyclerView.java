package wellijohn.org.stickscrollview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:28
 * @email:
 * @desc:
 */
public class ChildRecyclerView extends RecyclerView {

    private static final String TAG = "MyRecyclerView";

    private float mLastY = 0;

    private boolean isScrolledToTop = true;
    private boolean isScrolledToBottom = false;

    private StickViewScrollView mStickViewScrollView;

    private static int minPageSlop;

    private float mLastX;

    public ChildRecyclerView(Context context) {
        this(context, null, 0);
    }

    public ChildRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(false);
        post(new Runnable() {
            @Override
            public void run() {
                View tempView = ChildRecyclerView.this;
                while (!(tempView.getParent() instanceof StickViewScrollView)) {
                    tempView = (View) tempView.getParent();
                }
                mStickViewScrollView = (StickViewScrollView) tempView.getParent();
            }
        });
        minPageSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
    }
//
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        if (getScrollY() == 0) {
//            isScrolledToTop = true;
//            isScrolledToBottom = false;
//        } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
//            isScrolledToBottom = true;
//            isScrolledToTop = false;
//        } else {
//            isScrolledToTop = false;
//            isScrolledToBottom = false;
//        }
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mStickViewScrollView == null) return super.onTouchEvent(event);
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            mLastX = event.getX();
            mLastY = event.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (action == MotionEvent.ACTION_MOVE) {
            float nowY = event.getY();
            if (!mStickViewScrollView.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return super.onTouchEvent(event);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            } else if (mStickViewScrollView.isBottom() && !isScrolledToBottom && nowY - mLastY < 0) {
                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return super.onTouchEvent(event);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            } else if (mStickViewScrollView.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return super.onTouchEvent(event);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }

        return super.onTouchEvent(event);
    }
}
