package com.jzhu.io.gank.mvp.presenter;

import com.jzhu.io.baselibrary.BaseAbstractPresenter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.baselibrary.BaseSubscriber;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.data.service.GankIoService;
import com.jzhu.io.gank.mvp.view.IosView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jzhu on 2016/11/22.
 */

public class IosPresenter extends BaseAbstractPresenter<IosView> {

    @Inject
    GankIoService gankIoService;

    @Inject
    public IosPresenter() {
    }

    public void getIosList(int rows, int pageNum, BaseActivity act) {
        if (!checkNetWork()) {
            return;
        }
        mView.showLoading();
        gankIoService.execute(new BaseSubscriber<List<GankAndroidAndiOSEntities>>(mView) {
            @Override
            public void onNext(List<GankAndroidAndiOSEntities> list) {
                mView.getList(list);
                gankIoService.unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.onThrowable(e);
            }
        }, gankIoService.getIos(rows, pageNum), act);
    }

}
