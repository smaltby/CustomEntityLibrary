package kabbage.customentitylibrary;

import java.util.ConcurrentModificationException;

import kabbage.customentitylibrary.listeners.LibraryEntityListener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEntityLibrary
{	
	public static JavaPlugin plugin;
	
	public static void load(JavaPlugin plugin)
	{
		CustomEntityLibrary.plugin = plugin;
		LibraryEntityListener eListener = new LibraryEntityListener();
		PluginManager pm = Bukkit.getServer().getPluginManager();
		eListener.registerEvents(pm, plugin);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
		{
			@Override
			public void run()
			{
				onTick();
			}
		}, 1L, 1L);
	}
	
	private static int tick = 0;
	private static void onTick()
	{
		if(tick % 20 == 0)
		{
			for(World w : plugin.getServer().getWorlds())
			{
				try
				{
					for(LivingEntity ent : w.getLivingEntities())
					{
						if(CustomEntityWrapper.instanceOf(ent))
						{
							CustomEntityWrapper entity = CustomEntityWrapper.getCustomEntity(ent);
							entity.getType().showSpecialEffects(ent);
						}
					}
				} catch(ConcurrentModificationException e) {}
			}
		}
		tick++;
	}
}
