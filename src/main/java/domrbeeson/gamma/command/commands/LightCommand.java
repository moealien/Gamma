package domrbeeson.gamma.command.commands;

import domrbeeson.gamma.command.Command;
import domrbeeson.gamma.command.CommandSender;
import domrbeeson.gamma.entity.Pos;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.world.World;

public class LightCommand implements Command {
    @Override
    public String getName() {
        return "light";
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Run command as player");
            return;
        }

        World world = player.getWorld();
        Pos pos = player.getPos();
        int x = pos.getBlockX();
        int y = pos.getBlockY();
        int z = pos.getBlockZ();
        String posHead = "[" + x + ", " + (y + 1) + ", " + z + "]";
        String posFeet = "[" + x + ", " + y + ", " + z + "]";
        sender.sendMessage();
        sender.sendMessage("Sky light at head " + posHead + ": " + world.getSkyLight(x, y + 1, z));
        sender.sendMessage("Sky light at feet " + posFeet + ": " + world.getSkyLight(x, y, z));
        sender.sendMessage("Block light at head " + posHead + ": " + world.getBlockLight(x, y + 1, z));
        sender.sendMessage("Block light at feet " + posFeet + ": " + world.getBlockLight(x, y, z));
    }
}
