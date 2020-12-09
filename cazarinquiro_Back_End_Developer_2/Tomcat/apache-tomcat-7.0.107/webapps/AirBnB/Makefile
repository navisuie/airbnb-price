run: compile
	./../Tomcat/apache-tomcat-7.0.107/bin/startup.sh
	open http://localhost:8080/AirBnB

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