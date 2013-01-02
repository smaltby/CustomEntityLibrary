package kabbage.customentitylibrary;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomEntitySpawnEvent extends Event
{
	private static final HandlerList handlers = new HandlerList();
	private CustomEntityWrapper ent;
	private Location spawn;
	private boolean cancel;
	 
	public CustomEntitySpawnEvent(CustomEntityWrapper ent, Location spawn)
	{
		this.ent = ent;
		this.spawn = spawn;
	}
	
	@Override
	public HandlerList getHandlers()
	{
	    return handlers;
	}
	
	@Override
	public String getEventName()
	{
		return "CustomEntity Spawn Event";
	}
	
	public static HandlerList getHandlerList()
	{
	    return handlers;
	}
	
	public void setCancelled(boolean cancel)
	{
		this.cancel = cancel;
	}
	
	public boolean isCancelled()
	{
		return cancel;
	}
	
	public Location getLocation()
	{
		return spawn;
	}
	
	public CustomEntityWrapper getEntity()
	{
		return ent;
	}
}
