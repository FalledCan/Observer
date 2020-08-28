package masa3mc.observer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class Gui implements CommandExecutor {

    static HashMap<Player,Player> obs = new HashMap<Player, Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if(player.hasPermission("ob.gui")){
                if(args.length == 1){
                    Player t = Bukkit.getPlayer(args[0]);
                    if(!t.isOnline()){
                        player.sendMessage(ChatColor.RED +"そのプレイヤーはオンラインではありません。");
                        return true;
                    }
                    obs.put(((Player) sender).getPlayer(),Bukkit.getPlayer(args[0]));
                    Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "ObserverGUI");
                    ItemStack itemStack1 = new ItemStack(Material.CHEST);
                    ItemMeta meta1 = itemStack1.getItemMeta();
                    meta1.setDisplayName(ChatColor.GOLD + "Open Player Inventory") ;
                    itemStack1.setItemMeta(meta1);
                    inv.setItem(10, itemStack1);

                    ItemStack itemStack2 = new ItemStack(Material.ENDER_CHEST);
                    ItemMeta meta2 = itemStack2.getItemMeta();
                    meta2.setDisplayName(ChatColor.DARK_PURPLE + "Open Player Ender-chest");
                    itemStack2.setItemMeta(meta2);
                    inv.setItem(12, itemStack2);

                    ItemStack itemStack3 = new ItemStack(Material.RED_DYE);
                    ItemMeta meta3 = itemStack3.getItemMeta();
                    meta3.setDisplayName(ChatColor.RED + "Teleport To Player");
                    itemStack3.setItemMeta(meta3);
                    inv.setItem(14, itemStack3);

                    ItemStack itemStack4 = new ItemStack(Material.PAPER);
                    ItemMeta meta4 = itemStack4.getItemMeta();
                    meta4.setDisplayName(ChatColor.WHITE + "Player Info");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.WHITE + "mcid:" + ChatColor.GREEN  + t.getName());
                    lore.add(ChatColor.WHITE + "uuid:" + ChatColor.GREEN  + t.getUniqueId());
                    lore.add(ChatColor.WHITE + "hp:" + ChatColor.GREEN  + t.getHealth() + "/20");
                    lore.add(ChatColor.WHITE + "food:" + ChatColor.GREEN  + t.getFoodLevel() + "/20");
                    lore.add(ChatColor.WHITE + "level:" + ChatColor.GREEN  + t.getLevel());
                    lore.add(ChatColor.WHITE + "gamemode:" + ChatColor.GREEN  + t.getGameMode());
                    lore.add(ChatColor.WHITE + "world:" + ChatColor.GREEN + t.getWorld().getName());
                    lore.add(ChatColor.WHITE + "x:" + ChatColor.GREEN  + (int)t.getLocation().getX() + ChatColor.WHITE + "y:" + ChatColor.GREEN  + (int)t.getLocation().getY() + ChatColor.WHITE + "z:" + ChatColor.GREEN  + (int)t.getLocation().getZ());
                    lore.add(ChatColor.WHITE + "fly:" + ChatColor.GREEN  + t.getAllowFlight());
                    meta4.setLore(lore);
                    itemStack4.setItemMeta(meta4);
                    inv.setItem(16, itemStack4);

                    ItemStack itemStack5 = new ItemStack(Material.ICE);
                    ItemMeta meta5 = itemStack5.getItemMeta();
                    meta5.setDisplayName(ChatColor.AQUA + "freeze");
                    itemStack5.setItemMeta(meta5);
                    inv.setItem(22, itemStack5);

                    ItemStack itemStack6 = new ItemStack(Material.BARRIER);
                    ItemMeta meta6 = itemStack6.getItemMeta();
                    meta6.setDisplayName(ChatColor.RED + "close");
                    itemStack6.setItemMeta(meta6);
                    inv.setItem(26, itemStack6);

                    player.openInventory(inv);
                    return true;
                }else {
                    player.sendMessage(ChatColor.RED + "/ob [player name]");
                    return true;
                }
            }
        }else {
            sender.sendMessage(ChatColor.RED + "you cannot use this cmd! this cmd is player only!");
            return true;
        }
        return false;
    }
}
