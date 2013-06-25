/**
 * 
 */
package org.sofaecke.vialshuffler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An ordered set of items with automatically generated names.
 * 
 * @author david
 */
public class VialSet {
	private final int size;
	private List<String> items;

	/**
	 * Creates a new set of the given size.
	 * 
	 * @param size
	 *            The number of items in the set.
	 */
	public VialSet(int size) {
		this.size = size;
		this.items = generateItems(this.size);
	}

	/**
	 * Randomizes the order of the set.
	 */
	public void shuffle() {
		Collections.shuffle(items);
	}

	/**
	 * Get the current list of items in the set.
	 * 
	 * @return An immutable version of the current set.
	 */
	public List<String> getItems() {
		return Collections.unmodifiableList(items);
	}

	/**
	 * Generates a list of item names.
	 * 
	 * @param number
	 *            How many items to generate.
	 * @return A list of size number item names.
	 */
	private static List<String> generateItems(int number) {
		List<String> items = new ArrayList<String>(number);
		for (int i = 1; i <= number; i++) {
			items.add(Integer.toString(i));
		}
		return items;
	}
}
