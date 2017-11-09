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
import android.widget.LinearLayout;

import wellijohn.org.scrollviewwithstickview.R;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/9-11:34
 * @email: jiangweiwei@qccr.com
 * @desc:
 */
public class TestFragment extends Fragment {
    private static final float HORIZONTALLY_SLIDE_MAX_LENGTH = 10;
    private RecyclerView mRv;
    float mDownX = 0;
    float mDownY = 0;
    private LinearLayout mLl;

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
        LinearLayoutManager ll = new LinearLayoutManager(getActivity());
//        mRv.setLayoutManager(ll);
//        mRv.setAdapter(new ReListAdapter());
//        mRv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN:
//                        mDownX = event.getX();
//                        mDownY = event.getY();
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        v.getParent().requestDisallowInterceptTouchEvent(false);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if ((Math.abs(event.getY() - mDownY) > 100)) {
//                            v.getParent().requestDisallowInterceptTouchEvent(true);
//                        } else {
//                            v.getParent().requestDisallowInterceptTouchEvent((Math.abs(event.getX() - mDownX) < HORIZONTALLY_SLIDE_MAX_LENGTH));
//                        }
//                        break;
//                }
//                return false;
//            }

//        });

    }

    private void initView(View view) {
//        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mLl = (LinearLayout) view.findViewById(R.id.ll);
    }
}
