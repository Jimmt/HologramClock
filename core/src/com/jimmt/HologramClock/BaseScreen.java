package com.jimmt.HologramClock;

import java.util.Calendar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class BaseScreen implements Screen {
	Stage stage;
	Skin skin;
	SettingsDialog settingsDialog;
	TimeDisplay[] displays = new TimeDisplay[4];
	int hour, day, month, year, minute, second;
	boolean militaryTime;

	public BaseScreen(HologramClock main) {
		stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("Cyberfunk.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 100;
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();

		float[] rotations = { 0, 90, -90, 180 };

		for (int i = 0; i < 4; i++) {    
			displays[i] = new TimeDisplay(font, DisplayEffect.YINYANG, rotations[i], i == 1 || i == 2);
			displays[i].setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2);
			stage.addActor(displays[i]);
		}

		updateTime();

		float box = 144f;
		float buffer = 200f;
		
		Gdx.input.setInputProcessor(stage);

		addIcon();
		
		settingsDialog = new SettingsDialog(skin);
//		settingsDialog.setVisible(false);
//		stage.addActor(settingsDialog);
	}

	public void addIcon() {
		Image icon = new Image(new Texture(Gdx.files.internal("settingsicon.png")));
		stage.addActor(icon);
		icon.setSize(128, 128);
		icon.setPosition(Constants.WIDTH / 2 - icon.getWidth() / 2,
				Constants.HEIGHT / 2 - icon.getHeight() / 2);
		icon.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				settingsDialog.show(stage);
			}
		});
	}

	public void rotateEffect(ParticleEffect effect, float deg) {
		effect.getEmitters().get(0).getAngle()
				.setLow(effect.getEmitters().get(0).getAngle().getLowMin() - deg);
		effect.getEmitters()
				.get(0)
				.getAngle()
				.setHigh(effect.getEmitters().get(0).getAngle().getHighMin() - deg,
						effect.getEmitters().get(0).getAngle().getHighMax() - deg);
	}

	public void updateTime() {
		if (militaryTime) {
			hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		} else {
			hour = Calendar.getInstance().get(Calendar.HOUR);
		}

		day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		month = Calendar.getInstance().get(Calendar.MONTH);
		year = Calendar.getInstance().get(Calendar.YEAR);
		minute = Calendar.getInstance().get(Calendar.MINUTE);
		second = Calendar.getInstance().get(Calendar.SECOND);
	}

	public void updateDisplays() {
		for (int i = 0; i < displays.length; i++) {
			displays[i].label.setText(hour + ":" + String.format("%02d", minute) + ":"
					+ String.format("%02d", second));
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		updateTime();
		updateDisplays();

// for (Container container : containers) {
// // if (container.getActions().size == 0) {
// // container.addAction(Actions.sequence(Actions.scaleBy(1, 1, 1),
// // Actions.scaleBy(-1, -1, 1)));
// // }
// container.setOrigin(10, 10);
//
// }
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
