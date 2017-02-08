package flightreservation;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
public class ReadRemainingSeats 
{
    public void read(DisplayManager ob)
    {
        BufferedReader inn=null;
        try
        {
            inn=new BufferedReader(new FileReader("RemainingSeats.csv"));
        }
        catch(Exception e){}
        String input;
        try {
            while(((input=inn.readLine())!=null) && (input.length()!=0))
            {
                String internationalFlight=inn.readLine();
                StringTokenizer st=new StringTokenizer(input,",");
                StringTokenizer st1=new StringTokenizer(internationalFlight,",");
                int day=Integer.parseInt(st.nextToken());
                int u=ob.domesticflights[day].numberofflights;
                String city=st.nextToken();
                String destination=st.nextToken();
                String arrival=st.nextToken();
                String departure=st.nextToken();
                int numberofpassengers=Integer.parseInt(st.nextToken());
                for(int i=0;i<u;i++)
                {
                if(ob.domesticflights[day].arr[i].origin.equals(city) && ob.domesticflights[day].arr[i].destination.equals(destination))
                {
                    if(ob.domesticflights[day].arr[i].arrival.equals(arrival))
                    {
                        if(ob.domesticflights[day].arr[i].departure.equals(departure))
                        {
                            ob.domesticflights[day].arr[i].numberofpassengers=numberofpassengers;
                            break;
                        }
                    }
                }
                }//end of for loop
                day=Integer.parseInt(st1.nextToken());
                u=ob.internationalflights[day].numberofFlights;
                String intermediate=st1.nextToken().toLowerCase();
                destination=st1.nextToken();
                arrival=st1.nextToken();
                departure=st1.nextToken();
                numberofpassengers=Integer.parseInt(st1.nextToken());
                for(int i=0;i<u;i++)
                {
                String internationalSource=(ob.internationalflights[day].arr[i].Source).toLowerCase();
                if(internationalSource.contains(intermediate))
                {
                    if(ob.internationalflights[day].arr[i].Arrival.equals(arrival))
                    {
                        if(ob.internationalflights[day].arr[i].Departure.equals(departure))
                        {
                            ob.internationalflights[day].arr[i].numberofpassengers=numberofpassengers;
                            break;
                        }
                    }
                }
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ReadRemainingSeats.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

