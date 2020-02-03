package plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

import plugin.Plugin;

/**
 * Allows to create a PluginFilter
 */
public class PluginFilter implements FilenameFilter {

    @Override
    /**
     * Allows to see if it's accepted
     * @param dir directory
     * @param name a name
     * @return boolean *true if is accepted *false if isn't accepted
     */
    public boolean accept(File dir, String name) {

        if (! endingByClass(name)) return false;
        
        try {
            Class<?> cls = chargeClass(name.substring(0, name.length() - ".class".length()));
            return checkGoodInterface(cls) && checkGoodConstructor(cls) && checkGoodPackage(cls);
        } catch (ClassNotFoundException e) { 
            return false; 
        }
        catch (NoClassDefFoundError e) {
            return false;
        }
    }

    private boolean endingByClass(String name) {
        if (name.endsWith(".class"))
            return true;
        return false;
    }

    private Class<?> chargeClass(String name) throws ClassNotFoundException {
        return Class.forName("plugins."+name);
    }

    private boolean checkGoodInterface(Class<?> cls) {
        // System.out.println(Plugin.class.isAssignableFrom(cls) + " interface ?");
        return Plugin.class.isAssignableFrom(cls);
    }

    private boolean checkGoodPackage(Class<?> cls) {
        String packName = cls.getPackage().getName();
        // System.out.println(packName + " package ?");
        return packName.indexOf("plugins") != -1;
    }

    private boolean checkGoodConstructor(Class<?> cls) {
        try {
            Constructor<?> cons = cls.getConstructor();
            // System.out.println(cons.toString() + " constructor ?");
            return cons != null;
        } catch (NoSuchMethodException e ) {
            return false;
        }
    }


}