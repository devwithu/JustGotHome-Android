package com.tikoyapps.justgothome.cellid;

import android.content.Context;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;
import com.tikoyapps.justgothome.data.CellId;
import com.tikoyapps.justgothome.data.CellIdRepository;
import java.util.List;

/**
 * Created by xcptan on 07/09/2016.
 */
public class CellIdPresenter implements CellIdContract.Presenter {

    CellIdContract.View mView;
    CellIdRepository mCellIdRepository;
    FlowContentObserver mFlowContentObserver;
    Context mContext;

    public CellIdPresenter(Context context, CellIdRepository cellIdRepository,
        CellIdContract.View view) {
        this.mContext = context;
        this.mView = view;
        this.mCellIdRepository = cellIdRepository;
        this.mFlowContentObserver = new FlowContentObserver();
        mView.setPresenter(this);
    }

    @Override
    public void requestCellId() {
        //Should call to api first
        mCellIdRepository.getCellIds(new CellIdRepository.LoadCellIdsCallback() {
            @Override
            public void onCellIdsLoaded(List<CellId> cellIds) {
                mView.updateList(cellIds);
            }
        });
    }

    @Override
    public void start() {
        mFlowContentObserver.registerForContentChanges(mContext, CellId.class);
        mFlowContentObserver.addOnTableChangedListener(
            new FlowContentObserver.OnTableChangedListener() {
                @Override
                public void onTableChanged(
                    @Nullable
                    Class<? extends Model> tableChanged, BaseModel.Action action) {
                    mCellIdRepository.getCellIds(new CellIdRepository.LoadCellIdsCallback() {
                        @Override
                        public void onCellIdsLoaded(List<CellId> cellIds) {
                            mView.updateList(cellIds);
                        }
                    });
                }
            });
    }

    @Override
    public void stop() {
        mFlowContentObserver.unregisterForContentChanges(mContext);
    }
}
