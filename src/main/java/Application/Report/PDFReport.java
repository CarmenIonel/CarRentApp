package Application.Report;

import Application.Model.Rent;
import Application.Repository.RentRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Component
public class PDFReport implements GenerateReport
{
    @Autowired
    private RentRepository rr;

    private static String FILE = "E:\\SemII\\PS\\ProiectFinal\\src\\main\\resources\\PDFreport.pdf";
    private static Font font = new Font("TimesRoman", Font.PLAIN, 20);

    private static void addEmptyLine(Paragraph paragraph, int number)
    {
        for (int i = 0; i < number; i++)
        {
            paragraph.add(new Paragraph(" "));
        }
    }
    private void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add("Report");
        addEmptyLine(preface,2);
        preface.add(new Paragraph());
        document.add(preface);
    }

    private void addContent(Document document, List<Rent> rents)throws DocumentException
    {
        //table
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Numar Inmatriculare"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Model"));
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Culoare"));
        table.addCell(c1);

        for(int i=0; i<rents.size(); i++)
        {
            table.addCell(rents.get(i).getCar().getNrInmatriculare());
            table.addCell(rents.get(i).getCar().getModel());
            table.addCell(rents.get(i).getCar().getCuloare());
        }
        document.add(table);
    }

    private void addMetaData(Document document)
    {
        document.addTitle("PDF Report");
        document.addAuthor("Ionel Carmen");
        document.addCreator("Ionel Carmen");
    }

    public void generateRaport(List<Rent> rents) throws IOException
    {
        try
        {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document,rents);
            document.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
