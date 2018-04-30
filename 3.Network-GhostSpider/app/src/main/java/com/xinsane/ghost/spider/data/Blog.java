package com.xinsane.ghost.spider.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blog {

    public static class Post implements Serializable {
        public String title;
        public String author;
        public String content;
    }

    public List<Post> posts = new ArrayList<>();
    public String title;
    public String description;
}
