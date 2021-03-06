README for Team Project Four (CS400 @ UW Madison)
==================================================

Every member of a team must have an individual README.txt file filled in in their folder on
the team's GitHub repo.

Name of submitting team member: Luis Cazarin Quiroga
Wisc email of submitting team member: cazarinquiro@wisc.edu
Team name: BC
Role of submitting team member: Back End Developer 2
TA: Brianna Cochran
Lecturer: Florian Heimerl

Contributions Week 1:
---------------------
Pushed BackEnd.java, MapADT.java, and HashTableMap.java for the first Back End draft.
There are placeholders for the data and data loading, but otherwise it is a general skeleton for the Back End that implements a hash table and an object class.
Still working on implementing the data as well as the Java servlet, and the Apache Tomcat 7.0 servlet container.

Contributions Week 2:
---------------------
Updated backend by expanding on the Airbnb class, and fleshing out the skeleton from last week. Incorporated most of the data loading.
Incorporated OpenCSV into BackEnd.java. Scanner and Stream weren't cutting it - having so many CSVs with so many lines didn't make it practical, so I decided to not reinvent the wheel and instead use OpenCSV to read the CSVs. This is reflected in the new dependencies folder in BackEnd. Otherwise, wrapped up Backend and just need to make sure loading data works
correctly, then we can start putting everything together. 
Might reconsider using OpenCSV - if I can get streams working, will use streams instead.
Added an extra column to Data/Cities.csv for states. 

Contributions Week 3:
---------------------
<Replace this with a list your contributions for the week 3 deliverable.
You can list the java files that you wrote and committed during the first week,
or the data files your created or worked on. If you haven't written any code or
other content that your can commit, provide a list of the work that you did
during week 3. Make sure that all your week 3 contributions and this section
of the readme are committed and pushed to the GitHub repo by the week 3 deadline
on 12/08 at 11:59pm CST.>
Updated Backend several times to fix bugs, incorporated Backend into Jake's servlet classes
Updated Backend to provide methods for filtered and unfilitered listings, as well as provide a given quantity of Airbnbs according to user input


Files written by me:
--------------------
BackEnd.java
Makefile (with some of Jake's help)
Java files in CS400 HashTable (MapADT and HashTableMap)
Helped with all the Backend/business logic of the Servlet classes made by Jake

Files submitted with this project that were developed in an earlier project:
----------------------------------------------------------------------------
HashTableMap.java
MapADT.java
Originally developed for Project 1 application.

Web address at which the program is available:
----------------------------------------------
<If you and your team have written a web app that is available on the internet,
replace this text with its address. This is not a requirement of Project Four
and you will not loose points for not implementing a web application or for not
having a running version on the web.>
http://localhost:8080/AirBnB
This should open automatically when running the Makefile on the CS lab machines

Additional notes about the submission:
--------------------------------------
Thanks for a great semester!
