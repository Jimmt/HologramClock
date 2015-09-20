package com.jimmt.HologramClock;

import com.badlogic.gdx.graphics.Color;


public enum DisplayEffect {
	WHISPER(200f, 25f, "effects/whisper/whisper_red.p", "effects/whisper/whisper_pink.p", "effects/whisper/whisper_aqua.p", "effects/whisper/whisper_navy.p", "effects/whisper/whisper_green.p"), 
	LAVA(200f, 25f, "effects/lava/lava_red.p", "effects/lava/lava_pink.p", "effects/lava/lava_orange.p", "effects/lava/lava_green.p", "effects/lava/lava_blue.p"), 
	BOKEH(200f, 25f, "effects/bokeh/bokeh.p", "effects/bokeh/bokeh_orange.p", "effects/bokeh/bokeh_pink.p", "effects/bokeh/bokeh_redblue.p", "effects/bokeh/bokeh_ice.p", "effects/bokeh/bokeh_white.p");

	static DisplayEffect[] values;
	
	String[] paths;
	float offset, effectOffset;
	
	DisplayEffect(float offset, float effectOffset, String... paths) {
		this.paths = paths;
		this.offset = offset; 
		this.effectOffset = effectOffset;
	}
	
	public String[] getPaths(){
		return paths;
	}
	
	public float getOffset(){
		return offset;
	}
	
	public float getEffectOffset(){
		return effectOffset;
	}
	
	
	public static DisplayEffect[] getValues() {
		if (values == null) {
			values = DisplayEffect.values();
		}

		return values;
	}
}
