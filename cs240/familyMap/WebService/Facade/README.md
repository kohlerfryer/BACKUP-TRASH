Web APIs

The primary function of the server is to publish a set of web APIs for use by the Family Map
client. Your server should implement each of the following web API operations.


/user/register
URL Path: /user/register
Description: Creates a new user account, generates 4 generations of ancestor data for the new
user, logs the user in, and returns an auth token.
HTTP Method: POST
4
Auth Token Required: No
Request Body:
{
"userName": "susan", // Non-empty string
"password": "mysecret", // Non-empty string
"email": "susan@gmail.com", // Non-empty string
"firstName": "Susan", // Non-empty string
"lastName": "Ellis", // Non-empty string
"gender": "f" // “f” or “m”
}
Errors: Request property missing or has invalid value, Username already taken by another user,
Internal server error
Success Response Body:
{
"authToken": "cf7a368f", // Non-empty auth token string
"userName": "susan", // User name passed in with request
"personId": "39f9fe46" // Non-empty string containing the Person ID of the
// user’s generated Person object
}
Error Response Body:
{
“message”: “Description of the error”
}


/user/login
URL Path: /user/login
Description: Logs in the user and returns an auth token.
HTTP Method: POST
Auth Token Required: No
Request Body:
{
"userName": "susan", // Non-empty string
"password": "mysecret" // Non-empty string
}
Errors: Request property missing or has invalid value, Internal server error
Success Response Body:
{
"authToken": "cf7a368f", // Non-empty auth token string
"userName": "susan", // User name passed in with request
"personId": "39f9fe46" // Non-empty string containing the Person ID of the
// user’s generated Person object
}
5
Error Response Body:
{
“message”: “Description of the error”
}


/clear
URL Path: /clear
Description: Deletes ALL data from the database, including user accounts, auth tokens, and
generated person and event data.
HTTP Method: POST
Auth Token Required: No
Request Body: None
Errors: Internal server error
Success Response Body:
{
“message”: “Clear succeeded.”
}
Error Response Body:
{
“message”: “Description of the error”
}
/fill/[username]/{generations}
URL Path: /fill/[username]/{generations}
Example: /fill/susan/3
Description: Populates the server's database with generated data for the specified user name.
The required "username" parameter must be a user already registered with the server. If there is
any data in the database already associated with the given user name, it is deleted. The
optional “generations” parameter lets the caller specify the number of generations of ancestors
to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
persons each with associated events).
HTTP Method: POST
Auth Token Required: No
Request Body: None
Errors: Invalid username or generations parameter, Internal server error
Success Response Body:
{
“message”: “Successfully added X persons and Y events to the database.”
}
Error Response Body:
{
“message”: “Description of the error”
6
}


/load
URL Path: /load
Description: Clears all data from the database (just like the /clear API), and then loads the
posted user, person, and event data into the database.
HTTP Method: POST
Auth Token Required: No
Request Body: The “users” property in the request body contains an array of users to be
created. The “persons” and “events” properties contain family history information for these
users. The objects contained in the “persons” and “events” arrays should be added to the
server’s database. The objects in the “users” array have the same format as those passed to
the /user/register API with the addition of the personID. The objects in the “persons” array have
the same format as those returned by the /person/[personID] API. The objects in the “events”
array have the same format as those returned by the /event/[eventID] API.
{
“users”: [ /* Array of User objects */ ],
“persons”: [ /* Array of Person objects */ ],
“events”: [ /* Array of Event objects */ ]
}
Errors: Invalid request data (missing values, invalid values, etc.), Internal server error
Success Response Body:
{
“message”: “Successfully added X users, Y persons, and Z events to the database.”
}
Error Response Body:
{
“message”: “Description of the error”
}


/person/[personID]
URL Path: /person/[personID]
Example: /person/7255e93e
Description: Returns the single Person object with the specified ID.
HTTP Method: GET
Auth Token Required: Yes
Request Body: None
Errors: Invalid auth token, Invalid personID parameter, Requested person does not belong to
this user, Internal server error
Success Response Body:
{
7
"descendant": "susan", // Name of user account this person belongs to
"personID": "7255e93e", // Person’s unique ID
"firstName": "Stuart", // Person’s first name
"lastName": "Klocke", // Person’s last name
"gender": "m", // Person’s gender (“m” or “f”)
“father”: “7255e93e” // ID of person’s father [OPTIONAL, can be missing]
“mother”: “f42126c8” // ID of person’s mother [OPTIONAL, can be missing]
"spouse":"f42126c8" // ID of person’s spouse [OPTIONAL, can be missing]
}
Error Response Body:
{
“message”: “Description of the error”
}


/person
URL Path: /person
Description: Returns ALL family members of the current user. The current user is
determined from the provided auth token.
HTTP Method: GET
Auth Token Required: Yes
Request Body: None
Errors: Invalid auth token, Internal server error
Success Response Body: The response body returns a JSON object with a “data” attribute that
contains an array of Person objects. Each Person object has the same format as described in
previous section on the /person/[personID] API.
{
"data": [ /* Array of Person objects */ ]
}
Error Response Body:
{
“message”: “Description of the error”
}


/event/[eventID]
URL Path: /event/[eventID]
Example: /event/251837d7
Description: Returns the single Event object with the specified ID.
HTTP Method: GET
Auth Token Required: Yes
Request Body: None
8
Errors: Invalid auth token, Invalid eventID parameter, Requested event does not belong to this
user, Internal server error
Success Response Body:
{
"descendant": "susan" // Name of user account this event belongs to (non-empty
// string)
"eventID": "251837d7", // Event’s unique ID (non-empty string)
"personID": "7255e93e", // ID of the person this event belongs to (non-empty string)
"latitude": 65.6833, // Latitude of the event’s location (number)
"longitude": -17.9, // Longitude of the event’s location (number)
"country": "Iceland", // Name of country where event occurred (non-empty
// string)
"city": "Akureyri", // Name of city where event occurred (non-empty string)
"eventType": "birth", // Type of event (“birth”, “baptism”, etc.) (non-empty string)
"year": "1912", // Year the event occurred (integer formatted as string)
}
Error Response Body:
{
“message”: “Description of the error”
}


/event
URL Path: /event
Description: Returns ALL events for ALL family members of the current user. The current
user is determined from the provided auth token.
HTTP Method: GET
Auth Token Required: Yes
Request Body: None
Errors: Invalid auth token, Internal server error
Success Response Body: The response body returns a JSON object with a “data” attribute that
contains an array of Event objects. Each Event object has the same format as described in
previous section on the /event/[eventID] API.
{
"data": [ /* Array of Event objects */ ]
}
Error Response Body:
{
“message”: “Description of the error”
}