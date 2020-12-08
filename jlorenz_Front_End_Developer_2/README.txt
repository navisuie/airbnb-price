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
<Replace this with a list your contributions for the week 3 deliverable.
You can list the java files that you wrote and committed during the first week,
or the data files your created or worked on. If you haven't written any code or
other content that your can commit, provide a list of the work that you did
during week 3. Make sure that all your week 3 contributions and this section
of the readme are committed and pushed to the GitHub repo by the week 3 deadline
on 12/08 at 11:59pm CST.>

Files written by me:
--------------------
<List ALL of the source files here that were written by you. Note that each of the
files must contain correct file headers, including your name, team, and role. List
files one per line in this section of the README. Only complete this section with
your final week 3 submission of project four.>

Files submitted with this project that were developed in an earlier project:
----------------------------------------------------------------------------
- src/CS400HashTable/MapADT.java
- src/CS400HashTable/HashTableMap.java
- src/CS400HashTable/HashTableLinkedList.java
- src/CS400HashTable/HashTableLinkedListNode.java
- src/CS400HashTable/TestHashTable.java

Web address at which the program is available:
----------------------------------------------
<If you and your team have written a web app that is available on the internet,
replace this text with its address. This is not a requirement of Project Four
and you will not loose points for not implementing a web application or for not
having a running version on the web.>

Additional notes about the submission:
--------------------------------------
Since the core architecture of this application involves using Java Servlets and Apache Tomcat Application Servers, neither of which is expected nor covered in this class, I took on some responsibility to help the backend get this connected and get the servlet classes developed. I take credit for setting up the class templates and implementing the HTTP API portions, and the backend developers will be responsible for the business logic that runs inside the servlet classes when invoked.
