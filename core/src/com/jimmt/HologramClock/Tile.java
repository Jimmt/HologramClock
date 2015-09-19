package com.jimmt.HologramClock;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Tile extends Group {
	Image background;

	public Tile(DisplayEffect effect) {
		ParticleEffectActor actor = new ParticleEffectActor(effect.getPath(), effect.getPath()
				+ "/..");
		ParticleEffect particleEffect = actor.effect;
		for (int i = 0; i < particleEffect.getEmitters().size; i++) {
			particleEffect.getEmitters().get(i).getScale().setHigh(100f);
		}
		
		background = new Image(Assets.getTex("tile.png"));
		actor.setPosition(getWidth() / 2, getHeight() / 2);
		addActor(background);
		addActor(actor);
	}
	
	@Override
	public void setWidth(float width){
		super.setWidth(width);
		
		background.setWidth(width);
	}
	
	@Override
	public void setHeight(float height){
		super.setHeight(height);
		background.setHeight(height);
	}
	
	@Override
	public float getWidth(){
		return background.getWidth();
	}
	
	@Override
	public float getHeight(){
		return background.getHeight();
	}
}
