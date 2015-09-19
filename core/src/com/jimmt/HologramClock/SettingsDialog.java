package com.jimmt.HologramClock;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class SettingsDialog extends Dialog {
	Image background, selection;
	Table table;
	Array<Tile> tiles;
	boolean set;

	public SettingsDialog(Skin skin) {
		super("", skin);

		selection = new Image(Assets.getTex("selection.png"));
		background = new Image(Assets.getTex("background.png"));
		background(background.getDrawable());

		table = getContentTable();
		table.setPosition(background.getX(), background.getY());

		DisplayEffect[] values = DisplayEffect.getValues();
		tiles = new Array<Tile>();

		for (int i = 0; i < values.length; i++) {
			final Tile tile = new Tile(values[i]);
			table.add(tile);
			table.debug();
			tile.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					selection.setPosition(tile.getX(), tile.getY());
				}
			});
			tiles.add(tile);
		}

		table.addActor(selection);

// addActor(background);
// addActor(table);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (!set) {
			set = true;
			selection.setPosition(tiles.get(0).getX(), tiles.get(0).getY()); // change
		}

	}
}
