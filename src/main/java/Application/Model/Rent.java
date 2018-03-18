package Application.Model;

import Application.Utility.Utils;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Entity
@Table(name="rent")
public class Rent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private double pret;
    @Column(nullable = false)
    private Date start;
    @Column(nullable = false)
    private Date end;
    @Column(nullable = false)
    private String code;

    @OneToOne
    private User user;

    @OneToOne
    private Car car;

    public Rent()
    {

    }

    public Rent(Date start, Date end, User user, Car car, String code)
    {
        this.start = start;
        this.end = end;
        this.user = user;
        this.car = car;
        this.pret=0.0;
        this.code=code;
    }

    public int getId()
    {
        return id;
    }

    public double getPret()
    {
        return pret;
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setPret(double pret)
    {
        this.pret = pret;
    }

    public String getDataS()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d2=this.end;
        String x=df.format(d2);
        System.out.println(x);
        return x;
    }

    public String getDataE()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d2=this.start;
        String x=df.format(d2);
        System.out.println(x);
        return x;
    }
}
