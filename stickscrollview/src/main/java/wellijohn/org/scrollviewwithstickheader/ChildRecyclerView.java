package wellijohn.org.scrollviewwithstickheader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import wellijohn.org.scrollviewwithstickheader.layoutmanager.NoSlideLinearLayoutManager;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:28
 * @email:
 * @desc:
 */
public class ChildRecyclerView extends RecyclerView {

    private static final String TAG = "MyRecyclerView";

    private float mLastY = 0;

    private ScrollViewWithStickHeader mScrollViewWithStickHeader;

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
                while (!(tempView.getParent() instanceof ScrollViewWithStickHeader)) {
                    tempView = (View) tempView.getParent();
                }
                mScrollViewWithStickHeader = (ScrollViewWithStickHeader) tempView.getParent();
            }
        });
        minPageSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
    }


    private boolean isScrolledToTop() {
        return getLayoutManager() instanceof LinearLayoutManager
                && ((LinearLayoutManager) (getLayoutManager())).findFirstCompletelyVisibleItemPosition() == 0;
    }

    private boolean isScrolledToBottom() {
        return getLayoutManager() instanceof LinearLayoutManager
                && ((LinearLayoutManager) (getLayoutManager())).findLastCompletelyVisibleItemPosition() == (getAdapter().getItemCount() - 1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            LayoutManager layoutManager = getLayoutManager();
            boolean isRVScroll =
                    mScrollViewWithStickHeader.isBottom() || (!mScrollViewWithStickHeader.isBottom() && !isScrolledToTop());
            if (layoutManager instanceof NoSlideLinearLayoutManager) {
                ((NoSlideLinearLayoutManager) layoutManager).setCanVerScroll(isRVScroll);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (mScrollViewWithStickHeader == null) return super.onTouchEvent(event);
//        int action = event.getAction();
////        if (action == MotionEvent.ACTION_DOWN) {
////            boolean isRVScroll =
////                    mScrollViewWithStickHeader.isBottom() || (!mScrollViewWithStickHeader.isBottom() && !isScrolledToTop());
////            mLastX = event.getX();
////            mLastY = event.getY();
////            //首先判断外层ScrollView是否滑动到底部
////            if (isRVScroll) {
////                getParent().requestDisallowInterceptTouchEvent(true);
////                return super.onTouchEvent(event);
////            } else {
////                //拦截事件 本身不处理
////                getParent().requestDisallowInterceptTouchEvent(false);
////                return false;
////            }
////        }
////        if (action == MotionEvent.ACTION_MOVE) {
////            float nowY = event.getY();
////            if (mScrollViewWithStickHeader.isBottom() && !isScrolledToBottom() && nowY - mLastY < 0) {
////                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
////                    getParent().requestDisallowInterceptTouchEvent(true);
////                    return super.onTouchEvent(event);
////                } else {
////                    getParent().requestDisallowInterceptTouchEvent(true);
////                    return false;
////                }
////            } else if (mScrollViewWithStickHeader.isBottom() && !isScrolledToTop() && nowY - mLastY > 0) {
////                if (Math.abs(event.getX() - mLastX) < minPageSlop) {
////                    getParent().requestDisallowInterceptTouchEvent(true);
////                    return super.onTouchEvent(event);
////                } else {
////                    getParent().requestDisallowInterceptTouchEvent(true);
////                    return false;
////                }
////            } else {
////                getParent().requestDisallowInterceptTouchEvent(false);
////            }
////        }
////
////        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
////            getParent().requestDisallowInterceptTouchEvent(false);
////            if (mScrollViewWithStickHeader.isNeedAutoScroll())
////                mScrollViewWithStickHeader.startScrollerTask();
////        }
////
////        return super.onTouchEvent(event);
//
//        if (action == MotionEvent.ACTION_DOWN) {
//            boolean isRVScroll =
//                    mScrollViewWithStickHeader.isBottom() || (!mScrollViewWithStickHeader.isBottom() && !isScrolledToTop());
//            getParent().requestDisallowInterceptTouchEvent(isRVScroll);
//        }
//        return super.onTouchEvent(event);
//    }

}
