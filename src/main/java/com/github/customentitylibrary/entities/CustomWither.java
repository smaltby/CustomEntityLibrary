package com.github.customentitylibrary.entities;

import net.minecraft.server.v1_5_R2.EntityWither;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

public class CustomWither extends EntityWither
{
	public CustomWither(World world)
	{
		super(((CraftWorld) world).getHandle());
	}
}
