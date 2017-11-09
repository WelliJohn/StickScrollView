package wellijohn.org.scrollviewwithstickview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.TextView;

import wellijohn.org.scrollviewwithstickview.adapter.TabFragmentAdapter;
import wellijohn.org.stickscrollview.ChildScrollView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initUI();
    }

    private void initUI() {
        mVp.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        mVp.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mOrderManagerTabs));
        mVp.setOffscreenPageLimit(3);
        mOrderManagerTabs.setupWithViewPager(mVp);
    }

    private void initView() {
//        mTvStickHeader = (TextView) findViewById(R.id.tv_stick_header);
        mOrderManagerTabs = (TabLayout) findViewById(R.id.order_manager_tabs);
//        mCsv = (ChildScrollView) findViewById(R.id.csv);
        mVp = (ViewPager) findViewById(R.id.vp);

    }
}
