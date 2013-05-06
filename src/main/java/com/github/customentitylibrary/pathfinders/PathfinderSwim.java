package com.github.customentitylibrary.pathfinders;

import java.lang.reflect.Field;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import com.github.customentitylibrary.utils.NMS;

import net.minecraft.server.v1_5_R3.EntityLiving;

public class PathfinderSwim extends PathfinderBase
{
	EntityLiving entity;
	int i = 0;
	boolean canDive;
	public PathfinderSwim(EntityLiving ent, boolean canDive)
	{
		entity = ent;
		this.canDive = canDive;
	}
	
	@Override
	public boolean shouldExecute()
	{
		if(entity.G() && !entity.isInvulnerable())	//If is in water and hasn't just been hit
		{
			Entity ent = entity.getBukkitEntity();
			double speed = .23f;
			try
			{
				Field f = EntityLiving.class.getDeclaredField(NMS.SPEED);
				f.setAccessible(true);
				speed = f.getFloat(entity);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Vector dir = ent.getLocation().getDirection().normalize().multiply(speed/2);
			double y = dir.getY();
			if(y > -.05 && i % 20 == 0)
				y = .3;
			if(!canDive && y < 0)
				y = 0;
			Vector vec = new Vector(dir.getX(), y, dir.getZ());
			i++;
			ent.setVelocity(vec);
		}
		return false;
	}

	@Override
	public boolean continueExecuting()
	{
		return false;
	}

	@Override
	public void startExecuting()
	{
		
	}

	@Override
	public void stopExecuting()
	{
		
	}

	@Override
	public void update()
	{
		
	}
}
