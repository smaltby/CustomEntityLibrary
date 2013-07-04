package com.github.customentitylibrary.entities;

import net.minecraft.server.v1_6_R1.EntityGiantZombie;
import net.minecraft.server.v1_6_R1.World;

public class CustomGiant extends EntityGiantZombie
{
	public CustomGiant(World world)
	{
		super(world);
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean bb()
	{
		return true;
	}
}
