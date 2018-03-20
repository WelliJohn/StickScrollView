package wellijohn.org.scrollviewwithstickview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import wellijohn.org.scrollviewwithstickview.R;
import wellijohn.org.scrollviewwithstickview.adapter.RVRightListAdapter;
import wellijohn.org.scrollviewwithstickview.adapter.ReListAdapter;
import wellijohn.org.scrollviewwithstickview.listener.OnRVItemClickListener;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/9-11:34
 * @email:
 * @desc:
 */
public class TestFragment extends Fragment {
    private static final float HORIZONTALLY_SLIDE_MAX_LENGTH = 10;
    private static final String KEY_FRAGMENT_INT = "KEY_FRAGMENT_INT";
    private RecyclerView mRv;
    float mDownX = 0;
    float mDownY = 0;
    private LinearLayout mLl;
    private WebView mWebview;
    private RecyclerView mChildRecyclerview;
    private RecyclerView mChildRecyclerviewRight;
    private ReListAdapter mReListAdapter;
    private LinearLayoutManager mLlRight;
    private boolean move;
    private boolean mIsLeftTouch = true;
    private int mOldLeftIndex;
    private RecyclerViewListener mRecyclerViewListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.recyclerview, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WebSettings webSettings = mWebview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
//        mWebview.loadUrl("https://juejin.im/post/5a0e907f6fb9a0452a3be487");
        switch (getArguments().getInt(KEY_FRAGMENT_INT)) {
            case 0:
                mWebview.loadUrl("https://www.baidu.com/");
                break;
            case 1:
                mWebview.loadUrl("https://juejin.im/user/58ea34c661ff4b0061a3d3c2");
                break;
            case 2:
                mWebview.loadUrl("https://segmentfault.com/a/1190000011276939");
                break;
        }

        mRecyclerViewListener = new RecyclerViewListener();

        mLlRight = new LinearLayoutManager(getActivity());

        mReListAdapter = new ReListAdapter();
        LinearLayoutManager ll = new LinearLayoutManager(getActivity());
        mChildRecyclerview.setLayoutManager(ll);
        mChildRecyclerview.setAdapter(mReListAdapter);
        mReListAdapter.setOnRVItemClickListener(new OnRVItemClickListener<String>() {
            @Override
            public void onClick(String s, int index) {
                mIsLeftTouch = true;
                if (mOldLeftIndex != index) {
                    int scrollIndex = index * 2;
                    mRecyclerViewListener.setIndex(scrollIndex);
                    int firstItem = mLlRight.findFirstVisibleItemPosition();
                    int lastItem = mLlRight.findLastVisibleItemPosition();

                    //然后区分情况
                    if (scrollIndex <= firstItem) {
                        //当要置顶的项在当前显示的第一个项的前面时
                        mChildRecyclerviewRight.smoothScrollToPosition(scrollIndex);
                    } else if (scrollIndex <= lastItem) {
                        //当要置顶的项已经在屏幕上显示时，计算它离屏幕原点的距离
                        int top = mChildRecyclerviewRight.getChildAt(scrollIndex - firstItem).getTop();
                        mChildRecyclerviewRight.smoothScrollBy(0, top);
                    } else {
                        //当要置顶的项在当前显示的最后一项的后面时
//            llRight.scrollToPositionWithOffset(scrollIndex, 0);
                        mChildRecyclerviewRight.smoothScrollToPosition(scrollIndex);
                        //记录当前需要在RecyclerView滚动监听里面继续第二次滚动
                        move = true;
                    }
                    mOldLeftIndex = index;
                }
            }
        });


        mChildRecyclerviewRight.setLayoutManager(mLlRight);
        mChildRecyclerviewRight.setAdapter(new RVRightListAdapter());
        mChildRecyclerviewRight.addOnScrollListener(mRecyclerViewListener);
    }


    private void initView(View view) {
//        mRv = (RecyclerView) view.findViewById(R.id.rv);
//        mLl = (LinearLayout) view.findViewById(R.id.ll);
        mWebview = (WebView) view.findViewById(R.id.webview);
        mChildRecyclerview = (RecyclerView) view.findViewById(R.id.child_recyclerview);
        mChildRecyclerviewRight = (RecyclerView) view.findViewById(R.id.child_recyclerview_right);


    }

    public static Fragment newInstance(int position) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FRAGMENT_INT, position);
        fragment.setArguments(bundle);
        return fragment;
    }


    class RecyclerViewListener extends RecyclerView.OnScrollListener {

        private int mIndex;


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //在这里进行第二次滚动（最后的距离）
            if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                if (!mIsLeftTouch) {
                    leftLocation();
                }
                mIsLeftTouch = false;
                if (move) {
                    rightRvScoll(recyclerView);
                }
            }
        }

        public void setIndex(int index) {
            this.mIndex = index;
        }


        private void rightRvScoll(RecyclerView paramRv) {
            move = false;
            //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
            int n = mIndex - mLlRight.findFirstVisibleItemPosition();
            if (0 <= n && n < paramRv.getChildCount()) {
                //获取要置顶的项顶部离RecyclerView顶部的距离
                int top = paramRv.getChildAt(n).getTop();
                //最后的移动
                paramRv.smoothScrollBy(0, top);
            }
        }

        private void leftLocation() {
            int firstItem = mLlRight.findFirstVisibleItemPosition();
            int newRightPos = firstItem / 2;
            if (newRightPos != mOldLeftIndex) {
                mReListAdapter.setSelectIndex(newRightPos);
                mOldLeftIndex = newRightPos;
            }
        }

    }
}
