package com.xinsane.ghost.spider.parser;

import com.google.gson.Gson;
import com.xinsane.ghost.spider.data.Blog;

public class JSONParser {

    public static Blog parse(String jsonData) {
        return new Gson().fromJson(jsonData, Blog.class);
    }

}
