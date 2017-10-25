/**
* SQl Database connection with helper methods
*/
public class SQLDBConnection implements DBConnection{

    /** wrapper class for DB username and password*/
    private Properties connectionProperties;

    /** DB url*/
    private String databaseURL;

    /** DB driver type*/
    private final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    SQLDBConnection(String databaseURL, String username, String password){
        //TODO:: since this data is user driven, check it, create a username and password class
        //server url example -- jdbc:mysql://localhost:3306/database_name
        this.databaseURL = databaseURL;
        connectionProperties = new Properties();
        this.connectionProperties.setProperty("user", username);
        this.connectionProperties.setProperty("password", password);

    }

    private Connection establishConnection(String databaseURL = this.databaseURL, Properties properties = this.connectionProperties) {
        Connection connection;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(databaseURL, properties);
        } catch (SQLException e) {
            //TODO: handle invalid db url or refused connection
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //due to the simplicity of project specs and small time frame, this will not 
    //take the obvious security mesures such as prottection against sql injection ect...
    private JsonObject executeQuery(String query){
        Connection connection = this.establishConnection();
        JsonObject resultJson;
        try {
            ResultSet result = connection.prepareStatement(sql).executeQuery();
            resultJson = JavaUtil.convertResultSetToJSON(Result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return resultJson;
    }

    public JsonObject getTuple(String fromRelation, String key, String delimeter, String desiredValue){
        String query = MessageFormat.format(
            "SELECT * FROM {0,String} where {1, String} {2, String} {3, String}",
            fromRelation, key, delimeter, desiredValue);
        JsonObject results = this.executeQuery(query);
        return results;
    }
    
    public JsonObject updateTuple(String relation, JsonObject tuple);
    public JsonObject addTuple(String relation, JsonObject tuple);
    public JsonObject getTupleFromJoin(String firstRelation, String secondRelation, String key, String delimeter, String desiredValue);

    public void truncateRelation(String relation);
    public void truncateAllRelations();

}