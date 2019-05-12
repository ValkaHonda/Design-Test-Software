package project.areas.parsing;

import project.areas.utilities.Utilities;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineFactory {
    private static LineFactory instance = new LineFactory();
    private final Map<Pattern, Line> supportedFormats;

    public static LineFactory getInstance() {
        return instance;
    }

    private LineFactory() {
        supportedFormats = new HashMap<>();
        Class[] classes;
        try{
            classes = Utilities.getClasses("parsing");
            for(Class clazz : classes){
                if(Objects.equals(clazz.getGenericSuperclass(), Line.class) && !Modifier.isAbstract(clazz.getModifiers())){
                    Constructor[] constructors = clazz.getConstructors();
                    for(Constructor constructor : constructors){
                        if(constructor.getParameterCount() == 0){
                            Line line = (Line) constructor.newInstance();
                            supportedFormats.put(line.getLineFormat(), line);
                        }
                    }
                }
            }
        }
        catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    private Line canConvert(String fromString){
        if(Utilities.isNullOrEmpty(fromString)){
            return null;
        }

        for(Pattern pattern : supportedFormats.keySet()){
            Matcher matcher = pattern.matcher(fromString);
            if(matcher.matches())
                return supportedFormats.get(pattern);
        }

        return null;
    }

    public Line convert(String fromString){
        Line line = canConvert(fromString);
        if(line == null)
            return null;

        return line.getInstanceFromString(fromString);
    }
}
