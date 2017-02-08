package flightreservation;
class DisplayManager
{
    public InternationalFlights internationalflights[];
    public DomesticFlights domesticflights[];
    public void displayDisplayPage(InternationalFlights temp1[], DomesticFlights temp2[],DisplayManager object,int arguments)
    {
        object.internationalflights=temp1;
        object.domesticflights=temp2;
        ReadRemainingSeats rrs=new ReadRemainingSeats();
        rrs.read(object);            // update remaining passengers in flights
        if(arguments!=2)
        DisplayScreen1(object);
            
    }
    public void DisplayScreen1(DisplayManager object)
    {
        new DisplayPage(object);    //display Screen1 for users inputs
    }
    public void DisplayScreen2(int day, String city, int numberofpassengers, DisplayManager object)
    {
        SearchManager ob=new SearchManager();
        ComboFlight[] cf=ob.GetFlightDetails(day,city,numberofpassengers,object);
        int daynumber=ob.getdaynum();
        SortComboFlights objectscf=new SortComboFlights();
        cf=objectscf.sortcomboflight(cf,object);
        if(cf==null)
        {
            object.DisplayScreen1(object);//if No Flights Available display screen 1
        }
        else
        {
        DisplayTable screen2=new DisplayTable();//display available flights
        screen2.displaytable(city,cf,daynumber,object,numberofpassengers);
        }
    }
    public void DisplayScreen3(String result[], DisplayManager object)
    {
        DisplayTicket ob=new DisplayTicket();//Display the ticket
        ob.getValues(result,object);
        ob.setVisible(true);
    }
}
