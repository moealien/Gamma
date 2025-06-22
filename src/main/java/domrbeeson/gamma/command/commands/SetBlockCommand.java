package domrbeeson.gamma.command.commands;

import domrbeeson.gamma.command.Command;
import domrbeeson.gamma.command.CommandSender;
import domrbeeson.gamma.entity.Pos;
import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.player.Player;

public class SetBlockCommand implements Command {

    @Override
    public String getName() {
        return "setblock";
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (!sender.isPlayer()) {
            sender.sendMessage("Cannot run this from console!");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage("/setblock <ID[:data] or material>");
            return;
        }

        String idOrMaterial = args[0];
        Material material = null;
        byte data = 0;
        try {
            material = Material.valueOf(idOrMaterial.toUpperCase());
        } catch (Exception e) {
        }

        if (material == null) {
            try {
                String[] parts = idOrMaterial.split(":");
                if (parts.length > 2) {
                    sender.sendMessage("/setblock <ID[:data] or material>");
                    return;
                }

                short id = Short.parseShort(parts[0]);
                data = parts.length == 2 ? Byte.parseByte(parts[1]) : 0;
                material = Material.get(id, (short) 0);
            } catch (Exception e) {
                sender.sendMessage("/setblock <ID[:data] or material>");
                return;
            }
        }

        if (!material.block) {
            sender.sendMessage(String.format("'%s' is not a block!", material.name()));
            return;
        }

        Player player = (Player) sender;
        Pos pos = player.getPos();
        player.getWorld().setBlock(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ(), material.blockId, data);
    }
}
