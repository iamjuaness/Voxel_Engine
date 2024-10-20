package juancraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.AtlasCubeModel;
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

    // Static references for loader and shader for use throughout the application.
    public static Loader loader1 = null; // Loader instance for model loading
    public static StaticShader shader1 = null; // Shader instance for rendering
    
    // List of chunks to be rendered in the game world.
    static List<Chunck> chunks = Collections.synchronizedList(new ArrayList<Chunck>());
    
    // Vector representing the position of the camera.
    static Vector3f camPos = new Vector3f(0, 0, 0);
    
    // List of positions that have been used for placing entities to avoid duplication.
    static List<Vector3f> usedPos = new ArrayList<Vector3f>();
    
    // Defines the size of the world (distance from the camera in each direction).
    static final int WORLD_SIZE = 9 * 16;

    /**
     * The main method that starts the game. It initializes the display, creates a
     * MasterRenderer for rendering, and enters the game loop.
     * 
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create and initialize the display window for the game.
        DisplayManager.createDisplay();

        // Create a Loader instance for loading models and shaders.
        Loader loader = new Loader();
        loader1 = loader; // Store the loader instance for potential future use.
        StaticShader shader = new StaticShader();
        shader1 = shader; // Store the shader instance for potential future use.

        // Instantiate the MasterRenderer to handle rendering operations.
        MasterRenderer renderer = new MasterRenderer();

        // Load the vertices, indices, and UV coordinates into a RawModel.
        RawModel model = loader.loadToVao(AtlasCubeModel.vertices, AtlasCubeModel.indices, AtlasCubeModel.uv);

        // Load a texture from the specified file and create a Modeltexture object.
        Modeltexture texture = new Modeltexture(loader.loadTexture("grassTex"));

        // Create a TexturedModel object using the loaded texture and the 3D model.
        TexturedModel texturedModel = new TexturedModel(model, texture);

        // Create a Camera object positioned at the origin with no rotation.
        Camera camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);
        
        // Create a new thread to manage entities creation in the positive X and Z quadrant.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Loop continuously while the display is open.
                while (!Display.isCloseRequested()) {
                    // Loop through a 20x20 grid area in the positive X and Z quadrant around the camera.
                    for (int x = (int) (camPos.x - WORLD_SIZE) / 16; x < (camPos.x + WORLD_SIZE) / 16; x++) {
                        for (int z = (int) (camPos.z - WORLD_SIZE) / 16; z < (camPos.z + WORLD_SIZE) / 16; z++) {
                            // Check if the position is already used to avoid duplicate entities.
                            if (!usedPos.contains(new Vector3f(x * 16, 0, z * 16))) {
                                
                                // Create a list of blocks (entities) for the current chunk.
                                List<Entity> blocks = new ArrayList<Entity>();
                                
                                // Loop to create a 16x16 grid of blocks for the current chunk.
                                for (int i = 0; i < 16; i++) {
                                    for (int j = 0; j < 16; j++) {
                                        blocks.add(new Entity(
                                            texturedModel,
                                            new Vector3f((x * 16) + i, 0, (z * 16) + j),
                                            0, 0, 0, 1
                                        ));
                                    }
                                }
                                
                                // Add the new chunk to the list of chunks.
                                chunks.add(new Chunck(blocks, new Vector3f(x * 16, 0, z * 16)));
                                
                                // Mark the position as used.
                                usedPos.add(new Vector3f(x * 16, 0, z * 16));
                            }
                        }
                    }
                }
            }
        }).start();

        // Main game loop, which runs continuously until the display requests to close.
        while (!Display.isCloseRequested()) {
            
            // Call the move method of the camera to update its position based on input.
            camera.move(); // Update camera position based on user input
            
            // Get the current camera position for entity placement logic.
            camPos = camera.getPosition();

            // Render each chunk if within the specified world size.
            for (int i = 0; i < chunks.size(); i++) {
                
                Vector3f origin = chunks.get(i).getOrigin();
                
                // Calculate the distance between the camera and the chunk along the X-axis.
                int distX = (int) (camPos.x - origin.x);
                // Calculate the distance between the camera and the chunk along the Z-axis.
                int distZ = (int) (camPos.z - origin.z);

                // Convert negative distances to positive values.
                if (distX < 0) {
                    distX = -distX;
                }

                if (distZ < 0) {
                    distZ = -distZ;
                }

                // If the chunk is within the world size range, render its blocks.
                if (distX <= WORLD_SIZE && distZ <= WORLD_SIZE) {
                    for (Entity block : chunks.get(i).getBlocks()) {
                        renderer.addEntity(block); // Add each block entity to the renderer.
                    }
                }
            }
            
            // Render the scene with the camera's current view.
            renderer.render(camera);

            // Update the display (sync frame rate and render new frame).
            DisplayManager.updateDisplay();
        }

        // Close the display and clean up resources when the loop exits.
        DisplayManager.closeDisplay();
    }
}
