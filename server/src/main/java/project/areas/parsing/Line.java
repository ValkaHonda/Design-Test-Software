package project.areas.parsing;

import java.util.regex.Pattern;

public abstract class Line {
    public Line(){

    }

    public abstract Line getInstanceFromString(String fromString);

    public abstract Pattern getLineFormat();
}

