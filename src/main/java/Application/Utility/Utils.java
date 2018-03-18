package Application.Utility;

import Application.Model.Car;

import java.util.Date;
import java.util.Random;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
public class Utils
{
    //make data
    public String date(String date)
    {
        String aux=new String();
        String[] parts = date.split("T");
        for(int i=0; i<parts.length; i++)
        {
            aux = aux.concat(parts[i]);
            aux=aux.concat(" ");
        }
        return aux;
    }

    //verificare date
    public boolean verifDate(String d1, String d2)
    {
        int a1, a2, m1, m2,day1,day2,h1,h2,min1,min2;
        a1=1000*d1.charAt(0)+100*d1.charAt(1)+10*d1.charAt(2)+d1.charAt(3);
        a2=1000*d2.charAt(0)+100*d2.charAt(1)+10*d2.charAt(2)+d2.charAt(3);

        m1=10*d1.charAt(5)+d1.charAt(6);
        m2=10*d2.charAt(5)+d2.charAt(6);

        day1=10*d1.charAt(8)+d1.charAt(9);
        day2=10*d2.charAt(8)+d2.charAt(9);

        h1=10*d1.charAt(11)+d1.charAt(12);
        h2=10*d2.charAt(11)+d2.charAt(12);

        min1=10*d1.charAt(14)+d1.charAt(15);
        min2=10*d2.charAt(14)+d2.charAt(15);

        if(a1<a2)
            return true;
        else
            if(a1==a2 && m1<m2)
                return true;
            else
                if(a1==a2 && m1==m2 && day1<day2)
                    return true;
                else
                    if(a1==a2 && m1==m2 && day1==day2 && h1<h2)
                        return true;
                    else
                        if(a1==a2 && m1==m2 && day1==day2 && h1==h2 && min1<min2)
                            return true;
                        else
                            if(a1==a2 && m1==m2 && day1==day2 && h1==h2 && min1==min2)
                                return true;
                            else
                                return false;
    }
    //generare cod
    public String genCode()
    {
        String code=new String();
        for(int i=0; i<8; i++)
        {
            Random random = new Random();
            int n = random.nextInt(69) + 32;
            if (n > 96) {
                n += 26;
            }
            char c = (char) n;
            code+=c;
        }
        return code;
    }

    //pret inchiriere
    public double calculatePret(Car c, Date start, Date end)
    {
        double pret=c.getPret();

        long diff=end.getTime()-start.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);

        long min=diffMinutes+diffHours*60;

        return pret*min;
    }
}
