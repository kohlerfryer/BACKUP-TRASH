/**
* represents a single Authentication tuple
*/
public class Authentication{

    /** tuples unique identifier in relation */
    private int id;

    /** unique token used for establishing logging sessions */
    private String token;

    /** unique userId*/
    private int userId;

    /** used to expire timestamps */
    private Timestamp timestamp;

    public Authentication(int id, token token, int userId, Timestamp timestamp){}

    public int getId(){}
    public Token getToken(){}
    public int userId(){}
    public Timestamp getTimestamp(){}

    public void setToken(Token token){}
    public void setUserId(int userId){}
    public void setTimestamp(Timestamp timestamp){}
    
    private void generateToken(){}
}