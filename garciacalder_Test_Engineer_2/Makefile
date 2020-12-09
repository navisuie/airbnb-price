run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."
	java frontend/Main

compile:
	@echo "FIXME: *compile* should compile your code"
	javac src/frontend/Main.java

test: compile
	@echo "FIXME: *test* should run all your group's testing engineer's JUnit tests for the application"
	javac -cp .:src/test/junit5.jar src/test/BackEndTest.java
	java -jar src/test/junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) backend/*.class
	$(RM) frontend/*.class
	$(RM) test/*.class
