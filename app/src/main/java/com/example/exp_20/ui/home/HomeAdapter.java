package com.example.exp_20.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exp_20.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder> {

    List<HomeModel> modelList = new ArrayList<>();
    Context context;
    public RecyclerViewClickListener listener;

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public HomeAdapter(List<HomeModel> modelMatchList, Context context, RecyclerViewClickListener listener) {
        this.modelList = modelMatchList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rc,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeAdapter.viewHolder holder, int position) {
        if(modelList.size()>0){
            HomeModel model_match = modelList.get(position);
            holder.t1.setText(model_match.getTeam1());
            holder.t2.setText(model_match.getTeam2());
            holder.d.setText(model_match.getDtg());
        }
//        holder.t2.setText(model.getTeam2());        }

    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView t1, t2, d;

        public viewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.tv1);
            t2 = itemView.findViewById(R.id.tv2);
            d = itemView.findViewById(R.id.tv3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
