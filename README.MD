[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/MYVtI0hB)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=11361325)
# Project name BumbiBearSpringApp

## Description

A SpringBoot Apache Kafka Java application, that lets the user (through console of the IDE) both produce, and view data of a specific type, specie and full name of a BumbiBear. The data is all processed through Apache Kafka as an integration platform, hence all the data is saved in Apache Kafka localhost broker on port 8080. The data that is input, is also going through a web API (HTTP) that initially was used in this program through postman using the POST-request, but was later implemented in this program to handle the inputs instead by an Http protocol and a console UI.

The data that is sent through kafka, is also saved in both a local mySQL host on default port 3306, and either mongoDB Atlas remote with your connectionstring or local mongoDB on default port 27017 if that first choice falter. The data is also saved locally in the app in .Txt-file next to where this Readme file is located.

All is saved simultaneously with one single choice and type-in from the UI in the console.
<br> The application also have TDD (test-driven development) in testclasses connected to variable classes in the app. They can be run separately.

- What was your motivation?

I wanted to make an application that challenged my knowledge in this, specifically around systems-integration and Kafka. The original challenge was to make an application that only used one single database and saved the data there. 
But i thought, hey, why not maybe explore the other possibilities that also exist out there? So i made the decision to do both SQL, NoSQL and also a local .Txt-file. And amazingly it all works, and i understand how (but maybe i shouldnt say all that out loud)

- What problem does it solve?

It lets the user both input different parameters of chosen subject BumbiBears, send to Kafka broker, then also saves that data to databases. And, also lets the user through another console-choice view the data that has been saved through the various possibilities.

- What did you learn?

Alot about trying to handle multiple databases at once, trying to make them coexist in an application that maybe wouldnt be too hard to understand for 3rd person, and also easy to recreate and alter if that 3rd person would like to do that.
<br> Also learned alot Apache Kafka itself, how it acts as a broker that handles microservices (in this case BumbiBears) and puts them into the broker through a producer (breaking down the cog-wheels from internet and breaking it down in smaller parts)

I also like to call, and what i myself realised and came up with during this course;
<br> is that this part with Integration in developing is like <span style="color:red"> "loose candy" </span>from the shelves of the swedish term. 
<br> Where different producers produce multiple sets of different candy(information) and then put them into a big-big candy-shelves(Brokers/servers) and then lets the consumer(or groups as well) choose exactly what type of candybag they want for themselves.
<br> Free customization and creatability (but also with a little bit of ground-structure) and people be able to create what themselves want instead of someone else telling them what. Thats what its all about.

## Installation
1. Download Apache Kafka (https://kafka.apache.org/quickstart) and set it up on your local machine, and make sure the setup uses port 8080 (default) follow the precise instructions from the quickstart.
2. Download mySQL workbench (https://dev.mysql.com/downloads/workbench/) if you dont already have it and set it up there with the port 3306 (default) and
3. Create and set up your remote MongoDB environment through (https://account.mongodb.com/account/login) if you dont already have it, and also create the local MongoDB environment through the GUI compass (https://www.mongodb.com/products/tools/compass) if you would like to test the local connection as well through port 27017 (default)

4. Clone this project from Github into your own IDE.

Make sure your firewall isnt blocking any ports needed for the app, and make sure you do the setup for the different databases and Apache kafka correct if you dont already have them and know how to operate them.

Maven Dependencies in a pom.xml file thats included in this project shall have everything thats required to run this program.

Go further down to #Usage to setup the parameters needed to use mySQL and MongoDB.

## Usage


<B>For MongoDB connection:</B>
* In src->resources, alter the file <I>application.properties</I> where the String ```mongodb.connection-string=mongodb+srv://<yourmongoDBconnectionstring>``` shows up.  
  Copy-paste the your own ConnectionString from MongoDB (found under DEPLOYMENT->Database->Connect) with your own ConnectionString, and it shall work.
* If the server connection fails, it will automatically assume a local connection on port 27017.  
  Make sure you have the MongoDB Compass installed on your computer from https://www.mongodb.com/try/download/shell to try, and that it is pointing towards port 27017.

<B>For mySQL connection:</B>
* In src->resources, alter the file <I>application.properties</I> 
change the password on datasource.password,  and tablename from datasource.url to your own in order to be able test local MySQL on port 3306.


Finally, Run the program from the IDE and handle the UI-choices from the console menu, and view the data in real-time getting shown in the console produced and saved, from either (or both) mySQL, MongoDB and .Txt-file.


## Credits
Classmates from school for the various groupdiscussions, ball-plank, solving and implementations, Myself, my family, Mighty Duck rubber duck and some chatGPT for debugging.

## License
🏆 MIT License

## Badges
![badmath](https://img.shields.io/badge/Java-100%25-blue)

## Features
Save specific parameters for a BumbiBear through console to database(:s)  
View specific parameters for a BumbiBear through console from database(:s)

## Tests
Tests included (Mockito5, SpringBootTest and JUNIT5) for various public methods in the program.