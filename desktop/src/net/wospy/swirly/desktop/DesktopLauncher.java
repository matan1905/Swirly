package net.wospy.swirly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.wospy.swirly.Swirly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= 1290/2;
		config.height=720/2;
		config.samples=3;
		new LwjglApplication(new Swirly(), config);
	}
}
