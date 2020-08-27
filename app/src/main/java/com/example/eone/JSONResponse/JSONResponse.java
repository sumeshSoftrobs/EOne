package com.example.eone.JSONResponse;

public class JSONResponse {
    String title;
    String imgUrl;
    String desc;
    String videoId;

    public JSONResponse() {

    }

    public JSONResponse(String title, String imgUrl, String desc, String videoId) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.desc = desc;
        this.videoId = videoId;
    }

    public String getDesc() {
        return desc;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
