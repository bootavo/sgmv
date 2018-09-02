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
import com.app.sgmv.sgmv.entities.user.User;
import com.app.sgmv.sgmv.listeners.OnItemClickListener;
import com.app.sgmv.sgmv.utilities.GlideApp;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gtufinof on 6/16/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mList;
    private OnItemClickListener listener;
    private Context ctx;
    private int lastPosition = -1;

    public UserAdapter(Context ctx, List<User> mList, OnItemClickListener listener) {
        this.mList = mList;
        this.listener = listener;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        return new UserViewHolder(ctx, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
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
    public void onViewDetachedFromWindow(@NonNull UserViewHolder holder) {
        holder.itemView.clearAnimation();
    }

    public void setfilter(List<User> listitem) {
        mList = new ArrayList<User>();
        mList.addAll(listitem);
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        private Context ctx;
        private TextView mName;
        private TextView mRol;
        private CircleImageView mPhoto;

        public UserViewHolder(Context ctx, View view) {
            super(view);
            this.mName = view.findViewById(R.id.tv_name);
            this.mRol = view.findViewById(R.id.tv_rol);
            this.mPhoto = view.findViewById(R.id.civ_photo);
            this.ctx = ctx;
        }

        public void bind(final User user, OnItemClickListener listener){
            mName.setText(""+user.getNames()+", "+user.getLast_name());
            mRol.setText(""+user.getRol());
            if(user.getPhoto() == null || user.getPhoto().equals("")){
                GlideApp.with(ctx).load(R.drawable.ic_man).into(mPhoto);
            }else{
                GlideApp.with(ctx).load(user.getPhoto()).into(mPhoto);
            }
            itemView.setOnClickListener(v -> {
                listener.onItemClick(user);
            });
        }

    }

}
