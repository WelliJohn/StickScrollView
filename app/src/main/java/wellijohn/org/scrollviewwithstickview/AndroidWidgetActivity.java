package wellijohn.org.scrollviewwithstickview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import wellijohn.org.scrollviewwithstickview.adapter.TabFragmentAdapter;

/**
 * @author: JiangWeiwei
 * @time: 2018/8/2-10:05
 * @email: jiangweiwei@qccr.com
 * @desc:
 */
public class AndroidWidgetActivity extends AppCompatActivity {
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mToolbarLayout;
    private ImageView mImageview;
    private Toolbar mToolbar;
    private TabLayout mTablayout;
    private ViewPager mVp;
    private TabFragmentAdapter mTabFragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_widget);
        initView();
        initUI();
    }

    private void initUI() {
        mTabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager());
        mVp.setAdapter(mTabFragmentAdapter);
        mVp.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        mVp.setOffscreenPageLimit(3);
        mTablayout.setupWithViewPager(mVp);
    }

    private void initView() {
        mAppBar = findViewById(R.id.app_bar);
        mToolbarLayout = findViewById(R.id.toolbar_layout);
        mImageview = findViewById(R.id.imageview);
        mToolbar = findViewById(R.id.toolbar);
        mTablayout = findViewById(R.id.tablayout);
        mVp = findViewById(R.id.viewpage);
    }
}
