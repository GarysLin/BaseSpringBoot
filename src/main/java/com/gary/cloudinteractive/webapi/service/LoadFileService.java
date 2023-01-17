package com.gary.cloudinteractive.webapi.service;

import org.springframework.core.io.InputStreamResource;

public interface LoadFileService {
    InputStreamResource loadPdf();
    byte[] lodeMergePdf();
}
