package com.tikoyapps.justgothome.cellid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tikoyapps.justgothome.R;
import com.tikoyapps.justgothome.data.CellId;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public class CellIdListAdapter extends RecyclerView.Adapter<CellIdListAdapter.VH> {

    private Context mContext;
    private List<CellId> mCellIdList;
    private DateFormat mDateFormat = DateFormat.getDateTimeInstance();
    private CellIdContract.Presenter mCellIdPresenter;

    public CellIdListAdapter(Context context, List<CellId> cellIdList,
        CellIdContract.Presenter cellIdPresenter) {
        this.mContext = context;
        this.mCellIdList = cellIdList;
        this.mCellIdPresenter = cellIdPresenter;
    }

    public void updateList(List<CellId> cellIdList) {
        this.mCellIdList = cellIdList;
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cellid_list_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final CellId cellId = mCellIdList.get(position);
        holder.cellIdTextView.setText("cell id: " + cellId.getCellId());
        holder.idTextView.setText("id: " + cellId.getId());
        holder.timeStampTextView.setText(mDateFormat.format(new Date(cellId.getTimestamp())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCellIdPresenter.openChooseActionDialog(cellId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCellIdList.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView cellIdTextView;
        TextView timeStampTextView;
        TextView idTextView;

        public VH(View itemView) {
            super(itemView);
            cellIdTextView = (TextView) itemView.findViewById(R.id.cellid_list_item_cellid);
            timeStampTextView = (TextView) itemView.findViewById(R.id.cellid_list_item_timestamp);
            idTextView = (TextView) itemView.findViewById(R.id.cellid_list_item_id);
        }
    }
}
