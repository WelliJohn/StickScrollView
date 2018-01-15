package wellijohn.org.scrollviewwithstickview.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wellijohn.org.scrollviewwithstickview.fragment.TestFragment;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/9-11:33
 * @email:
 * @desc:
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "点餐";
        }
        if (position == 1) {
            return "评价";
        }
        if (position == 2) {
            return "商家";
        }
        return "tab" + position;
    }
}
