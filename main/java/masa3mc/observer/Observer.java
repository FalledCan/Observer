package masa3mc.observer;

import org.bukkit.plugin.java.JavaPlugin;

public final class Observer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("ob").setExecutor(new Gui());
        getCommand("Observer").setExecutor(new Gui());
        getServer().getPluginManager().registerEvents(new ClickInv(),this);
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
