package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;

public class PlayButton extends MenuButton {

    StarShooterGame game;

    /**
     * Constructor.
     * @param region - sprite object texture.
     * @param position - position on the screen.
     * @param game - game.
     */
    public PlayButton(TextureRegion region, Vector2 position, StarShooterGame game) {
        super(region, position);
        this.game = game;
    }

    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if( isInside(touchPosition) ) {
            game.setScreen(StarShooterGame.ScreenType.GAME);
            return true;
        }
        else {
            return false;
        }
    }
}
