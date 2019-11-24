package org.itstep.msk.app.tools;

import java.util.ArrayList;

public class VkPhotoResponse {
    public Integer count;
    public ArrayList<VkPhotoItem> items;

    public VkPhotoResponse (){}
    public VkPhotoResponse(Integer count, ArrayList<VkPhotoItem> items) {
        this.count = count;
        this.items = items;
    }
}
