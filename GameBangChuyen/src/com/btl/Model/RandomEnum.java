package com.btl.Model;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomEnum.
 * 
 * @param <E>
 *            the element type
 */
public class RandomEnum<E extends Enum<?>> {

	private static final Random RND = new Random();
	private final E[] values;

	/**
	 * Instantiates a new random enum.
	 * 
	 * @param token
	 *            the token
	 */
	public RandomEnum(Class<E> token) {
		values = token.getEnumConstants();
	}

	/**
	 * Random.
	 * 
	 * @return the e
	 */
	public E random() {
		return values[RND.nextInt(values.length)];
	}
}
