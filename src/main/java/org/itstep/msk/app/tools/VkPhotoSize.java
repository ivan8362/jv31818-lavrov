package org.itstep.msk.app.tools;

public class VkPhotoSize {
    public String type;
    public String url;
    public Integer width;
    public Integer height;

    public VkPhotoSize(){}

    public VkPhotoSize(String type, String url, Integer width, Integer height) {
        this.type = type;
        this.url = url;
        this.width = width;
        this.height = height;
    }
}
