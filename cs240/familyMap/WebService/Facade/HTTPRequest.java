/**
* Represents a simplified HTTPRequest
*/
public class HTTPRequest{
    /** HTTP request method like GET or POST */
    private String requestMethod;

    /** url paramters of HTTP request */
    private String[] urlParameters;

    /** request body of HTTP request */
    private JsonObject requestBody;

    /** 
    * @param requestMethod  HTTP request method like GET or POST
    * @param urlParameters  url paramters of HTTP request
    * @param requestBody  request body of HTTP request
    */    
    public HTTPRequest(String requestMethod, String[] urlParameters, JsonObject requestBody){};
    
    public String getRequestMethod(){}
    public String getUrlParameter(int index){}
    public String getJSONValue(String key){}
}