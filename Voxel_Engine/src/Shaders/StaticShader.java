package Shaders;

/**
 * The StaticShader class represents a shader program used for rendering static
 * geometry. It extends the ShaderProgram class to manage the loading and
 * compiling of shaders.
 */
public class StaticShader extends ShaderProgram {

	// File paths for the vertex and fragment shader source code
	private static final String vertexFile = "/Shaders/vertexShader.txt";
	private static final String fragmentFile = "/Shaders/fragmentShader.txt";

	/**
	 * Constructor for the StaticShader class. Calls the parent constructor with the
	 * file paths for the vertex and fragment shaders.
	 */
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

    /**
     * Binds the attributes for the shader program. It specifies which vertex 
     * attribute corresponds to which index in the shader. This setup is crucial 
     * for properly linking vertex data (like positions and texture coordinates) 
     * to their corresponding variables in the vertex shader.
     */
    @Override
    protected void bindAttributes() {
        // Bind the "position" attribute to index 0 in the shader
        super.bindAttribute("position", 0);
        // Bind the "textureCoords" attribute to index 1 in the shader
        super.bindAttribute("textureCoords", 1);
    }


}
