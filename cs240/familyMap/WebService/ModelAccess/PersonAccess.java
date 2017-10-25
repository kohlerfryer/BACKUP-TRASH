/**
 * API's with DBConnection and returns/manages models
 */
public class PersonAccess extends DataAccess{
    
    /** Reference to Singleton DB*/
    private DataHandler db;

    public PersonAccess(DataHandler db){}

    /** 
    * gets person with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return String matching eventType
    */
    public Person getPerson(String key, string delimeter, String desiredValue){}

    /** 
    * creates person in db modeled after parameters
    * @param firstName firstName of person
    * @param lastName lastName of person
    * @param gender gender of person
    * @param fatherId treferrs to father tuple of this person 
    * @param motherId referrs to mohter tuple of this person 
    * @param spouseId referrs to spouse tuple of this person 
    * @return Person
    */
    public Person createPerson(Name firstName, Name lastName, char gender, int fatherId, int motherId, int spouseId){}

    /** 
    * update specific user in db
    * @param user user with new parameters
    * @return EventType
    */
    public Person updatePerson(Person user){}
    
    /** 
    * generates random data for a user up
    * to desired generation length
    * @param generationSize amount of generations of data to make
    * @return void
    */
    public void generateFamilyData(int generationSize){}

}