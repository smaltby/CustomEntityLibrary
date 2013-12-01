package com.github.customentitylibrary.utils;

public class NMS
{
	//Can be found in PathfinderGoalSelector class, it's the first list
	public static final String PATHFINDER_LIST = "b";
	//Found in Ocelot's second goal selector (or wolf)
	public static final String ANIMAL_GOALSELECTOR2 = "bp";
	//In Ocelot's field, private PathfinderGoalTempt <name>;
	public static final String OCELOT_TEMPT = "bq";
	//Sheep, field: private PathfinderGoalEatTile <name> = new PathfinderGoalEatTile(this);
	public static final String SHEEP_EAT_TILE = "bs";
	//Wither, field: private static final IEntitySelector <name> = new EntitySelectorNotUndead();
	public static final String WITHER_SELECTOR = "bw";
}
