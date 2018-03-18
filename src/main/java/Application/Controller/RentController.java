package Application.Controller;

import Application.Model.Car;
import Application.Model.Rent;
import Application.Model.User;
import Application.Repository.CarRepository;
import Application.Repository.RentRepository;
import Application.Repository.UserRepository;
import Application.Utility.Mail;
import Application.Utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ionel Carmen on 25.05.2017.
 */
@Controller
public class RentController
{
    @Autowired
    private RentRepository rr;

    @Autowired
    private UserRepository ur;

    @Autowired
    private CarRepository cr;

    private Utils utils=new Utils();
    private final String from="rentcarapplication@gmail.com";

    public boolean isAvailable(Car c, Date d1, Date d2)
    {
        List<Rent> rents=rr.findByCar(c);
        if(rents.size()==0)
        {
            return true;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        boolean x=false;
        for(int i=0; i<rents.size(); i++)
            if(rents.get(i). getCar().getNrInmatriculare().equalsIgnoreCase(c.getNrInmatriculare()))
            {
                if(utils.verifDate(df.format(rents.get(i).getStart()),df.format(d1))&& utils.verifDate(df.format(d1), df.format(rents.get(i).getEnd())))
                    return false;
                if(utils.verifDate(df.format(rents.get(i).getStart()),df.format(d2))&& utils.verifDate(df.format(d2), df.format(rents.get(i).getEnd())))
                    return false;
                if(utils.verifDate(df.format(d1),df.format(rents.get(i).getStart())) &&
                        utils.verifDate(df.format(d1),df.format(rents.get(i).getEnd())) &&
                        utils.verifDate(df.format(rents.get(i).getStart()),df.format(d2)) &&
                        utils.verifDate(df.format(rents.get(i).getEnd()),df.format(d2)))
                    return false;
            }
        return true;
    }

    //add a rent
    @RequestMapping(value="/addRent", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/addRent";
    }
    @RequestMapping(value="/addRent", method= RequestMethod.POST)
    public String addRent(HttpServletRequest ht)throws Exception
    {
        try
        {
            Rent rent=null;
            User user = null;
            user=ur.findByUsername(ht.getRemoteUser());
            Car c=null;
            c=cr.findByNrInmatriculare(ht.getParameter("carNr"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d1 = df.parse(utils.date(ht.getParameter("data1")));
            Date d2=df.parse(utils.date(ht.getParameter("data2")));
            Date cur=new Date();
            String code=utils.genCode();
            if(utils.verifDate(utils.date(ht.getParameter("data1")),utils.date(ht.getParameter("data2"))) &&
                    utils.verifDate(df.format(cur),utils.date(ht.getParameter("data1"))))
                if(isAvailable(c,d1,d2))
                {
                    rent=new Rent(d1,d2,user,c,code);
                    rent.setPret(utils.calculatePret(c,d1,d2));
                }
            rr.save(rent);
            Mail m=new Mail();
            String to=user.getEmail();
            m.send(from,to,"Your code is",code);
            return "/addRent";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //delete rent
    @RequestMapping(value="/delRent", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {
        return "/delRent";
    }
    @RequestMapping(path="/delRent", method= RequestMethod.POST)
    public String deleteRent(HttpServletRequest ht)throws Exception
    {
        try
        {
            Rent rent = null;
            rent = rr.findByCode(ht.getParameter("search"));
            if(rent !=null)
            {
                User user = rent.getUser();
                if (user.getUsername().equalsIgnoreCase(ht.getRemoteUser()))
                {
                    rent.setCar(null);
                    rent.setUser(null);
                    rr.save(rent);
                    rr.delete(rent);
                }
            }
            return "/delRent";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //update rent
    @RequestMapping(value="/updateRent", method= RequestMethod.GET)
    public String u(HttpServletRequest ht)
    {
        return "/updateRent";
    }
    @RequestMapping(path="/updateRent", method= RequestMethod.POST)
    public String updateRent(HttpServletRequest ht) throws Exception
    {
        try
        {
            Rent rent=null;
            User user = null;
            Car c=null;
            rent=rr.findByCode(ht.getParameter("search"));
            if(rent!=null)
            {
                user=rent.getUser();
                c=rent.getCar();
                if(user.getUsername().equals(ht.getRemoteUser()))
                {
                    if(ht.getParameter("carNr")!="")
                    {
                        c=cr.findByNrInmatriculare(ht.getParameter("carNr"));
                        if(c!=null)
                            rent.setCar(c);
                    }
                    if(ht.getParameter("data1")!="")
                    {
                        String code=rent.getCode();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date d1 = df.parse(utils.date(ht.getParameter("data1")));
                        Date d2=rent.getEnd();
                        String x=df.format(d2);
                        d2=df.parse(x);
                        Date cur=new Date();
                        rr.delete(rent);
                        if(utils.verifDate(utils.date(ht.getParameter("data1")),utils.date(df.format(d2))) &&
                                utils.verifDate(df.format(cur),utils.date(ht.getParameter("data1"))))
                        {
                            if (isAvailable(c, d1, d2))
                            {
                                rent.setStart(d1);
                                rent.setPret(utils.calculatePret(c, d1, d2));
                            }
                        }
                    }
                    if(ht.getParameter("data2")!="")
                    {
                        String code=rent.getCode();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date d1=rent.getStart();
                        Date d2=df.parse(utils.date(ht.getParameter("data2")));
                        String x=df.format(d1);
                        d1=df.parse(x);
                        Date cur=new Date();

                        rr.delete(rent);
                        rr.delete(rent);
                        if(utils.verifDate(utils.date(df.format(d1)),utils.date(ht.getParameter("data2"))) &&
                                utils.verifDate(df.format(cur),utils.date(df.format(d1))))
                            if(isAvailable(c,d1,d2))
                            {
                                rent=new Rent(d1,d2,user,c,code);
                                rent.setPret(utils.calculatePret(c,d1,d2));
                            }
                    }
                }
            }

            rr.save(rent);
            return "/updateRent";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "/error";
        }
    }

    //view
    @RequestMapping(value="/viewRent", method= RequestMethod.GET)
    public String e(HttpServletRequest ht, Model m)
    {
        User u=ur.findByUsername(ht.getRemoteUser());
        List<Rent> rent=rr.findByUser(u);
        m.addAttribute("rent", rent);
        return "/viewRent";
    }
}
