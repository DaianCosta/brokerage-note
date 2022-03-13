package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.helpers.FormatHelper;
import com.daiancosta.brokeragenote.helpers.PdfHelper;
import com.daiancosta.brokeragenote.models.NoteItem;
import com.daiancosta.brokeragenote.models.Note;
import com.daiancosta.brokeragenote.models.constants.NoteConstant;
import com.daiancosta.brokeragenote.models.enums.InstitutionEnum;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileNoteServiceImpl implements FileNoteService {

    public void save(final String filePath, final String password) throws IOException, ParseException {

        final String pdfToText = PdfHelper.getText(filePath, password);
        System.out.println(pdfToText);

        String[] documentLines = pdfToText.split("\r\n|\r|\n");

        final List<Note> notesList = new ArrayList<>();
        Note note = new Note();
        List<NoteItem> businessItems = new ArrayList<>();

        for (int i = 0; i < documentLines.length; i++) {
            setNumber(documentLines, i, note);
            setDate(documentLines, i, note);
            setInstitution(documentLines, i, note);
            setRegister(documentLines, i, note);
            setSettlementFee(documentLines, i, note);
            setRegistrationFee(documentLines, i, note);
            setTotalBovespa(documentLines, i, note);
            setTotalOperationCost(documentLines, i, note);
            setLiquidFor(documentLines, i, note);
            setTotalTypeMarket(documentLines, i, note);
            setItems(documentLines, i, businessItems);

            //FINISH SHEET NOTE
            if (documentLines[i].contains(NoteConstant.FINISH_SHEET_NOTE)) {
                note.setItems(businessItems);
                notesList.add(note);
                note = new Note();
                businessItems = new ArrayList<>();
            }
        }
    }

    private void setNumber(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.NUMBER_NOTE)) {
            note.setNumber(documentLines[i + 1]);
        }
    }

    private void setDate(final String[] documentLines, final Integer i, Note note) throws ParseException {
        if (documentLines[i].contains(NoteConstant.DATE)) {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Date parsedDate = formatter.parse(documentLines[i + 1]);
            note.setDate(parsedDate);
        }
    }

    private void setInstitution(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(InstitutionEnum.XP.getDescription())) {
            note.setInstitution(InstitutionEnum.XP.getDescription());
        } else if (documentLines[i].contains(InstitutionEnum.CLEAR.getDescription())) {
            note.setInstitution(InstitutionEnum.CLEAR.getDescription());
        } else if (documentLines[i].contains(InstitutionEnum.INTER.getDescription())) {
            note.setInstitution(InstitutionEnum.INTER.getDescription());
        }
    }

    private void setRegister(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.REGISTER)) {
            note.setRegister(documentLines[i - 1]);
        }
    }

    //LIQUIDO PARA
    private void setTotalTypeMarket(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.BUSINESS_SUMMARY)) {
            note.setTotalInCashSales(FormatHelper.stringToBigDecimal(documentLines[i - 7]));
            note.setTotalInCashPurchases(FormatHelper.stringToBigDecimal(documentLines[i - 6]));
            note.setTotalOptionsPurchase(FormatHelper.stringToBigDecimal(documentLines[i - 5]));
            note.setTotalOptionsSale(FormatHelper.stringToBigDecimal(documentLines[i - 4]));
        }
    }

    //TAXA DE LIQUIDACAO
    private void setSettlementFee(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.SETTLEMENT_FEE)) {
            final String[] value = documentLines[i].split(NoteConstant.SETTLEMENT_FEE);
            note.setSettlementFee(FormatHelper.stringToBigDecimal(value[0]));
        }
    }

    //TAXA DE REGISTRO
    private void setRegistrationFee(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.REGISTRATION_FEE)) {
            final String[] value = documentLines[i].split(NoteConstant.REGISTRATION_FEE);
            note.setRegistrationFee(FormatHelper.stringToBigDecimal(value[0]));
        }
    }

    //TOTAL BOVESPA
    private void setTotalBovespa(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.TOTAL_BOVESPA)) {
            final String[] value = documentLines[i].split(NoteConstant.TOTAL_BOVESPA);
            note.setTotalFeeBovespa(FormatHelper.stringToBigDecimal(value[0]));
        }
    }

    //LIQUIDO PARA
    private void setLiquidFor(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.LIQUID_FOR)) {
            final String[] value = documentLines[i].split(NoteConstant.LIQUID_FOR);
            note.setLiquidFor((FormatHelper.stringToBigDecimal(value[0])));
        }
    }

    //TOTAL CUSTOS / DESPESAS OPERACIONAIS
    private void setTotalOperationCost(final String[] documentLines, final Integer i, Note note) {
        if (documentLines[i].contains(NoteConstant.TOTAL_OPERATION_COST)) {
            final String[] value = documentLines[i].split(NoteConstant.TOTAL_OPERATION_COST);
            note.setTotalOperationCost((FormatHelper.stringToBigDecimal(value[0])));
        }
    }

    //ITEMS
    private void setItems(final String[] documentLines, final Integer i, List<NoteItem> items) {
        if (documentLines[i].contains(NoteConstant.BOVESPA)) {
            final NoteItem item = new NoteItem();
            final String[] itemArray = documentLines[i].split(" ");

            if (itemArray[2].contains(NoteConstant.SELL_OPTION)) {
                item.setTypeMarket(NoteConstant.SELL_OPTION);
            } else {
                item.setTypeMarket(NoteConstant.IN_CASH);
            }

            item.setTypeOperation(itemArray[1]);
            item.setTypeTransaction(itemArray[itemArray.length - 1]);

            item.setPrice(FormatHelper.stringToBigDecimal(itemArray[itemArray.length - 2]));
            item.setPriceUnit(FormatHelper.stringToBigDecimal(itemArray[itemArray.length - 3]));
            item.setQuantity(FormatHelper.stringToBigDecimal(itemArray[itemArray.length - 4]));

            items.add(item);
        }
    }


}
