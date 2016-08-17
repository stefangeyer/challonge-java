package com.exsoloscript.challonge.model.query;

import java.io.File;

public class AttachmentQuery {
    private File file;
    private String url;
    private String description;

    private AttachmentQuery(File file, String url, String description) {
        this.file = file;
        this.url = url;
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private File file;
        private String url;
        private String description;

        public Builder setFile(File file) {
            this.file = file;
            return this;
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
            return new AttachmentQuery(file, url, description);
        }
    }
}
