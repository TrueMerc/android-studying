package ru.ryabtsev.game.object.explosion;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import ru.ryabtsev.game.object.SpritesPool;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureRegion explosionRegion;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas, Sound explosionSound) {
        explosionRegion = atlas.findRegion("Explosion");
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(explosionRegion, 9, 9, 74, explosionSound);
    }
}
