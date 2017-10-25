

package cs240;

import java.io.*;
import java.net.*;
import java.util.*;
import com.sun.net.httpserver.*;
import static java.net.HttpURLConnection.HTTP_OK;

class Client {

    public static void main(String[] args) throws Exception {

	Client client = new Client();

	client.getPerson();
	client.addPerson();

    }

    void getPerson() throws Exception {

	String url = "http://localhost:8888/person/get";

	System.out.println("CLIENT: getPerson: " + url);
	System.out.println();

	HttpURLConnection connection =
	    (HttpURLConnection) new URL(url).openConnection();

	connection.setRequestMethod("GET");
	connection.setRequestProperty("Authorization", "1a2b4c8d");
	connection.connect();

	//sendRequestBody(connection); // no request body

	getResponseHeaders(connection);

	// this is a mock response handler
	// real code would get the Json from the response body
	// decode the Json to get a Person object
	// and return the person to the client

	String response = getResponseBody(connection);

	System.out.println();

    }
	
    void addPerson() throws Exception {

	String url = "http://localhost:8888/person/add";

	System.out.println("CLIENT: addPerson: " + url);
	System.out.println();

	HttpURLConnection connection =
	    (HttpURLConnection) new URL(url).openConnection();

	connection.setRequestMethod("POST");
	connection.setDoOutput(true);
	connection.setRequestProperty("Authorization", "1a2b4c8d");
	connection.connect();

	// this is a mock request
	// real code would get a person from the client
	// encode the person into Json
	// add send the Json in the request body

	String request = "{ \"name\": \"Bob\", \"age\": 64 }";

	sendRequestBody(connection, request);

	getResponseHeaders(connection);

	//getResponseBody(connection); // no response body

	System.out.println();

    }

    void sendRequestBody(HttpURLConnection connection, String request)
	throws Exception {

	PrintWriter out = new PrintWriter(connection.getOutputStream());
	out.print(request);
	out.close();

	System.out.println("request body:");
	System.out.println(request);
	System.out.println();

    }

    void getResponseHeaders(HttpURLConnection connection) throws Exception {

	System.out.println("response:");

	int status = connection.getResponseCode();
	System.out.println("status: " + status);

	String message = connection.getResponseMessage();
	System.out.println("message: " + message);

	System.out.println();

	//printHeaders(connection.getHeaderFields());

    }
    
    String getResponseBody(HttpURLConnection connection) throws Exception {

	String response = "";
	
	System.out.println("response body:");

	int status = connection.getResponseCode();
	if (status == HTTP_OK) {

	    Scanner in = new Scanner(connection.getInputStream());
	    while (in.hasNextLine()) {
		String line = in.nextLine();
		response += line + "\n";
		System.out.println(line);
	    }
	    in.close();

	}

	System.out.println();

	return response;
	
    }

    void printHeaders(Map<String, List<String>> headers) {

	System.out.println("response headers:");

	for (String name : headers.keySet())
	    System.out.println(name + " = " + headers.get(name));

	System.out.println();

    }

}


