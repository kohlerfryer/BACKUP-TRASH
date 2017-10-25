/**
* represents a single Person tuple
*/
public class Person{

    /** tuples unique identifier in relation */
    private int id;

    /** first name of person */
    private Name firstName;

    /** last name of person */
    private Name lastName;

    /** gender of person */
    private char gender;

    /** referrs to father tuple of this person */
    private int fatherId;

    /** referrs to mother tuple of this person */
    private int motherId;

    /** referrs to spouse tuple of this person */
    private int spouseId;
    
    public Person(int id, Name firstName, Name lastName, char gender, int fatherId, int motherId, int spouseId){}
       
    public int getId(){}
    public Name getFirstName(){}
    public Name getLastName(){}
    public char getGender(){}
    public int getFatherId(){}
    public int getMotherId(){}
    public int getSpouseId(){}

    public void setUsername(UserName username){}
    public void setEmail(Email email){}
    public void setGender(char gender){}
    public void setFatherId(int fatherId){}
    public void setMotherId(int motherId){}
    public void setSpouseId(int spouseId){}
}