package wellijohn.org.scrollviewwithstickheader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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

    private ScrollViewWithStickHeader mScrollViewWithStickHeader;

    private static int minPageSlop;

    private float mDownY;
    private float mScaledTouchSlop;

    public ChildRecyclerView(Context context) {
        this(context, null, 0);
    }

    public ChildRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
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


    public boolean isScrolledToTop() {
        return getLayoutManager() instanceof LinearLayoutManager
                && ((LinearLayoutManager) (getLayoutManager())).findFirstCompletelyVisibleItemPosition() == 0;
    }

    public boolean isScrolledToBottom() {
        return getLayoutManager() instanceof LinearLayoutManager
                && ((LinearLayoutManager) (getLayoutManager())).findLastCompletelyVisibleItemPosition() == (getAdapter().getItemCount() - 1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mScrollViewWithStickHeader == null) return super.onTouchEvent(event);
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            boolean isRVScroll =
                    mScrollViewWithStickHeader.isBottom() || (!mScrollViewWithStickHeader.isBottom() && !isScrolledToTop());
            getParent().requestDisallowInterceptTouchEvent(isRVScroll);
        }
        return super.onTouchEvent(event);
    }

}
