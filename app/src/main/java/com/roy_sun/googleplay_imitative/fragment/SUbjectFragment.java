package com.roy_sun.googleplay_imitative.fragment;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.roy_sun.googleplay_imitative.base.BaseHolder;
import com.roy_sun.googleplay_imitative.base.SuperBaseAdapter;
import com.roy_sun.googleplay_imitative.bean.SubjectBean;
import com.roy_sun.googleplay_imitative.holder.SubjectHolder;
import com.roy_sun.googleplay_imitative.protocol.SubjectProtocol;
import com.roy_sun.googleplay_imitative.utils.UIUtils;
import com.roy_sun.googleplay_imitative.view.LoadDataUI;

import java.util.List;

/**
 * Created by Roy Sun on 2016/2/27.
 */
public class SubjectFragment extends LoadDataFragment {

    private SubjectProtocol   mProtocol;
    private List<SubjectBean> mData;
    @Override
    protected LoadDataUI.Result doInBackground() {

        mProtocol = new SubjectProtocol();

        try {
            mData = mProtocol.loadPage(0);

            if (mData == null || mData.size() == 0) {
                return LoadDataUI.Result.EMPTY;
            } else {
                return LoadDataUI.Result.SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;
        }

    }

    @Override
    protected View initSuccessView() {

        ListView listView = new ListView(UIUtils.getContext());

        listView.setAdapter(new SubjectAdapter(mData));

        return listView;
    }

    protected class SubjectAdapter extends SuperBaseAdapter<SubjectBean>  {
        public SubjectAdapter(List<SubjectBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder getItemHolder() {
            return new SubjectHolder();
        }
    }
}
