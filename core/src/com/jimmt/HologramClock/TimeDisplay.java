package com.jimmt.HologramClock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class TimeDisplay extends Group {
	ParticleEffectActor effectActor;
	Label label;
	Container<Label> container;

	public TimeDisplay(BitmapFont font, DisplayEffect effect, float rotation, boolean flipY) {
		float offset = effect.getOffset();
		effectActor = new ParticleEffectActor(effect.getPaths()[Prefs.prefs.getInteger("effectIndex")], "effects");
		effectActor.rotateEffect(rotation);
		effectActor.setPosition(effectActor.getX() + MathUtils.sinDeg(rotation) * offset,
				effectActor.getY() + MathUtils.cosDeg(rotation) * offset);
		LabelStyle style = new LabelStyle();
		style.font = font;
		style.fontColor = Color.WHITE;

		label = new Label("00:00:00", style);
		label.setOrigin(label.getWidth() / 2, label.getHeight() / 2);
		container = new Container<Label>(label);
		container.setTransform(true);
		container.setRotation(rotation);
		container.setScaleX(flipY ? 1 : -1);
		container.setScaleY(flipY ? -1 : 1);
		offset += effect.getEffectOffset();
		container.setPosition(container.getX() + MathUtils.sinDeg(rotation) * offset,
				container.getY() + MathUtils.cosDeg(rotation) * offset);

		addActor(effectActor);
		addActor(container);
	}

}
