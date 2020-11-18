# CS400 Project Four Proposal

TEAM: BC TEAM FLIPGRID: http://flipgrid.com/cochran8164

TA: Bri TA EMAIL: bcochran2@wisc.edu

TEAM EMAILS: 1. dnair5@wisc.edu 2. ajgreen4@wisc.edu 3. pfbuck@wisc.edu 4. nsingh49@wisc.edu 5. cazarinquiro@wisc.edu 6. jlorenz2@wisc.edu 7. garciacalder@wisc.edu 


## Project Title: AirBnB Locator

### Brief Project Description:
The app we will develop will help people find an AirBnB based on location, price, residential status, and top hits. People who are on a roadtrip or anyone on vacation trying to find an AirBnB in the area will find this application helpful.

### Four Chosen Requirements that this Project Fulfills:

- CSV File: All information used by the website will be gathered and formatted into a CSV file, which will then be loaded into the program and parsed when it starts

- HashTable: After the CSV file has been loaded into the program, the data will be stored in Hash Tables for easy retrieval, modification, and deletion throughout the program’s lifespan

- JavaScript: Javascript will be used within the website’s front end to interact with the html dynamically and allow for website functionality that is built into the webpage and does not require a new web page to be rendered

- CSS & HTML: HTML and CSS will be used to build the application’s GUI

- Extra: Streams & Lambda Expressions: Streams and Lambda Expressions will be used in this application for any results filtering that might be taken into consideration

- Extra: Regular Expressions / Matching: Regular Expressions / Matching will be used in this application when the user searches for locations or other available demographics. This will allow for searching on partial matches instead of requiring the entire correct value

## Data Wranglers: Navpreet Singh  

### Application Data: 
The data wrangler will provide the team with a CSV file which includes information about a number of AirBnBs including name, room id, host id, neighborhood location and other information.

### Data Format:
Once the file is loaded into the program it will be parsed into AirBnB program objects and stored in a hash table for further use.

## Back End Developer: Dyuthi Nair Luis Cazarin Quiroga 

### Data Processing:
Based on user input, we will select a state and retrieve a list of possible Airbnb locations in different cities they can stay at, which will include name, room id, host id, neighborhood, and other relevant information. We will also be able to retrieve AirBnBs from specific cities, list available cities, or choose a random city for the user. To do this, we will use a hash table to store AirBnB rental objects, keep sets of cities, and possibly use other data structures or tools when necessary.

### Front End Interface:
We will implement a find() method, as well as a get() method so the front end can find possible AirBnBs for a given city / state, and get the information for whichever location the user chooses. We will also implement a list() method to list available cities, and a method that randomly selects a city. With the methods provided by the backend, there should be no circumstance in which the front end should ever need to directly interact with the application’s underlying hash map. The front end reserves the right to request additional backend methods depending on the evolution of the application’s features and functionalities.

## Front End Developer: Alex Green Jacob Lorenz 

### User Commands:
On the website, users will have the option to input a city / state they would like to travel to. That will redirect them to a page that gives them a list of options for available AirBnBs in popular cities within that state, or see options for the specific city they provide. Any time the user enters a new location to check, a new html page is generated. Additional commands include retrieving a list of available cities and having the website choose a random city for you.

### Error Messages:
A well-written front end / application should not have error messages as a part of the standard usage / application flow. Any and all ‘errors’ the user would run into such as missing data, non-existent data, etc. will be handled gracefully. This involves communicating the issue with the user and allowing them the chance to try their request again.

## Test Engineer: Patrick Buck LEO Garcia Calderon 

### Test Descriptions:
Check that a specific city gives the correct AirBnB options

Check to see if the application correctly redirects when looking at an AirBnB

Error handling

Check that the AirBnB has the correct number of rooms

Check that the AirBnB is at the correct price

## Additional Responsibilities and Notes:

- Front end reserves the right to request additional backend methods depending on application progression / agreement on additional functionality beyond what is initially proposed
- Due to the disproportionate workload of front end / backend to other roles, frontend and backend reserves the right to request assistance from data wranglers and test engineers if the scope of the application grows to be larger than the scope outlined in this proposal
- If anyone lends their assistance to the deliverables of a role they are not assigned to, the complete authorship of source code files will reflect the collection of contributors

## Schedule:

### Due 11/24
#### Data Wranglers
All CSV Files necessary to start
#### Back End Developers
First draft of Backend.java
#### Front End Developers
First draft of Frontend.java
#### Test Engineers
First draft of Test Suite methods

### Due 12/01
#### Data Wranglers
Possibly combine the CSV files into one massive file depends on group consensus
#### Back End Developers
Final working version of Backend.java
#### Front End Developers
Final working version of Frontend.java
#### Test Engineers
Final working draft of Test Suite methods

### Due 12/08
#### Data Wranglers
Make sure Final draft of Files is uploaded and delete or add any last minute features
#### Back End Developers
Make sure Backend.java and all other files work as intended. 
#### Front End Developers
Ensure Frontend.java and all other sections function together.
#### Test Engineers
Ensure Test Suite passes after Front/Backend integration

## Signatures

Jacob Lorenz, Front End Developer 2, 11/17/2020

Alex Green, Front End Developer 1, 11/17/2020

Navpreet Singh Data Wrangler 1, 11/17/2020

Dyuthi Nair, Back End Developer 1, 11/17/2020

Luis Cazarin Quiroga, Back End Developer 2, 11/17/2020

Leonardo Garcia Calderon, Test Engineer 2, 11/17/2020

Patrick Buck, Test Engineer 1, 11/17/2020

#### End of Proposal
