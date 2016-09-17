package com.exsoloscript.challonge.model.query;

import java.io.File;

/**
 * Query for creating or updating an attachment. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class AttachmentQuery {

    private File asset;
    private String url;
    private String description;

    private AttachmentQuery(File asset, String url, String description) {
        this.asset = asset;
        this.url = url;
        this.description = description;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A file upload (250KB max, no more than 4 attachments per match).
     * If provided, the url parameter will be ignored.
     */
    public File asset() {
        return asset;
    }

    /**
     * A web URL. The attachment will contain a link to the given URL.
     */
    public String url() {
        return url;
    }

    /**
     * Text to describe the file or URL attachment,
     * or this can simply be standalone text.
     */
    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentQuery that = (AttachmentQuery) o;

        if (asset != null ? !asset.equals(that.asset) : that.asset != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = asset != null ? asset.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public static class Builder {
        private File asset;
        private String url;
        private String description;

        public Builder setAsset(File asset) {
            throw new UnsupportedOperationException("The Challonge API is unable to process assets at the moment. This bug is caused by the API provider.");
//            this.asset = asset;
//            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AttachmentQuery build() {
            if (asset == null && url == null && description == null)
                throw new IllegalArgumentException("At least 1 of the 3 parameters (asset, url or description) must be provided.");
            if (asset != null)
                throw new UnsupportedOperationException("This library is currently not supporting file uploads");
            return new AttachmentQuery(asset, url, description);
        }
    }
}
