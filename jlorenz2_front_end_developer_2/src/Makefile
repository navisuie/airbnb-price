run: compile
	java frontend/Main

compile:
	javac frontend/Main.java

test: compile
	javac -cp .:dependencies/junit/junit5.jar test/BackendTest.java
	java -jar dependencies/junit/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) backend/*.class
	$(RM) frontend/*.class
	$(RM) test/*.class