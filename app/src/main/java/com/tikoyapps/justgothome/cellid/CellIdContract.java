package com.tikoyapps.justgothome.cellid;

import com.tikoyapps.justgothome.BasePresenter;
import com.tikoyapps.justgothome.BaseView;
import com.tikoyapps.justgothome.data.CellId;
import java.util.List;

/**
 * Created by xcptan on 07/09/2016.
 */
public interface CellIdContract {
    interface View extends BaseView<Presenter> {
        void updateList(List<CellId> cellIdList);
        void showChooseAction(CellId cellId);
    }

    interface Presenter extends BasePresenter {
        void requestCellId();
        void chooseAction(CellId cellid);
    }
}
