package com.github.customentitylibrary.entities;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import net.minecraft.server.v1_5_R3.EntitySkeleton;

public class CustomSkeleton extends EntitySkeleton
{
	public CustomSkeleton(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
	
	//This method adds pathfinders to the entity we don't want
	@Override
	public void m()
	{
		
	}
}
