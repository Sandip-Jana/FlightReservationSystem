package flightreservation;
import java.io.*;
public class WriteCsv 
{
    public static int day,internationalday,internationalpassengers,domesticpassengers;
    public static String city;
    public static void WriteBookingCsv(String username, String gmailid, String traveldetails[])
    {
        PrintWriter pw=null;
        try{
        pw=new PrintWriter(new FileWriter("Booking.csv",true));
        }catch(Exception e)
        {}
        pw.append(username+" ");
        pw.append(gmailid+" ");
        int l=traveldetails.length;
        for(int i=0;i<l;i++)
        pw.append(traveldetails[i]+" ");
        pw.append("\n");
        pw.close();
    }
    public void writeRemainingPassengers(String details[]) 
    {
        PrintWriter pwrite=null;
        try
        {
         pwrite=new PrintWriter(new FileWriter("RemainingSeats.csv",true));
        }
        catch(Exception e){} 
        pwrite.write(day+",");
        pwrite.write(city+",");
        pwrite.write(details[3]+",");
        pwrite.write(details[2]+",");
        pwrite.write(details[1]+",");
        pwrite.write(domesticpassengers+"\n");
        pwrite.write(internationalday+",");
        pwrite.write(details[3]+",");
        pwrite.write("Singapore"+",");
        pwrite.write(details[6]+",");
        pwrite.write(details[5]+",");
        pwrite.write(internationalpassengers+"\n");
        pwrite.close();
     }
    public void getDomesticDayNumber(int day,int domesticpassengers,String city)
    {
        this.day=day;
        this.city=city;
        this.domesticpassengers=domesticpassengers;
    }
    public void getInternationalDay(int day2,int internationalpassengers)
    {
        this.internationalday=day2;
        this.internationalpassengers=internationalpassengers;
    }
}
