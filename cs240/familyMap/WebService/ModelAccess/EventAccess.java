/**
 * API's with DBConnection and returns/manages models
 */
public class EventAccess extends DataAccess{

    /** Reference to Singleton DB*/
    private DataHandler db;

    public EventAccess(DataHandler db){}

    /** 
    * gets events with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return String[] matching events
    */
    public Event[] getEvents(String key, string delimeter, String desiredValue){}

    /** 
    * creates events in db modeled after object
    * @param lattitude lattitude of event location 
    * @param longitude longitude of event location 
    * @param country city of event
    * @param city city of event
    * @param eventTypeId refers to eventType a relation tuple
    * @param year year of event
    * @return Authentication
    */
    public Event createEvent(int lattitude, int longitude, String country, String city, int eventTypeId, Year year){}

    /** 
    * update specific event in db
    * @param event event with new parameters
    * @return Event
    */
    public Event updateEvent(Event event){}

}