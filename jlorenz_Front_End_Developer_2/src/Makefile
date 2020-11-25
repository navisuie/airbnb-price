run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."
	java frontend/Main

compile:
	@echo "FIXME: *compile* should compile your code"
	javac frontend/Main.java

test: compile
	@echo "FIXME: *test* should run all your group's testing engineer's JUnit tests for the application"
	javac -cp .:dependencies/junit/junit5.jar test/BackendTest.java
	java -jar dependencies/junit/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) backend/*.class
	$(RM) frontend/*.class
	$(RM) test/*.class