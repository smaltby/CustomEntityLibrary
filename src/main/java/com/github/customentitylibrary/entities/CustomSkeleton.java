package com.github.customentitylibrary.entities;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;
import net.minecraft.server.v1_5_R2.EntitySkeleton;

public class CustomSkeleton extends EntitySkeleton
{
	public CustomSkeleton(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
}
