package f.star.iota.milk.ui.umei.u;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class UMEIFragment extends ScrollImageFragment<UMEIPresenter, UMEIAdapter> {


    public static UMEIFragment newInstance(String url) {
        UMEIFragment fragment = new UMEIFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        bundle.putString("page_suffix", ".htm");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected UMEIPresenter getPresenter() {
        return new UMEIPresenter(this);
    }

    @Override
    protected UMEIAdapter getAdapter() {
        return new UMEIAdapter();
    }
}
