package com.source.dinhtv.fashionecommercecore.http.response.payload;

public class ImageResponse {
    public Integer id;
    public String caption;
    public String address;

    public ImageResponse(Integer id, String caption, String address) {
        this.id = id;
        this.caption = caption;
        this.address = address;
    }
}
