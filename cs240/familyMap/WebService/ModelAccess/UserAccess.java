/**
 * API's with DBConnection and returns/manages models
 */
public class UserAccess extends DataAccess{

    /** Reference to Singleton DB*/
    private DataHandler db;
     
    public UserAccess(DataHandler db){}

    /** 
    * gets user event with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return User
    */
    public User getUser(String key, string delimeter, String desiredValue){}

    /** 
    * creates user event in db modeled after parameters
    * @param username username of user
    * @param email email of user
    * @param personId email of user
    * @return User
    */
    public User createUser(UserName username, Email email, int personId){}

    /** 
    * update User db
    * @param user with updated values
    * @return User
    */
    public User updateUser(User user){}

}
