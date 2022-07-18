package gladiators.gladiatorsgame;

import org.bukkit.plugin.java.JavaPlugin;

public final class Gladiators extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new GladiatorsMechanic(), this);
    }
}
