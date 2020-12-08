run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."

compile: BackEnd.class
	@echo "Project has compiled"
	
BackEnd.class: HashTableMap.class
	javac BackEnd/BackEnd.java

HashTableMap.class: Node.class MapADT.class
	javac BackEnd/HashTableMap.java

Node.class:
	javac BackEnd/Node.java

MapADT.class:
	javac BackEnd/MapADT.java

test: compile
	javac -cp .:Test/junit5.jar Test/backend_test.java
	java -jar Test/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) BackEnd/*.class
	$(RM) Test/*.class
