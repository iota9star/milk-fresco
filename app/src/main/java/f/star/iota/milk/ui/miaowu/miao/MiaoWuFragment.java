package f.star.iota.milk.ui.miaowu.miao;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class MiaoWuFragment extends ScrollImageFragment<MiaoWuPresenter, MiaoWuAdapter> {


    public static MiaoWuFragment newInstance(String url) {
        MiaoWuFragment fragment = new MiaoWuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        bundle.putString("page_suffix", ".html");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MiaoWuPresenter getPresenter() {
        return new MiaoWuPresenter(this);
    }

    @Override
    protected MiaoWuAdapter getAdapter() {
        return new MiaoWuAdapter();
    }
}
