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

import wellijohn.org.scrollviewwithstickview.R;
import wellijohn.org.scrollviewwithstickview.adapter.RVRightListAdapter;

/**
 * @author: JiangWeiwei
 * @time: 2018/8/2-10:36
 * @email: jiangweiwei@qccr.com
 * @desc:
 */
public class RecyclerViewFragment extends LazyFragment {
    private static final String KEY_FRAGMENT_INT = "KEY_FRAGMENT_INT";

    private RecyclerView mRv;
    private RecyclerView mRvRight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.just_recyclerview, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(new RVRightListAdapter());

        mRvRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRight.setAdapter(new RVRightListAdapter());
    }

    @Override
    void lazyFetchData() {
//        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRv.setAdapter(new RVRightListAdapter());
    }


    private void initView(View view) {
        mRv = view.findViewById(R.id.rv);
        mRvRight = view.findViewById(R.id.rv_right);
    }

    public static Fragment newInstance(int position) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FRAGMENT_INT, position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
