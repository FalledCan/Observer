package masa3mc.observer;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class ClickInv implements Listener {

    private HashMap<Player, Boolean> fr = new HashMap<Player, Boolean>();

    @EventHandler
    public void Click(InventoryClickEvent e){
        if(e.getView().getTitle().equals(ChatColor.GOLD + "ObserverGUI")){
            if(e.getCurrentItem().getItemMeta() != null){
                if(e.getCurrentItem().getItemMeta().getDisplayName()!=null){
                    Player player = (Player) e.getWhoClicked();
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Open Player Inventory")){
                        player.closeInventory();
                        player.openInventory(Gui.obs.get(e.getWhoClicked()).getInventory());
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Open Player Ender-chest")){
                        player.closeInventory();
                        player.openInventory(Gui.obs.get(e.getWhoClicked()).getEnderChest());
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Teleport To Player")){
                        player.closeInventory();
                        if(player.getGameMode() != GameMode.SPECTATOR){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GOLD + "Spectatorモードに変更し" + ChatColor.RED + Gui.obs.get(e.getWhoClicked()).getName() + ChatColor.GOLD + "へテレポートしました。");
                        player.teleport(Gui.obs.get(e.getWhoClicked()).getLocation());
                        }else {
                            player.sendMessage(ChatColor.RED + Gui.obs.get(e.getWhoClicked()).getName() + ChatColor.GOLD + "へテレポートしました。");
                            player.teleport(Gui.obs.get(e.getWhoClicked()).getLocation());
                        }
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "freeze")) {
                        Player player1 = Gui.obs.get(e.getWhoClicked());
                        if(fr.get(player1) == null){
                            fr.put(player1, true);
                            player.sendMessage("§bFreeze "+ player1.getName());
                        }if(fr.get(player1) == true){
                            fr.remove(player1);
                            player.sendMessage("§bunFreeze "+ player1.getName());
                        }
                        player.closeInventory();
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "close")) {
                        player.closeInventory();
                    }else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFreeze(PlayerMoveEvent event){
        if(fr.get(event.getPlayer()) == true) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(fr.get(event.getPlayer()) == true) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(fr.get(event.getPlayer()) == true) {
            event.setCancelled(true);
        }
    }
}
