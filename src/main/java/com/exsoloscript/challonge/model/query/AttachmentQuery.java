/*
 * Copyright 2017 Stefan Geyer <stefangeyer at outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exsoloscript.challonge.model.query;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    public String getMimeType() throws IOException {
        Validate.notNull(asset, "Cannot get MIME type since asset is null");
        return Files.probeContentType(asset.toPath());
    }
}
