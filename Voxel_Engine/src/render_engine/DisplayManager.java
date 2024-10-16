package render_engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import juancraft.MainGameLoop;

/**
 * DisplayManager is responsible for managing the display window for the game.
 * It handles the creation, updating, and closing of the display using LWJGL
 * (Lightweight Java Game Library).
 */
public class DisplayManager {

	// The width of the display window.
	private static final int WIDTH = 1920;
	// The height of the display window.
	private static final int HEIGHT = 1080;
	// The frame rate cap (frames per second).
	private static final int FPS_CAP = 120;

	/**
	 * Creates the display window with specified width, height, and OpenGL context.
	 * Sets up the OpenGL context attributes and initializes the window. The display
	 * is set to fullscreen, and the viewport is configured. It also grabs the mouse
	 * input for a more immersive experience.
	 */
	public static void createDisplay() {

		// Define OpenGL context attributes: version 3.2, forward compatible, core
		// profile.
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			// Set the display mode with the defined width and height.
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			// Create the display with the specified pixel format and context attributes.
			Display.create(new PixelFormat(), attribs);
			// Set the title of the window.
			Display.setTitle("JuanCraft");
			// Enable fullscreen mode.
			Display.setFullscreen(true);
			// Set the OpenGL viewport to cover the entire display area.
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());

		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		// Capture the mouse input for the game.
		Mouse.setGrabbed(true);
	}

	/**
	 * Updates the display window to synchronize the frame rate and process input
	 * events. This method keeps the frame rate capped to the defined FPS and
	 * handles toggling mouse input grabbing when the 'E' key is pressed.
	 */
	public static void updateDisplay() {

		// Sync the display to maintain the defined frame rate cap.
		Display.sync(FPS_CAP);
		// Update the display content.
		Display.update();

		// Process keyboard input events.
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {

				// If 'ESCAPE' is pressed, the program is closed.
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					closeDisplay();
				}
				// If 'E' is pressed, toggle mouse grabbing (capture or release the mouse).
				if (Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
				} else if (Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
			}
		}
	}

	/**
	 * Closes the display window and exits the program. Releases all resources
	 * associated with the display and ensures a proper shutdown.
	 */
	public static void closeDisplay() {

		// Clean up any resources used by the Loader before closing the display.
		MainGameLoop.loader1.cleanUp();

		// Clean up any resources used by the StaticShader before closing the display.
		MainGameLoop.shader1.cleanUp();

		// Destroy the display and release any allocated resources.
		Display.destroy();
		// Exit the program with status code 0.
		System.exit(0);
	}
}
