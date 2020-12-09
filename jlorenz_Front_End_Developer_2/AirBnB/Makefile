# run: compile
# 	java frontend/Main

# compile:
# 	javac graph/GraphADT.java
# 	javac graph/CS400Graph.java
# 	javac backend/RoadTripPath.java
# 	javac backend/SessionController.java
# 	javac frontend/Main.java

# test: compile
# 	javac -cp .:dependencies/junit/junit5.jar test/BackendTest.java
# 	javac -cp .:dependencies/junit/junit5.jar test/GraphTest.java
# 	java -jar dependencies/junit/junit5.jar --class-path . --scan-classpath --details tree

# clean:
# 	$(RM) backend/*.class
# 	$(RM) frontend/*.class
# 	$(RM) graph/*.class
# 	$(RM) test/*.class


run: compile
	./../Tomcat/apache-tomcat-7.0.107/bin/startup.sh
	xdg-open http://localhost:8080/AirBnB

compile:
	jar -cvf AirBnB.war *
	cp AirBnB.war ../Tomcat/apache-tomcat-7.0.107/webapps
	cp ../src/backend/business/*.java WEB-INF/classes/backend/business/
	cp ../src/backend/servlet/*.java WEB-INF/classes/backend/servlet/
	cp ../src/CS400HashTable/*.java WEB-INF/classes/CS400HashTable/

test:

clean:
	./../Tomcat/apache-tomcat-7.0.107/bin/shutdown.sh
	$(RM) AirBnB.war
	$(RM) ../Tomcat/apache-tomcat-7.0.107/webapps/AirBnB.war
	$(RM) -r ../Tomcat/apache-tomcat-7.0.107/webapps/AirBnB/