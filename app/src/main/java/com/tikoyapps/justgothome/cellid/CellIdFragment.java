package com.tikoyapps.justgothome.cellid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tikoyapps.justgothome.data.CellId;
import com.tikoyapps.justgothome.data.CellIdRepository;
import com.tikoyapps.justgothome.databinding.CellidFragmentBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcptan on 07/09/2016.
 */
public class CellIdFragment extends Fragment implements CellIdContract.View {

    private CellIdContract.Presenter mPresenter;

    private CellIdRepository mCellIdRepository;
    private CellIdListAdapter mCellIdListAdapter;

    private CellidFragmentBinding binding;

    public static CellIdFragment newInstance(Bundle bundle) {
        CellIdFragment fragment = new CellIdFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        mPresenter.start();
        super.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
        @Nullable
        ViewGroup container,
        @Nullable
        Bundle savedInstanceState) {
        binding = CellidFragmentBinding.inflate(inflater, container, false);
        binding.requestcellidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestCellId();
            }
        });
        mCellIdListAdapter = new CellIdListAdapter(getActivity(), new ArrayList<CellId>());
        RecyclerView.LayoutManager layoutManager =
            new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.cellidList.setLayoutManager(layoutManager);
        binding.cellidList.setAdapter(mCellIdListAdapter);

        mPresenter.requestCellId();

        return binding.getRoot();
    }

    @Override
    public void updateList(List<CellId> cellIdList) {
        mCellIdListAdapter.updateList(cellIdList);
        binding.cellidList.smoothScrollToPosition(0);
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Cell IDs updated",
            Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(CellIdContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
