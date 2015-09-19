package com.jimmt.HologramClock;

import com.badlogic.gdx.Game;

public class HologramClock extends Game {

	@Override
	public void create() {
		setScreen(new BaseScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
