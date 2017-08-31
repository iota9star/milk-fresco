package f.star.iota.milk.ui.akabe.a;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class AKabeFragment extends ScrollImageFragment<AKabePresenter, AKabeAdapter> {


    public static AKabeFragment newInstance(String url) {
        AKabeFragment fragment = new AKabeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        bundle.putString("page_suffix", ".php");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected AKabePresenter getPresenter() {
        return new AKabePresenter(this);
    }

    @Override
    protected AKabeAdapter getAdapter() {
        return new AKabeAdapter();
    }
}
