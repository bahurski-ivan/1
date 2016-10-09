package lesson8;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Ivan on 08/10/16.
 */
public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws InstantiationException, ClassNotFoundException {
        Object instance = null;

        try {
            PluginClassLoader classLoader = new PluginClassLoader(pluginRootDirectory, pluginName + '.' + pluginClassName);
            instance = classLoader.getMainClass().newInstance();    // create instance of plugin

        } catch (IllegalAccessException | InstantiationException e) {
            throw new InstantiationException(".class cannot be instantiated: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            throw new ClassNotFoundException(e.getMessage());
        }

        return new PluginProxyImpl(instance);   // return loaded instance with wrapper
    }

    private class PluginProxyImpl implements Plugin {
        private final Object instance;

        PluginProxyImpl(Object instance) {
            this.instance = instance;
        }

        @Override
        public void doUsefull() {
            try {
                instance.getClass().getMethod("doUsefull").invoke(instance);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }
        }
    }
}
