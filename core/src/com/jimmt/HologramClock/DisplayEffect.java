package com.jimmt.HologramClock;


public enum DisplayEffect {
	WHISPER("effects/whisper.p", 200f), YINYANG("effects/yinyang.p", 200f);

	static DisplayEffect[] values;
	
	String path;
	float offset;
	
	DisplayEffect(String path, float offset) {
		this.path = path;
		this.offset = offset;
	}
	
	public String getPath(){
		return path;
	}
	
	public float getOffset(){
		return offset;
	}
	
	public static DisplayEffect[] getValues() {
		if (values == null) {
			values = DisplayEffect.values();
		}

		return values;
	}
}
