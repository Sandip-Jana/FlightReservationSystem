package flightreservation;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TextBasedUi 
{
    public int numberoftickets=0,numberofcomboflights,day;
    public String city="",date="";
    public void TextBasedUiDesign(DisplayManager object)
    {
        displayTextBased();
        ComboFlight[] cf=null;
        int counter=0;
        do{
            if(counter==0)
                counter=1;
            else
            {
            System.out.println("Sorry! No Fights Available on this Date.");
            System.out.println();
            System.out.println("Please Select Another Date");
            displayTextBased();
            }
            SearchManager ob=new SearchManager();
            StringTokenizer st=new StringTokenizer(date,"/");
            day=Integer.parseInt(st.nextToken());
            cf=ob.GetFlightDetails(day,city,numberoftickets,object);
        }while(cf==null || cf[0]==null);
        ComboFlight finalcomboflightsarray[]=SortComboFlightsTextBased(cf);
        System.out.println();
        System.out.print("SpiceFlightId"+" "+"Departure"+" ");
        System.out.print("Arrival"+" "+"Intermediate"+" ");
        System.out.print("SilkFlightId"+" "+"Departure"+" ");
        System.out.print("Arrival"+" "+"Duration"+" ");
        System.out.println();
        for(int j=0;j<numberofcomboflights;j++)
        {
            System.out.print(""+(j+1)+" ");
            showdetail(finalcomboflightsarray[j]);
        }
        System.out.println("Please enter your selection (1 to "+ numberofcomboflights +"):");
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        int choice=0;
        try {
            do
            {
               choice = Integer.parseInt(in.readLine());
            }while(choice<1 || choice>numberofcomboflights);
        } catch (IOException ex) {
            Logger.getLogger(TextBasedUi.class.getName()).log(Level.SEVERE, null, ex);
        }
        choice=choice-1;
        System.out.println("You have selected ...");
        showdetail(finalcomboflightsarray[choice]);
        System.out.println("Proceed to Booking (y/n)?");
        try {
            char ch=(char)in.read();
            if(ch!='n')
            {
                System.out.println("Booking");
                showdetail(finalcomboflightsarray[choice]);
                String detail[]=getDetail(finalcomboflightsarray[choice]);
                updatePassengers(object,finalcomboflightsarray[choice]);
                WriteCsv wc=new WriteCsv();
                wc.WriteBookingCsv("Random","@",detail);
                wc.writeRemainingPassengers(detail);
                System.out.println("Congratulation! Your flight reservation was successful!");
                System.out.println();
                return;
            }
            else
                return;
        } catch (IOException ex) {
            Logger.getLogger(TextBasedUi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ComboFlight[] SortComboFlightsTextBased(ComboFlight[] cf)
    {
        int count=0; // count the number of comboflights
        for(int i=0;i<50;i++)
        {
            if(cf[i]==null)
            {
                break;
            }
            else
                count+=1;
        }
        numberofcomboflights=count;
         Arrays.sort(cf,0,count,new Comparator<ComboFlight>(){
            @Override  
            public int compare(ComboFlight x,ComboFlight y)
                {
                   if(x.hour==y.hour)
                       return x.min-y.min;
                   else
                       return x.hour-y.hour;
                }
        });
         return cf;
        }
    public String[] getDetail(ComboFlight cf)
    {
                String detail[]=new String[8];
                detail[0]=(cf.spiceflightnumber+" ");
                detail[1]=(cf.spicedepareture+" ");
                detail[2]=(cf.spicearrival+" ");
                detail[3]=(cf.intermediate+" ");
                detail[4]=(cf.silkflightnumber+" ");
                detail[5]=(cf.silkdeparture+" ");
                detail[6]=(cf.silkarrival+" ");
                detail[7]=(cf.duration+" ");
                return detail;
    }
    public void showdetail(ComboFlight cf)
    {
                System.out.print(cf.spiceflightnumber+" ");
                System.out.print(cf.spicedepareture+" ");
                System.out.print(cf.spicearrival+" ");
                System.out.print(cf.intermediate+" ");
                System.out.print(cf.silkflightnumber+" ");
                System.out.print(cf.silkdeparture+" ");
                System.out.print(cf.silkarrival+" ");
                System.out.print(cf.duration+" ");
                System.out.println();
    }
    public void displayTextBased()
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        try {
            char ch;
            do{
        System.out.println("Welcome to the Flight Reservation System!");
        System.out.println("=========================================");
        do
        {
        System.out.println("Please enter Date of Travel (dd/mm/yyyy):");
        date=in.readLine();
        }while(checkValidDate(date)==false);
        System.out.println();
        System.out.println("Please enter Source City (DELHI, MUMBAI or PUNE):");
        city=in.readLine();
        city.toUpperCase();
        System.out.println();
        System.out.println("Destination City is SINGAPORE");
        System.out.println();
        System.out.println("Please enter Number of Tickets (1 to 10):");
        numberoftickets=Integer.parseInt(in.readLine());
        System.out.println();
        System.out.println("You have entered: ");
        System.out.println("Date of Travel - "+date);
        System.out.println("Source City - "+city);
        System.out.println("Number of Tickets - "+numberoftickets);
        System.out.print("Proceed to Search (y/n)?");
        ch=(char)in.read();
        }while(ch=='n');
        } catch (IOException ex) {
            Logger.getLogger(TextBasedUi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public boolean checkValidDate(String date)
    {
        StringTokenizer st=new StringTokenizer(date,"/");
        int day=Integer.parseInt(st.nextToken());
        int month=Integer.parseInt(st.nextToken());
        int year=Integer.parseInt(st.nextToken());
        if(year==2015 && month==10)
        {
            if(day>=1 && day<=24)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public void updatePassengers(DisplayManager ob,ComboFlight cf)
    {
        // update the remaining passengers field for both the domestic and international flight...
            WriteCsv wc=new WriteCsv();//Update Remaining Seats
            int u=ob.domesticflights[day].numberofflights;
            for(int i=0;i<u;i++)
            {
                if(ob.domesticflights[day].arr[i].origin.equals(city) && ob.domesticflights[day].arr[i].destination.equals(cf.intermediate))
                {
                    if(ob.domesticflights[day].arr[i].arrival.equals(cf.spicearrival))
                    {
                        if(ob.domesticflights[day].arr[i].departure.equals(cf.spicedepareture))
                        {
                            int t=ob.domesticflights[day].arr[i].numberofpassengers;
                            ob.domesticflights[day].arr[i].numberofpassengers=(t+numberoftickets);
                            wc.getDomesticDayNumber(day,ob.domesticflights[day].arr[i].numberofpassengers,city); 
                            break;
                        }
                    }
                }
            }
            u=ob.internationalflights[day].numberofFlights;
            int v=ob.internationalflights[day+1].numberofFlights;
            String intermediate=cf.intermediate.toLowerCase();
            int k=0;
            for(int i=0;i<u;i++)
            {
                String internationalSource=(ob.internationalflights[day].arr[i].Source).toLowerCase();
                if(internationalSource.contains(intermediate))
                {
                    if(ob.internationalflights[day].arr[i].Arrival.equals(cf.spicearrival))
                    {
                        if(ob.internationalflights[day].arr[i].Departure.equals(cf.silkdeparture))
                        {
                            ob.internationalflights[day].arr[i].numberofpassengers+=numberoftickets;
                            wc.getInternationalDay(day,ob.internationalflights[day].arr[i].numberofpassengers);
                            k=1;
                            break;
                        }
                    }
                }
            }
            if(k==0)
            {
                for(int i=0;i<v;i++)
                {
                String internationalSource=(ob.internationalflights[day+1].arr[i].Source).toLowerCase();
                if(internationalSource.contains(intermediate))
                {
                    if(ob.internationalflights[day+1].arr[i].Arrival.equals(cf.silkarrival))
                    {
                        if(ob.internationalflights[day+1].arr[i].Departure.equals(cf.silkdeparture))
                        {
                            ob.internationalflights[day+1].arr[i].numberofpassengers+=numberoftickets;
                            wc.getInternationalDay(day+1,ob.internationalflights[day+1].arr[i].numberofpassengers);
                            break;
                        }
                    }
                }
                }
            }
    }
}
