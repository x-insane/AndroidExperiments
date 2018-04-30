package com.xinsane.ghost.spider;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xinsane.ghost.spider.adapter.PostListAdapter;
import com.xinsane.ghost.spider.data.Blog;
import com.xinsane.ghost.spider.parser.JSONParser;
import com.xinsane.ghost.spider.parser.XmlParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@SuppressLint("SetTextI18n")
public class SpiderActivity extends AppCompatActivity {

    private Blog blog;
    private TextView text_wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider);
        final String url = getIntent().getStringExtra("url");
        final String type = getIntent().getStringExtra("type");
        text_wait = findViewById(R.id.text_wait);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Message message = new Message();
                message.what = MessageHandler.MSG_ERROR;
                message.obj = "Request fail!";
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                if (body == null) {
                    Message message = new Message();
                    message.what = MessageHandler.MSG_ERROR;
                    message.obj = "Empty response!";
                    handler.sendMessage(message);
                    return;
                }
                String data = body.string();
                switch (type) {
                    case "pull":
                        blog = XmlParser.pullParser(data);
                        break;
                    case "sax":
                        blog = XmlParser.saxParser(data);
                        break;
                    case "json":
                        blog = JSONParser.parse(data);
                        break;
                    default:
                        Message message = new Message();
                        message.what = MessageHandler.MSG_ERROR;
                        message.obj = "Wrong type!";
                        handler.sendMessage(message);
                        return;
                }
                Message message = new Message();
                message.what = MessageHandler.MSG_SUCCESS;
                message.obj = "title: " + blog.title + "\n";
                message.obj += "description: " + blog.description + "\n";
                message.obj += "There are " + blog.posts.size() + " posts.";
                handler.sendMessage(message);
            }
        });
    }

    public void loadList() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PostListAdapter adapter = new PostListAdapter(blog.posts, this);
        recyclerView.setAdapter(adapter);
    }

    private MessageHandler handler = new MessageHandler(this);

    public static class MessageHandler extends Handler {
        static final int MSG_ERROR = 0x01;
        static final int MSG_SUCCESS = 0x02;

        private SpiderActivity activity;
        MessageHandler(SpiderActivity activity) {
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ERROR:
                    activity.text_wait.setText(msg.obj.toString());
                    break;
                case MSG_SUCCESS:
                    Toast.makeText(activity, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    activity.text_wait.setVisibility(View.GONE);
                    activity.loadList();
                    break;
            }
        }
    }

}
