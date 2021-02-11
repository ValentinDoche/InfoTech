package com.infotech.infotech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    String data1[];
    Context context;

    public CoursesAdapter(Context ct, String s1[]){

        data1 = s1;
        context = ct;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.courseName.setText(data1[position]);
        holder.courseDesc.setText(data1[position]);
        holder.coursePrice.setText(data1[position]);
        /** holder.courseImg.setText(data1[position]); **/
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView courseName, courseDesc;
        ImageView courseImg;
        Chip coursePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.course_name);
            courseDesc = itemView.findViewById(R.id.course_desc);
            courseImg = itemView.findViewById(R.id.course_img);
            coursePrice = itemView.findViewById(R.id.course_price);

        }
    }
}
