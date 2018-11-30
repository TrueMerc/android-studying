package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen extends Base2DScreen {

    private Texture background;
    private Texture spaceShip;

    @Override
    public void show() {
        super.show();
        background = new Texture("space_background.png");
        spaceShip = new Texture( "star_ship.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(spaceShip, 0, 0, spaceShip.getWidth() * 0.1f, spaceShip.getHeight() * 0.1f );
        batch.end();
    }

    @Override
    public void dispose() {
        spaceShip.dispose();
        background.dispose();
        super.dispose();
    }
}
