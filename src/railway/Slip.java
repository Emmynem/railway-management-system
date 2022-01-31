package railway;

/**
 *
 * @author Xnyder
 */
public class Slip {

    boolean cancelled = false;

    public Slip(String tick_num, String fullname, String title, String class_,
            String from, String to, String depart, String arrive, String tname, String tnumber,
            String seat, String date, String time, String hh_format) {

        String Header
                = "                ***CUSTOMERS COPY***\n"
                + "Date: " + date + "\n"
                + "Time: " + time + " " + hh_format + "\n"
                + "------------------------------------------------------------\n"
                + "Ticket Number: " + tick_num + "\n"
                + "Seats: " + seat + "\n"
                + "------------------------------------------------------------\n";

        String Body
                = "------------------------------------------------------------\n"
                + "                   Name : " + fullname + "\n"
                + "------------------------------------------------------------\n"
                + "Title : " + title + "\n"
                + "Class : " + class_ + "\n"
                + "------------------------------------------------------------\n"
                + "From : " + from + "\n"
                + "Departure Time : " + depart + "\n"
                + "------------------------------------------------------------\n"
                + "To : " + to + "\n"
                + "Arrival Time : " + arrive + "\n"
                + "------------------------------------------------------------\n"
                + "Train Number : " + tnumber + "\n"
                + "Train Name : " + tname + "\n"
                + "------------------------------------------------------------\n";

        String Footer = "                   Powered By Emmynem \n"
                + "              https://github.com/Emmynem/ \n";

        String full = Header + Body + Footer;

        String slip = full;

        synchronized (this) {
            Print prt = new Print(slip);
            if (prt.cancelled) {
                cancelled = true;
            }
        }
    }

}
