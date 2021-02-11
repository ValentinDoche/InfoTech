package com.infotech.infotech;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;

import java.util.List;

public class OpportunitiesAdapter extends RecyclerView.Adapter<OpportunitiesAdapter.ViewHolder> {

    final List<String> opporName, opporType, opporDesc, opporLocation, opporURL;
    final Context context;

    public OpportunitiesAdapter(Context ct, List<String> name, List<String> type, List<String> desc, List<String> location, List<String> url){
        context = ct;
        opporName = name;
        opporType = type;
        opporDesc = desc;
        opporLocation = location;
        opporURL = url;
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
        holder.opporName.setText(opporName.get(position));
        holder.opporType.setText(opporType.get(position));
        holder.opporDesc.setText(opporDesc.get(position));
        holder.opporLocation.setText(opporLocation.get(position));

        holder.opporCard.setOnClickListener(view -> openWebPage(opporURL.get(position)));
    }

    @Override
    public int getItemCount() {
        return opporName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView opporName;
        final TextView opporDesc;
        final TextView opporLocation;
        final Chip opporType;
        final MaterialCardView opporCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            opporName = itemView.findViewById(R.id.oppor_name);
            opporType = itemView.findViewById(R.id.oppor_type);
            opporDesc = itemView.findViewById(R.id.oppor_desc);
            opporLocation = itemView.findViewById(R.id.oppor_location);
            opporCard = itemView.findViewById(R.id.oppor_card);

        }
    }

    /**
     * Open a web page of a specified URL
     *
     * @param url URL to open
     */
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        context.startActivity(intent);
    }


}
