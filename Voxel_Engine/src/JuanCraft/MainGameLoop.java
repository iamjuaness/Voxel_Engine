package juancraft;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import render_engine.DisplayManager;
import render_engine.Loader;
import render_engine.MasterRenderer;
import shaders.StaticShader;
import textures.Modeltexture;

/**
 * The MainGameLoop class is the entry point of the JuanCraft game application.
 * It initializes the display, prepares the rendering engine, and enters the
 * main game loop where rendering occurs until the display is closed.
 */
public class MainGameLoop {

	// Static reference to the Loader instance for use throughout the application.
	public static Loader loader1 = null;
	public static StaticShader shader1 = null;

	/**
	 * The main method that starts the game. It initializes the display, creates a
	 * MasterRenderer for rendering, and enters the game loop.
	 * 
	 * @param args Command line arguments (not used in this application).
	 */
	public static void main(String[] args) {

		// Create and initialize the display window for the game.
		DisplayManager.createDisplay();

		// Instantiate the MasterRenderer to handle rendering operations.
		MasterRenderer renderer = new MasterRenderer();

		// Create a Loader instance for loading models and shaders.
		Loader loader = new Loader();
		loader1 = loader; // Store the loader instance for potential future use.
		StaticShader shader = new StaticShader();
		shader1 = shader; // Store the shader instance for potential future use.

		// Define vertices for a simple square model.
		float[] vertices = { -0.5f, 0.5f, 0, // Top left corner of the square
				-0.5f, -0.5f, 0, // Bottom left corner of the square
				0.5f, -0.5f, 0, // Bottom right corner of the square
				0.5f, 0.5f, 0 // Top right corner of the square
		};

		// Define indices for the square's two triangles.
		int[] indices = { 0, 1, 2, // First triangle (top left, bottom left, bottom right)
				2, 3, 0 // Second triangle (bottom right, top right, top left)
		};

		// Define texture coordinates (UV mapping) for the vertices.
		float[] uv = { 0, 0, // Top-left corner of the texture
				0, 1, // Bottom-left corner of the texture
				1, 1, // Bottom-right corner of the texture
				1, 0 // Top-right corner of the texture
		};

		// Load the vertices, indices, and UV coordinates into a RawModel.
		RawModel model = loader.loadToVao(vertices, indices, uv);

		// Load a texture from the specified file and create a Modeltexture object.
		// The texture is loaded using the loader, which handles the texture 
		// loading process and returns a texture ID.
		Modeltexture texture = new Modeltexture(loader.loadTexture("dirtTex"));

		// Create a TexturedModel object using the loaded texture and the 3D model.
		TexturedModel texturedModel = new TexturedModel(model, texture);

		// Create an Entity object with the textured model, positioned at the origin (0, 0, 0)
		// with no rotation (0, 0, 0) and a scale factor of 1.
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);

		// Main game loop, which runs continuously until the display requests to close.
		// This loop is responsible for rendering frames and handling game logic.
		while (!Display.isCloseRequested()) {
		    // Prepare the rendering for the current frame (e.g., clear the screen).
		    renderer.prepare();
		    
		    entity.increasePosition(0.001f, 0, 0);
		    entity.increaseScale(-0.001f);
		    entity.increaseRotation(0, 0, 0.1f);

		    // Start using the shader program for rendering the entities.
		    shader.start();

		    // Render the loaded entity using the renderer.
		    // This method call delegates the rendering to the EntityRenderer class.
		    renderer.render(entity, shader);

			// Stop using the shader after rendering.
			shader.stop();

			// Update the display (sync frame rate and render new frame).
			DisplayManager.updateDisplay();
		}

		// Close the display and clean up resources when the loop exits.
		DisplayManager.closeDisplay();
	}
}
