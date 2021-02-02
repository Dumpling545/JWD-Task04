package by.tc.task04.server.parsers;

import by.tc.task04.server.exceptions.ParseException;

public interface Parser<Source, Destination> {
    Destination parse(Source src) throws ParseException;
    Source restore(Destination dest);
}
