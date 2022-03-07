package com.daiancosta.brokeragenote.helpers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfHelper {
    public static String getText(final String filePath, final String password) throws IOException {

        final File input = new File(filePath);
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
