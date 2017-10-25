/**
 * API's with DBConnection and returns/manages models
 */
public class PersonEventAccess extends DataAccess{

    /** Reference to Singleton DB*/
    private DataHandler db;

    public PersonEventAccess(DataHandler db){}

    /** 
    * gets person event with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return PersonEvent
    */
    public PersonEvent getPersonEvent(String key, string delimeter, String desiredValue){}

    /** 
    * creates person event in db modeled after parameters
    * @param eventId id of event connected
    * @param personId id of person connected
    * @return PersonEvent
    */
    public PersonEvent createPersonEvent(int eventId, int personId){}

    /** 
    * update PersonEventin db
    * @param PersonEvent with updated values
    * @return PersonEvent
    */
    public PersonEvent updatePersonEvent(PersonEvent personEvent){}
}