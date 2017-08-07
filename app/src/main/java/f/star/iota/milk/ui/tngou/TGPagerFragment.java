package f.star.iota.milk.ui.tngou;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.Net;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseFragment;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.TitlePagerAdapter;
import f.star.iota.milk.util.SnackbarUtils;


public class TGPagerFragment extends BaseFragment implements PVContract.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private TabLayout mTabLayout;
    private List<Fragment> fragments;
    private TGClassifyPresenter mPresenter;

    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = new TGClassifyPresenter(this);
        mPresenter.get(Net.TNGOU_CLASSIFY);
        mTabLayout = getActivity().findViewById(R.id.tab_layout);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager;
    }


    @Override
    public void success(Object result) {
        List beans = (List) result;
        List<String> titles = new ArrayList<>();
        fragments = new ArrayList<>();
        for (Object bean : beans) {
            String title = ((ClassifyBean.TngouBean) bean).getTitle();
            int id = ((ClassifyBean.TngouBean) bean).getId();
            titles.add(title);
            TGFragment fragment = TGFragment.newInstance(Net.TNGOU_LIST + id + "&page=");
            fragments.add(fragment);
        }
        TitlePagerAdapter adapter = new TitlePagerAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void error(String error) {
        SnackbarUtils.create(getActivity(), error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTabLayout != null) {
            mTabLayout.removeAllTabs();
            mTabLayout.setVisibility(View.GONE);
        }
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void changeRecyclerViewSpanCount() {
        int selectedTabPosition = mTabLayout.getSelectedTabPosition();
        ((BaseFragment) fragments.get(selectedTabPosition)).changeRecyclerViewSpanCount();
    }

    @Override
    public void scrollToTop() {
        int selectedTabPosition = mTabLayout.getSelectedTabPosition();
        ((BaseFragment) fragments.get(selectedTabPosition)).scrollToTop();
    }
}
