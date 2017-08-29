package f.star.iota.milk.ui.mmjpg.mm;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class MmjpgFragment extends ScrollImageFragment<MmjpgPresenter, MmjpgAdapter> {


    public static MmjpgFragment newInstance(String url) {
        MmjpgFragment fragment = new MmjpgFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MmjpgPresenter getPresenter() {
        return new MmjpgPresenter(this);
    }

    @Override
    protected MmjpgAdapter getAdapter() {
        return new MmjpgAdapter();
    }
}
