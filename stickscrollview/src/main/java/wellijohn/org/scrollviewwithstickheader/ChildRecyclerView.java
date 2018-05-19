package wellijohn.org.scrollviewwithstickheader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import wellijohn.org.scrollviewwithstickheader.layoutmanager.NoSlideLinearLayoutManager;
import wellijohn.org.scrollviewwithstickheader.utils.ViewUtil;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:28
 * @email:
 * @desc:
 */
public class ChildRecyclerView extends RecyclerView {
    private static final String TAG = "MyRecyclerView";

    private ScrollViewWithStickHeader mScrollViewWithStickHeader;

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
    }


    float downY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean isRVScroll =
                mScrollViewWithStickHeader.isBottom() || (!mScrollViewWithStickHeader.isBottom() &&
                        !ViewUtil.isScrolledToTop(this));
        if (action == MotionEvent.ACTION_DOWN) {
            downY = ev.getY();
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof NoSlideLinearLayoutManager) {
                ((NoSlideLinearLayoutManager) layoutManager).setCanVerScroll(isRVScroll);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}
