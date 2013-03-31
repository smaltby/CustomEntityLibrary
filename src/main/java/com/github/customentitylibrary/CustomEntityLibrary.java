package main.java.com.github.customentitylibrary;

import java.lang.reflect.Method;
import java.util.Stack;

import main.java.com.github.customentitylibrary.entities.CustomEntityWrapper;
import main.java.com.github.customentitylibrary.entities.CustomGiant;
import main.java.com.github.customentitylibrary.entities.CustomSkeleton;
import main.java.com.github.customentitylibrary.entities.CustomWither;
import main.java.com.github.customentitylibrary.entities.CustomWolf;
import main.java.com.github.customentitylibrary.entities.CustomZombie;
import main.java.com.github.customentitylibrary.listeners.LibraryEntityListener;

import net.minecraft.server.v1_5_R2.EntityGiantZombie;
import net.minecraft.server.v1_5_R2.EntityLiving;
import net.minecraft.server.v1_5_R2.EntitySkeleton;
import net.minecraft.server.v1_5_R2.EntityTypes;
import net.minecraft.server.v1_5_R2.EntityWither;
import net.minecraft.server.v1_5_R2.EntityWolf;
import net.minecraft.server.v1_5_R2.EntityZombie;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEntityLibrary
{	
	public static JavaPlugin plugin;
	
	public static void load(JavaPlugin plugin)
	{
		CustomEntityLibrary.plugin = plugin;
		loadCustomEntities();
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
			Stack<EntityLiving> toRemove = new Stack<EntityLiving>();
			for(CustomEntityWrapper ent : CustomEntityWrapper.getCustomEntities().values())
			{
				if(!ent.getEntity().isAlive())
					toRemove.add(ent.getEntity());
				else
					ent.getType().showSpecialEffects((LivingEntity) ent.getEntity().getBukkitEntity());
			}
			for(EntityLiving ent : toRemove) CustomEntityWrapper.getCustomEntities().remove(ent);
		}
		tick++;
	}
	
    @SuppressWarnings("rawtypes")
	private static void loadCustomEntities()
	{
		try
        {
			Class[] entityWithEggArgs = {Class.class, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
            Method entityWithEggList = EntityTypes.class.getDeclaredMethod("a", entityWithEggArgs);
            entityWithEggList.setAccessible(true);
            
            entityWithEggList.invoke(entityWithEggList, CustomZombie.class, "Zombie", 54, 44975, 7969893);
            entityWithEggList.invoke(entityWithEggList, EntityZombie.class, "Zombie", 54, 44975, 7969893);
            entityWithEggList.invoke(entityWithEggList, CustomWolf.class, "Wolf", 95, 14144467, 13545366);
            entityWithEggList.invoke(entityWithEggList, EntityWolf.class, "Wolf", 95, 14144467, 13545366);
            entityWithEggList.invoke(entityWithEggList, CustomSkeleton.class, "Skeleton", 51, 12698049, 4802889);
            entityWithEggList.invoke(entityWithEggList, EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
            
            Class[] entityWithoutEggArgs = {Class.class, String.class, Integer.TYPE};
            Method entityWithoutEggList = EntityTypes.class.getDeclaredMethod("a", entityWithoutEggArgs);
            entityWithoutEggList.setAccessible(true);
            
            entityWithoutEggList.invoke(entityWithoutEggList, CustomWither.class, "WitherBoss", 64);
            entityWithoutEggList.invoke(entityWithoutEggList, EntityWither.class, "WitherBoss", 64);
            entityWithoutEggList.invoke(entityWithoutEggList, CustomGiant.class, "Giant", 53);
            entityWithoutEggList.invoke(entityWithoutEggList, EntityGiantZombie.class, "Giant", 53);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}
}