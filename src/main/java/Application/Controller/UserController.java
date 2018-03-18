package Application.Controller;

import Application.Model.User;
import Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ionel Carmen on 24.05.2017.
 */
@Controller
public class UserController
{
    @Autowired
    private UserRepository ur;

    //add User(register)
    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/register";
    }
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String addUser(HttpServletRequest ht)throws Exception
    {
        try
        {
            User user = null;
            user=ur.findByUsername(ht.getParameter("username"));
            System.out.println(user==null);
            if(user==null && ht.getParameter("password").equals(ht.getParameter("confirm")))
                user = new User(ht.getParameter("name"), ht.getParameter("address"), ht.getParameter("phone"), ht.getParameter("email"),
                     ht.getParameter("username"), ht.getParameter("password"),"");
            System.out.println(user.getName());
            ur.save(user);
            return "/login";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //update user (doar pe el insusi)
    @RequestMapping(value="/updateUser", method= RequestMethod.GET)
    public String u(HttpServletRequest ht)
    {
        return "/updateUser";
    }
    @RequestMapping(path="/updateUser", method= RequestMethod.POST)
    public String updateUser(HttpServletRequest ht) throws Exception
    {
        try
        {
            User user = null;
            if(ht.getRemoteUser().equalsIgnoreCase(ht.getParameter("search")))
            {
                user = ur.findByUsername(ht.getParameter("search"));
                if (user != null) {
                    if (ht.getParameter("name") != "")
                        user.setName(ht.getParameter("name"));
                    if (ht.getParameter("address") != "")
                        user.setAddress(ht.getParameter("address"));
                    if (ht.getParameter("email") != "")
                        user.setEmail(ht.getParameter("email"));
                    if (ht.getParameter("phone") != "")
                        user.setPhone(ht.getParameter("phone"));
                    if (ht.getParameter("password") != "")
                        user.setPassword(ht.getParameter("password"));
                    if (ht.getParameter("username") != "")
                        user.setUsername(ht.getParameter("username"));
                }
            }
            ur.save(user);
            return "/updateUser";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //delete user( doar pe el insusi)
    @RequestMapping(value="/deleteUser", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {
        return "/deleteUser";
    }
    @RequestMapping(path="/deleteUser", method= RequestMethod.POST)
    public String deleteUser(HttpServletRequest ht)throws Exception
    {
        try
        {
            User user = null;
            user = ur.findByUsername(ht.getParameter("search"));
            if(user !=null && user.getUsername().equalsIgnoreCase(ht.getRemoteUser()))
                ur.delete(user);
            else
                user=null;
            return "/register";
        }
        catch(Exception e)
        {
            return "/error";
        }
    }

    //view user (doar pe el)
    @RequestMapping(value="viewUser", method= RequestMethod.GET)
    public String e(HttpServletRequest ht, Model m)
    {
        User u=ur.findByUsername(ht.getRemoteUser());
        m.addAttribute("user", u);
        return "/viewUser";
    }

    @RequestMapping(value = "/index/poll",method = RequestMethod.POST)
    public @ResponseBody
    Boolean poll(HttpServletRequest request)
    {
//        System.out.println("o intrat");
//        try
//        {
//            List<User> user=ur.findAll();
//           // User us=ur.findByUsername(request.getRemoteUser());
//            for(int i=0; i<user.size(); i++)
//                if ( user.get(i).getShouldBeNotified()!="")// && us.getRole().equalsIgnoreCase("ROLE_ADMIN"))
//                {
//                    return true;
//                }
//            return null;
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            return null;
//        }
        return true;
    }
}
