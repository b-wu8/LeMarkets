Here is all you need to be able to compile and run Farmer's Market Search Engine.

This also serves as a short user manual. NOTE: setup.sh serves as install.txt.

Platform: Ubuntu 18.04 LTS
Language: java-8-openjdk-amd64
Java Compiler Version: javac 1.8.0_222
Java Version: openjdk version "1.8.0_222"
Javadoc Version: javadoc 1.8.0_222
Tomcat Version: Tomcat 9.0.22
MySQL Version: 5.7.27-0ubuntu0.18.04.1 (Ubuntu)

RUNNING DIRECTIONS:
IMPORTANT:
You need the following packages added to java-8-openjdk-amd64/jre/lib/ext to ensure the program execution.
commons-lang3-3.9-sources.jar
commons-lang3-3.9.jar
javaee.jar
jsp-api.jar
jstl-1.2.jar
mysql-connector-java-8.0.17.jar
opencsv-4.6.jar
servlet-api.jar

Also, you need to add these jar files into the WebContent/WEB-INF/lib.

For MySQL connection, the credentials for the setup are as followed:
USER: root
PASSWORD: ''             (no password)
Please adjust the user password for the program to run.

1.
To setup the database, run setup.sh first. This will create a database named markets and create three tables in markets.
2.
To load into the database, run load_data.sh. This will load all data into the database.
Notice that this will take a while since there are, in total, more than 50,000 rows to load in.
3.
To compile all java files, run compile.sh. This will compile all java and servlet files and copy them into respective package folder under /build/classes.
4.
Copy the hw5.war file to your Tomcat directory, under /webapps.
Start the Tomcat server. Open your browser and go to http://localhost:8080/hw5/home.jsp. Now you should see the search engine showing up:)

To start:
You can specify the search radius by checking the selection box. The default is set to 30 miles.
You can search farmer's markets by zip code, by city and state, and by state.
The engine does not support searching only by city because, i.e., there are multiple Troy across United States.
PLEASE DO NOT SEARCH USING BOTH ZIP AND CITY AS THAT WILL BREAK THE PROGRAM.
After inputing the values, you can hit submit and wait for the result to show up.
There are 10 results per page. If there are more than 10 results, pagination will be activated. You can browse through the result using Previous and Next button.
You can also jump to a desired page by clicking the page number.
