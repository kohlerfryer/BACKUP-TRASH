/**
 * API's with DBConnection and returns/manages models
 */
public class AuthenticationAccess extends DataAccess{

    /** Reference to Singleton DB*/
    private DataConnection db;

    public AuthenticationAccess(DataConnection db){}

    /** 
    * gets authentication with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return Authentication
    */
    public Authentication getAuthentication(String key, string delimeter, String desiredValue){}

    /** 
    * creates authentication in db modeled after parameters
    * @param token unique session token
    * @param userId the user who owns token
    * @param timestamp creation date of token
    * @return Authentication
    */
    public Authentication createAuthentication(String token, int userId, Timestamp timestamp){}

    /** 
    * generates unique token
    * @return String unique token
    */
    private String generateToken(){}
}