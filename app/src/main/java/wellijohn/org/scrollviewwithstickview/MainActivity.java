package wellijohn.org.scrollviewwithstickview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvStickHeader;
    private ScrollView mScrollviewChild;
    private RecyclerView mRv;
    private ReListAdapter mReListAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvStickHeader = (TextView) findViewById(R.id.tv_stick_header);
    }
}
