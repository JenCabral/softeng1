Organizr

Group 4 

Our project uses the 3-tier architecture, which we left unchanged from the 
first iteration. The main function of the the app is contained in Main.java
within comp3350.organizr.application. The source 
code for each layer of our architecture is contained in its own package.
In the persistence package, we have the implementation of our stub database
as well as the functions to access it. The presentation layer handles 
interaction with the user; it provides list displays of collections and items
with buttons to handle the operations for each. The business layer mediates 
communication between the persistence and presentation layers, and takes
responsibility for the logic of our application. For our first iteration the
logic implemented counts the items in a collection.

From iteration 2 to 3, our biggest change was the introduction of integration
and acceptance tests to our system. We also added additional sorting options
and a new item field. Implementation of these new features required some changes
to each layer and some refactoring of existing unit tests and addition of new ones.
Our unaddressed issues included the ability to "group" items within collections - 
essentially creating sub-collections. We chose to leave this user story unimplemented 
due to time constraints and complexity. This feature would require a major overhaul
to the way our system is constructed and as such, would not be possible in the
time limit of this iteration.

Compile the project with Compile.bat. It will run SetClasspath.bat.
Unit tests can be run by invoking RunUnitTests.bat and integration
tests can be run by invoking RunIntegrationTests.bat.

/home/student/umcabra2/comp3350/organizr