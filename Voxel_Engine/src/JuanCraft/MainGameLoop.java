package juancraft;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import chunks.Chunk;
import chunks.ChunkMesh;
import entities.Camera;
import entities.Entity;
import entities.Pointer;
import fisics.CollisionHandler;
import models.CubeModel;
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
    public static List<ChunkMesh> chunks = new CopyOnWriteArrayList<>();
    
    // Vector representing the position of the camera.
    static Vector3f camPos = new Vector3f(0, 0, 0);
       
    // List of positions that have been used for placing entities to avoid duplication.
    static List<Vector3f> usedPos = new ArrayList<Vector3f>();
    
    static List<Entity> entities = new ArrayList<Entity>();
    
    // Defines the size of the world (distance from the camera in each direction).
    static final int WORLD_SIZE = 9 * 32; // World size is 288 units (9 chunks of 32 units each).
    
    /**
     * The main method that starts the game. It initializes the display, creates a
     * MasterRenderer for rendering, and enters the game loop.
     * 
     * @param args Command line arguments (not used in this application).
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Create and initialize the display window for the game.
        DisplayManager.createDisplay();

        // Create a Loader instance for loading models and shaders.
        Loader loader = new Loader();
        loader1 = loader; // Store the loader instance for potential future use.
        StaticShader shader = new StaticShader();
        shader1 = shader; // Store the shader instance for potential future use.
        
        // Set properties for the pointer entity.
        Pointer.setSize(20.0f);
        Pointer.setColor(1.0f, 0.0f, 0.0f); // Set pointer color to red.

        // Instantiate the MasterRenderer to handle rendering operations.
        MasterRenderer renderer = new MasterRenderer();

        // Load the vertices, indices, and UV coordinates into a RawModel for a cube.
        RawModel model = loader.loadToVao(CubeModel.vertices, CubeModel.indices, CubeModel.uv);

        // Load a texture from the specified file and create a Modeltexture object.
        Modeltexture texture = new Modeltexture(loader.loadTexture("DefaultPack"));

        // Create a TexturedModel object using the loaded texture and the 3D model.
        TexturedModel texturedModel = new TexturedModel(model, texture);

        // Create a Camera object positioned at the origin with no rotation.
        Camera camera = new Camera(new Vector3f(0, 2, 0), 0, 0, 0);

        // Create a new thread to manage entity creation in the positive X and Z quadrant.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Generate the chunk where the camera is located.
                Chunk.generateChunkAtCameraPosition(camPos, usedPos);
                
                // Generate nearby chunks in a separate thread.
                while (!Display.isCloseRequested()) {
                    // Loop to generate chunks around the camera in a 32x32 area.
                    for (int x = (int) (camPos.x - WORLD_SIZE) / 32; x < (camPos.x + WORLD_SIZE) / 32; x++) {
                        for (int z = (int) (camPos.z - WORLD_SIZE) / 32; z < (camPos.z + WORLD_SIZE) / 32; z++) {
                            // Avoid generating already used chunks.
                            if (!usedPos.contains(new Vector3f(x * 32, 0, z * 32))) {
                                Chunk.generateChunkAt(x, z, usedPos); // Generate the chunk.
                            }
                        }
                    }

                    try {
                        Thread.sleep(100); // Sleep to prevent overloading the loop.
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupted state.
                    }
                }
            }
        }).start();
        
        // Main game loop, which runs continuously until the display requests to close.
        int index = 0; // Index for tracking chunks to be loaded.
        boolean showDebugInfo = false; // Toggle for debug information display.
        while (!Display.isCloseRequested()) {
            
            // Allow camera movement only if the chunk is generated.
            if (Chunk.isChunkAtCameraReady) {
                camera.move();  
            }
                    
            // Get the current camera position for entity placement logic.
            camPos = camera.getPosition();
            
            // Apply gravity if there is no ground below the camera.
            if (!CollisionHandler.isGroundBelowCamera(camera)) {
                camera.applyGravity();
            }
            
            // Load chunks into the world until we reach the limit.
            if (index < chunks.size()) {
                // Load the chunk's mesh data into a RawModel.
                RawModel model123 = loader.loadToVao(chunks.get(index).positions, chunks.get(index).uvs);
                
                // Create a textured model for the chunk using the loaded texture.
                TexturedModel texModel = new TexturedModel(model123, texture);
                
                // Create an entity for the chunk, placed at the chunk's origin.
                Entity entity = new Entity(texModel, chunks.get(index).chunck.getOrigin(), 0, 0, 0, 1);
                entities.add(entity); // Add the new entity to the list of entities.
                
                // Clean up chunk data to free memory.
                chunks.get(index).positions = null;
                chunks.get(index).uvs = null;
                chunks.get(index).normals = null;
                
                index++; // Move to the next chunk.
            }
            
            // Render each chunk entity that is within the specified world size.
            for (int i = 0; i < entities.size(); i++) {
                Vector3f origin = entities.get(i).getPosition(); // Get the position of the entity.
                
                // Calculate the distance from the camera to the chunk along the X and Z axes.
                int distX = Math.abs((int) (camPos.x - origin.x));
                int distZ = Math.abs((int) (camPos.z - origin.z));

                // If the chunk is within the world size range, render its blocks.
                if (distX <= WORLD_SIZE && distZ <= WORLD_SIZE) {
                    renderer.addEntity(entities.get(i)); // Add entity for rendering.
                }
            }
            
            // Clear the frame buffer before rendering the new frame.
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Render the scene with the camera's current view.
            renderer.render(camera);
            
            // Render the pointer.
            Pointer.renderPointer();
            
            // Update the display (sync frame rate and render new frame).
            DisplayManager.updateDisplay();
        }
        
        // Clean up pointer resources.
        Pointer.cleanup();

        // Close the display and clean up resources when the loop exits.
        DisplayManager.closeDisplay();
    }
}
