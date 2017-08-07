package f.star.iota.milk.ui.magmoe.mag;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class MagPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("萌星星");
        titles.add("Cosplay");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MagFragment.newInstance(Net.MAG_MOE_STAR));
        fragments.add(MagFragment.newInstance(Net.MAG_MOE_COSPLAY));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_FIXED;
    }
}
