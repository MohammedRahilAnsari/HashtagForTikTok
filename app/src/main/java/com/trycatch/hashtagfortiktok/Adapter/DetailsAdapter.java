package com.trycatch.hashtagfortiktok.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trycatch.hashtagfortiktok.Model.DataResponses;
import com.trycatch.hashtagfortiktok.R;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {

    private Context context;
    private List<DataResponses> dataResponses;
    private onItemClick click;
    private static int selected_position = -1;

    public DetailsAdapter(Context context, List<DataResponses> dataResponses,onItemClick click) {
        this.context = context;
        this.dataResponses = dataResponses;
        this.click = click;
    }

    public interface onItemClick{
        void onRowClick(DataResponses dataResponses);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_category_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(dataResponses.get(position).getTitle());

        if(selected_position == position){
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.tabtextcolor));
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.textView.setTextColor(context.getResources().getColor(R.color.tabtextcolor));
        }
    }

    @Override
    public int getItemCount() {
        return dataResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.SubCategory_textview);
            textView2 = itemView.findViewById(R.id.chip_group);
            linearLayout = itemView.findViewById(R.id.Category);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onRowClick(dataResponses.get(getAdapterPosition()));
                    selected_position = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}