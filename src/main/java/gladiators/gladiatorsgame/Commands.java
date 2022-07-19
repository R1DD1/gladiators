package gladiators.gladiatorsgame;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    private final Gladiators plugin;
    List<Player> onlinePlayers = new ArrayList<Player>();
    int playersInLine;

    public Commands(Gladiators plugin) {
        this.plugin = plugin;
    }

    public int randomInt(int arg0){
        int i = (int) (Math.random() * arg0);
        return i;
    }

    public void spawnPlayers(){

    }

    int gameProcess;
    int punishmentOfGods;
    int timer;
    int newZ = 10;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        timer = 125;
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("startGlad")){
            for (Player ps : Bukkit.getOnlinePlayers()){
                onlinePlayers.add(ps);
                ps.teleport(new Location(Bukkit.getWorld("GladiatorsWorld"), -10, 30, newZ));
            }

            gameProcess = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    p.sendMessage(String.valueOf(timer));
                    timer = timer-1;

                    if (timer==120){
                        int radius  = 5;
                        for (int x = p.getLocation().getBlockX() - radius ; x <= p.getLocation().getBlockX() + radius ; x++) {
                            for (int y = p.getLocation().getBlockY() - radius ; y <= p.getLocation().getBlockY() + radius ; y++) {
                                for (int z = p.getLocation().getBlockZ() - radius ; z <= p.getLocation().getBlockZ() + radius ; z++) {
                                    Block block = p.getLocation().getWorld().getBlockAt(x, y, z);
                                    if (block.getType().equals(Material.GLASS)){
                                        block.setType(Material.AIR);
                                    }
                                }
                            }
                        }
                    }else if(timer == 60){
                        Bukkit.broadcastMessage("КАРА БОГОВ");
                        punishmentOfGods = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                            @Override
                            public void run() {

                                for (Player p : Bukkit.getOnlinePlayers()){
                                    p.setHealth(p.getHealth()-1);
                                }
                            }
                        },0, 60);
                    } else if (timer == 0) {
                        Bukkit.broadcastMessage("КОНЕЦ");
                        Bukkit.getScheduler().cancelTask(gameProcess);
                        Bukkit.getScheduler().cancelTask(punishmentOfGods);
                    }
                }
            },20,20);
//            int playerAmount = onlinePlayers.size();
//
//            if (playerAmount%4==0){
//                playersInLine = playerAmount/4;
//            }else {
//                int remains = playerAmount%4;
//                playersInLine = playerAmount/4;
//                playersInLine = playersInLine+1;
//            }
//
//            for (int i =0; i<=playersInLine; i++){
//
//                onlinePlayers.get(i).teleport(new Location(Bukkit.getWorld("GladiatorsWorld"), -10, 30, newZ));
//                newZ = newZ -5;
//
//            }
        }
        return true;
    }
}
