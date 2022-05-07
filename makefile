SOURCE = *.java
CC		= javac
EX		= java
EXFILE = Test
OUT = *.class
FLAG = -ea

all: $(OBJECTS)
	$(CC) $(SOURCE)
	$(EX) $(FLAG) $(EXFILE)

ass: $(OBJECTS)
	$(CC) $(SOURCE)
	$(EX) $(EXFILE)

clean:
	rm **/$(OUT)