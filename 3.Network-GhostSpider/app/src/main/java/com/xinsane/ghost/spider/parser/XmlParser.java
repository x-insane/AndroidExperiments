package com.xinsane.ghost.spider.parser;

import com.xinsane.ghost.spider.data.Blog;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlParser {

    public static Blog saxParser(String xmlData) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        XmlSAXHandler handler = new XmlSAXHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new ByteArrayInputStream(xmlData.getBytes("utf-8")), handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return handler.getBlog();
    }

    public static Blog pullParser(String xmlData) {
        Blog blog = new Blog();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type = parser.getEventType();
            Blog.Post post = null;
            while(type != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (type) {
                    case XmlPullParser.START_TAG: {
                        switch (nodeName) {
                            case "item":
                                post = new Blog.Post();
                                break;
                            case "title":
                                parser.next();
                                String title = parser.getText();
                                if (blog.title == null)
                                    blog.title = title;
                                else if (post != null)
                                    post.title = title;
                                break;
                            case "description":
                                parser.next();
                                String description = parser.getText();
                                if (blog.description == null)
                                    blog.description = description;
                                break;
                            case "dc:creator":
                                parser.next();
                                String author = parser.getText();
                                if (post != null)
                                    post.author = author;
                                break;
                            case "content:encoded":
                                parser.next();
                                String content = parser.getText();
                                if (post != null)
                                    post.content = content;
                                break;
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if (nodeName.equals("item")) {
                            blog.posts.add(post);
                            post = null;
                        }
                        break;
                    }
                }
                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blog;
    }

}
