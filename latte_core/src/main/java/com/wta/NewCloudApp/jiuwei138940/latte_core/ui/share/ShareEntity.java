package com.wta.NewCloudApp.jiuwei138940.latte_core.ui.share;

/**
 * 分享内容
 */

public class ShareEntity {
    private String description = null;
    private String url = null;
    private String title = null;
    private String thumb = null;

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        if (url == null)
            return "";
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        if (title == null)
            return "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        if (thumb == null)
            return "";
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
