package wellijohn.org.stickscrollview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:28
 * @email:
 * @desc:
 */
public class ChildScrollView extends ScrollView implements View.OnTouchListener {

    private static final String TAG = "MyRecyclerView";

    private float mLastY = 0;

    private boolean isScrolledToTop = true;
    private boolean isScrolledToBottom = false;

    private StickViewScrollView mStickViewScrollView;

    public ChildScrollView(Context context) {
        this(context, null, 0);
    }

    public ChildScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        post(new Runnable() {
            @Override
            public void run() {
                View tempView = ChildScrollView.this;
                while (!(tempView.getParent() instanceof StickViewScrollView)) {
                    tempView = (View) tempView.getParent();
                }
                mStickViewScrollView = (StickViewScrollView) tempView.getParent();
            }
        });
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

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (getScrollY() == 0) {
            isScrolledToTop = true;
            isScrolledToBottom = false;
        } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
            isScrolledToBottom = true;
            isScrolledToTop = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = false;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Disallow the touch request for parent scroll on touch of child view
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            mLastY = event.getY();
            v.getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (action == MotionEvent.ACTION_MOVE) {
            float nowY = event.getY();
            if (!mStickViewScrollView.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else if (mStickViewScrollView.isBottom() && !isScrolledToBottom && nowY - mLastY < 0) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else if (mStickViewScrollView.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }

        return false;

    }

}
