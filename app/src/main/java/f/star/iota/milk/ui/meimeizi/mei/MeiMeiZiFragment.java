package f.star.iota.milk.ui.meimeizi.mei;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class MeiMeiZiFragment extends ScrollImageFragment<MeiMeiZiPresenter, MeiMeiZiAdapter> {


    public static MeiMeiZiFragment newInstance(String url) {
        MeiMeiZiFragment fragment = new MeiMeiZiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MeiMeiZiPresenter getPresenter() {
        return new MeiMeiZiPresenter(this);
    }

    @Override
    protected MeiMeiZiAdapter getAdapter() {
        return new MeiMeiZiAdapter();
    }
}
