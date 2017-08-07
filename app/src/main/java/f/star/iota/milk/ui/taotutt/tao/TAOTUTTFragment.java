package f.star.iota.milk.ui.taotutt.tao;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class TAOTUTTFragment extends ScrollImageFragment<TAOTUTTPresenter, TAOTUTTAdapter> {
    public static TAOTUTTFragment newInstance(String url) {
        TAOTUTTFragment fragment = new TAOTUTTFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        bundle.putString("page_suffix", ".html");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected TAOTUTTPresenter getPresenter() {
        return new TAOTUTTPresenter(this);
    }

    @Override
    protected TAOTUTTAdapter getAdapter() {
        return new TAOTUTTAdapter();
    }
}
