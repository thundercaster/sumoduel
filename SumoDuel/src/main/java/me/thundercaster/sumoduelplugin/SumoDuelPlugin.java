package me.thundercaster.sumoduelplugin;

import me.thundercaster.sumoduelplugin.Commands.SumoCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SumoDuelPlugin extends JavaPlugin {

    public List<Player> plist = new ArrayList<>();
    @Override
    public void onEnable() {

        // Plugin startup logic
        getCommand("SumoDuel").setExecutor(new SumoCommand(this));

    }

    public boolean inventorycheck(Player p){
        if (p.getInventory().isEmpty()){
            p.sendMessage(ChatColor.AQUA + "Your Inventory is Empty");
            return true;
        }
        p.sendMessage(ChatColor.RED + "You must Empty Your Inventory");
        return false;
    }
    public boolean healthcheck(Player p){
        if (p.getHealth() == p.getMaxHealth()){
            p.sendMessage(ChatColor.AQUA + "You are at full health");
            return true;
        }
        return false;
    }
    public boolean saturationcheck(Player p){
        if (p.getSaturation() == 14){
            p.sendMessage(ChatColor.AQUA + "Your Saturation is full");
            return true;
        }
        p.sendMessage(ChatColor.RED + "Your Should Saturation Be Full");
        return false;
    }
    public boolean overallcheck(Player p){
        if (saturationcheck(p) & healthcheck(p) & inventorycheck(p)){
            return true;
        }
        return false;
    }

}
