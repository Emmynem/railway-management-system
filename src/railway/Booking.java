package railway;

/**
 *
 * @author Xnyder
 */
public class Booking {

    String fname, lname, mobile, gender, title, class_, from, to, depart, arrive, tname, tnumber, seat;

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setClass_(String class_)
    {
        this.class_ = class_;
    }
    
    public void setFrom(String from)
    {
        this.from = from;
    }
    
    public void setTo(String to)
    {
        this.to = to;
    }
    
    public void setDepart(String depart)
    {
        this.depart = depart;
    }
    
    public void setArrive(String arrive)
    {
        this.arrive = arrive;
    }
    
    public void setTname(String tname)
    {
        this.tname = tname;
    }
    
    public void setTnumber(String tnumber)
    {
        this.tnumber = tnumber;
    }
    
    public void setSeat(String seat)
    {
        this.seat = seat;
    }
    
    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getClass_()
    {
        return class_;
    }
    
    public String getFrom()
    {
        return from;
    }
    
    public String getTo()
    {
        return to;
    }
    
    public String getDepart()
    {
        return depart;
    }
    
    public String getArrive()
    {
        return arrive;
    }
    
    public String getTname()
    {
        return tname;
    }
    
    public String getTnumber()
    {
        return tnumber;
    }
    
    public String getSeat()
    {
        return seat;
    }
    
    public String toString() 
    {
        String details = getFname() + " " + getLname() + ", Title : " + getTitle() + ", Class : " + getClass_() + ", From : " + getFrom()
        + ", To : " + getTo() + ", Departure Time : " + getDepart() + ", Train Name : "  + getTname() + ", Train Number : " + getTnumber();
        StringBuffer buffer = new StringBuffer();
        buffer.append(details);
        return buffer.toString();
    }

}
