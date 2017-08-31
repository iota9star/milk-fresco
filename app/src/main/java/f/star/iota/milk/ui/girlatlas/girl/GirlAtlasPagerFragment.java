package f.star.iota.milk.ui.girlatlas.girl;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class GirlAtlasPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("精华图片集");
        titles.add("最新图片集");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GirlAtlasFragment.newInstance(Net.GIRL_ATLAS_JH));
        fragments.add(GirlAtlasFragment.newInstance(Net.GIRL_ATLAS_ZX));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_FIXED;
    }
}
