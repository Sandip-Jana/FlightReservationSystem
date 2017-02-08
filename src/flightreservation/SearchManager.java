package flightreservation;
import java.util.*;
class SearchManager
{
    public ComboFlight cf[]=new ComboFlight[100]; // final adjacency list
    public DisplayManager ob;
    public int daynum,durahr,duramin,silkf,silksec; // variables to store basic data
    public ComboFlight[] GetFlightDetails(int day,String cityname,int numberofpassengers, DisplayManager ob)
    {
        daynum=day;
        this.ob=ob;
        int numberofcomboflights=0;
        int numberofdomesticflights=ob.domesticflights[daynum].numberofflights; //gives no of domestic flights for that day
      
        for(int i=0;i<numberofdomesticflights;i++) // loop to iterate over all domestic flights
        {
            if((ob.domesticflights[daynum].arr[i].origin).equals(cityname) && (ob.domesticflights[daynum].arr[i].numberofpassengers+numberofpassengers)<=15)
            {
                String intermediateFlight=ob.domesticflights[daynum].arr[i].destination;  // Intermediate Flight
                String spicedeparturetime=gettime(ob.domesticflights[daynum].arr[i].departure);  
                String spicearrivaltime=gettime(ob.domesticflights[daynum].arr[i].arrival);
                StringTokenizer spicedepsplit=new StringTokenizer(spicedeparturetime);
                int departureHour=Integer.parseInt(spicedepsplit.nextToken());
                int departureMinute=Integer.parseInt(spicedepsplit.nextToken());
                StringTokenizer spicesplit=new StringTokenizer(spicearrivaltime);
                int arrivalHour=Integer.parseInt(spicesplit.nextToken());
                int arrivalMinute=Integer.parseInt(spicesplit.nextToken());
                String durationspice[]=getdurationvalue(arrivalHour,arrivalMinute,departureHour,departureMinute).split(" ");
                durahr=Integer.parseInt(durationspice[0]);
                duramin=Integer.parseInt(durationspice[1]);
                int condition=0;
                //hour along with 2 hr checking is checked
                if(arrivalHour+2>=24)
                    condition=2;
                else if(arrivalHour+2<24 && arrivalHour+6>=24)   //check both sides i.e arival hour between extra 2 hrs and 6 hrs limit
                    condition=1;
                int extendedMinTime=(arrivalHour+2)%24;
                int extendedMaxTime=(arrivalHour+6)%24;
                // both same day
                
                //loopsfor international flights checking and movingto corresponding comboFlight array
                if(condition==0)
                {
                     int u=ob.internationalflights[daynum].numberofFlights;
                     for(int j=0;j<u;j++) 
                        {
                            String silksource=ob.internationalflights[daynum].arr[j].Source;
                            String silks[]=silksource.split(" ");
                            silks[0]=silks[0].toUpperCase();
                                if(silks[0].equals(intermediateFlight) && (ob.internationalflights[daynum].arr[j].numberofpassengers+numberofpassengers)<=15)
                                {
                                   String silktime=ob.internationalflights[daynum].arr[j].Departure;
                                   silkf=Integer.parseInt(silktime.substring(0,2));
                                   silksec=Integer.parseInt(silktime.substring(2,4));
                                   
                                   // within the range given
                                   if(silkf>=extendedMinTime && silkf<=extendedMaxTime)
                                   {
                                       if(silkf==extendedMinTime && silksec>=arrivalMinute)
                                       {
                                            initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,0);
                                            numberofcomboflights+=1;
                                       }
                                       else if(silkf==extendedMaxTime && silksec<=arrivalMinute)
                                       {
                                            initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,0);
                                            numberofcomboflights+=1;
                                       }
                                       else if(silkf>extendedMinTime && silkf<extendedMaxTime)
                                       {
                                            initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,0);
                                            numberofcomboflights+=1;
                                       }
                                   }
                                }//end of if
                        }//end of j loop
                }// end of k=0
                else if(condition==1)
                {
                    int u=ob.internationalflights[daynum].numberofFlights;
                     for(int j=0;j<u;j++)
                        {
                            String silksource=ob.internationalflights[daynum].arr[j].Source;
                            String silks[]=silksource.split(" ");
                            silks[0]=silks[0].toUpperCase().trim();
                                if(silks[0].equals(intermediateFlight)  && (ob.internationalflights[daynum].arr[j].numberofpassengers+numberofpassengers)<=15)
                                {
                                   String silktime=ob.internationalflights[daynum].arr[j].Departure;
                                   silkf=Integer.parseInt(silktime.substring(0,2));
                                   silksec=Integer.parseInt(silktime.substring(2,4));
                                   if(silkf>=extendedMinTime && silkf<=24)
                                   {
                                       if(silkf==extendedMinTime && silksec>=arrivalMinute)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,0);
                                           numberofcomboflights+=1;
                                       }
                                       else if(silkf>extendedMinTime)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,0);
                                           numberofcomboflights+=1;
                                       }
                                   }
                                }//end of if
                        }//end of j loop
                 //end of first for j loop
                     if(daynum<24)
                     {
                     u=ob.internationalflights[daynum+1].numberofFlights;
                     for(int j=0;j<u;j++)
                        {
                            String silksource=ob.internationalflights[daynum+1].arr[j].Source;
                            String silks[]=silksource.split(" ");
                            silks[0]=silks[0].toUpperCase().trim();
                                if(silks[0].equals(intermediateFlight)  && (ob.internationalflights[daynum+1].arr[j].numberofpassengers+numberofpassengers)<=15)
                                {
                                   String silktime=ob.internationalflights[daynum+1].arr[j].Departure;
                                   silkf=Integer.parseInt(silktime.substring(0,2));
                                   silksec=Integer.parseInt(silktime.substring(2,4));
                                   if(silkf<=extendedMaxTime)
                                   {
                                       if(silkf==extendedMaxTime && silksec<=arrivalMinute)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,1);
                                            numberofcomboflights+=1;
                                       }
                                       else if(silkf<extendedMaxTime)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,1);
                                            numberofcomboflights+=1;
                                       }
                                   }
                                }//end of if
                        }//end of j loop
                     }//end of if
                }//end of k==1
                else
                {
                     int u=ob.internationalflights[daynum+1].numberofFlights;
                     for(int j=0;j<u;j++)
                        {
                            String silksource=ob.internationalflights[daynum+1].arr[j].Source;
                            String silks[]=silksource.split(" ");
                            silks[0]=silks[0].toUpperCase().trim();
                                if(silks[0].equals(intermediateFlight)  && (ob.internationalflights[daynum+1].arr[j].numberofpassengers+numberofpassengers)<=15)
                                {
                                   String silktime=ob.internationalflights[daynum+1].arr[j].Departure;
                                   silkf=Integer.parseInt(silktime.substring(0,2));
                                   silksec=Integer.parseInt(silktime.substring(2,4));
                                   if(silkf>=extendedMinTime && silkf<=extendedMaxTime)
                                   {
                                       if(silkf==extendedMinTime && silksec>=arrivalMinute)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,1);
                                            numberofcomboflights+=1;
                                       }
                                       else if(silkf==extendedMaxTime && silksec<=arrivalMinute)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,1);
                                           numberofcomboflights+=1;
                                       }
                                       else if(silkf>extendedMinTime && silkf<extendedMaxTime)
                                       {
                                           initcomboflight(numberofcomboflights,daynum,i,j,intermediateFlight,numberofpassengers,1);
                                           numberofcomboflights+=1;
                                       }
                                   }
                                }//end of if
                        }//end of j loop
                }//end of else
            }//end of if
        }//end of i loop
        return cf;  // return array of combo flights
    }//end of function
    
    
    //convert PM to hours
    public String gettime(String flightTime)
    {
                    String spiceTime=flightTime;
                    StringTokenizer spicetime=new StringTokenizer(spiceTime);
                    StringTokenizer spicetimedetail=new StringTokenizer(spicetime.nextToken(),":");
                    String spicetime1=(spicetime.nextToken()).trim();
                    if(spicetime1.equals("PM"))
                    {
                        int hour=Integer.parseInt(spicetimedetail.nextToken())+12;
                        int min=Integer.parseInt(spicetimedetail.nextToken());
                        spiceTime=""+hour+" "+min;
                    }
                    else
                    {
                        spiceTime=""+spicetimedetail.nextToken()+" "+spicetimedetail.nextToken();
                    }
                    return spiceTime;
    }
    public int getdaynum()
    {
        return daynum;
    }
    //check format of time
    public String getdurationvalue(int x1,int y1,int d1,int d2)
    {
                int durahr;
                int duramin;
                if(x1>=d1)
                {
                    durahr=x1-d1;
                    durahr+=(d2+y1)/60;
                    duramin=(d2+y1)%60;
                }
                else if(d1==23 && d2>0)
                {
                    durahr=x1+((d2+y1)/60);
                    duramin=(d2+y1)%60;
                }
                else
                {
                    durahr=(24-d1)+x1+((d2+y1)/60);
                    duramin=(d2+y1)%60;
                }
                return (""+durahr+" "+duramin);
    }
    public void initcomboflight(int numberofcomboflights,int day,int i,int j,String inter,int numberofpassengers,int inc)
    {
         // initialize the comboflight array.
         cf[numberofcomboflights]=new ComboFlight();
         cf[numberofcomboflights].spiceflightnumber=ob.domesticflights[daynum].arr[i].flightnum;
         cf[numberofcomboflights].spicedepareture=ob.domesticflights[daynum].arr[i].departure;
         cf[numberofcomboflights].spicearrival=ob.domesticflights[daynum].arr[i].arrival;
         cf[numberofcomboflights].intermediate=inter;
         cf[numberofcomboflights].silkflightnumber=ob.internationalflights[daynum+inc].arr[j].FlightNo;
         cf[numberofcomboflights].silkdeparture=ob.internationalflights[daynum+inc].arr[j].Departure;
         cf[numberofcomboflights].silkarrival=ob.internationalflights[daynum+inc].arr[j].Arrival;
         String silkarr=ob.internationalflights[daynum+inc].arr[j].Arrival;
          int silkarr1=Integer.parseInt(silkarr.substring(0,2));
          int silkarr2=Integer.parseInt(silkarr.substring(2,4));
          String silkd=getdurationvalue(silkarr1,silkarr2,silkf,silksec);
          String val[]=silkd.split(" ");
          int silkhr=Integer.parseInt(val[0]);
          int silkmin=Integer.parseInt(val[1]);
          int tothr=durahr+silkhr+(silkmin+duramin)/60;
          int totmin=(silkmin+duramin)%60;                                  
         cf[numberofcomboflights].hour=tothr;
         cf[numberofcomboflights].min=totmin;
         cf[numberofcomboflights].numberofpassengers+=numberofpassengers;
         cf[numberofcomboflights].duration=(""+tothr+"Hrs "+totmin+"Mins.");                              
    }
}