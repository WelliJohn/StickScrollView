package wellijohn.org.stickscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
    }


}
