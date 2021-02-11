package com.infotech.infotech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

public class OpportunitiesAdapter extends RecyclerView.Adapter<OpportunitiesAdapter.ViewHolder> {

    final String[] data1;
    final Context context;

    public OpportunitiesAdapter(Context ct, String[] s1){
        context = ct;
        data1 = s1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.opportunity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.opporName.setText(data1[position]);
        holder.opporType.setText(data1[position]);
        holder.opporDesc.setText(data1[position]);
        holder.opporLocation.setText(data1[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView opporName;
        final TextView opporDesc;
        final TextView opporLocation;
        final Chip opporType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            opporName = itemView.findViewById(R.id.oppor_name);
            opporType = itemView.findViewById(R.id.oppor_type);
            opporDesc = itemView.findViewById(R.id.oppor_desc);
            opporLocation = itemView.findViewById(R.id.oppor_location);

        }
    }
}
