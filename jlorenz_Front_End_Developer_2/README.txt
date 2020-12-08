README for Team Project Four (CS400 @ UW Madison)
==================================================

Name of submitting team member: Jacob Lorenz
Wisc email of submitting team member: jlorenz2@wisc.edu
Team name: BC
Role of submitting team member: Front End Developer 2
TA: Brianna Cochran
Lecturer: Gary Dahl

Contributions Week 1:
---------------------

The following files written by me are the main UI / Frontend components built using a standard DHTML tech stack.
The UI is built using Bootstrap, jQuery, and vanilla Javascript. The relevant libraries are included in src/frontend/dependencies.

- src/frontend/index.html
- src/frontend/script.js
- src/frontend/style.css

Note: Some of the content that would be available in the final product is not currently displayed in this UI since the data for it relies on API connections.

The following files written by me constitute my personal implementation of the CS400 HashTable and relevant tests

- src/CS400HashTable/MapADT.java
- src/CS400HashTable/HashTableMap.java
- src/CS400HashTable/HashTableLinkedList.java
- src/CS400HashTable/HashTableLinkedListNode.java
- src/CS400HashTable/TestHashTable.java

Left to do:

- Finish any remaining UI components
- Update the aesthetics of the UI components; remove misc borders (there to describe the layout at the moment)
- Connect the UI to the backend via Java servlets / API
- Get the entire web-app running successfully in an Apache Tomcat server

Contributions Week 2:
---------------------

For week 2, I spent some time working on setting up template Java servlet classes for our team to use in the backend. Files written by me include:

- src/backend/servlets/CityListServlet.java
- src/backend/servlets/FilteredListingsServlet.java
- src/backend/servlets/InitialListingsServlet.java

Note: Some of the content that would be available in the final product is not currently displayed in this UI since the data for it relies on API connections.

Left to do:

- Connect the UI to the backend via Java servlets / API
- Update the aesthetics of the UI components; remove misc borders (there to describe the layout at the moment) (This can be done once we connect the data)
- Get the entire web-app running successfully in an Apache Tomcat server

Contributions Week 3:
---------------------
In week 3, I:
- Debugged the backend and fixed API methods to work with frontend
- Debugged the frontend and fixed API methods to work with backend
- Made a few small additions to the frontend
- Fixed the servlets to properly read request parameters
- Fixed the servlets to properly generate a valid JSON response
- Figured out the correct project directory structure needed for a webapp running in Tomcat
- Figured out how to turn the unpackaged project into a packaged .WAR file from command line
- Got the entire application working in a standalone Tomcat Server
- Wrote instructions for the rest of my team to get the application working in Tomcat
- Assisted the members of my team with getting their project directories structured properly and built / running

Files written by me:
--------------------
- src/backend/servlet/CityListServlet.java
- src/backend/servlet/FiltersListingsServlet.java
- src/backend/servlet/InitialListingsServlet.java
- src/frontend/index.html
- src/frontend/js/script.js
- src/frontend/css/style.css

Significant contributions to:
- src/backend/business/Backend.java

Libraries procured by me, but not developed by me:
- src/frontend/lib/bootstrap.min.js
- src/frontend/lib/jquery-3.3.1.min.js
- src/frontend/lib/jquery-ui.min.js
- src/frontend/css/boostrap.min.css
- src/frontend/css/jquery-ui.min.css

Files submitted with this project that were developed in an earlier project:
----------------------------------------------------------------------------
- src/CS400HashTable/MapADT.java
- src/CS400HashTable/HashTableMap.java
- src/CS400HashTable/HashTableLinkedList.java
- src/CS400HashTable/HashTableLinkedListNode.java
- src/CS400HashTable/TestHashTable.java

Web address at which the program is available:
----------------------------------------------
The webapp will be run in the browser; however, it is not available at a public domain. To run the app,
please follow the instructions provided (Instructions.txt), and if everything is installed correctly the app
will be available at:

http://localhost:8080/AirBnB

Additional notes about the submission:
--------------------------------------
Since the core architecture of this application involves using Java Servlets and Apache Tomcat Application Servers, neither of which is expected nor covered in this class, I took on some responsibility to help the backend get this connected and get the servlet classes developed. I take credit for setting up the class templates and implementing the HTTP API portions, and the backend developers will be responsible for the business logic that runs inside the servlet classes when invoked.
