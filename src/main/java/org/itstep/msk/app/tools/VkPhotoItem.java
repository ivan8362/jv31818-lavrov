package org.itstep.msk.app.tools;

import java.util.ArrayList;

public class VkPhotoItem {
    public Integer id;
    public Integer album_id;
    public Integer owner_id;
    public ArrayList<VkPhotoSize> sizes;
    public String text;
    public Integer date;
    public Integer post_id;

    public VkPhotoItem(){}

    public VkPhotoItem(Integer id, Integer album_id, Integer owner_id, ArrayList<VkPhotoSize> sizes, String text, Integer date, Integer post_id) {
        this.id = id;
        this.album_id = album_id;
        this.owner_id = owner_id;
        this.sizes = sizes;
        this.text = text;
        this.date = date;
        this.post_id = post_id;
    }
}
