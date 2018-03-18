package Application.Controller;

import Application.Model.Car;
import Application.Report.GenerateReport;
import Application.Report.ReportFactory;
import Application.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Ionel Carmen on 26.05.2017.
 */
@Controller
public class ReportController
{
    //genereaza rapoarte
    @Autowired
    private RentRepository rr;

    @RequestMapping(value="/getReport", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {

        ReportFactory rf=new ReportFactory();
        GenerateReport report1, report2;
        report1=rf.getType("pdf");
        report2=rf.getType("csv");
        try
        {
            report1.generateRaport(rr.findAll());
            report2.generateRaport(rr.findAll());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "/index";
    }

}
