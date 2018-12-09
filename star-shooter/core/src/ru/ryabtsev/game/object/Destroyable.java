package ru.ryabtsev.game.object;

/**
 * Interface for game objects which can be destroyed.
 */
public interface Destroyable {

    /**
     * @return true if the object destroyed and false if it isn't destroyed.
     */
    boolean isDestroyed();

    /**
     * Destroys the object.
     */
    void destroy();

    /**
     * Makes object alive.
     */
    void alive();
}
