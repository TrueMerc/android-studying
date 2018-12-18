package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;

/**
 * Menu screen button which starts new game.
 */
public class PlayButton extends Button {

    private StarShooterGame game;

    /**
     * Constructor.
     * @param region sprite object texture.
     * @param game game.
     */
    public PlayButton(TextureRegion region, StarShooterGame game) {
        super(region);
        this.game = game;
    }

    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if( isInside(touchPosition) ) {
            game.setScreen(StarShooterGame.ScreenType.GAME);
            return true;
        }
        return false;
    }
}
