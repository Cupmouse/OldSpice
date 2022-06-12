package net.sssanma.spice.core;

import java.util.ArrayList;

import net.sssanma.spice.core.items.Item;

public class Resources {
	private ArrayList<Item> items = new ArrayList<>();
	
	public Item[] getItems() {
		return items.toArray(new Item[items.size()]);
	}
}
