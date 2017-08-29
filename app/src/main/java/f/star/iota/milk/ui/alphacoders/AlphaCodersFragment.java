package f.star.iota.milk.ui.alphacoders;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class AlphaCodersFragment extends ScrollImageFragment<AlphaCodersPresenter, AlphaCodersAdapter> {

    public static AlphaCodersFragment newInstance(String url) {
        AlphaCodersFragment fragment = new AlphaCodersFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected AlphaCodersPresenter getPresenter() {
        return new AlphaCodersPresenter(this);
    }

    @Override
    protected AlphaCodersAdapter getAdapter() {
        return new AlphaCodersAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
