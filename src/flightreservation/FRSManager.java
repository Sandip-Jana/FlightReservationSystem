package flightreservation;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * @author - Sandip Jana
 */
class FRSManager 
{
    public static void main(String args[])throws IOException
    {
        int numberofarguments=args.length;
        if(numberofarguments==0)
        {
          DataManager dm=new DataManager();
          DisplayManager ob=new DisplayManager();
          ob.displayDisplayPage(dm.ReadsilkFile("Files/2015.silkair.csv"),dm.ReadSpicejet("Files/2015.spicejet.csv"),ob,numberofarguments);
        }
        else if(numberofarguments==3)
        {
          String spicejet=args[0];
          String silkair=args[1];
          DataManager dm=new DataManager();
          DisplayManager ob=new DisplayManager();
          ob.displayDisplayPage(dm.ReadsilkFile(silkair),dm.ReadSpicejet(spicejet),ob,numberofarguments);
        }
        else if(numberofarguments==2)
        {
          String spicejet=args[0];
          String silkair=args[1];
          DataManager dm=new DataManager();
          DisplayManager ob=new DisplayManager();
          ob.displayDisplayPage(dm.ReadsilkFile(silkair),dm.ReadSpicejet(spicejet),ob,numberofarguments);
          int userchoice=0;
          do
          {
          TextBasedUi object=new TextBasedUi();
          object.TextBasedUiDesign(ob);
          System.out.println("Please Select :");
          System.out.println("1. Make another reservation, or");
          System.out.println("0. Exit");
          BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
          try
          {
          userchoice=Integer.parseInt(reader.readLine());
            } catch (IOException ex) {
            Logger.getLogger(FRSManager.class.getName()).log(Level.SEVERE, null, ex);
            }
          }while(userchoice!=0);
          System.out.println("Goodbye, please visit us again.");
          System.exit(0);
        }
    }
}
