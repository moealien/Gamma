package domrbeeson.gamma.command.commands;

import domrbeeson.gamma.command.Command;
import domrbeeson.gamma.command.CommandSender;
import domrbeeson.gamma.player.Player;

public class YCommand implements Command {
    @Override
    public String getName() {
        return "y";
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("console does not have any coordinates");
            return;
        }

        player.sendMessage("Head Y: " + (player.getPos().getBlockY() + 1) + ", Feet Y: " + player.getPos().getBlockY());
    }
}
