package com.example.tabswithapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InfoUserAdapter extends RecyclerView.Adapter<InfoUserAdapter.InfoUserViewHolder> {


    private List<User> userList;

    public void setData(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InfoUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_user,parent,false);
        return new InfoUserAdapter.InfoUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoUserViewHolder holder, int position) {
        User user = userList.get(position);
        int id = user.getId();
        String name = user.getName();
        String username = user.getUsername();
        String email = user.getEmail();

        holder.id.setText(""+id);
        holder.name.setText(name);
        holder.username.setText(username);
        holder.email.setText(email);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class InfoUserViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        TextView username;
        TextView email;

        public InfoUserViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textViewid);
            name = (TextView) itemView.findViewById(R.id.textView9);
            username = (TextView) itemView.findViewById(R.id.textView10);
            email = (TextView) itemView.findViewById(R.id.textView11);
        }
    }

}
