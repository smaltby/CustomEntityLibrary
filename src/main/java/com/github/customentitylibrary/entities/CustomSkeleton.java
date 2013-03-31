package main.java.com.github.customentitylibrary.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;
import main.java.com.github.customentitylibrary.CustomEntityMoveEvent;
import net.minecraft.server.v1_5_R2.EntitySkeleton;
import net.minecraft.server.v1_5_R2.NBTTagCompound;

public class CustomSkeleton extends EntitySkeleton
{
	public CustomSkeleton(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
	
	@Override
	public void move(double d0, double d1, double d2)
	{
		CustomEntityMoveEvent event = new CustomEntityMoveEvent(this.getBukkitEntity(), new Location(this.world.getWorld(), lastX, lastY, lastZ), new Location(this.world.getWorld(), locX, locY, locZ));
		if(event != null)
			Bukkit.getServer().getPluginManager().callEvent(event);
		super.move(d0, d1, d2);
	}
	
	@Override
	public void a(NBTTagCompound nbttagcompound)
	{
        super.a(nbttagcompound);
        //A bunch of redundant stuff regarding skeleton type removed
	}
}
