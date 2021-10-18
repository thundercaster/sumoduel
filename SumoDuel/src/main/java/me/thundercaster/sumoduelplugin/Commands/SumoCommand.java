package me.thundercaster.sumoduelplugin.Commands;

import me.thundercaster.sumoduelplugin.SumoDuelPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class SumoCommand implements CommandExecutor, Listener {

    SumoDuelPlugin plugin;

    public SumoCommand(SumoDuelPlugin plugin) {
        this.plugin = plugin;
    }
    public int number = 3;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender==null){
            return false;
        }
        if (sender instanceof Player p){
            if (!(args.length==1)){
                p.sendMessage(ChatColor.RED + "Please enter the name of the person that you want to duel");
            }
            else {
                Player p1 = plugin.getServer().getPlayer(args[0]);
                if (p1 == null){
                    p.sendMessage(ChatColor.RED + "couldn't find targeted player");
                }
                else {
                    if (plugin.overallcheck(p) & plugin.overallcheck(p1)){
                        Location pl = p.getLocation();
                        p1.teleport(pl);
                        plugin.plist.add(p);
                        plugin.plist.add(p1);
                        WoolCircle(pl, 10);
                        plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin,new Runnable(){
                            @Override
                            public void run() {
                                if (number!= -1){
                                    if (number!=0){
                                        p.sendMessage("" + number);
                                        p1.sendMessage("" + number);
                                        number--;

                                    }else{
                                        p.sendMessage(ChatColor.GOLD + "FIGHT!");
                                        p1.sendMessage("" + number);
                                        number--;
                                    }
                                }
                            }
                        }, 0L, 20L);
                        //not finished im tired
                        //detector of result
                        //result
                        //health resest


                    }
                    else{
                        p.sendMessage(ChatColor.RED + "A verification isn't right");
                    }
                }


            }
        }
        return true;
    }
    @EventHandler
    public void freeze(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if (plugin.plist.contains(p)){
            e.setCancelled(true);
        }


    }
    @EventHandler
    public void nohit(EntityDamageByEntityEvent e){
        if (e instanceof Player p){
            if (plugin.plist.contains(p)){
                if(e.getEntity()instanceof Player){
                    e.setCancelled(true);
                }
            }
        }
    }
    public void WoolCircle(Location l, float radius){
        for (double t = 0; t<15; t+=0.5) {
            float x = radius*(float)Math.sin(t);
            float z = radius*(float)Math.cos(t);
            l.getBlock().setType(Material.RED_WOOL);

        }

    }
}

