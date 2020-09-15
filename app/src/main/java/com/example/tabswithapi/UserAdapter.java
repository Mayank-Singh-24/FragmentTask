package com.example.tabswithapi;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Post> postList;

    public void setData(List<Post> postList){
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_post,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Post post = postList.get(position);
        int id = post.getId();
        int userid = post.getUserId();
        String title = post.getTitle();

        holder.id.setText(""+id);
        holder.userid.setText(""+userid);
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView userid;
        TextView title;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.postId);
            userid = (TextView) itemView.findViewById(R.id.postUserId);
            title = (TextView) itemView.findViewById(R.id.postTitle);
        }
    }
}
