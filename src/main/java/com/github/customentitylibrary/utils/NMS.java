package main.java.com.github.customentitylibrary.utils;

public class NMS
{
	//Can be found in PathfinderSelector class, it's the first list
	public static final String PATHFINDER_LIST = "a";
	//Can be found pretty much anywhere
	public static final String SPEED = "bI";
	//Found in Ocelot's second goal selector (or wolf, or sheep)
	public static final String ANIMAL_GOALSELECTOR2 = "d";
	//In Ocelot's field, private PathfinderGoalTempt <name>;
	public static final String OCELOT_TEMPT = "e";
	//Sheep, field: private PathfinderGoalEatTile <name> = new PathfinderGoalEatTile(this);
	public static final String SHEEP_EAT_TILE = "g";
	//Wither, field: private static final IEntitySelector <name> = new EntitySelectorNotUndead();
	public static final String WITHER_SELECTOR = "bK";
}
