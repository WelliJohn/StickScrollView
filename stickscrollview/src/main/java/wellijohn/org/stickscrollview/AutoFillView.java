package wellijohn.org.stickscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/7-11:48
 * @email:
 * @desc:
 */
public class AutoFillView extends FrameLayout {
    public AutoFillView(@NonNull Context context) {
        this(context, null);
    }

    public AutoFillView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFillView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        AutoFillView.this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View tempView = AutoFillView.this;
                while (!(tempView.getParent() instanceof ContentFrameLayout)) {
                    tempView = (View) tempView.getParent();
                }
                if (tempView.getParent() instanceof View) {
                    ViewGroup.LayoutParams lp = AutoFillView.this.getLayoutParams();
                    lp.height = ((View) tempView.getParent()).getHeight();
                    AutoFillView.this.setLayoutParams(lp);
                }
            }
        });
    }
}
