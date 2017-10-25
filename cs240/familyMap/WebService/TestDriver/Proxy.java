

package cs240;

import java.io.*;
import java.net.*;
import java.util.*;
import com.sun.net.httpserver.*;
import static java.net.HttpURLConnection.HTTP_OK;
import com.google.gson.Gson;

class Proxy {

    Client client = new Client();
    Encoder encoder = new Encoder();

    public static void main(String[] args) throws Exception {

	Proxy proxy = new Proxy();

	Person bob = new Person("Ned", 24);
	Person sue = new Person("Sue", 42);

	proxy.addPerson(bob);
	proxy.addPerson(sue);

	proxy.getPerson();
	proxy.getPerson();

    }

    void addPerson(Person person) throws Exception {

	System.out.println("PROXY: addPerson:");
	System.out.println(person);
	System.out.println();

	String url = "http://localhost:8888/person/add";

	String request = encoder.encodePerson(person);

	client.post(url, request);

	System.out.println("PROXY: addPerson:");
	System.out.println();
	System.out.println();

    }

    Person getPerson() throws Exception {

	System.out.println("PROXY: getPerson:");
	System.out.println();

	String url = "http://localhost:8888/person/get";

	String response = client.get(url);

	Person person = encoder.decodePerson(response);

	System.out.println("PROXY: getPerson:");
	System.out.println(person);
	System.out.println();
	System.out.println();

	return person;
	
    }
    
}

class Facade {

    // this is a mock facade
    // a real facade would use a database
    
    private List<Person> people = new ArrayList<>();
    private int index = 0;

    void addPerson(Person person) {

	System.out.println("FACADE: addPerson:");
	System.out.println(person);
	System.out.println();

	people.add(person);

	System.out.println("FACADE: people:");
	System.out.println(people);
	System.out.println();

    }

    Person getPerson() {

	Person person = people.get(index);

	index++;
	if (index >= people.size())
	    index = 0;
	
	System.out.println("FACADE: getPerson:");
	System.out.println(person);
	System.out.println();

	return person;
    }
    
}


class Encoder {

    Gson gson = new Gson();
    
    String encodePerson(Person person) {
	return gson.toJson(person);
    }
    
    Person decodePerson(String json) {
	return gson.fromJson(json, Person.class);
    }
    
}


class Server {

    Facade facade = new Facade();
    Encoder encoder = new Encoder();
    
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

	    Person person = facade.getPerson();
	    String response = encoder.encodePerson(person);

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

	    String request = getRequestBody(exchange);

	    Person person = encoder.decodePerson(request);
	    facade.addPerson(person);
	
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


class Client {

    String get(String url) throws Exception {

	System.out.println("CLIENT: GET: " + url);
	System.out.println();

	HttpURLConnection connection =
	    (HttpURLConnection) new URL(url).openConnection();

	connection.setRequestMethod("GET");
	connection.setRequestProperty("Authorization", "1a2b4c8d");
	connection.connect();

	getResponseHeaders(connection);

	String response = getResponseBody(connection);

	return response;

    }
	
    void post(String url, String request) throws Exception {

	System.out.println("CLIENT: POST: " + url);
	System.out.println();

	HttpURLConnection connection =
	    (HttpURLConnection) new URL(url).openConnection();

	connection.setRequestMethod("POST");
	connection.setDoOutput(true);
	connection.setRequestProperty("Authorization", "1a2b4c8d");
	connection.connect();

	sendRequestBody(connection, request);

	getResponseHeaders(connection);

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


class Person {
	
    private String name = "";
    private int age = 0;
	
    public Person(String name, int age) {
	setName(name);
	setAge(age);
    }
	
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    @Override
    public String toString() {
	return getClass().getName() + ": name: " + name + ", age: " + age;
    }
	
}


