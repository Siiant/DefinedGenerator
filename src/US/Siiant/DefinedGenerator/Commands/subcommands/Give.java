package US.Siiant.DefinedGenerator.Commands.subcommands;

import US.Siiant.DefinedGenerator.Commands.SubCommand;
import US.Siiant.DefinedGenerator.Generator.Generator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Give extends SubCommand { // /definedgenerator give <player> <type> <amount>

    // Name of the subcommand
    @Override
    public String getName() {
        return "give";
    }


    //Description of the subcommand
    @Override
    public String getDescription() {
        return "Gives Player Defined Generator";
    }


    //How to properly use the command
    @Override
    public String getSyntax() {
        return "/definedgenerator give <player> <generator> <amount>";
    }


    //Code for the command
    @Override
    public void perform(Player player, String[] args) {
        if(args.length != 4){
            player.sendMessage(getSyntax());
            return;
        }
        try {
            Generator generator = new Generator(args[2]);
            Bukkit.getPlayer(args[1]).getInventory().addItem(generator.getBlock());
            player.sendMessage("Given Item.");
        } catch(NullPointerException e){
            player.sendMessage("Invalid Generator.");
            System.out.println(e);
            return;
        }
    }
}
