
/**
* Abstracts CRUD interaction with DB
* Interface for all types of DB connections. 
* All DB connections that implement this 
* interface should use the Singleton Pattern.
*/
public interface DBConnection{

    /** 
    * gets connection from a DB driver
    * @return instance of the connection
    */
    public Connection getConnection();
    
    /** 
    * gets tuple with specified value
    * @param fromRelation  name of first relation in DB
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return JsonObject Jsonified version of tuple
    */
    public JsonObject getTuple(String fromRelation, String key, String delimeter, String desiredValue);

    /**
    * gets first relation with specified value and then joins
    * with other relation. Assumes that the foreign key's
    * are of form *foreignTableName*_id
    * @param firstRelation  name of first relation in DB
    * @param secondRelation name of second relation in DB
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return JsonObject Jsonified version of tuple
    */
    public JsonObject getTupleFromJoin(String firstRelation, String secondRelation, String key, String delimeter, String desiredValue);

    /** 
    * Updates current existing tuple in DB
    * @param relation  name of relation in DB
    * @param tuple Jsonified version of tuple to update
    * @return JsonObject Jsonified version of tuple
    */
    public JsonObject updateTuple(String relation, JsonObject tuple);

    /** 
    * Updates current existing tuple in DB
    * @param relation  name of relation in DB
    * @param tuple Jsonified version of tuple to update
    * @return int id of added row
    */
    public int addTuple(String relation, JsonObject tuple);

    /** 
    * truncates relation in DB
    * @param relation  name of relation in DB
    * @return void
    */
    public void truncateRelation(String relation);

    /** 
    * truncates all relations in DB
    * @param relation  name of relation in DB
    * @return void
    */
    public void truncateAllRelations();
}


