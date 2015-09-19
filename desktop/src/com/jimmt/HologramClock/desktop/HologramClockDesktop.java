package com.jimmt.HologramClock.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jimmt.HologramClock.HologramClock;

public class HologramClockDesktop {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new HologramClock(), config);
	}
}
