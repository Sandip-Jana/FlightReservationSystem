package flightreservation;
class DomesticFlights
{
	DomesticFlightDetails arr[];
	int numberofflights;
	DomesticFlights()
	{
		arr=new DomesticFlightDetails[100];
                for(int i=0;i<100;i++)
                    arr[i]=new DomesticFlightDetails();
		numberofflights=0;
	}
}
