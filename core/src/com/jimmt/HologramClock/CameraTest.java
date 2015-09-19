package com.jimmt.HologramClock;

import java.util.Calendar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class CameraTest implements Screen {
	Stage stage;
	Skin skin;
	OrthographicCamera camera;
	Label label;
	Container container;
	OrthographicCamera[] cameras = new OrthographicCamera[3];
	boolean set, militaryTime;
	int hour, day, month, year, minute, second;

	public CameraTest(HologramClock main) {
		stage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("Cyberfunk.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 100;
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();

		LabelStyle style = new LabelStyle();
		style.font = font;
		style.fontColor = Color.WHITE;
		label = new Label(hour + ":" + minute + ":" + second, style);
		label.setOrigin(label.getWidth() / 2, label.getHeight() / 2);
		container = new Container(label);
		container.setTransform(true);
		container.setScaleX(-1);
		stage.addActor(container);
		float box = 144f;
		float buffer = 150f;
		container.setPosition(Constants.WIDTH / 2, Constants.HEIGHT / 2 + buffer + box / 2);

		for (int i = 0; i < 3; i++) {
			cameras[i] = new OrthographicCamera();
			cameras[i].setToOrtho(true, Constants.WIDTH, Constants.HEIGHT);
		}
		updateTime();

		// square: 144x144

		Gdx.input.setInputProcessor(stage);

		addBox();
	}

	public void addBox() {
		Image box = new Image(new Texture(Gdx.files.internal("box.png")));
		stage.addActor(box);
		box.setPosition(Constants.WIDTH / 2 - box.getWidth() / 2,
				Constants.HEIGHT / 2 - box.getHeight() / 2);
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
		label.setText(hour + ":" + String.format("%02d", minute) + ":"
				+ String.format("%02d", second));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		for (int i = 0; i < 3; i++) {
			cameras[i].update();
		}

		updateTime();
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
