package com.daiancosta.brokeragenote.helpers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfHelper {
    public static String getText(final InputStream input, final String password) throws IOException {

        PDDocument document;
        if (password != null) {
            document = PDDocument.load(input, password);
        } else {
            document = PDDocument.load(input);
        }

        PDFTextStripper stripper = new PDFTextStripper();
        int lastPage = document.getNumberOfPages();
        stripper.setStartPage(1);
        stripper.setEndPage(lastPage);
        return stripper.getText(document);
    }
}
