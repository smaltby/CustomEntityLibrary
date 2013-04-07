package com.github.customentitylibrary.entities;

import com.github.customentitylibrary.CustomEntityMoveEvent;
import net.minecraft.server.v1_5_R2.EntityWither;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

public class CustomWither extends EntityWither
{
	public CustomWither(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
	
	@Override
	public void move(double d0, double d1, double d2)
	{
		CustomEntityMoveEvent event = new CustomEntityMoveEvent(this.getBukkitEntity(), new Location(this.world.getWorld(), lastX, lastY, lastZ), new Location(this.world.getWorld(), locX, locY, locZ));
		Bukkit.getServer().getPluginManager().callEvent(event);
		super.move(d0, d1, d2);
	}
}
