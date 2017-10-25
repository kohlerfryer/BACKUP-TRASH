/**
 * API's with DBConnection and returns/manages models
 */
public class EventTypeAccess extends DataAccess{

    /** Reference to Singleton DB*/
    private DataHandler db;

    public EventTypeAccess(DataHandler db){}

    /** 
    * gets events with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return String matching eventType
    */
    public EventType getEventType(String key, string delimeter, String desiredValue){}

    /** 
    * creates EventType in db modeled after parameters
    * @param eventType type of event
    * @return EventType
    */
    public EventType createEventType(String eventType){}

    /** 
    * update specific EventType in db
    * @param event event with new parameters
    * @return EventType
    */
    public EventType updateEventType(EventType eventType){}

}
