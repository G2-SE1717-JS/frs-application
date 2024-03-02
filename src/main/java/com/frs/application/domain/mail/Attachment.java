package com.frs.application.domain.mail;

import com.frs.application.constants.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private String fileName;
    private ContentType contentType;
    private String base64Content;
}
