package Application.Model;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Entity
@Table(name="car")
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String culoare;
    @Column(nullable = false)
    private String nrInmatriculare;
    @Column(nullable = false)
    private String locatie;
    @Column(nullable = false)
    private double pret;
    @Column(nullable = false)
    private String recenzii;
    @Column(nullable = false)
    private String path;

    public Car()
    {

    }

    public Car(String model, String culoare, String nrInmatriculare, String locatie, double pret, String path)
    {
        this.model = model;
        this.culoare = culoare;
        this.nrInmatriculare = nrInmatriculare;
        this.locatie = locatie;
        this.pret = pret;
        this.recenzii="";
        this.path="/images/"+path;
    }

    public int getId()
    {
        return id;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getCuloare()
    {
        return culoare;
    }

    public void setCuloare(String culoare)
    {
        this.culoare = culoare;
    }

    public String getNrInmatriculare()
    {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare)
    {
        this.nrInmatriculare = nrInmatriculare;
    }

    public String getLocatie()
    {
        return locatie;
    }

    public void setLocatie(String locatie)
    {
        this.locatie = locatie;
    }

    public double getPret()
    {
        return pret;
    }

    public void setPret(double pret)
    {
        this.pret = pret;
    }

    public String getRecenzii()
    {
        return recenzii;
    }

    public void setRecenzii(String recenzii)
    {
        this.recenzii += recenzii;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = "images/"+path;
    }

}
