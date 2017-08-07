package f.star.iota.milk.ui.youwu.you;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class YouWuFragment extends ScrollImageFragment<YouWuPresenter, YouWuAdapter> {


    public static YouWuFragment newInstance(String url) {
        YouWuFragment fragment = new YouWuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        bundle.putString("page_suffix", ".html");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected YouWuPresenter getPresenter() {
        return new YouWuPresenter(this);
    }

    @Override
    protected YouWuAdapter getAdapter() {
        return new YouWuAdapter();
    }
}
