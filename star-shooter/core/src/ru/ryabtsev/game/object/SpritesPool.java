package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite & Destroyable> {

    protected List<T> activeObjects = new ArrayList<T>();
    protected List<T> freeObjects = new ArrayList<T>();

    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
            object.alive();
        }
        activeObjects.add(object);
        System.out.println("active/free:" + activeObjects.size() + "/" + freeObjects.size());
        return object;
    }

    protected abstract T newObject();

    public void updateActiveSprites(float delta) {
        for(int i = 0; i < activeObjects.size(); ++i) {
            T sprite = activeObjects.get(i);

            if (!sprite.isDestroyed()) {
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch batch) {
        for(int i = 0; i < activeObjects.size(); ++i) {
            T sprite = activeObjects.get(i);
            if (!sprite.isDestroyed()) {
                sprite.draw(batch);
            }
        }
    }

    public void freeAllDestroyedActiveSprites() {
        for(int i = 0; i < activeObjects.size(); ++i) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                --i;
            }
        }
    }

    private void free(T object) {
        if (activeObjects.remove(object)) {
            freeObjects.add(object);
            System.out.println("active/free:" + activeObjects.size() + "/" + freeObjects.size());
        }
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }
}
