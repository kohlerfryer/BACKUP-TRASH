

package cs240;

import java.io.*;
import java.net.*;
import java.util.*;
import com.sun.net.httpserver.*;
import static java.net.HttpURLConnection.HTTP_OK;

class Server {

    public static void main(String[] args) throws Exception {

	Server server = new Server();
	server.startServer();

    }

    void startServer() throws Exception {

	int port = 8888;
	
	System.out.println("server listening on port: " + port);
	System.out.println();
	
	HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

	server.createContext("/person/get", new GetPersonHandler());
	server.createContext("/person/add", new AddPersonHandler());

	server.start();
	
    }
    
    class GetPersonHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {

	    System.out.println("SERVER: /person/get handler");
	    System.out.println();

	    getRequestHeaders(exchange);

	    //getRequestBody(exchange); // no request body expected

	    // this is a mock response
	    // real code would get a person using the server facade
	    // encode the person into Json
	    // add send the Json in the response body

	    String response = "{ \"name\": \"Zed\", \"age\": 42 }";

	    exchange.sendResponseHeaders(HTTP_OK, 0);

	    sendResponseBody(exchange, response);

	    System.out.println();

	}
    }

    class AddPersonHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {

	    System.out.println("SERVER: /person/add handler");
	    System.out.println();

	    getRequestHeaders(exchange);

	    // this is mock request handler
	    // real code would get the Json from the request body
	    // decode the Json to get a Person object
	    // and add the person using the server facade

	    String request = getRequestBody(exchange);

	    exchange.sendResponseHeaders(HTTP_OK, -1); // -1 for no body

	    //sendResponseBody(exchange); // no response body

	    System.out.println();

	}
    };

    void getRequestHeaders(HttpExchange exchange) {

	System.out.println("request:");

	String method = exchange.getRequestMethod();
	System.out.println("method: " + method);
		    
	URI uri = exchange.getRequestURI();
	System.out.println("uri: " + uri);
		    
	String auth = exchange.getRequestHeaders().getFirst("Authorization");
	System.out.println("auth: " + auth);

	System.out.println();

	//printHeaders(exchange.getRequestHeaders());

    }

    String getRequestBody(HttpExchange exchange) {

	System.out.println("request body:");

	String request = "";
	
	Scanner in = new Scanner(exchange.getRequestBody());
	while (in.hasNextLine()) {
	    String line = in.nextLine();
	    request += line + "\n";
	    System.out.println(line);
	}
	in.close();

	System.out.println();

	return request;
	
    }

    void sendResponseBody(HttpExchange exchange, String response) {

	PrintWriter out = new PrintWriter(exchange.getResponseBody());
	out.print(response);
	out.close();

	System.out.println("response body:");
	System.out.println(response);
	System.out.println();

    }

    void printHeaders(Map<String, List<String>> headers) {

	System.out.println("request headers:");

	for (String name : headers.keySet())
	    System.out.println(name + " = " + headers.get(name));

	System.out.println();

    }

}


