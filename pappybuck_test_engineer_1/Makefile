run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."

compile:
	javac BackEnd/BackEnd.java
	javac BackEnd/HashTableMap.java
	javac BackEnd/MapADT.java
	javac BackEnd/Node.java


test: compile
	javac -cp .:Test/junit5.jar Test/BackendTest.java
	java -jar Test/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) BackEnd/*.class
	$(RM) Test/*.class
