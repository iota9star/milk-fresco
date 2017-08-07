package f.star.iota.milk.ui.taotutt.tutt;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class TUTTFragment extends ScrollImageFragment<TUTTPresenter, TUTTAdapter> {

    public static TUTTFragment newInstance(String url) {
        TUTTFragment fragment = new TUTTFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url.replace(".html", "_"));
        bundle.putString("page_suffix", ".html");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected TUTTPresenter getPresenter() {
        return new TUTTPresenter(this);
    }

    @Override
    protected TUTTAdapter getAdapter() {
        return new TUTTAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
