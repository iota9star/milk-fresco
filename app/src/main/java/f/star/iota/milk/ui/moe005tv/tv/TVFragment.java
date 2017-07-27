package f.star.iota.milk.ui.moe005tv.tv;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class TVFragment extends FixedImageFragment<TVPresenter, TVAdapter> {

    public static TVFragment newInstance(String url) {
        TVFragment fragment = new TVFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected TVPresenter getPresenter() {
        return new TVPresenter(this);
    }

    @Override
    protected TVAdapter getAdapter() {
        return new TVAdapter();
    }

}
