package US.Siiant.DefinedGenerator.Init;

import US.Siiant.DefinedGenerator.Commands.subcommands.Bank;
import US.Siiant.DefinedGenerator.Commands.subcommands.Menu;
import US.Siiant.DefinedGenerator.Commands.subcommands.Shop;
import US.Siiant.DefinedGenerator.gUI.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(guiCheck(e)){
            menuClick(e);
            pageClick(e);
            }
        }

    public void pageClick(InventoryClickEvent e){
        if(e.getCurrentItem().equals(gUIMenu.getBackPage())){
            Player player = (Player) e.getWhoClicked();
            Menu.next = e.getClickedInventory();
            player.closeInventory();
            player.openInventory(Menu.previous);
        } else if (e.getCurrentItem().equals(gUIMenu.getNextPage())){
            Player player = (Player) e.getWhoClicked();
            Menu.previous = e.getClickedInventory();
            player.closeInventory();
            player.openInventory(Menu.next);
        } else if (e.getCurrentItem().equals(gUIMenu.getMenuBlock())){
            Menu.previous = e.getClickedInventory();
            Player player = (Player) e.getWhoClicked();
            player.closeInventory();
            player.openInventory(Menu.menu);
        }
    }


    public void menuClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        player.updateInventory();
        switch (getItemToString(e)) {
            case "Shop":
                Menu.previous = e.getView().getTopInventory();
                player.closeInventory();
                player.openInventory(Menu.menuShop);
                return;
            case "Cancel":
                player.closeInventory();
                return;
            case "Bank":
                Menu.previous = e.getView().getTopInventory();
                player.closeInventory();
                player.openInventory(Menu.menuBank);
                return;
            case "PlacedGenerators":
                Menu.previous = e.getView().getTopInventory();
                player.closeInventory();
                player.openInventory(Menu.menuGenerators);
                return;
            case "Upgrades":
                Menu.previous = e.getView().getTopInventory();
                player.closeInventory();
                player.openInventory(Menu.menuUpgrades);
                return;
        }
    }


    private String getItemToString(InventoryClickEvent e){
        try {
            if (e.getCurrentItem().equals(gUIShop.getItemStack())) {
                return "Shop";
            } else if (e.getCurrentItem().equals(gUIBank.getItemStack())) {
                return "Bank";
            } else if (e.getCurrentItem().equals(gUIPlacedGenerators.getItemStack())) {
                return "PlacedGenerators";
            } else if (e.getCurrentItem().equals(gUIUpgrades.getItemStack())) {
                e.getWhoClicked().sendMessage("Test");
                return "Upgrades";
            } else if (e.getCurrentItem().equals(gUIMenu.getCancelItemStack())) {
                return "Cancel";
            } else {
                return "";
            }
        } catch (NullPointerException msg) {
            return "";
        }
    }


    private static boolean guiCheck(InventoryClickEvent event){
        try{
            Player player = (Player) event.getWhoClicked();
            Inventory topInv = event.getView().getTopInventory();
            if(topInv.equals(Menu.menu) || topInv.equals(Menu.menuShop) || topInv.equals(Menu.menuBank) || topInv.equals(Menu.menuUpgrades) || topInv.equals(Menu.menuGenerators)){
                return true;
            } else {
                return false;
            }
        } catch(NullPointerException e){
            System.out.println(e);
            return false;
        }
    }
}
