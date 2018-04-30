package com.xinsane.ghost.spider;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSpider(View view) {
        TextInputEditText et_url = findViewById(R.id.et_url);
        AppCompatRadioButton sax = findViewById(R.id.radio_sax);
        AppCompatRadioButton pull = findViewById(R.id.radio_pull);
        Intent intent = new Intent(this, SpiderActivity.class);
        intent.putExtra("url", et_url.getText().toString());
        if (sax.isChecked())
            intent.putExtra("type", "sax");
        if (pull.isChecked())
            intent.putExtra("type", "pull");
        startActivity(intent);
    }

    public void startSpiderForJSON(View view) {
        TextInputEditText et_url = findViewById(R.id.et_url_json);
        Intent intent = new Intent(this, SpiderActivity.class);
        intent.putExtra("url", et_url.getText().toString());
        intent.putExtra("type", "json");
        startActivity(intent);
    }
}
