package Application.Report;

import Application.Model.Rent;
import Application.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Component
public class CSVReport implements GenerateReport
{
    @Autowired
    private RentRepository rr;

    private static String FILE = "E:\\SemII\\PS\\ProiectFinal\\src\\main\\resources\\CSVreport.csv";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Numar Inmatriculare  Model   Culoare";

    public void generateRaport(List<Rent> rents) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(FILE);
            fileWriter.append("Report");
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            for(int i=0; i<rents.size(); i++)
            {
                fileWriter.append(rents.get(i).getCar().getNrInmatriculare());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(rents.get(i).getCar().getModel());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(rents.get(i).getCar().getCuloare());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
