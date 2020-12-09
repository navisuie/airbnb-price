  run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."

compile: Backend.class
	@echo "Project has compiled"
	
Backend.class: HashTableMap.class
	javac backend/buisness/Backend.java

HashTableMap.class: LinkedListADT.class
	javac src/backend/HashTableMap.java

LinkedListADT.class: LinkedNode.class
	javac src/backend/LinkedListADT.java

LinkedNode.class: 
	javac src/backend/LinkedNode.java

test: compile
	javac -cp .:src/test/junit5.jar src/test/backend_test.java
	java -jar src/test/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) src/backend/business/*.class
	$(RM) src/test/*.class
