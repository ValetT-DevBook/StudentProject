package plugin;

import plugin.observer.FileChecker;
import plugin.observer.SimplePluginObserver;

public class Main {

    public static void main(String[] args) {
        FileChecker check = new FileChecker(new PluginFilter());
        check.addFileListener(new SimplePluginObserver());
        check.start("repository/plugins");
        while(true) {}
    }
}
