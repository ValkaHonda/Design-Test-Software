package project.areas.parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public final class MoodleLogLine extends Line{
    private final LocalDateTime time;
    private final String eventContext;
    private final String eventContextTarget;
    private final String component;
    private final String eventName;
    private final String eventDescription;
    private final String origin;
    private final String ip;

    private static final Pattern delimiter = Pattern.compile(",");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("\"d/M/yy H:mm\"");
    private static final Pattern eventContextPattern = Pattern.compile("(.+)(:\\s)(.+)");

    // TODO: Annotation processor to require default constructor
    //Default constructor required
    public MoodleLogLine(){
        super();
        time = null;
        eventContext = null;
        eventContextTarget = null;
        component = null;
        eventName = null;
        eventDescription = null;
        origin = null;
        ip = null;
    }

    private MoodleLogLine(LocalDateTime time, String eventContext, String eventContextTarget, String component, String eventName, String eventDescription, String origin, String ip) {
        this.time = time;
        this.eventContext = eventContext;
        this.eventContextTarget = eventContextTarget;
        this.component = component;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.origin = origin;
        this.ip = ip;
    }

    @Override
    public Line getInstanceFromString(String fromString){
        String[] parts = fromString.split(",");
        //time
        LocalDateTime time = LocalDateTime.parse(parts[0].concat(parts[1]), dateTimeFormatter);

        //event context
        String eventContext;
        String eventContextTarget = "";
        if(eventContextPattern.matcher(parts[2]).matches()){
            String[] eventContextParts = parts[2].split(":\\s");
            eventContext = eventContextParts[0];
            eventContextTarget = eventContextParts[1];
        }
        else {
            eventContext = parts[2];
        }

        //component
        String component = parts[3];

        //event name
        String eventName = parts[4];

        String eventDescription = parts[5];

        //origin
        String origin = parts[6];

        //ip
        String ip = parts.length > 7 ? parts[7] : "ADMIN";

        return new MoodleLogLine(time, eventContext, eventContextTarget, component, eventName, eventDescription, origin, ip);
    }

    @Override
    public Pattern getLineFormat(){
        return Pattern.compile("^\"([0-3]?\\d\\/{1})([01]?\\d\\/{1})(([1-9]\\d{3})|\\d{2}),\\s*(([01]\\d)|(2[0-4])):([0-5]\\d)\"(,[^,]+){5},((\\d+\\.){3}(\\d+))?");
    }

    @Override
    public String toString() {
        return "MoodleLogLine{" +
                "time=" + time +
                ", eventContext='" + eventContext + '\'' +
                ", eventContextTarget='" + eventContextTarget + '\'' +
                ", component='" + component + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", origin='" + origin + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}

