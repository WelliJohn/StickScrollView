package wellijohn.org.scrollviewwithstickview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import wellijohn.org.scrollviewwithstickheader.ChildRecyclerView;
import wellijohn.org.scrollviewwithstickheader.ChildScrollView;
import wellijohn.org.scrollviewwithstickheader.ScrollViewWithStickHeader;
import wellijohn.org.scrollviewwithstickview.adapter.ReListAdapter;
import wellijohn.org.scrollviewwithstickview.adapter.TabFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView mTvStickHeader;
    private ScrollView mScrollviewChild;
    private RecyclerView mRv;
    private ReListAdapter mReListAdapter;

    private static final String TAG = "MainActivity";
    private TabLayout mOrderManagerTabs;
    private ChildScrollView mCsv;
    private ViewPager mVp;
    private TabFragmentAdapter mTabFragmentAdapter;
    private WebView mWebview;
    private LinearLayout mLLStickList;
    private Button mButton;
    private ScrollViewWithStickHeader mStickScrollView;
    private LinearLayout mLl;
    private ChildRecyclerView mCrv;
    private View mViewBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initUI();
        mStickScrollView.setContentView(mLLStickList);
        mStickScrollView.setSuspensionView(mViewBottom);
    }

    private void initUI() {

        mTabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager());
        mVp.setAdapter(mTabFragmentAdapter);
        mVp.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mOrderManagerTabs));
        mVp.setOffscreenPageLimit(3);
        mOrderManagerTabs.setupWithViewPager(mVp);
//        WebSettings webSettings = mWebview.getSettings();
//        //设置WebView属性，能够执行Javascript脚本
//        webSettings.setJavaScriptEnabled(true);
//        mWebview.loadUrl("https://segmentfault.com/u/wellijhon_58622d2f61c2d");

//        Log.d(TAG, "initUI: " + (mLl.getDescendantFocusability() == ViewGroup.FOCUS_BLOCK_DESCENDANTS));
    }

    private void initView() {
//        mTvStickHeader = (TextView) findViewById(R.id.tv_stick_header);
        mOrderManagerTabs = (TabLayout) findViewById(R.id.order_manager_tabs);
//        mCsv = (ChildScrollView) findViewById(R.id.csv);
        mVp = (ViewPager) findViewById(R.id.vp);
        mLLStickList = (LinearLayout) findViewById(R.id.ll_stick_list);

        mWebview = (WebView) findViewById(R.id.webview);
        mButton = (Button) findViewById(R.id.button);
        mStickScrollView = (ScrollViewWithStickHeader) findViewById(R.id.stick_scroll_view);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mViewBottom = (View) findViewById(R.id.tv_bottom);
//        mCrv = (ChildRecyclerView) findViewById(R.id.crv);


    }

}
