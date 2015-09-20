package com.jimmt.HologramClock;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class TileGroup extends Table {
	Array<Tile> tiles;
	int index;

	public TileGroup(DisplayEffect effect, int index) {
		this.index = index;
		tiles = new Array<Tile>();
		
		for (int i = 0; i < effect.getPaths().length; i++) {
			Tile tile = new Tile(effect, i);
			tiles.add(tile);
			add(tile).padLeft(5f);
		}
	}

	public void addListeners(final BaseScreen screen) {
		for (Tile tile : tiles) {
			final Tile temp = tile;
			tile.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					screen.settingsDialog.selection.setPosition(getX() + tiles.get(temp.getIndex()).getX(), getY() + tiles.get(temp.getIndex()).getY());
					Prefs.prefs.putInteger("displayIndex", index);
					Prefs.prefs.putInteger("effectIndex", temp.getIndex());
					Prefs.prefs.flush();
					screen.switchEffect(temp.effect);
				}
			});
		}
	}
}
 