package ssf.assessment.model;

import java.io.IOException;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;

public class Fields implements Data {
    private static final Logger logger = LoggerFactory.getLogger(Fields.class);

    private String id;
    private int publishedOn;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(int publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public static Fields createJson(JsonObject jo) throws IOException {
        logger.info("Fields json");
        Fields f = new Fields();

        JsonString jsnId = jo.getJsonString("id");
        f.id = jsnId.getString();

        JsonNumber jsnPublishedOn = jo.getJsonNumber("published_on");
        f.publishedOn = jsnPublishedOn.intValue();

        JsonString jsnTitle = jo.getJsonString("title");
        f.title = jsnTitle.getString();

        JsonString jsnUrl = jo.getJsonString("url");
        f.url = jsnUrl.getString();

        JsonString jsnImageUrl = jo.getJsonString("imageurl");
        f.imageurl = jsnImageUrl.getString();

        JsonString jsnBody = jo.getJsonString("body");
        f.body = jsnBody.getString();

        JsonString jsnTags = jo.getJsonString("tags");
        f.tags = jsnTags.getString();

        JsonString jsnCategories = jo.getJsonString("categories");
        f.categories = jsnCategories.getString();

        return f;
    }

    
}
