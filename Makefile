all: jeu.jar cls

compile-class:
	javac -classpath jars/json-20220924.jar:src -d classes src/pandemics/*.java

compile-test:
	javac -classpath classes:jars/junit-4.13.2.jar -d classes_test test/*.java

cls: compile-class compile-test

main: compile-class	
	java -cp jars/json-20220924.jar:classes pandemics.Main

test: cls
	java -jar jars/junit-platform-console-standalone-1.9.2.jar -cp classes:test:jars/json-20220924.jar --scan-classpath 

docs:
	javadoc -classpath jars/json-20220924.jar:src -d docs -subpackages pandemics

clean:
	rm -rf docs classes classes_test

jeu.jar: cls
	jar cvfm jars/jeu.jar manifest.txt -C classes .

.PHONY: all clean