package flightreservation;
public class TicketDetail 
{
    public static String details[]=new String[8];
    public void getDetail(String detail[])
    {
        System.arraycopy(detail, 0, details, 0, 8);
    }
    public String[] passDetail()
    {
        return details;
    }
}
