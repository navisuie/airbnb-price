run: compile
	./../Tomcat/apache-tomcat-7.0.107/bin/startup.bat
	xdg-open http://localhost:8080/AirBnB

compile: BackEnd.class
	cd AirBnb;
	jar -cvf AirBnB.war *
	cp AirBnB.war ../Tomcat/apache-tomcat-7.0.107/webapps
	cp ../src/backend/business/*.java WEB-INF/classes/backend/business/
	cp ../src/backend/servlet/*.java WEB-INF/classes/backend/servlet/
	cp ../src/CS400HashTable/*.java WEB-INF/classes/CS400HashTable/
	
	
BackEnd.class: HashTableMap.class
	javac backend/buisness/Backend.java

HashTableMap.class: Node.class MapADT.class
	javac src/CS400HashTable/HashTableMap.java

Node.class:
	javac src/CS400HashTable/Node.java

MapADT.class:
	javac src/CS400HashTable/MapADT.java

test: compile
	javac -cp .:src/test/junit5.jar src/test/backend_test.java
	java -jar src/test/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) src/backend/business/*.class
	$(RM) src/test/*.class
	$(RM) src/CS400HashTable/*.class
