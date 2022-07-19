package gladiators.gladiatorsgame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class GladiatorsMechanic implements Listener {

    private final  Gladiators plugin;

    public GladiatorsMechanic(Gladiators plugin) {
        this.plugin = plugin;
    }



    public void setBlockAt(int argX, int argY, int argZ, Material mat){
        Bukkit.getWorld("GladiatorsWorld").getBlockAt(argX, argY, argZ).setType(mat);

    }



    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        if (p.getGameMode().equals(GameMode.SURVIVAL)){
            if(p.getWorld().equals(Bukkit.getWorld("GladiatorsWorld"))){
                Location loc = p.getLocation();
                setBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ(), Material.GLASS);
                setBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ(), Material.GLASS);
                setBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ(), Material.GLASS);
                setBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ(), Material.GLASS);

                setBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ(), Material.GLASS);
                setBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ(), Material.GLASS);

                setBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+1, Material.GLASS);
                setBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()+1, Material.GLASS);
                setBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-1, Material.GLASS);
                setBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()-1, Material.GLASS);



            }
        }
    }

    @EventHandler
    public void playerRegeneration(EntityRegainHealthEvent e){
        if (e.getEntity() instanceof  Player){
            e.setCancelled(true);
        }
    }
}
