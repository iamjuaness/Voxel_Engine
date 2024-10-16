package render_engine;

import org.lwjgl.opengl.GL11;

import entities.Entity;
import shaders.StaticShader;

/**
 * MasterRenderer is responsible for handling the rendering setup and
 * preparation for the game scene. It sets up the background color and clears
 * the screen before rendering new frames. Additionally, it handles the
 * rendering of 3D entities.
 */
public class MasterRenderer {

	/**
	 * Prepares the OpenGL context for rendering by setting the clear color and
	 * clearing the color buffer. This method should be called before rendering each
	 * new frame to ensure that the screen is cleared and ready for drawing.
	 * 
	 * - The background color is set using `glClearColor`, which defines the color
	 * that the screen will be cleared to. The values are RGB and alpha
	 * (transparency). In this example, the color is a light blue (0.4, 0.7, 1.0,
	 * 1). - `glClear` is called with `GL_COLOR_BUFFER_BIT` to clear the color
	 * buffer, effectively resetting the screen with the specified background color.
	 */
	public void prepare() {
		// Set the clear color to a light blue with RGB values (0.4, 0.7, 1.0) and full
		// opacity (1).
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);

		// Clear the color buffer to apply the new clear color, preparing the screen for
		// drawing.
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Renders the specified Entity by delegating the rendering process to
	 * the EntityRenderer. This method accepts an Entity and uses the static
	 * render method of the EntityRenderer class to draw the model associated 
	 * with the entity.
	 * 
	 * @param entity The Entity to be rendered, which contains the model's 
	 *               VAO ID, vertex count, and texture data.
	 * @param shader The StaticShader used for rendering the model, allowing 
	 *               transformation and texture application.
	 */
	public void render(Entity entity, StaticShader shader) {
	    // Delegate the rendering of the model to the static render method in
	    // EntityRenderer.
	    EntityRenderer.render(entity, shader);
	}


}
