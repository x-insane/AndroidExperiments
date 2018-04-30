package com.xinsane.ghost.spider.parser;

import com.xinsane.ghost.spider.data.Blog;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSAXHandler extends DefaultHandler {

    private String tag = "";
    private Blog.Post post = null;

    private Blog blog = new Blog();
    public Blog getBlog() { return blog; }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tag = qName;
        if (qName.equals("item")) {
            post = new Blog.Post();
            post.content = "";
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tag = "";
        if (qName.equals("item")) {
            blog.posts.add(post);
            post = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        switch (tag) {
            case "title":
                if (blog.title == null)
                    blog.title = data;
                else if (post != null)
                    post.title = data;
                break;
            case "description":
                if (blog.description == null)
                    blog.description = data;
                break;
            case "dc:creator":
                if (post != null)
                    post.author = data;
                break;
            case "content:encoded":
                if (post != null)
                    post.content += data;
                break;
        }
    }
}
