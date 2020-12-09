run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."

compile: BackEnd.class
	@echo "Project has compiled"
	
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
