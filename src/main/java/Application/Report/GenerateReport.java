package Application.Report;

import Application.Model.Rent;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
public interface GenerateReport
{
    void generateRaport(List<Rent> rents) throws IOException;
}
