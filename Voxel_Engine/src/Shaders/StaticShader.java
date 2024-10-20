package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import toolbox.Maths;

/**
 * The StaticShader class represents a shader program used for rendering static
 * geometry. It extends the ShaderProgram class to manage the loading and
 * compiling of shaders.
 */
public class StaticShader extends ShaderProgram {

    // File paths for the vertex and fragment shader source code
    private static final String vertexFile = "/Shaders/vertexShader.txt"; // Path to the vertex shader
    private static final String fragmentFile = "/Shaders/fragmentShader.txt"; // Path to the fragment shader

    // Location of the transformation matrix uniform variable in the shader
    private int location_transformationMatrix; // Variable to hold the location of the transformation matrix
    private int location_projectionMatrix; // Variable to hold the location of the projection matrix
    private int location_viewMatrix; // Variable to hold the location of the view matrix

    /**
     * Constructor for the StaticShader class. Calls the parent constructor with the
     * file paths for the vertex and fragment shaders.
     */
    public StaticShader() {
        super(vertexFile, fragmentFile); // Initialize the shader program with the specified shader files
    }

    /**
     * Binds the attributes for the shader program. It specifies which vertex
     * attribute corresponds to which index in the shader. This setup is crucial for
     * properly linking vertex data (like positions and texture coordinates) to
     * their corresponding variables in the vertex shader.
     */
    @Override
    protected void bindAttributes() {
        // Bind the "position" attribute to index 0 in the shader
        super.bindAttribute("position", 0);
        // Bind the "textureCoords" attribute to index 1 in the shader
        super.bindAttribute("textureCoords", 1);
    }

    /**
     * Retrieves the locations of all uniform variables used in the shader program.
     * This method is called after linking the shader program to ensure all uniforms
     * are accessible for setting values.
     */
    @Override
    protected void getAllUniformLocations() {
        // Get the location of the transformation matrix uniform variable
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    /**
     * Loads the transformation matrix into the shader program. This matrix is
     * typically used for transforming vertex positions from object space to world
     * space or camera space.
     * 
     * @param matrix The transformation matrix to load into the shader.
     */
    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix); // Load the matrix using the parent method
    }

    /**
     * Loads the projection matrix into the shader program. This matrix is used to
     * define the perspective projection of the scene, affecting how objects are
     * displayed based on camera settings.
     *
     * @param matrix The projection matrix to load into the shader.
     */
    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix); // Load the matrix using the parent method
    }
    
    /**
     * Loads the view matrix for the specified camera into the shader program.
     * The view matrix is responsible for transforming coordinates from world space
     * to camera space, effectively positioning and orienting the scene as seen 
     * through the camera.
     *
     * @param camera The Camera object containing the current camera's position and 
     *               rotation, which is used to generate the view matrix.
     */
    public void loadViewMatrix(Camera camera) {
        // Call the superclass method to load the view matrix into the shader.
        super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
    }
}
