package wellijohn.org.scrollviewwithstickheader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:28
 * @email:
 * @desc:
 */
public class ChildScrollView extends ScrollView {

    private static final String TAG = "MyRecyclerView";

    private float mLastY = 0;

    private boolean isScrolledToTop = true;
    private boolean isScrolledToBottom = false;

    private ScrollViewWithStickHeader mScrollViewWithStickHeader;

    private int minSlop;
    private int minPageSlop;

    private float mLastX;

    private boolean mIsDraging;

    public ChildScrollView(Context context) {
        this(context, null, 0);
    }

    public ChildScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(false);
        post(new Runnable() {
            @Override
            public void run() {
                View tempView = ChildScrollView.this;
                while (!(tempView.getParent() instanceof ScrollViewWithStickHeader)) {
                    tempView = (View) tempView.getParent();
                }
                mScrollViewWithStickHeader = (ScrollViewWithStickHeader) tempView.getParent();
            }
        });
        minSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
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

    float downY;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mScrollViewWithStickHeader == null) {
            return super.onTouchEvent(event);
        }
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            Log.d(Constant.KEY_TAG, "dispatchTouchEvent: ACTION_DOWN");
            mLastX = event.getX();
            mLastY = event.getY();
            //首先判断外层ScrollView是否滑动到底部
            if (mScrollViewWithStickHeader.isBottom()) {
                getParent().requestDisallowInterceptTouchEvent(true);
                return super.dispatchTouchEvent(event);
            } /*else {
			//拦截事件 本身不处理
			getParent().requestDisallowInterceptTouchEvent(false);
			return false;
		}*/
            return super.dispatchTouchEvent(event);
        }
        if (action == MotionEvent.ACTION_MOVE) {
            Log.d(Constant.KEY_TAG, "dispatchTouchEvent: ACTION_MOVE");
            float nowY = event.getY();
            if (!mScrollViewWithStickHeader.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return super.dispatchTouchEvent(event);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            } else if (mScrollViewWithStickHeader.isBottom() && !isScrolledToBottom && nowY - mLastY < 0) {
                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return super.dispatchTouchEvent(event);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            } else if (mScrollViewWithStickHeader.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return super.dispatchTouchEvent(event);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            Log.d(Constant.KEY_TAG, "dispatchTouchEvent: ACTION_UP/ACTION_CANCEL");
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE) && (mIsDraging)) {
            return true;
        }

        if (super.onInterceptTouchEvent(ev)) {
            return true;
        }
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mIsDraging = false;
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                if (Math.abs(moveY - downY) > minSlop) {
                    requestDisallowInterceptTouchEvent(true);
                    mIsDraging = true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsDraging = false;
                break;

        }
        return mIsDraging;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(Constant.KEY_TAG, "onTouchEvent: ");
//        if (mScrollViewWithStickHeader == null) return super.onTouchEvent(event);
//        int action = event.getAction();
//
//        if (action == MotionEvent.ACTION_DOWN) {
//            mLastX = event.getX();
//            mLastY = event.getY();
//            //首先判断外层ScrollView是否滑动到底部
//            if (mScrollViewWithStickHeader.isBottom()) {
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return super.onTouchEvent(event);
//            } else {
//                //拦截事件 本身不处理
//                getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        }
//        if (action == MotionEvent.ACTION_MOVE) {
//            float nowY = event.getY();
//            if (!mScrollViewWithStickHeader.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
//                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                    return super.onTouchEvent(event);
//                } else {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                    return false;
//                }
//            } else if (mScrollViewWithStickHeader.isBottom() && !isScrolledToBottom && nowY - mLastY < 0) {
//                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                    return super.onTouchEvent(event);
//                } else {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                    return false;
//                }
//            } else if (mScrollViewWithStickHeader.isBottom() && !isScrolledToTop && nowY - mLastY > 0) {
//                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                    return super.onTouchEvent(event);
//                } else {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                    return false;
//                }
//            } else {
//                getParent().requestDisallowInterceptTouchEvent(false);
//            }
//        }
//
//        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
//            getParent().requestDisallowInterceptTouchEvent(false);
//        }

        return super.onTouchEvent(event);
    }
}
