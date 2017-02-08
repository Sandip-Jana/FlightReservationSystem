package flightreservation;
import java.util.*;
import javax.swing.JOptionPane;
public class SortComboFlights
{
    public ComboFlight[] sortcomboflight(ComboFlight cf[],DisplayManager object)
    {
        int i=cf.length;
        int count=0;
        for(int j=0;j<100;j++)
        {
            if(cf[j]!=null)
            {
                count+=1;
            }
        }
        if(count==0)
        {
            JOptionPane.showMessageDialog(null, "No Flights Available On this Date. Please Select Another Date.");
            return null;
        }
        else
        {
        //Sorting the combo flights...
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
        }
        return cf;
    }
}
