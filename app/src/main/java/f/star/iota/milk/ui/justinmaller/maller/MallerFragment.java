package f.star.iota.milk.ui.justinmaller.maller;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class MallerFragment extends FixedImageFragment<MallerPresenter, MallerAdapter> {

    public static MallerFragment newInstance(String url) {
        MallerFragment fragment = new MallerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MallerPresenter getPresenter() {
        return new MallerPresenter(this);
    }

    @Override
    protected MallerAdapter getAdapter() {
        return new MallerAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
