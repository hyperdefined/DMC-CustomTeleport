package lol.hyper.customteleport;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Do /tp <x> <y> <z> instead.");
            return false;
        } else {
            if (NumberUtils.isDigits(args[0].replace("-", ""))) {
                if (NumberUtils.isDigits(args[1].replace("-", ""))) {
                    if (NumberUtils.isDigits(args[2].replace("-", ""))) {
                        int x = Integer.parseInt(args[0]);
                        int y = Integer.parseInt(args[1]);
                        int z = Integer.parseInt(args[2]);
                        if (x >= 29999999 || x <= -29999999) {
                            sender.sendMessage(ChatColor.RED + "Number is too high.");
                            return false;
                        }
                        if (z >= 29999999 || z <= -29999999) {
                            sender.sendMessage(ChatColor.RED + "Number is too high.");
                            return false;
                        }
                        Player player = (Player) sender;
                        if (!CustomTeleport.cooldowns.containsKey(player) || System.currentTimeMillis() - CustomTeleport.cooldowns.get(player) > 30000) {
                            player.teleport(new Location(player.getWorld(), x, y, z));
                            CustomTeleport.cooldowns.put(player, System.currentTimeMillis());
                            return true;
                        } else {
                            sender.sendMessage(ChatColor.RED + "Please wait " + (30 - ((System.currentTimeMillis() - CustomTeleport.cooldowns.get(player)) / 1000) +  " seconds."));
                            return false;
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Do /tp <x> <y> <z> instead.");
                        return false;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Do /tp <x> <y> <z> instead.");
                    return false;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Do /tp <x> <y> <z> instead.");
                return false;
            }
        }
    }
}
