package f.star.iota.milk.ui.zdt8.zd;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class ZDT8Fragment extends ScrollImageFragment<ZDT8Presenter, ZDT8Adapter> {


    public static ZDT8Fragment newInstance(String url) {
        ZDT8Fragment fragment = new ZDT8Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        bundle.putString("page_suffix", ".html");
        bundle.putInt("initial_page", 0);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected ZDT8Presenter getPresenter() {
        return new ZDT8Presenter(this);
    }

    @Override
    protected ZDT8Adapter getAdapter() {
        return new ZDT8Adapter();
    }
}
