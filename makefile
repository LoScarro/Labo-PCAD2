SOURCE = *.java
CC		= javac
EX		= java
EXFILE = Test
OUT = *.class


all: $(OBJECTS)
	$(CC) $(SOURCE)
	$(EX) $(EXFILE)

clean:
	rm **/$(OUT)