package RenderEngine;

import org.lwjgl.opengl.GL11;

/**
 * MasterRenderer is responsible for handling the rendering setup and preparation
 * for the game scene. It sets up the background color and clears the screen before
 * rendering new frames.
 */
public class MasterRenderer {

    /**
     * Prepares the OpenGL context for rendering by setting the clear color and
     * clearing the color buffer. This method should be called before rendering
     * each new frame to ensure that the screen is cleared and ready for drawing.
     * 
     * - The background color is set using `glClearColor`, which defines the color that
     *   the screen will be cleared to. The values are RGB and alpha (transparency).
     *   In this example, the color is a light blue (0.4, 0.7, 1.0, 1).
     * - `glClear` is called with `GL_COLOR_BUFFER_BIT` to clear the color buffer,
     *   effectively resetting the screen with the specified background color.
     */
    public void prepare() {
        // Set the clear color to a light blue with RGB values (0.4, 0.7, 1.0) and full opacity (1).
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
        
        // Clear the color buffer to apply the new clear color, preparing the screen for drawing.
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
}
