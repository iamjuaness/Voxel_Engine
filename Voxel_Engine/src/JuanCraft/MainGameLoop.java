package juancraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
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

    // Static references for loader and shader for use throughout the application.
    public static Loader loader1 = null;
    public static StaticShader shader1 = null;
    
    // List of entities to be rendered in the game world.
    static List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());
    
    // Vector representing the position of the camera.
    static Vector3f camPos = new Vector3f(0, 0, 0);
    
    // List of positions that have been used for placing entities to avoid duplication.
    static List<Vector3f> usedPos = new ArrayList<Vector3f>();
    
    // Defines the size of the world (distance from the camera in each direction).
    static final int WORLD_SIZE = 15;

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
        MasterRenderer renderer = new MasterRenderer(shader1);

        // Define vertices for a simple square model.
        float[] vertices = { 
            -0.5f, 0.5f, -0.5f,	
            -0.5f, -0.5f, -0.5f,	
            0.5f, -0.5f, -0.5f,	
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, 0.5f,	
            -0.5f, -0.5f, 0.5f,	
            0.5f, -0.5f, 0.5f,	
            0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, -0.5f,	
            0.5f, -0.5f, -0.5f,	
            0.5f, -0.5f, 0.5f,	
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f,	
            -0.5f, -0.5f, -0.5f,	
            -0.5f, -0.5f, 0.5f,	
            -0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f
        };

        // Define indices for the square's two triangles.
        int[] indices = { 
            0, 1, 3,	
            3, 1, 2,	
            4, 5, 7,
            7, 5, 6,
            8, 9, 11,
            11, 9, 10,
            12, 13, 15,
            15, 13, 14,	
            16, 17, 19,
            19, 17, 18,
            20, 21, 23,
            23, 21, 22
        };

        // Define texture coordinates (UV mapping) for the vertices.
        float[] uv = { 
            0, 0,
            0, 1,
            1, 1,
            1, 0,			
            0, 0,
            0, 1,
            1, 1,
            1, 0,			
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };

        // Load the vertices, indices, and UV coordinates into a RawModel.
        RawModel model = loader.loadToVao(vertices, indices, uv);

        // Load a texture from the specified file and create a Modeltexture object.
        Modeltexture texture = new Modeltexture(loader.loadTexture("dirtTex"));

        // Create a TexturedModel object using the loaded texture and the 3D model.
        TexturedModel texturedModel = new TexturedModel(model, texture);

        // Create a Camera object positioned at the origin with no rotation.
        Camera camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);
        
     // Create a new thread to manage entities creation on the positive X and Z quadrant.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Loop continuously while the display is open.
                while (!Display.isCloseRequested()) {
                    // Loop through a 20x20 grid area in the positive X and Z quadrant around the camera.
                    for (int x = (int) (camPos.x - WORLD_SIZE); x < camPos.x; x++) {
                        for (int z = (int) (camPos.z); z < camPos.z + WORLD_SIZE; z++) {
                            // Check if the position is already used to avoid duplicate entities.
                            if (!usedPos.contains(new Vector3f(x, 0, z))) {
                                // Create a new entity at the specified position.
                                entities.add(new Entity(texturedModel, new Vector3f(x, 0, z), 0, 0, 0, 1));
                                // Mark the position as used.
                                usedPos.add(new Vector3f(x, 0, z));
                            }
                        }
                    }

                    // Loop through another 20x20 grid area in the positive X and Z quadrant.
                    for (int x = (int) (camPos.x); x < camPos.x + WORLD_SIZE; x++) {
                        for (int z = (int) (camPos.z); z < camPos.z + WORLD_SIZE; z++) {
                            // Check if the position is already used to avoid duplicate entities.
                            if (!usedPos.contains(new Vector3f(x, 0, z))) {
                                // Create a new entity at the specified position.
                                entities.add(new Entity(texturedModel, new Vector3f(x, 0, z), 0, 0, 0, 1));
                                // Mark the position as used.
                                usedPos.add(new Vector3f(x, 0, z));
                            }
                        }
                    }
                }
            }
        }).start();

        // Create a second thread to manage entities creation on the negative Z quadrant.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Loop continuously while the display is open.
                while (!Display.isCloseRequested()) {
                    // Loop through a 20x20 grid area in the negative Z and positive X quadrant.
                    for (int x = (int) (camPos.x - WORLD_SIZE); x < camPos.x; x++) {
                        for (int z = (int) (camPos.z - WORLD_SIZE); z < camPos.z; z++) {
                            // Check if the position is already used to avoid duplicate entities.
                            if (!usedPos.contains(new Vector3f(x, 0, z))) {
                                // Create a new entity at the specified position.
                                entities.add(new Entity(texturedModel, new Vector3f(x, 0, z), 0, 0, 0, 1));
                                // Mark the position as used.
                                usedPos.add(new Vector3f(x, 0, z));
                            }
                        }
                    }

                    // Loop through another 20x20 grid area in the negative Z and positive X quadrant.
                    for (int x = (int) (camPos.x); x < camPos.x + WORLD_SIZE; x++) {
                        for (int z = (int) (camPos.z - WORLD_SIZE); z < camPos.z; z++) {
                            // Check if the position is already used to avoid duplicate entities.
                            if (!usedPos.contains(new Vector3f(x, 0, z))) {
                                // Create a new entity at the specified position.
                                entities.add(new Entity(texturedModel, new Vector3f(x, 0, z), 0, 0, 0, 1));
                                // Mark the position as used.
                                usedPos.add(new Vector3f(x, 0, z));
                            }
                        }
                    }
                }
            }
        }).start();

        // Create a third thread to manage removal of entities that are out of range of the camera.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Loop continuously while the display is open.
                while (!Display.isCloseRequested()) {
                    // Iterate over all existing entities.
                    for (int i = 0; i < entities.size(); i++) {
                        // Calculate the distance between the camera and the entity along the X-axis.
                        int distX = (int) (camPos.x - entities.get(i).getPosition().x);
                        // Calculate the distance between the camera and the entity along the Z-axis.
                        int distZ = (int) (camPos.z - entities.get(i).getPosition().z);

                        // Convert negative distances to positive values.
                        if (distX < 0) {
                            distX = -distX;
                        }

                        if (distZ < 0) {
                            distZ = -distZ;
                        }

                        // If the entity is beyond the world size range, remove it.
                        if ((distX > WORLD_SIZE) || (distZ > WORLD_SIZE)) {
                            // Remove the entity's position from the used positions set.
                            usedPos.remove(entities.get(i).getPosition());
                            // Remove the entity from the list.
                            entities.remove(i);
                        }
                    }
                }
            }
        }).start();

        
        
        // Main game loop, which runs continuously until the display requests to close.
        while (!Display.isCloseRequested()) {
            // Prepare the rendering for the current frame (e.g., clear the screen).
            renderer.prepare();
            
            // Call the move method of the camera to update its position based on input.
            camera.move(); // Update camera position based on user input
            
         // Get the current camera position for entity placement logic.
            camPos = camera.getPosition();

            // Start using the shader program for rendering the entities.
            shader.start();
            
            // Load the camera view matrix into the shader.
            shader.loadViewMatrix(camera);
            
            // Render each entity using the MasterRenderer and shader.
            for (int i = 0; i < entities.size(); i++) {
                renderer.render(entities.get(i), shader);
            }

            // Stop using the shader after rendering.
            shader.stop();

            // Update the display (sync frame rate and render new frame).
            DisplayManager.updateDisplay();
        }

        // Close the display and clean up resources when the loop exits.
        DisplayManager.closeDisplay();
    }
}