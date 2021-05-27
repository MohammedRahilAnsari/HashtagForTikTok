package com.trycatch.hashtagfortiktok.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.trycatch.hashtagfortiktok.Activities.Sub_Category_List;
import com.trycatch.hashtagfortiktok.Model.Example;
import com.trycatch.hashtagfortiktok.R;

import java.util.List;

public class CatergoryAdapter extends RecyclerView.Adapter<CatergoryAdapter.MyViewHolder> {

    private Context context;
    private static List<Example> exampleList;

    public CatergoryAdapter(Context context, List<Example> exampleList) {
        this.context = context;
        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public CatergoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(exampleList.get(position).getCatName());
        holder.textView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_down));
        Glide.with(context)
                .asBitmap()
                .load(exampleList.get(position).getCatImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView linearLayout;
        Chip chip;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.Category_Img);
            textView = itemView.findViewById(R.id.Category_textview);
            linearLayout = itemView.findViewById(R.id.Category);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, Sub_Category_List.class);
                    intent.putExtra("key", exampleList.get(getAdapterPosition()).getId());
                    intent.putExtra("catname", exampleList.get(getAdapterPosition()).getCatName());
                    context.startActivity(intent);

                }
            });
        }
    }
}