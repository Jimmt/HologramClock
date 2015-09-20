package com.jimmt.HologramClock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class SettingsDialog extends Dialog {
	Array<TileGroup> tileGroups;
	Image xButton;
	Image background, selection, colorSelection;
	Table table;
	int initialSelection;
	boolean set;

	public SettingsDialog(Skin skin, int initialSelection, final BaseScreen screen) {
		super("", skin);

		this.initialSelection = initialSelection;
		tileGroups = new Array<TileGroup>();
		
		xButton = new Image(Assets.getTex("xbutton.png"));
		xButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				hide();
			}
		});
		selection = new Image(Assets.getTex("selection.png"));
		colorSelection = new Image(Assets.getTex("selection.png"));
		background = new Image(Assets.getTex("background.png"));
		background(background.getDrawable());

		table = getContentTable();
		table.setPosition(background.getX(), background.getY());

		DisplayEffect[] values = DisplayEffect.getValues();

		CheckBoxStyle checkboxStyle = new CheckBoxStyle();
		checkboxStyle.checkboxOff = new Image(Assets.getTex("box_unchecked.png")).getDrawable();
		checkboxStyle.checkboxOn = new Image(Assets.getTex("box_checked.png")).getDrawable();
		checkboxStyle.font = screen.fontSmall;
		checkboxStyle.fontColor = Color.BLACK;
		final CheckBox militaryTimeBox = new CheckBox("   24 hour time", checkboxStyle);
		militaryTimeBox.setChecked(Prefs.prefs.getBoolean("militaryTime"));
		militaryTimeBox.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Prefs.prefs.putBoolean("militaryTime", militaryTimeBox.isChecked());
				Prefs.prefs.flush();
				screen.militaryTime = militaryTimeBox.isChecked();
			}
		});

		table.add(militaryTimeBox).colspan(DisplayEffect.values().length).padBottom(20f).row();

		for (int i = 0; i < values.length; i++) {
			final DisplayEffect effect = values[i];
			
			final TileGroup tileGroup = new TileGroup(effect, i);
			table.add(tileGroup).left().row();
			tileGroup.addListeners(screen);
			tileGroups.add(tileGroup);
		}
		table.padBottom(20f);
		table.row();

		table.addActor(selection);

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		xButton.setPosition(getX() + getWidth() - xButton.getWidth(), getY() + getHeight()
				- xButton.getHeight());
		xButton.toFront();

		if (!set) {
			set = true;
			getStage().addActor(xButton);
			int effectIndex = Prefs.prefs.getInteger("effectIndex");
			TileGroup group = tileGroups.get(initialSelection);
			selection.setPosition(group.getX() + group.tiles.get(effectIndex).getX(), group.getY());

		}

	}
}
