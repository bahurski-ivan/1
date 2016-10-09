package lesson8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 09/10/16.
 */
public class PluginClassLoader extends ClassLoader {
    private final String pluginFolderPath;
    private Map<String, Class<?>> cache = new HashMap<>();
    private Class<?> mainClass;

    public PluginClassLoader(String pluginFolderPath, String pluginMainClassName) throws ClassNotFoundException {
        this.pluginFolderPath = pluginFolderPath;
        Class<?> clazz = loadClass(pluginMainClassName), pluginInterface = null;

        if (Arrays.stream(clazz.getInterfaces())
                .filter(c -> c.getName().endsWith("Plugin") && compareInterfaces(c, Plugin.class))
                .count() == 0)
            throw new ClassNotFoundException("plugin '" + pluginMainClassName + "' not implements 'Plugin' interface");

        mainClass = clazz;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }

    public Class<?> getMainClass() {
        return mainClass;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = cache.get(name);
        if (clazz != null)  // if class already loaded -> return it
            return clazz;

        byte[] fileContent;
        Path filePath = Paths.get(pluginFolderPath + '/' + name.replace('.', '/').concat(".class"));

        try {
            fileContent = Files.readAllBytes(filePath);
            clazz = handleClassBytes(name, fileContent);
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException(".class file not found", e);
        } catch (NoSuchFileException e) {
            clazz = getParent().loadClass(name);
        } catch (IOException e) {
            throw new ClassNotFoundException("error while reading .class file", e);
        }

        return clazz;
    }

    private Class<?> handleClassBytes(String name, byte[] buffer) {
        Class<?> clazz = defineClass(name, buffer, 0, buffer.length);
        cache.put(name, clazz);
        return clazz;
    }

    private boolean compareInterfaces(Class<?> a, Class<?> b) {
        Set<String> methodsA = new HashSet<>();
        Set<String> methodsB = new HashSet<>();

        methodsA.addAll(Arrays
                .stream(a.getMethods())
                .map(Method::getName)
                .collect(Collectors.toList()));

        methodsB.addAll(Arrays
                .stream(b.getMethods())
                .map(Method::getName)
                .collect(Collectors.toList()));

        return methodsA.equals(methodsB);
    }
}
