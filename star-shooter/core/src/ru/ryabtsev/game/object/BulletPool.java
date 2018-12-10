package ru.ryabtsev.game.object;


/**
 * Pool for bullet objects.
 */
public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
