package f.star.iota.milk.ui.mmjpg.jpg;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class JpgFragment extends ScrollImageFragment<JpgPresenter, JpgAdapter> {

    public static JpgFragment newInstance(String url) {
        JpgFragment fragment = new JpgFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected JpgPresenter getPresenter() {
        return new JpgPresenter(this);
    }

    @Override
    protected JpgAdapter getAdapter() {
        return new JpgAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
