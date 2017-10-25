/**
* represents a single Event tuple
*/
public class Event{
    /** tuples unique identifier in relation */
    private int id;

    /** lattitude of event location */
    private int lattitude;

    /** longitude of event location */
    private int longitude;

    /** country of event */
    private String country;

    /** city of event */
    private String city;

    /** refers to eventType a relation tuple*/
    private int eventTypeId;

    /** year of event */
    private Year year;

    public Event(int id, int lattitude, int longitude, String country, String city, int eventTypeId, Year year){}

    public int getId(){}
    public int getLattitude(){}
    public int getLongitude(){}
    public String getCountry(){}
    public String getCity(){}
    public int getEventTypeId(){}
    public Year getYear(){}

    public void setLattitude(int lattitude){}
    public void setLongitude(int longitude){}
    public void setCountry(String country){}
    public void setCity(String city){}
    public void setEventTypeId(int eventTypeId){}
    public void setYear(Year year){}
    
}