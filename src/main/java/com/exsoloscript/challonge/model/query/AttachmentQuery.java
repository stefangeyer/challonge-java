package com.exsoloscript.challonge.model.query;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * Query for creating or updating an attachment. This class can be accessed using it's builder.
 *
 * @author EXSolo
 * @version 20160819.1
 */
@Data
@Accessors(fluent = true)
@Builder
public class AttachmentQuery {

    private File asset;
    private String url;
    private String description;
}
