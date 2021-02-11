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

    final String[] coursesTitle, coursesPrice, coursesDescription;
    final int[] coursesImages;
    final Context context;

    public CoursesAdapter(Context ct, String[] title, String[] price, String[] description, int[] images){

        coursesTitle = title;
        coursesPrice = price;
        coursesDescription = description;
        coursesImages = images;
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
        holder.courseName.setText(coursesTitle[position]);
        holder.courseDesc.setText(coursesDescription[position]);
        holder.coursePrice.setText(coursesPrice[position]);
        holder.courseImg.setImageResource(coursesImages[position]);
    }

    @Override
    public int getItemCount() {
        return coursesTitle.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        final TextView courseName;
        final TextView courseDesc;
        final ImageView courseImg;
        final Chip coursePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.course_name);
            courseDesc = itemView.findViewById(R.id.course_desc);
            courseImg = itemView.findViewById(R.id.course_img);
            coursePrice = itemView.findViewById(R.id.course_price);

        }
    }
}
