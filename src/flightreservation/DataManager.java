package flightreservation;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
class DataManager
{
public InternationalFlights[] ReadsilkFile(String filename)//Creating object array of class InternationalFlights
{
    BufferedReader in=null;
    try {
        in=new BufferedReader(new FileReader(filename));//Taking the input stream of file
    } catch (FileNotFoundException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    String s;
    InternationalFlights internationalflights[]=new InternationalFlights[24+2];//array of international flights created
                for(int i=0;i<26;i++)
                    internationalflights[i]=new InternationalFlights();
    try{
		String d[]={"Wed","Thu","Fri","Sat","Sun",
				    "Mon","Tue","Wed","Thu","Fri","Sat","Sun",
				    "Mon","Tue","Wed","Thu","Fri","Sat","Sun",
				    "Mon","Tue","Wed","Thu","Fri","Sat","Sun"};//calendar for the month of October created
		while((s=in.readLine())!=null && s.length()!=0)
		{
		StringTokenizer st=new StringTokenizer(s,"\\|");
		String sectors[]={"Bengaluru (BLR)","Chennai (MAA)","Hyderabad (HYD)","Kolkata (CCU)"};//Four sectors created fro domestic flight as per the question
		String destination=st.nextToken();//Extracting the source from where flight starts after tokenizing
		destination=destination.trim();
				if(destination!=null && (destination.equals(sectors[0]) || destination.equals(sectors[1]) || destination.equals(sectors[2]) || destination.equals(sectors[3]) ))
				{
					String Source=destination;
					String flightday=st.nextToken().trim();//extracting flight day
					String FlightNo=st.nextToken().trim();//extracting flight number
					String flightTiming=st.nextToken();//extracting flight timing
					flightTiming=flightTiming.trim();
                                        StringTokenizer splitflighttime=new StringTokenizer(flightTiming,"/");
                                        String departuretime=splitflighttime.nextToken(); 
                                        String arrivaltime=splitflighttime.nextToken();
                                        int Delay=0;
					if(arrivaltime.contains("+"))
					{
						StringTokenizer splitarrivaltime=new StringTokenizer(arrivaltime,"\\+");
						arrivaltime=splitarrivaltime.nextToken();
						Delay=Integer.parseInt(splitarrivaltime.nextToken());
					}
					String Departure=departuretime;//Extracting and initializing departure time
					String Arrival=arrivaltime;//Extracting and initializing arrival time
					int delay=Delay;
					flightday=flightday.trim();
					String getdays[]=flightday.split(",");//Returns list of days for each particular flight 
					for(int q=0;q<getdays.length;q++)//Loop control which runs upto the number of flights valid for a particular day
					{
						for(int k=1;k<=25;k++)//loop control running for the days in october
						{
							if((getdays[q].trim()).equals(d[k]))
							{	
							 int get=internationalflights[k].numberofFlights;//nth flight for kth day
							 internationalflights[k].arr[get].Arrival=Arrival;//returns arrival time
                                                         internationalflights[k].arr[get].Departure=Departure;//returns departure time
                                                         internationalflights[k].arr[get].FlightNo=FlightNo;//returns number of flights
                                                         internationalflights[k].arr[get].Source=Source;//returns sourceflight
                                                         internationalflights[k].arr[get].delay=delay;
                                                         internationalflights[k].arr[get].numberofpassengers=0;
							 internationalflights[k].numberofFlights+=1;//Returns number of flights for that day and moves pointer to the next	
							}
						}
					}
				}
			}
     	  in.close();
    }
    catch(IOException e)
    {
       Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
    }
    finally {
           if(in != null) {
               try {
                   in.close();
               }
               catch (IOException e) {
                  Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
               }
           }
    }
                return internationalflights;
          }
public DomesticFlights[] ReadSpicejet(String filename)throws IOException//Creating obect array of domestic flights
		{
                    BufferedReader in=null;
                    try
                    {
			in=new BufferedReader(new FileReader(filename));	
                    }
                    catch(Exception e){
                    Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
                    }
                       	DomesticFlights domesticflights[]=new DomesticFlights[24+1];
			for(int i=0;i<25;i++)
				domesticflights[i]=new DomesticFlights();
			String city[]={"DELHI","MUMBAI","PUNE"};
			String d[]={"WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY",
				    "MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY",
				    "MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY",
				    "MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};//creating calendar fpr domestic flights
			String input;
			try
                        {
                        while((input=in.readLine())!=null && input.length()!=0)
			{
				StringTokenizer st=new StringTokenizer(input,"\\|");
				int countTokens=st.countTokens();//counting the number of tokens in each string
				String SourceCity=st.nextToken();//Extracts source city
				SourceCity=SourceCity.trim();
				if(SourceCity!=null && SourceCity.length()!=0 && (SourceCity.equals(city[0]) || SourceCity.equals(city[1]) || SourceCity.equals(city[2])) && countTokens>=9)
				{
					String origin=SourceCity;//Initializing origin with source city
					String destination=st.nextToken().trim();//returns destination
					String daydomestic=st.nextToken().trim();//returns daydomesticflight
					int checkDaily;
					if(daydomestic.equals("DAILY"))//checks daily flight
						checkDaily=1;
					else
						checkDaily=0;
					String flightNumber=st.nextToken().trim();//returns flight number
					String departure=st.nextToken().trim();//returns departure
					String arrival=st.nextToken().trim();//returns arrival time
					String via=st.nextToken().trim();
					String validDate=checkvalid(st.nextToken().trim(),st.nextToken().trim());//returns validitydate
					StringTokenizer validity=new StringTokenizer(validDate);
					int counttokens=validity.countTokens();//counts if tokens are valid to two since there are two valid dates
					if(counttokens==2)
					{
							int start=Integer.parseInt(validity.nextToken());
							int end=Integer.parseInt(validity.nextToken());
							if(checkDaily==1)
							{
								for(int k=start;k<=end;k++)//loop control for startdate and end date
								{
									int get=domesticflights[k].numberofflights;
									domesticflights[k].arr[get].arrival=arrival;
									domesticflights[k].arr[get].departure=departure;
									domesticflights[k].arr[get].destination=destination;
									domesticflights[k].arr[get].flightnum=flightNumber;
									domesticflights[k].arr[get].numberofpassengers=0;
									domesticflights[k].arr[get].origin=origin;
									domesticflights[k].arr[get].via=via;
									domesticflights[k].numberofflights+=1;
								}
							}
							else if(checkDaily==0)
							{
								StringTokenizer daysplit=new StringTokenizer(daydomestic,",");
								int numtoken=daysplit.countTokens();
								for(int jj=0;jj<numtoken;jj++)
								{
									String daytoken=daysplit.nextToken().trim();
									for(int k=start;k<=end;k++)
									{
										if(daytoken.equals(d[k]))
										{
											int get=domesticflights[k].numberofflights;
											domesticflights[k].arr[get].arrival=arrival;
											domesticflights[k].arr[get].departure=departure;
											domesticflights[k].arr[get].destination=destination;
											domesticflights[k].arr[get].flightnum=flightNumber;
											domesticflights[k].arr[get].numberofpassengers=0;
											domesticflights[k].arr[get].origin=origin;
											domesticflights[k].arr[get].via=via;
											domesticflights[k].numberofflights+=1;
										}//end of if
									}//end of k
								}//end of j
							}
					}
			}//end of token
			}//end of readLine
		in.close();
                        }
                        catch(IOException e)
                        {
                        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
                        }
                        finally 
                        {
                        if(in != null) {
                            try {
                             in.close();
                                 }
                                 catch (IOException e) {
                                   Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, e);
                                  }
                                }
                         }//end of finally block
                return domesticflights;
		}
		public static String checkvalid(String a,String b)
		{

			StringTokenizer startdate=new StringTokenizer(a); 
			StringTokenizer enddate=new StringTokenizer(b);
			int startday=Integer.parseInt(startdate.nextToken());
			int startmonth=getstringnum(startdate.nextToken());
			int startyear=Integer.parseInt(startdate.nextToken());
			int endday=Integer.parseInt(enddate.nextToken());
			int endmonth=getstringnum(enddate.nextToken());
			int endyear=Integer.parseInt(enddate.nextToken());
			if(startyear<=15 && endyear>=15)
			{
				if(startyear==15 && endyear==15)
				{
					if(startmonth<=10 && endmonth>=10)
					{
						if(startmonth==10 && endmonth==10)
						{
							if(startday<=24)
							{
							if(endday>24)
							return ""+startday+" 24";
							else
							return ""+startday+" "+endday;
							}
							else
							return "0";
						}
						else if(startmonth==10 && endmonth>10)
						{
							if(startday<=24)
							{
								return ""+startday+" 24";
							}
							else
								return "0";
						}
						else if(startmonth<10 && endmonth==10)
						{
							if(endday>24)
							return "1 24";
							else
							return "1"+" "+endday;
						}
						else
						{
							return "1 24";
						}
					}
					else
						return "0";
				}
				else if(startyear<15 && endyear==15)
				{
					if(endmonth<10)
						return "0";
					else
					{
						if(endmonth==10)
						{
							if(endday>24)
							return "1 24";
							else
							return "1 "+endday;
						}
						else if(endmonth>10)
						{
							return "1 24";
						}
					}
				}
				else if(startyear==15 && endyear>15)
				{
					if(startmonth>10)
						return "0";
					else
					{
						if(startmonth==10)
						{
							if(startday>24)
								return "0";
							else
								return ""+startday+" 24";
						}
						else if(startmonth<10)
						{
							return "1 24";
						}
					}
				}
				else
				{
					return "1 24";
				}
			}
			else
			return "0";
			return "0";
		}
			public static int getstringnum(String month) // returns index of month
			{
				if(month.equals("JAN"))
					return 1;
				else if(month.equals("FEB"))
					return 2;
				else if(month.equals("MAR"))
					return 3;
				else if(month.equals("APR"))
					return 4;
				else if(month.equals("MAY"))
					return 5;
				else if(month.equals("JUN"))
					return 6;
				else if(month.equals("JUL"))
					return 7;
				else if(month.equals("AUG"))
					return 8;
				else if(month.equals("SEP"))
					return 9;
				else if(month.equals("OCT"))
					return 10;
				else if(month.equals("NOV"))
					return 11;
				else if(month.equals("DEC"))
					return 12;
				return 0;
			}
}
