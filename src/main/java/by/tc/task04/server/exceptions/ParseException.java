package by.tc.task04.server.exceptions;

public class ParseException extends RuntimeException {
    public ParseException(String fragment){
        super("Following fragment cannot be parsed:/n" + fragment);
    }
}
