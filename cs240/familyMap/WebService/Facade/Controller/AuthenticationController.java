/**
* Bridge between model and facade 
*/
public class AuthenticationController{

    /** Reference to Singleton DB*/
    DataConnection db;

    /** 
    * @param relation Reference to Singleton DB
    */
    public AuthenticationController(DataConnection db){}

    /** 
    * /user/register
    * Creates a new user account, generates 4 generations 
    * of ancestor data for the new user, logs the user in.
    * @param request with body containign userName, password
    * email, firstName, lastName, and gender.
    * @return HTTPResponse containing response body with
    * an AuthToken, Personid, and userName
    */
    public HTTPResponse registerUser(HTTPRequest request){}

    /** 
    * /user/login
    * Logs in the user  
    * @param request with body containign userName and password.
    * @return HTTPResponse containing response body with
    * an AuthToken, Personid, and userName
    */
    public HTTPResponse loginUser(HTTPRequest request){}
   
}