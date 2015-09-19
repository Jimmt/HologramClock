package com.jimmt.HologramClock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
	static Preferences prefs;

	public static void init(){
		prefs = Gdx.app.getPreferences("prefs");
		prefs.putBoolean("militaryTime", false);
		prefs.putString("display", "whisper");
		prefs.putString("font", "Cyberfunk");
	}
}
