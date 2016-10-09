package lesson8;

/**
 * Created by Ivan on 08/10/16.
 */
public class Homework {

    final static String PLUGIN_ROOT_FOLDER = "/Users/Ivan/Documents/dev/PluginRoot/bin";
    final static String PLUGIN_NAME = "SimplePlugin";
    final static String PLUGIN_MAIN_CLASS = "PluginImpl";

    public static void main(String[] args) {
        PluginManager pm = new PluginManager(PLUGIN_ROOT_FOLDER);

        Plugin plugin = load(pm, PLUGIN_NAME, PLUGIN_MAIN_CLASS);

        if (plugin != null)
            plugin.doUsefull();
    }

    public static Plugin load(PluginManager pm, String pluginName, String pluginMainClass) {
        try {
            return pm.load(pluginName, pluginMainClass);
        } catch (ClassNotFoundException | InstantiationException e) {
            System.out.println("cannot load plugin: " + e.toString());
        }
        return null;
    }
}
