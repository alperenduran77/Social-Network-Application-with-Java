# Variables
JAVAC = javac
JAVA = java
JAVADOC = javadoc
MAIN = Main
CLASSES = Person.java SocialNetworkGraph.java Main.java
DOCS = docs

# Default target
default: run

# Compile the Java classes
compile:
	$(JAVAC) $(CLASSES)

# Run the main class
run: compile
	$(JAVA) $(MAIN)

# Clean up the class files
clean:
	rm -f *.class

# Rebuild the project
rebuild: clean run

# Generate Javadoc
javadoc:
	$(JAVADOC) -d $(DOCS) $(CLASSES)

.PHONY: default compile run clean rebuild javadoc
