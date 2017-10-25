package TestDriver;

import org.junit.runner.*;

public class Driver {

    public static void main(String[] args) {

        JUnitCore.main(
	     "TestDriver.DictionaryTest",
	     "TestDriver.WordParserTest"
        );
	
    }

}

