JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	page_replacement.java \
	fifo.java \
	lru.java \
	optimal.java \
	random.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
