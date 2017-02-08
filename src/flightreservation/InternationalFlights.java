package flightreservation;
class InternationalFlights
{
	InternationalFlightDetails arr[];
	int numberofFlights;
	InternationalFlights()
	{
		arr=new InternationalFlightDetails[100];
                for(int i=0;i<100;i++)
                    arr[i]=new InternationalFlightDetails();
		numberofFlights=0;
	}
}