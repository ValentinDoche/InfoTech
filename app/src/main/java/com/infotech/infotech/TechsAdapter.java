package com.infotech.infotech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.List;

public class TechsAdapter extends RecyclerView.Adapter<TechsAdapter.ViewHolder> {

    List<String> techValueName, techValueType, techValueDescription, techValueImg, techValueJobs;
    Context context;

    public TechsAdapter(Context ct, List<String> name,  List<String> type,  List<String> description,  List<String> img, List<String> jobs){
        context = ct;
        techValueName = name;
        techValueType = type;
        techValueDescription = description;
        techValueImg = img;
        techValueJobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tech_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.techName.setText(techValueName.get(position));
        holder.techDescription.setText(techValueDescription.get(position));
        holder.techJobs.setText(techValueJobs.get(position));
    }

    @Override
    public int getItemCount() {
        return techValueName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView techName;
        TextView techDescription;
        Chip techJobs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            techName = itemView.findViewById(R.id.tech_name);
            techDescription = itemView.findViewById(R.id.tech_description);
            techJobs = itemView.findViewById(R.id.tech_offers);

        }
    }
}
