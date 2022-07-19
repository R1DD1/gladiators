package gladiators.gladiatorsgame;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gladiators extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new GladiatorsMechanic(this), this);
        getCommand("start").setExecutor(new Commands(this));

        WorldCreator gladiatorsWorld = new WorldCreator("GladiatorsWorld");
        gladiatorsWorld.type(WorldType.FLAT);
        gladiatorsWorld.generatorSettings("2;0;1;");
        gladiatorsWorld.createWorld();


    }
}
