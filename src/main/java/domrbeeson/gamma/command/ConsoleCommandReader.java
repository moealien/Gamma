package domrbeeson.gamma.command;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.Stoppable;
import domrbeeson.gamma.chat.ChatMessage;
import domrbeeson.gamma.task.ScheduledTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleCommandReader implements Runnable, CommandSender, Stoppable {

    private final MinecraftServer server;
    private final CommandManager commandManager;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private boolean running = true;

    public ConsoleCommandReader(MinecraftServer server, CommandManager commandManager) {
        this.server = server;
        this.commandManager = commandManager;
        new Thread(this).start();
    }

    @Override
    public void run() {
        final ConsoleCommandReader consoleReader = this;
        try {
            while (running) {
                while (!reader.ready()) {
                    Thread.sleep(100);
                }
                final String line = reader.readLine();
                server.getScheduler().scheduleTask(new ScheduledTask() {
                    @Override
                    public void accept(Long aLong) {
                        commandManager.parseAndRun(consoleReader, line);
                    }
                });
            }
        } catch (IOException | InterruptedException e) {
            if (running) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void sendMessage(ChatMessage message) {
        sendMessage(message.toString());
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    public void stop() {
        running = false;
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
