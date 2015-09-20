package com.jimmt.HologramClock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {
	ParticleEffect effect;

	public ParticleEffectActor(String effectPath, String effectDir) {
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal(effectPath), Gdx.files.internal(effectDir));
		effect.start();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		effect.update(delta);
		effect.setPosition(getX(), getY());
	}

	public void rotateEffect(float deg) {
		effect.getEmitters().get(0).getAngle()
				.setLow(effect.getEmitters().get(0).getAngle().getLowMin() - deg);
		effect.getEmitters()
				.get(0)
				.getAngle()
				.setHigh(effect.getEmitters().get(0).getAngle().getHighMin() - deg,
						effect.getEmitters().get(0).getAngle().getHighMax() - deg);
		if (deg == 90f || deg == -90f) {
			effect.getEmitters()
					.get(0)
					.getYOffsetValue()
					.setLow(effect.getEmitters().get(0).getXOffsetValue().getLowMin(),
							effect.getEmitters().get(0).getXOffsetValue().getLowMax());
		}
	}

	public void setEffectColor(Color color) {
		float[] tint = { color.r, color.g, color.b, color.a, color.r, color.g, color.b, color.a };
		effect.getEmitters().get(0).getTint().setColors(tint);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		effect.draw(batch);
	}
}
