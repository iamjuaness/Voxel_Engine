package JuanCraft;

import org.lwjgl.opengl.Display;

import Models.RawModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;

/**
 * The MainGameLoop class is the entry point of the JuanCraft game application.
 * It initializes the display, prepares the rendering engine, and enters the
 * main game loop where rendering occurs until the display is closed.
 */
public class MainGameLoop {
	
    // Static reference to the Loader instance for use throughout the application.
    public static Loader loader1 = null;

    /**
     * The main method that starts the game. It initializes the display,
     * creates a MasterRenderer for rendering, and enters the game loop.
     * 
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        
        // Create and initialize the display window for the game.
        DisplayManager.createDisplay();
        
        // Instantiate the MasterRenderer to handle rendering operations.
        MasterRenderer renderer = new MasterRenderer();
        
        // Create a Loader instance for loading models.
        Loader loader = new Loader();
        loader1 = loader;  // Store the loader instance for potential future use.
        
        // Define vertices for a simple square model.
        float[] vertices = {
        	-0.5f, 0.5f, 0,   // Top left
        	-0.5f, -0.5f, 0,  // Bottom left
        	0.5f, -0.5f, 0,   // Bottom right
        	0.5f, -0.5f, 0,   // Bottom right
        	0.5f, 0.5f, 0,    // Top right
        	-0.5f, 0.5f, 0    // Top left
        };
        
        // Load the vertices into a RawModel.
        RawModel model = loader.loadToVao(vertices);
        
        // Main game loop, runs until the display is requested to close.
        while(!Display.isCloseRequested()) {
            // Prepare the rendering for the current frame.
            renderer.prepare();
            
            // Render the loaded model.
            renderer.render(model);
            
            // Update the display (sync frame rate and render new frame).
            DisplayManager.updateDisplay();
        }
        
        // Close the display and clean up resources when the loop exits.
        DisplayManager.closeDisplay();
    }
}
