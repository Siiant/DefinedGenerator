package US.Siiant.DefinedGenerator.Commands.subcommands;

import US.Siiant.DefinedGenerator.Commands.SubCommand;
import US.Siiant.DefinedGenerator.gUI.gUIShop;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Shop extends SubCommand {
    public static Inventory shop;

    @Override
    public String getName() {
        return "shop";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public void perform(Player player, String[] args) {
        shop = gUIShop.Shop(player);
        player.openInventory(shop);
    }
}
