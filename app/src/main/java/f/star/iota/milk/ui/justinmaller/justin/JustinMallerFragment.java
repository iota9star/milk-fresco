package f.star.iota.milk.ui.justinmaller.justin;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class JustinMallerFragment extends FixedImageFragment<JustinMallerPresenter, JustinMallerAdapter> {


    public static JustinMallerFragment newInstance(String url) {
        JustinMallerFragment fragment = new JustinMallerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected JustinMallerPresenter getPresenter() {
        return new JustinMallerPresenter(this);
    }

    @Override
    protected JustinMallerAdapter getAdapter() {
        return new JustinMallerAdapter();
    }
}
