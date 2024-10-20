package render_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import entities.Camera; // Camera class representing the player's view
import entities.Entity; // Entity class representing 3D objects
import models.TexturedModel; // Model class with textures
import shaders.StaticShader; // Class representing the shader used for rendering

/**
 * The MasterRenderer class handles the overall rendering setup for the game scene.
 * It prepares the OpenGL context, manages the projection matrix, and coordinates 
 * the rendering of entities.
 */
public class MasterRenderer {
    
    private Matrix4f projectionMatrix; // The projection matrix for perspective projection

    private static final float FOV = 70f; // Field of View for the projection
    private static final float NEAR_PLANE = 0.1f; // Distance to the near clipping plane
    private static final float FAR_PLANE = 10000f; // Distance to the far clipping plane
    
    StaticShader shader = new StaticShader();
    EntityRenderer renderer = new EntityRenderer();
    Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

    /**
     * Constructor for the MasterRenderer class. Initializes the projection matrix
     * and sets the shader for rendering.
     */
    public MasterRenderer() {
        createProjectionMatrix(); // Create the projection matrix
        shader.start(); // Start the shader program
        shader.loadProjectionMatrix(projectionMatrix); // Load the projection matrix into the shader
        shader.stop(); // Stop the shader program
    }

    /**
     * Prepares the OpenGL context for rendering by setting the clear color and
     * clearing the color and depth buffers. This should be called before each new frame.
     */
    public void prepare() {
        // Enable depth testing to render closer objects in front of farther ones.
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        // Set the clear color to light blue.
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
        // Clear both color and depth buffers.
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Renders the scene using the provided camera for view transformations.
     * 
     * @param camera The camera used to render the scene from a specific viewpoint.
     */
    public void render(Camera camera) {
        prepare(); // Prepare the rendering context
        shader.start(); // Start the shader program
        shader.loadViewMatrix(camera); // Load the camera view matrix into the shader
        renderer.render(entities); // Render the entities
        shader.stop(); // Stop the shader program
        
        entities.clear(); // Clear the entity map for the next frame
    }
    
    /**
     * Adds an entity to the rendering queue. Entities are batched by their textured model.
     * 
     * @param entity The entity to be added to the rendering queue.
     */
    public void addEntity(Entity entity) {
    	TexturedModel model = entity.getModel();
    	List<Entity> batch = entities.get(model);
    	
    	if (batch != null) {
    		batch.add(entity); // Add to existing batch
    	} else {
    		List<Entity> newBatch = new ArrayList<Entity>();
    		newBatch.add(entity); // Create a new batch for the new model
    		entities.put(model, newBatch);
    	}
    }

    /**
     * Creates the projection matrix used for perspective rendering.
     */
    public void createProjectionMatrix() {
        projectionMatrix = new Matrix4f(); // Initialize the projection matrix

        // Calculate aspect ratio based on display dimensions
        float aspect = (float) Display.getWidth() / (float) Display.getHeight();
        float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f))); // Calculate y scale
        float xScale = (float) yScale / aspect; // Calculate x scale
        float zp = FAR_PLANE + NEAR_PLANE; // Sum of far and near planes
        float zm = FAR_PLANE - NEAR_PLANE; // Difference of far and near planes

        // Set the elements of the projection matrix
        projectionMatrix.m00 = xScale; // Set x scale
        projectionMatrix.m11 = yScale; // Set y scale
        projectionMatrix.m22 = -zp / zm; // Set z scale
        projectionMatrix.m23 = -1; // Set w-component
        projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm; // Set z translation
        projectionMatrix.m33 = 0; // Set last row for homogeneous coordinates
    }
}
