package main.creature;

public interface Contaminant {
	/**
	 * Contamine une creature.
	 * @param creature La créature contaminée.
	 */
	public abstract void contaminer(Creature creature);
}
