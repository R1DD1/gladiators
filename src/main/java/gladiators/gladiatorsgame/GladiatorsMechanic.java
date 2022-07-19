package gladiators.gladiatorsgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class GladiatorsMechanic implements Listener {

    private   Gladiators plugin;

    public GladiatorsMechanic(Gladiators plugin) {
        this.plugin = plugin;
    }


    Commands commands = new Commands(plugin);
//    public void setBlockAt(int argX, int argY, int argZ, Material mat){
//        Bukkit.getWorld("GladiatorsWorld").getBlockAt(argX, argY, argZ).setType(mat);
//
//    }

    int gameMechanicScheduler;

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        if (p.getGameMode().equals(GameMode.SURVIVAL)){
            if(p.getWorld().equals(Bukkit.getWorld("GladiatorsWorld"))){
                gameMechanicScheduler = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        for (Player players : Bukkit.getOnlinePlayers()){
                            if (players.getGameMode().equals(GameMode.SURVIVAL)){
                                players.getWorld().strikeLightning(players.getLocation());
                            }
                        }
                    }
                },65*20, 15*20);
            }
                //                Location loc = p.getLocation();
//                setBlockAt(loc.getBlockX()+1, loc.getBlockY(), loc.getBlockZ(), Material.GLASS);
//                setBlockAt(loc.getBlockX()+1, loc.getBlockY()+1, loc.getBlockZ(), Material.GLASS);
//                setBlockAt(loc.getBlockX()-1, loc.getBlockY(), loc.getBlockZ(), Material.GLASS);
//                setBlockAt(loc.getBlockX()-1, loc.getBlockY()+1, loc.getBlockZ(), Material.GLASS);
//
//                setBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ(), Material.GLASS);
//                setBlockAt(loc.getBlockX(), loc.getBlockY()+2, loc.getBlockZ(), Material.GLASS);
//
//                setBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()+1, Material.GLASS);
//                setBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()+1, Material.GLASS);
//                setBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()-1, Material.GLASS);
//                setBlockAt(loc.getBlockX(), loc.getBlockY()+1, loc.getBlockZ()-1, Material.GLASS);
        }
    }
    List<Player> onlinePlayers = new ArrayList<Player>();
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        p.setGameMode(GameMode.SPECTATOR);
        p.setHealth(20);
        e.setDeathMessage("");
        for (Player players : Bukkit.getOnlinePlayers()){
            if (players.getGameMode().equals(GameMode.SURVIVAL)){
                onlinePlayers.add(players);
            }
        }
        if (onlinePlayers.size()==1){
            Player winner = onlinePlayers.get(0);
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatColor.BOLD + "Победитель " + ChatColor.GREEN + winner.getPlayerListName());
            Bukkit.broadcastMessage("");
        }


    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        Player damager = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();
        if (damager.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)){
            victim.setWalkSpeed((float) (victim.getWalkSpeed()*0.5));
            victim.sendMessage("Вас ранили, ваша скорость уменшена в 2 раза!");
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    victim.setWalkSpeed(0.2F);
                    victim.sendMessage("Вы поправились");
                }
            }, 60);
        }
    }



    @EventHandler
    public void playerRegeneration(EntityRegainHealthEvent e){

        if (e.getEntity().getWorld().equals(Bukkit.getWorld("GladiatorsWorld"))){
            if (e.getEntity() instanceof  Player){
                e.setCancelled(true);
            }
        }
    }
}
