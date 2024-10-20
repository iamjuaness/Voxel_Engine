package render_engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import shaders.StaticShader;

/**
 * MasterRenderer is responsible for handling the rendering setup and
 * preparation for the game scene. It sets up the background color and clears
 * the screen before rendering new frames. Additionally, it handles the
 * rendering of 3D entities.
 */
public class MasterRenderer {
    
    private Matrix4f projectionMatrix; // The projection matrix used for perspective projection

    private static final float FOV = 70f; // Field of View for the projection
    private static final float NEAR_PLANE = 0.1f; // Distance to the near clipping plane
    private static final float FAR_PLANE = 10000f; // Distance to the far clipping plane

    /**
     * Constructor for the MasterRenderer class. Initializes the projection matrix
     * and sets the shader for rendering.
     * 
     * @param shader The StaticShader to be used for rendering 3D entities.
     */
    public MasterRenderer(StaticShader shader) {
        createProjectionMatrix(); // Create the projection matrix for rendering
        shader.start(); // Start the shader program
        shader.loadProjectionMatrix(projectionMatrix); // Load the projection matrix into the shader
        shader.stop(); // Stop the shader program
    }

    /**
     * Prepares the OpenGL context for rendering by setting the clear color and
     * clearing the color and depth buffers. This method should be called before rendering each
     * new frame to ensure that the screen is cleared and ready for drawing.
     * 
     * - `glEnable(GL_DEPTH_TEST)` enables depth testing to ensure that closer objects
     *   are rendered in front of farther ones.
     * - `glClearColor` sets the background color to a light blue (RGB: 0.4, 0.7, 1.0)
     *   with full opacity (alpha: 1.0).
     * - `glClear` clears both the color buffer and the depth buffer, ensuring that
     *   the screen is reset to the background color and prepared for drawing new elements.
     */
    public void prepare() {
        // Enable depth testing to properly render the depth of objects.
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        // Set the clear color to a light blue with RGB values (0.4, 0.7, 1.0) and full opacity (1).
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);

        // Clear the color buffer and depth buffer to apply the new clear color and prepare the screen for drawing.
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
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

    /**
     * Creates the projection matrix used for perspective rendering. This matrix
     * defines how the 3D scene is projected onto the 2D screen based on the
     * specified field of view, aspect ratio, and clipping planes.
     */
    public void createProjectionMatrix() {
        projectionMatrix = new Matrix4f(); // Initialize the projection matrix

        // Calculate aspect ratio based on the display dimensions
        float aspect = (float) Display.getWidth() / (float) Display.getHeight();
        float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f))); // Calculate y scale based on FOV
        float xScale = (float) yScale / aspect; // Calculate x scale based on aspect ratio
        float zp = FAR_PLANE + NEAR_PLANE; // Sum of far and near planes
        float zm = FAR_PLANE - NEAR_PLANE; // Difference of far and near planes

        // Set the elements of the projection matrix for perspective projection
        projectionMatrix.m00 = xScale; // Set x scale in the matrix
        projectionMatrix.m11 = yScale; // Set y scale in the matrix
        projectionMatrix.m22 = -zp / zm; // Set z scale in the matrix
        projectionMatrix.m23 = -1; // Set the w-component for perspective division
        projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm; // Set the z translation
        projectionMatrix.m33 = 0; // Set the last row for homogeneous coordinates
    }
}
