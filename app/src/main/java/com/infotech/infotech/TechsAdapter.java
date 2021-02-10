package com.infotech.infotech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TechsAdapter extends RecyclerView.Adapter<TechsAdapter.ViewHolder> {

    String data1[], data2[];
    Context context;

    public TechsAdapter(Context ct, String s1[], String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;

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
        holder.techName.setText(data1[position]);
        holder.techDescription.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView techName;
        TextView techDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            techName = itemView.findViewById(R.id.tech_name);
            techDescription = itemView.findViewById(R.id.tech_description);


        }
    }
}
