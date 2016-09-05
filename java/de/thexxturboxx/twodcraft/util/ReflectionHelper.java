package de.thexxturboxx.twodcraft.util;

import java.lang.reflect.Field;

public class ReflectionHelper {
	
	public static Object getObject(Object in, Class outOf, String nameOfField) {
		try {
			Field f = outOf.getDeclaredField(nameOfField);
			f.setAccessible(true);
			return f.get(in);
		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void set(Object in, Class outOf, String nameOfField, Object newValue) {
		try {
			Field f = outOf.getDeclaredField(nameOfField);
			f.setAccessible(true);
			f.set(in, newValue);
		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}