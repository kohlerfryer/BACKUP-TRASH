/**
* Bridge between model and facade 
*/
public class MainController{

    /** Reference to Singleton DB*/
    DataHandler db;
    
    public MainController(DataHanlder db){}

    /** 
    * /clear
    * Deletes ALL data from the database, 
    * including user accounts, auth tokens, and
    * generated person and event data..
    * @return HTTPResponse containing response body with
    * a message of success or failure
    */
    public HTTPResponse truncateDatabase(){}

    /** 
    * /fill/[username]/{generations}
    * Populates the server's database with generated data for the specified user name.
    * The required "username" parameter must be a user already registered with the server. If there is
    * any data in the database already associated with the given user name, it is deleted. The
    * optional “generations” parameter lets the caller specify the number of generations of ancestors
    * to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
    * persons each with associated events).
    * @param request with params containing username and generations.
    * @return HTTPResponse containing response body with
    * a message of success or failure
    */
    public HTTPResponse generateDataForUser(HTTPRequest request){}

    /** 
    * /load
    * Clears all data from the database (just like the /clear API), and then loads the
    * posted user, person, and event data into the database.
    * @param request with body containing arrays of users, persons, and
    * events to be created
    * @return HTTPResponse containing response body with
    * a message of success or failure
    */
    public HTTPResponse truncateFill(HTTPRequest request){}
 

    /** 
    * /person/[personID]
    * Returns the single Person object with the specified ID.
    * @param request with url parameter of person id
    * @return HTTPResponse with body containing a user object
    */
    public HTTPResponse getPerson(HTTPRequest request){}

    /** 
    * /person
    * Returns ALL family members of the current user. The current user is
    * determined from the provided auth token.
    * @return HTTPResponse containing array of Person Objects
    */
    public HTTPResponse getFamilyMembers(){}

    /** 
    * /event/[eventID]
    * Returns the single Event object with the specified ID.
    * @return HTTPResponse with body containing a event object
    */
    public HTTPResponse getEvent(){}

    /** 
    * /event
    * Returns ALL family members of the current user. The current user is
    * determined from the provided auth token.
    * @return HTTPResponse containing array of Event Objects
    */
    public HTTPResponse getAllEventsOfFamilyMembers(){}
    
}