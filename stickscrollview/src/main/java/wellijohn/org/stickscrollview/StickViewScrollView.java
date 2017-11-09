package wellijohn.org.stickscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-16:57
 * @email:
 * @desc:
 */
public class StickViewScrollView extends ScrollView {

    private boolean isChildToBottom;

    private ChildScrollView mChildScrollView;

    private static final String TAG = "StickViewScrollView";

    public StickViewScrollView(Context context) {
        this(context, null);
    }

    public StickViewScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickViewScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        post(new Runnable() {
            @Override
            public void run() {
                mChildScrollView = findChildScrollView(StickViewScrollView.this);
            }
        });
        setFocusableInTouchMode(false);
    }


    private ChildScrollView findChildScrollView(View paramView) {
        if (paramView instanceof ViewGroup) {
            ViewGroup tempVg = (ViewGroup) paramView;
            int count = tempVg.getChildCount();
            for (int index = 0; index < count; index++) {
                View tempView = tempVg.getChildAt(index);
                if (tempView instanceof ChildScrollView) {
                    mChildScrollView = (ChildScrollView) tempView;
                    return mChildScrollView;
                } else if (tempView instanceof ViewGroup) {
                    View view = findChildScrollView(tempView);
                    if (view != null) {
                        return (ChildScrollView) view;
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
        // 滑动的距离加上本身的高度与子View的高度对比
        if (t + getHeight() >= getChildAt(0).getMeasuredHeight()) {
            // ScrollView滑动到底部
            isChildToBottom = true;
        } else {
            isChildToBottom = false;
        }
    }

}
