package domrbeeson.gamma.command.commands;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.chat.ChatColor;
import domrbeeson.gamma.chat.ChatMessage;
import domrbeeson.gamma.command.Command;
import domrbeeson.gamma.command.CommandSender;

public class SayCommand implements Command {

    private final MinecraftServer server;

    public SayCommand(MinecraftServer server) {
        this.server = server;
    }

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        String message = String.join(" ", args);
        server.broadcast(new ChatMessage().add(ChatColor.PINK).add("[CONSOLE] " + message));
    }
}
