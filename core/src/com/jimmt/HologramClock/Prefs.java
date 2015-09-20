package com.jimmt.HologramClock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
	static Preferences prefs;

	public static void init() {
		prefs = Gdx.app.getPreferences("prefs");
		
		if (!prefs.contains("display")) {
			prefs.putBoolean("militaryTime", false);
			prefs.flush();
		}
		if(!prefs.contains("displayIndex")){
			prefs.putInteger("displayIndex", 0);
		}
		if(!prefs.contains("effectIndex")){
			prefs.putInteger("effectIndex", 0);
			prefs.flush();
		}
	}
}
