package com.jimmt.HologramClock;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Tile extends Group {
	Image background;
	DisplayEffect effect;
	int index;

	public Tile(DisplayEffect effect, int index) {
		this.effect = effect;
		this.index = index;
		
		ParticleEffectActor actor = new ParticleEffectActor(effect.getPaths()[index], "effects");
		ParticleEffect particleEffect = actor.effect;
		
		background = new Image(Assets.getTex("tile.png"));
		
		float scale = 1 / 4f;
		for (int i = 0; i < particleEffect.getEmitters().size; i++) {
			particleEffect.getEmitters().get(i).getScale().setHigh(particleEffect.getEmitters().get(i).getScale().getHighMax() * scale);
			particleEffect.getEmitters().get(i).getVelocity().setHigh(particleEffect.getEmitters().get(i).getVelocity().getHighMax() * scale);
			
			if(particleEffect.getEmitters().get(i).getXOffsetValue().getLowMin() != 0){
				particleEffect.getEmitters().get(i).getXOffsetValue().setLow(-getWidth() / 3, getWidth() / 3);
			}
		}
		
		
//		ParticleEmitter firstEmitter = particleEffect.getEmitters().get(0);
//		float verticalDistance = firstEmitter.getVelocity().getHighMax() * (firstEmitter.getLife().getHighMax() - firstEmitter.getLife().getHighMin()) / 200f;
//		System.out.println(firstEmitter.getVelocity().getHighMax() + " " + (firstEmitter.getLife().getHighMax() - firstEmitter.getLife().getHighMin()) / 2);
		
		actor.setPosition(getWidth() / 2, getHeight() / 3);
		addActor(background);
		addActor(actor);
	}
	
	public int getIndex(){
		return index;
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
