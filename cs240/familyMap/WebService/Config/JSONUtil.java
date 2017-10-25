public class JSONUtil {

 //JsonObject object = Json.createObjectBuilder().build();
 
 
// The class JsonBuilderFactory also contains methods to create JsonObjectBuilder instances. A factory instance can be used to create multiple builder instances with the same configuration. This the preferred way to create multiple instances. The example code below shows how to build a JsonObject model that represents the following JSON object:

 
//  {
//      "firstName": "John", "lastName": "Smith", "age": 25,
//      "address" : {
//          "streetAddress": "21 2nd Street",
//          "city": "New York",
//          "state": "NY",
//          "postalCode": "10021"
//      },
//      "phoneNumber": [
//          { "type": "home", "number": "212 555-1234" },
//          { "type": "fax", "number": "646 555-4567" }
//      ]
//  }
 
 
// The code to create the object shown above is the following:

 
//  JsonBuilderFactory factory = Json.createBuilderFactory(config);
//  JsonObject value = factory.createObjectBuilder()
//      .add("firstName", "John")
//      .add("lastName", "Smith")
//      .add("age", 25)
//      .add("address", factory.createObjectBuilder()
//          .add("streetAddress", "21 2nd Street")
//          .add("city", "New York")
//          .add("state", "NY")
//          .add("postalCode", "10021"))
//      .add("phoneNumber", factory.createArrayBuilder()
//          .add(factory.createObjectBuilder()
//              .add("type", "home")
//              .add("number", "212 555-1234"))
//          .add(factory.createObjectBuilder()
//              .add("type", "fax")
//              .add("number", "646 555-4567")))
//      .build();

    private static JsonArray convertResultSetToJSON(ResultSet resultSet){
		// if(resultSet == null){
		// 	return null;
        // }
		
		// JSONArray jsonArray = new JSONArray();
		// ResultSetMetaData metadata = resultSet.getMetaData();
		// int columnSize = metadata.getColumnCount();
		
		// while(resultSet.next()){
		// 	JsonObject jsonRowObject = new JSONObject();
		// 	for (int i = 1; i <= numColumns; ++i){
		// 		String columnSize = metadata.getColumnName(i);
		// 		jsonRowObject.put(columnSize, resultSet.getObject(columnSize));
		// 	}
		// 	jsonArray.add(obj);
		// }
		// return jsonArray;
        
    }

}