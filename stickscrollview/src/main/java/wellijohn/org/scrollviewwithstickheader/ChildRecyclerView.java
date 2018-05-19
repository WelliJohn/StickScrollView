package wellijohn.org.scrollviewwithstickheader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:28
 * @email:
 * @desc:
 */
public class ChildRecyclerView extends RecyclerView {
    private static final String TAG = "MyRecyclerView";

    public ChildRecyclerView(Context context) {
        this(context, null, 0);
    }

    public ChildRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public boolean isScrolledToTop() {
        return getLayoutManager() instanceof LinearLayoutManager &&
                ((LinearLayoutManager) (getLayoutManager())).findFirstCompletelyVisibleItemPosition() == 0;

    }

}
