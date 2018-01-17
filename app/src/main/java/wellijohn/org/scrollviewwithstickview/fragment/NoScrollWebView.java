package wellijohn.org.scrollviewwithstickview.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * @author: JiangWeiwei
 * @time: 2017/12/1-17:30
 * @email:
 * @desc:
 */
public class NoScrollWebView extends WebView {

    @SuppressLint("NewApi")
    public NoScrollWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NoScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        post(new Runnable() {
            @Override
            public void run() {
//                Log.d(TAG, "WebView的高度run: " + NoScrollWebView.this.getMeasuredHeight());
                ViewGroup.LayoutParams lp =NoScrollWebView.this.getLayoutParams();
                lp.height =3000;
                NoScrollWebView.this.setLayoutParams(lp);
            }
        });
//        setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Log.d(TAG, "WebView的高度run: " + view.getContentHeight());
//                ViewGroup.LayoutParams lp =view.getLayoutParams();
//                lp.height = view.getContentHeight();
//                view.setLayoutParams(lp);
//            }
//        });
    }

    public NoScrollWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoScrollWebView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }


}