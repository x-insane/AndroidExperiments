package com.xinsane.ghost.spider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.xinsane.ghost.spider.data.Blog;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Blog.Post post = (Blog.Post) getIntent().getSerializableExtra("post");
        TextView text_title = findViewById(R.id.text_title);
        text_title.setText(post.title);
        TextView text_author = findViewById(R.id.text_author);
        text_author.setText(post.author);
        WebView webView = findViewById(R.id.web_view);
        webView.loadData(post.content, "text/html;charset=UTF-8", null);
    }
}
