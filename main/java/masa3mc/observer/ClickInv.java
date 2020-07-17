package masa3mc.observer;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickInv implements Listener {
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
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GOLD + "Spectatorモードに変更し" + ChatColor.RED + Gui.obs.get(e.getWhoClicked()).getName() + ChatColor.GOLD + "へテレポートしました。");
                        player.teleport(Gui.obs.get(e.getWhoClicked()).getLocation());
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "close")) {
                        player.closeInventory();
                    }else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
