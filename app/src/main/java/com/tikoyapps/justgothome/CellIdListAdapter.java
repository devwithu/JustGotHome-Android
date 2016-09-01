package com.tikoyapps.justgothome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tikoyapps.justgothome.data.CellId;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public class CellIdListAdapter extends RecyclerView.Adapter<CellIdListAdapter.VH> {

    private Context mContext;
    private List<CellId> mCellIdList;

    public CellIdListAdapter(Context context, List<CellId> cellIdList) {
        this.mContext = context;
        this.mCellIdList = cellIdList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cellid_list_item,parent);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        CellId cellId = mCellIdList.get(position);
        holder.cellIdTextView.setText(cellId.getCellId());
        holder.idTextView.setText(cellId.getId());
        holder.itemView.setTag(cellId);
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
            cellIdTextView = (TextView)itemView.findViewById(R.id.cellid_list_item_cellid);
            timeStampTextView = (TextView)itemView.findViewById(R.id.cellid_list_item_timestamp);
            idTextView = (TextView)itemView.findViewById(R.id.cellid_list_item_id);
        }
    }
}
