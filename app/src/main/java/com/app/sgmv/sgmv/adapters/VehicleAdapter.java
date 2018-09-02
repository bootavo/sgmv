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
import com.app.sgmv.sgmv.entities.vehicle.Vehicle;
import com.app.sgmv.sgmv.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtufinof on 6/16/18.
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>{

    private List<Vehicle> mList;
    private OnItemClickListener listener;
    private Context ctx;
    private int lastPosition = -1;

    public VehicleAdapter(Context ctx, List<Vehicle> mList, OnItemClickListener listener) {
        this.mList = mList;
        this.listener = listener;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_vehicle, parent, false);
        return new VehicleViewHolder(ctx, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
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
    public void onViewDetachedFromWindow(@NonNull VehicleViewHolder holder) {
        holder.itemView.clearAnimation();
    }

    public void setfilter(List<Vehicle> listitem) {
        mList = new ArrayList<Vehicle>();
        mList.addAll(listitem);
        notifyDataSetChanged();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder{

        private Context ctx;
        private TextView mPlaca;
        private TextView mBrand;
        private TextView mModel;
        private TextView mKmTotal;

        public VehicleViewHolder(Context ctx, View view) {
            super(view);
            this.mPlaca = view.findViewById(R.id.tv_placa);
            this.mBrand = view.findViewById(R.id.tv_brand);
            this.mModel = view.findViewById(R.id.tv_model);
            this.mKmTotal = view.findViewById(R.id.tv_km_total);
            this.ctx = ctx;
        }

        public void bind(final Vehicle vehicle, OnItemClickListener listener){
            mPlaca.setText(""+vehicle.getPlaca());
            mBrand.setText(""+vehicle.getBrand());
            mModel.setText(""+vehicle.getModel());
            mKmTotal.setText(""+vehicle.getKm_total());
            itemView.setOnClickListener(v -> {
                listener.onItemClick(vehicle);
            });
        }

    }

}
