run: compile
	@echo "FIXME: *run* should be the default target and compile and execute your code."

compile:
	@echo "FIXME: *compile* should compile your code"

test:
	javac -cp .:junit5.jar BackendTest.java
	java -jar junit5.jar --class-path . --scan-classpath --details tree

clean:
	$(RM) *.class
