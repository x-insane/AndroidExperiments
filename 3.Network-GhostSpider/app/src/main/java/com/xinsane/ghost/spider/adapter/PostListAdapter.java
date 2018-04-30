package com.xinsane.ghost.spider.adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xinsane.ghost.spider.PostActivity;
import com.xinsane.ghost.spider.R;
import com.xinsane.ghost.spider.data.Blog;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {
    private List<Blog.Post> list;
    private AppCompatActivity activity;

    public PostListAdapter(List<Blog.Post> list, AppCompatActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.post_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(activity, PostActivity.class);
                intent.putExtra("post", list.get(position));
                activity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Blog.Post item = list.get(position);
        holder.text_id.setText(String.valueOf(position + 1));
        holder.text_title.setText(item.title);
        holder.text_author.setText(item.author);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_id;
        TextView text_title;
        TextView text_author;

        ViewHolder(View view) {
            super(view);
            text_id = view.findViewById(R.id.item_id);
            text_title = view.findViewById(R.id.item_title);
            text_author = view.findViewById(R.id.item_author);
        }
    }
}
