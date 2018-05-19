package wellijohn.org.scrollviewwithstickheader.layoutmanager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * @author: JiangWeiwei
 * @time: 2018/4/2-11:05
 * @email: jiangweiwei@qccr.com
 * @desc:
 */
public class NoSlideGridLayoutManager extends GridLayoutManager {
    private boolean isCanVerScroll;

    public NoSlideGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NoSlideGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public NoSlideGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setCanVerScroll(boolean canVerScroll) {
        isCanVerScroll = canVerScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return isCanVerScroll && super.canScrollVertically();
    }

}
