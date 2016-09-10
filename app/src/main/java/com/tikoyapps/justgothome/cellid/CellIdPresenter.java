package com.tikoyapps.justgothome.cellid;

import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;
import com.tikoyapps.justgothome.UIThread;
import com.tikoyapps.justgothome.data.CellId;
import com.tikoyapps.justgothome.data.CellIdRepository;
import java.util.List;

/**
 * Created by xcptan on 07/09/2016.
 */
public class CellIdPresenter implements CellIdContract.Presenter {

    CellIdContract.View mView;
    CellIdRepository mRepository;
    FlowContentObserver mObserver;

    public CellIdPresenter(CellIdRepository repository, CellIdContract.View view) {
        this.mView = view;
        this.mRepository = repository;
        this.mObserver = new FlowContentObserver();
        this.mView.setPresenter(this);
    }

    @Override
    public void requestCellId() {
        mRepository.getCellIds(new CellIdRepository.LoadCellIdsCallback() {
            @Override
            public void onCellIdsLoaded(List<CellId> cellIds) {
                mView.updateList(cellIds);
            }
        });
    }

    @Override
    public void chooseAction(CellId cellid) {
        mView.showChooseAction(cellid);
    }

    @Override
    public void start() {
        mObserver.registerForContentChanges(mView.getContext(), CellId.class);
        mObserver.addModelChangeListener(
            new FlowContentObserver.OnModelStateChangedListener() {
                @Override
                public void onModelStateChanged(Class<? extends Model> table,
                    BaseModel.Action action, SQLCondition[] primaryKeyValues) {
                    mRepository.getCellIds(new CellIdRepository.LoadCellIdsCallback() {
                        @Override
                        public void onCellIdsLoaded(final List<CellId> cellIds) {
                            UIThread.getInstance().post(new Runnable() {
                                @Override
                                public void run() {
                                    mView.updateList(cellIds);
                                }
                            });
                        }
                    });
                }
            });
    }

    @Override
    public void stop() {
        mObserver.unregisterForContentChanges(mView.getContext());
    }
}
