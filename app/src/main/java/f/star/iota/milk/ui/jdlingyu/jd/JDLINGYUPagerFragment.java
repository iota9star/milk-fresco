package f.star.iota.milk.ui.jdlingyu.jd;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PagerFragment;
import f.star.iota.milk.base.TitlePagerAdapter;


public class JDLINGYUPagerFragment extends PagerFragment {

    @Override
    protected TitlePagerAdapter getPagerAdapter() {
        List<String> titles = new ArrayList<>();
        titles.add("最新");
        titles.add("专题");
        titles.add("特点");
        titles.add("弄潮");
        titles.add("COS");
        titles.add("妹子图");
        titles.add("Hentai好物");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_ALL));
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_ZHUANTI));
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_TEDIAN));
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_NONGCAO));
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_COSPLAY));
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_MZITU));
        fragments.add(JDLINGYUFragment.newInstance(Net.JDLINGYU_HENTAI));
        return new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    protected int setTabMode() {
        return TabLayout.MODE_SCROLLABLE;
    }
}
