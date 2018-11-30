package com.acme.tictactoe.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acme.tictactoe.R;
import com.acme.tictactoe.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by MarchellinAntonia on 29/11/18.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private HashMap<Integer, Integer> userIds = new HashMap<>();
    private List<User> userList;

    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userId, name, email;

        public MyViewHolder(View view) {
            super(view);
            userId = (TextView) view.findViewById(R.id.tvUserId);
            name = (TextView) view.findViewById(R.id.tvUserName);
            email = (TextView) view.findViewById(R.id.tvUserEmail);
        }

        public void bind(final User item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


    public UserAdapter(List<User> userList, OnItemClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        userIds.put(position, user.getId());
        holder.userId.setText(String.valueOf(user.getId()));
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.bind(userList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }
}