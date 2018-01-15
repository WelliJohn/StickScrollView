package wellijohn.org.stickscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/7-11:48
 * @email:
 * @desc:
 */
public class AutoFillView extends FrameLayout {
    private ViewPager mViewPager;

    public AutoFillView(@NonNull Context context) {
        this(context, null);
    }

    public AutoFillView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFillView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        AutoFillView.this.post(new Runnable() {
//            @Override
//            public void run() {
//                View tempView = AutoFillView.this;
//                while (!(tempView.getParent() instanceof StickViewScrollView)) {
//                    tempView = (View) tempView.getParent();
//                }
//                if (tempView.getParent() instanceof View) {
//                    ViewGroup.LayoutParams lp = AutoFillView.this.getLayoutParams();
//                    int contentHeight = lp.height = ((View) tempView.getParent()).getHeight();
//                    AutoFillView.this.setLayoutParams(lp);
//
//                    int[] viewPageCoor = new int[2];
//                    int[] autoFillCoor = new int[2];
//                    mViewPager = findViewPager(AutoFillView.this);
//                    mViewPager.getLocationInWindow(viewPageCoor);
//                    AutoFillView.this.getLocationOnScreen(autoFillCoor);
//                    int tempStickHeight = viewPageCoor[1] - autoFillCoor[1];
//
//                    ViewGroup.LayoutParams vpLp = mViewPager.getLayoutParams();
//                    vpLp.height = contentHeight - tempStickHeight;
//                    mViewPager.setLayoutParams(vpLp);
//                }
//            }
//        });
    }


    private ViewPager findViewPager(View paramView) {
        if (paramView instanceof ViewGroup) {
            ViewGroup tempVg = (ViewGroup) paramView;
            int count = tempVg.getChildCount();
            for (int index = 0; index < count; index++) {
                View tempView = tempVg.getChildAt(index);
                if (tempView instanceof ViewPager) {
                    mViewPager = (ViewPager) tempView;
                    return mViewPager;
                } else if (tempView instanceof ViewGroup) {
                    View view = findViewPager(tempView);
                    if (view != null) {
                        return (ViewPager) view;
                    }
                }
            }
        }
        return null;
    }
}
