package ru.ryabtsev.game.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Base class for game fonts representation.
 */
public class Font extends BitmapFont {

    /**
     * Creates new font.
     * @param fontFile path to font (*.fnt) file.
     * @param imageFile path to font image file.
     */
    public Font( String fontFile, String imageFile ) {
        super(Gdx.files.internal(fontFile), Gdx.files.internal(imageFile), false, false);
    }

    /**
     * Sets font new size.
     * @param size new font size.
     */
    public void resize(float size) {
        this.getData().setScale( size / getCapHeight() );
    }

    /**
     * Draws text at the specified position.
     * @param batch draw batcher.
     * @param str text.
     * @param x text left border.
     * @param y text top border.
     * @param align text alignment.
     * @return glyph layout for a piece of text.
     */
    public GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int align) {
        return super.draw(batch, str, x, y, 0f, align, false);
    }
}
