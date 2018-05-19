package wellijohn.org.scrollviewwithstickview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wellijohn.org.scrollviewwithstickheader.layoutmanager.NoSlideLinearLayoutManager;
import wellijohn.org.scrollviewwithstickview.MainActivity;
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
public class TestFragment extends LazyFragment {
    private static final String KEY_FRAGMENT_INT = "KEY_FRAGMENT_INT";
    private RecyclerView mChildRecyclerview;
    private RecyclerView mChildRecyclerviewRight;
    private ReListAdapter mReListAdapter;
    private NoSlideLinearLayoutManager mLlRight;
    private boolean move;
    private boolean mIsLeftTouch = true;
    private int mOldLeftIndex;
    private RecyclerViewListener mRecyclerViewListener;
    private MainActivity mParentActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context !=null&&context instanceof MainActivity){
            mParentActivity = (MainActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.recyclerview, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mChildRecyclerviewRight.post(new Runnable() {
//            @Override
//            public void run() {
//                ViewGroup.LayoutParams layoutParams = mChildRecyclerviewRight.getLayoutParams();
//                layoutParams.height = 1400;
//                mChildRecyclerviewRight.setLayoutParams(layoutParams);
//            }
//        });
    }


    private void initView(View view) {
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
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!mIsLeftTouch) {
                leftLocation();
            }
            mIsLeftTouch = false;
        }


        public void setIndex(int index) {
            this.mIndex = index;
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

    @Override
    void lazyFetchData() {
        initUI();
    }

    private void initUI() {
        mRecyclerViewListener = new RecyclerViewListener();

        mLlRight = new NoSlideLinearLayoutManager(getActivity());

        mReListAdapter = new ReListAdapter();
        LinearLayoutManager ll = new NoSlideLinearLayoutManager(getActivity());
        mChildRecyclerview.setLayoutManager(ll);
        mChildRecyclerview.setAdapter(mReListAdapter);
        mReListAdapter.setOnRVItemClickListener(new OnRVItemClickListener<String>() {
            @Override
            public void onClick(String s, int index) {
                mIsLeftTouch = true;
                if (mOldLeftIndex != index) {
                    int scrollIndex = index * 2;
                    mRecyclerViewListener.setIndex(scrollIndex);
                    mLlRight.scrollToPositionWithOffset(scrollIndex, 0);
                    mOldLeftIndex = index;
                }
            }
        });


        mChildRecyclerviewRight.setLayoutManager(mLlRight);
        mChildRecyclerviewRight.setAdapter(new RVRightListAdapter());
        mChildRecyclerviewRight.addOnScrollListener(mRecyclerViewListener);
    }
}
