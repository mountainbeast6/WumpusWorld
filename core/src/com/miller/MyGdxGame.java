package com.miller;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
	public static boolean showWorld=false;
	@Override
	public void create() {
		setScreen(new SimScreen());
	}
}