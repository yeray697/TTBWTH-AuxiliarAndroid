package com.phile.yrj.takethebullfighterwiththehorns.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.Login_Application;
import com.phile.yrj.takethebullfighterwiththehorns.R;
import com.phile.yrj.takethebullfighterwiththehorns.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeray697 on 9/11/16.
 */

public class CommentsAdapterRecycler extends RecyclerView.Adapter<CommentsAdapterRecycler.CommentViewHolder> {

    List<Comment> comments;
    Context context;

    public CommentsAdapterRecycler(Context context, int idnew){
        this.context = context;
        this.comments = new ArrayList<Comment>();
        this.comments = ((Login_Application)context.getApplicationContext()).getComments(idnew);
    }


    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list_row, parent, false);
        return new CommentViewHolder(item);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.tvName.setText(comments.get(position).getUsername());
        holder.tvMessage.setText(comments.get(position).getMessage());
        holder.tvDate.setText(comments.get(position).getFormatedDate());
        if (this.comments.size() == (position + 1)) //It is the last one
            holder.viewSeparatorComment.setVisibility(View.GONE);
        else
            holder.viewSeparatorComment.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
    public static class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvName;
        TextView tvMessage;
        TextView tvDate;
        View viewSeparatorComment;

        public CommentViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvCommentName_New);
            tvMessage = (TextView) itemView.findViewById(R.id.tvCommentMessage_New);
            tvDate = (TextView) itemView.findViewById(R.id.tvCommentDate_New);
            viewSeparatorComment = (View) itemView.findViewById(R.id.viewSeparatorComment_New);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), tvName.getText()+": on click", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), tvName.getText()+": on long click", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

