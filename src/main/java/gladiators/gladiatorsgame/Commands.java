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

    public Commands(Gladiators plugin) {
        this.plugin = plugin;
    }

    boolean gameIsOn = false;

    public boolean isGameIsOn() {
        return gameIsOn;
    }

    public void setGameIsOn(boolean gameIsOn) {
        this.gameIsOn = gameIsOn;
    }

    List<Player> onlinePlayers = new ArrayList<Player>();
    Location firstLay;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("start")){
            if (!gameIsOn){
                ((Player) sender).teleport(new Location(Bukkit.getWorld("GladiatorsWorld"), 0, 35, 0));
                for (Player ps : Bukkit.getOnlinePlayers()){
                    onlinePlayers.add(ps);
                }
                Location startLoc = new Location(Bukkit.getWorld("GladiatorsWorld"), 10, 35, 10);

                for (int i =0; i<=onlinePlayers.size(); i++){
                    //первый слой игроков
                    firstLay = startLoc;

                    onlinePlayers.get(i).teleport(firstLay);
                    firstLay.setZ(startLoc.getBlockZ() -5);

                    if (i%5==0){
                        firstLay.setX(firstLay.getBlockX()-5);
                        firstLay.setZ(10);
                    }

                }
                gameIsOn = true;
            }else {
                sender.sendMessage("Игра уже идет");
            }
        }
        return true;
    }
}
