package wellijohn.org.scrollviewwithstickview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @author: JiangWeiwei
 * @time: 2018/3/15-12:24
 * @email: jiangweiwei@qccr.com
 * @desc:
 */
public abstract class LazyFragment extends Fragment {
    boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    boolean hasFetchData; // 标识已经触发过懒加载数据


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {//当当前为显示页面时
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyFetchDataIfPrepared();
    }

    void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true; //已加载过数据
            lazyFetchData();
        }
    }

    abstract void lazyFetchData();
}
