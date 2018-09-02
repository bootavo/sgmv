package com.app.sgmv.sgmv.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.app.sgmv.sgmv.R;
import com.app.sgmv.sgmv.entities.failure.FailureReport;
import com.app.sgmv.sgmv.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtufinof on 6/16/18.
 */

public class FailureAdapter extends RecyclerView.Adapter<FailureAdapter.FailureViewHolder>{

    private List<FailureReport> mList;
    private OnItemClickListener listener;
    private Context ctx;
    private int lastPosition = -1;

    public FailureAdapter(Context ctx, List<FailureReport> mList, OnItemClickListener listener) {
        this.mList = mList;
        this.listener = listener;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public FailureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_failure, parent, false);
        return new FailureViewHolder(ctx, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FailureViewHolder holder, int position) {
        holder.bind(mList.get(position), listener);
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.anim_item_list);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull FailureViewHolder holder) {
        holder.itemView.clearAnimation();
    }

    public void setfilter(List<FailureReport> listitem)
    {
        mList = new ArrayList<FailureReport>();
        mList.addAll(listitem);
        notifyDataSetChanged();
    }

    public static class FailureViewHolder extends RecyclerView.ViewHolder{

        private Context ctx;
        private TextView mTitle;
        private TextView mDescription;
        private TextView mDate;

        public FailureViewHolder(Context ctx, View view) {
            super(view);
            this.mTitle = view.findViewById(R.id.tv_title);
            this.mDescription = view.findViewById(R.id.tv_description);
            this.mDate = view.findViewById(R.id.tv_date);
            this.ctx = ctx;
        }

        public void bind(final FailureReport failureReport, OnItemClickListener listener){
            mTitle.setText(""+failureReport.getCod_report());
            mDescription.setText(""+failureReport.getObservations());
            mDate.setText(""+failureReport.getDate());
            itemView.setOnClickListener(v -> {
                listener.onItemClick(failureReport);
            });
        }

    }

}
