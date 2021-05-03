package US.Siiant.DefinedGenerator.gUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class gUIMenu {

    private static Inventory menu;
    private static ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    private static ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    private static ItemStack backPage = new ItemStack(Material.ARROW);
    private static ItemStack nextPage = new ItemStack(Material.ARROW);
    private static ItemStack menuBlock = new ItemStack(Material.NETHER_STAR);

    public static Inventory Menu(Player player){
        menu = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&cGenerator &8Menu"));
        ItemMeta cancelMeta = cancel.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&cExit the menu"));
        cancelMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&o&cExit"));
        cancelMeta.setLore(lore);
        cancel.setItemMeta(cancelMeta);
        menu.setItem(0, gUIBank.getItemStack());
        menu.setItem(1, empty);
        menu.setItem(2, empty);
        menu.setItem(3, gUIShop.getItemStack());
        menu.setItem(4, gUIPlacedGenerators.getItemStack());
        menu.setItem(5, gUIUpgrades.getItemStack());
        menu.setItem(6,empty);
        menu.setItem(7,empty);
        menu.setItem(8, cancel);
        return menu;
    }

    public static Inventory addBottomMenu(Inventory gui, String name){
        int size = gui.getSize();
        Inventory newGui = Bukkit.createInventory(null, gui.getSize() + 9, ChatColor.translateAlternateColorCodes('&', name));
        for(int i = 0; gui.getSize() > i; i++){
            newGui.setItem(i, gui.getItem(i));
        }
        ItemMeta nextPageMeta = nextPage.getItemMeta();
        ItemMeta backPageMeta = backPage.getItemMeta();
        ItemMeta menuMeta = menuBlock.getItemMeta();
        List<String> nextLore = new ArrayList<>();
        List<String> backLore = new ArrayList<>();
        nextLore.add(ChatColor.translateAlternateColorCodes('&', "&a&oNext Page"));
        nextPageMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aNext Page"));
        backLore.add(ChatColor.translateAlternateColorCodes('&', "&c&oBack"));
        backPageMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
        menuMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Main Menu"));
        backPage.setItemMeta(backPageMeta);
        backPage.setItemMeta(backPageMeta);
        menuBlock.setItemMeta(menuMeta);
        backPage.setItemMeta(backPageMeta);
        nextPage.setItemMeta(nextPageMeta);
        newGui.setItem(size + 3, backPage);
        newGui.setItem(size + 4, menuBlock);
        newGui.setItem(size + 5 , nextPage);
        return newGui;
    }

    public static ItemStack getBackPage( ){
        return backPage;
    }

    public static ItemStack getNextPage(){
        return nextPage;
    }

    public static ItemStack getMenuBlock(){
        return menuBlock;
    }

    public static ItemStack getEmptyItemStack(){
        return empty;
    }


    public static ItemStack getCancelItemStack(){
        return cancel;
    }
}
