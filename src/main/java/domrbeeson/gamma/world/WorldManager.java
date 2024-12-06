package domrbeeson.gamma.world;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.Tickable;
import domrbeeson.gamma.Unloadable;
import domrbeeson.gamma.world.format.InvalidWorldFormatException;
import domrbeeson.gamma.world.format.WorldFormat;
import domrbeeson.gamma.world.terrain.TerrainGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class WorldManager implements Unloadable, Tickable {

    private final Map<String, World> loadedWorlds = new HashMap<>();
    private final MinecraftServer server;

    private World defaultWorld = null;

    public WorldManager(MinecraftServer server) {
        this.server = server;
    }

    public void setDefaultWorld(World world) {
        defaultWorld = world;
    }

    public World getDefaultWorld() {
        return defaultWorld;
    }

    public @Nullable World loadOrCreateWorld(String name, WorldFormat format, TerrainGenerator generator) {
        World world = loadedWorlds.get(name);
        if (world == null) {
            try {
                world = new World(server, this, name, format, generator);
            } catch (InvalidWorldFormatException e) {
                e.printStackTrace();
                return null;
            }
            loadedWorlds.put(name, world);
        }
        return world;
    }

    public Collection<World> getWorlds() {
        return loadedWorlds.values();
    }

    public @Nullable World getWorld(String name) {
        return loadedWorlds.get(name);
    }

    public boolean isWorldLoaded(World world) {
        return loadedWorlds.containsValue(world);
    }

    @Override
    public void unload() {
        Collection<World> worlds = new ArrayList<>(loadedWorlds.values());
        worlds.forEach(World::unload);
    }

    protected void removeWorld(World world) {
        loadedWorlds.remove(world.getName());
    }

    @Override
    public void tick(long ticks) {
        loadedWorlds.values().forEach(world -> world.tick(ticks));
    }
}
