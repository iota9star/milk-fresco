package f.star.iota.milk.ui.kuvva.ku;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class KuvvaFragment extends ScrollImageFragment<KuvvaPresenter, KuvvaAdapter> {


    public static KuvvaFragment newInstance(String url) {
        KuvvaFragment fragment = new KuvvaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected KuvvaPresenter getPresenter() {
        return new KuvvaPresenter(this);
    }

    @Override
    protected KuvvaAdapter getAdapter() {
        return new KuvvaAdapter();
    }
}
