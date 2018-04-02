package wellijohn.org.scrollviewwithstickheader;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * @author: JiangWeiwei
 * @time: 2018/4/2-10:56
 * @email: jiangweiwei@qccr.com
 * @desc:
 */
public class NoSlideLayoutManager extends LinearLayoutManager {

    private boolean isCanVerScroll;

    public NoSlideLayoutManager(Context context) {
        super(context);
    }


    public void setCanVerScroll(boolean canVerScroll) {
        isCanVerScroll = canVerScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return isCanVerScroll && super.canScrollVertically();
    }
}
