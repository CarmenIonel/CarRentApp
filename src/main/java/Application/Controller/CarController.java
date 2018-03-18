package Application.Controller;

import Application.Model.Car;
import Application.Model.Rent;
import Application.Model.User;
import Application.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionel Carmen on 25.05.2017.
 */
@Controller
public class CarController
{
    @Autowired
    private CarRepository cr;

    //add car
    @RequestMapping(value="/addCar", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/addCar";
    }
    @RequestMapping(value="/addCar", method= RequestMethod.POST)
    public String addCar(HttpServletRequest ht)throws Exception
    {
        try
        {
            Car c = null;
            c=cr.findByNrInmatriculare(ht.getParameter("nr"));
            if(c==null)
                c = new Car(ht.getParameter("model"), ht.getParameter("culoare"), ht.getParameter("nr"), ht.getParameter("locatie"),
                        Double.parseDouble(ht.getParameter("price")),ht.getParameter("path"));
            cr.save(c);
            return "/addCar";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //update car
    @RequestMapping(value="/updateCar", method= RequestMethod.GET)
    public String u(HttpServletRequest ht)
    {
        return "/updateCar";
    }
    @RequestMapping(path="/updateCar", method= RequestMethod.POST)
    public String updateP(HttpServletRequest ht) throws Exception
    {
        try
        {
            Car c=null;
            c=cr.findByNrInmatriculare(ht.getParameter("search"));
            System.out.println(c.getPath());
            if (c != null)
            {
                if (ht.getParameter("model") != "")
                    c.setModel(ht.getParameter("model"));
                if (ht.getParameter("culoare") != "")
                    c.setCuloare(ht.getParameter("culoare"));
                if (ht.getParameter("nr") != "")
                    c.setNrInmatriculare(ht.getParameter("nr"));
                if (ht.getParameter("locatie") != "")
                    c.setLocatie(ht.getParameter("locatie"));
                if (ht.getParameter("price") != "")
                    c.setPret(Double.parseDouble(ht.getParameter("price")));
                if(ht.getParameter("path")!="")
                    c.setPath(ht.getParameter("path"));
            }
            System.out.println(c.getPath());
            cr.save(c);

            return "/updateCar";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "/error";
        }
    }

    //delete car
    @RequestMapping(value="/deleteCar", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {
        return "/deleteCar";
    }
    @RequestMapping(path="/deleteCar", method= RequestMethod.POST)
    public String deleteC(HttpServletRequest ht) throws Exception
    {
        try
        {
            Car c = null;
            c = cr.findByNrInmatriculare(ht.getParameter("search"));
            cr.delete(c);
            return "/deleteCar";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //add review
    @RequestMapping(value="/addReview", method= RequestMethod.GET)
    public String r(HttpServletRequest ht)
    {
        return "/addReview";
    }
    @RequestMapping(path="/addReview", method= RequestMethod.POST)
    public String rev(HttpServletRequest ht) throws Exception
    {
        try
        {
            Car c = null;
            c = cr.findByNrInmatriculare(ht.getParameter("search"));
            if(c!=null)
                c.setRecenzii(ht.getParameter("rev"));
            cr.save(c);
            return "/addReview";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //view
    @RequestMapping(value="/viewCars", method= RequestMethod.GET)
    public String e(HttpServletRequest ht, Model m)
    {
        List<Car> car=cr.findAll();
        m.addAttribute("car", car);
        return "/viewCars";
    }

    //view dupa model
    @RequestMapping(value="/searchM", method= RequestMethod.GET)
    public String m(HttpServletRequest ht)
    {
        return "/searchM";
    }
    @RequestMapping(path="/{model}/tabel", method= RequestMethod.GET)
    public String ms(@PathVariable String model, Model m)
    {
        List<Car> car=cr.findByModel(model);
        for(int i=0; i<car.size(); i++)
            System.out.println(car.get(i).getPath());
        m.addAttribute("car",car);
        return "/tabel";
    }

    @RequestMapping(path="/searchM", method= RequestMethod.POST)
    public String sm(HttpServletRequest ht) throws Exception
    {
        try
        {
            String model=ht.getParameter("search");
            if(cr.findByModel(model)!=null)
                return "redirect:/"+model+"/tabel";
            else
                return "/error";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }


    //view dupa pret
    @RequestMapping(value="/searchP", method= RequestMethod.GET)
    public String msd(HttpServletRequest ht)
    {
        return "/searchP";
    }
    @RequestMapping(path="/{x}/tabel2", method= RequestMethod.GET)
    public String ms1(@PathVariable String x, Model m)
    {
        String[] parts = x.split("T");
        Double p1=Double.parseDouble(parts[0]);
        Double p2=Double.parseDouble(parts[1]);
        List<Car> cars=cr.findAll();
        List<Car> aux=new ArrayList<Car>();
        for(int i=0; i<cars.size(); i++)
            if(cars.get(i).getPret()>=p1 && cars.get(i).getPret()<=p2)
                aux.add(cars.get(i));
        for(int i=0; i<aux.size(); i++)
            System.out.println(aux.get(i).getPath());
        m.addAttribute("car",aux);
        return "/tabel2";
    }
    @RequestMapping(path="/searchP", method= RequestMethod.POST)
    public String smw(HttpServletRequest ht) throws Exception
    {
        try
        {
            Double p1=Double.parseDouble(ht.getParameter("search1"));
            Double p2=Double.parseDouble(ht.getParameter("search2"));
            List<Car> cars=cr.findAll();
            System.out.println("cars"+cars.size());
            List<Car> aux=new ArrayList<Car>();
            for(int i=0; i<cars.size(); i++)
                if(cars.get(i).getPret()>=p1 && cars.get(i).getPret()<=p1)
                    aux.add(cars.get(i));
            String x="";
            x=x+p1;
            x=x+"T";
            x=x+p2;
            System.out.println("verificate"+x);
            if(cars!=null)
                return "redirect:/"+x+"/tabel2";
            else
                return "/error";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }
}
