package com.gary.cloudinteractive.webapi.service.client;

import com.gary.cloudinteractive.webapi.service.LoadFileService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoadFileServiceImpl implements LoadFileService {
    @Override
    public InputStreamResource loadPdf() {
        log.debug("loadPdf");
        String path = "pdf/test.pdf";

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();


        InputStream inputStream = classloader.getResourceAsStream(path);
        return inputStream != null ? new InputStreamResource(inputStream) : null;
    }

    @Override
    public byte[] lodeMergePdf() {
        log.debug("lodeMergePdf");
        String path = "pdf/test.pdf";

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        InputStream inputStream = classloader.getResourceAsStream(path);
        InputStream inputStream2 = classloader.getResourceAsStream(path);
        List<InputStream> pdfs = new ArrayList<>();
        pdfs.add(inputStream);
        pdfs.add(inputStream2);

        return mergePdf(pdfs);
    }


    private byte[] mergePdf(List<InputStream> inputStreams) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Document document = new Document(); // 建立新PDF
        byte[] pdfs = new byte[0];
        try {
            PdfCopy copy = new PdfCopy(document, bos);
            document.open();
            for (InputStream is : inputStreams) {
                byte[] bytes = IOUtils.toByteArray(is);
                is.close();
                PdfReader reader = new PdfReader(bytes);
                int pageTotal = reader.getNumberOfPages();
                for (int pageNo = 1; pageNo <= pageTotal; pageNo++){
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, pageNo);
                    copy.addPage(page);
                }
                reader.close();
            }
            document.close();
            pdfs = bos.toByteArray();
            bos.close();
            copy.close();
        } catch (DocumentException | IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return pdfs;
    }
}
