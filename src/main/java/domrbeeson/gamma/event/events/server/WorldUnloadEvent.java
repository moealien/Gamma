package domrbeeson.gamma.event.events.server;

import domrbeeson.gamma.event.Event;
import domrbeeson.gamma.world.World;

public record WorldUnloadEvent(World world) implements Event.GlobalEvent {

}
