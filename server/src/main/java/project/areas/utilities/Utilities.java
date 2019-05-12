package project.areas.utilities;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Utilities {
    public static boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

    @SuppressWarnings("ConstantConditions")
    public static Class[] getClasses(@NotNull String packageName)
            throws ClassNotFoundException, IOException {
        List<Class> classes = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //Should never be null
        assert classLoader != null;

        Enumeration<URL> resources = classLoader.getResources(packageName);
        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            File directory = new File(resource.getFile());
            if(directory.isDirectory()){
                for(File file : directory.listFiles()){
                    if(file.getName().endsWith(".class")){
                        Class clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                        classes.add(clazz);
                    }
                }
            }
        }

        return classes.toArray(new Class[0]);
    }
}
