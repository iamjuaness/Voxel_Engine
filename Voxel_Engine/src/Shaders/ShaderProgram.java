package shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * The ShaderProgram class is an abstract base class for managing OpenGL shader
 * programs. It handles the loading, compilation, and linking of vertex and
 * fragment shaders, as well as the management of shader attributes.
 * 
 * Subclasses must implement the bindAttributes method to specify how vertex
 * attributes are bound to the shader program.
 */
public abstract class ShaderProgram {

	/** The ID of the shader program created in OpenGL. */
	int programID;

	/** The ID of the vertex shader. */
	int vertexShaderID;

	/** The ID of the fragment shader. */
	int fragmentShaderID;

	/** Buffer to hold matrix data for uploading to the GPU. */
	FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	/**
	 * Constructor for ShaderProgram that initializes the shader program.
	 * 
	 * @param vertexFile   The file path of the vertex shader source code.
	 * @param fragmentFile The file path of the fragment shader source code.
	 */
	public ShaderProgram(String vertexFile, String fragmentFile) {
		programID = GL20.glCreateProgram(); // Create the shader program
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER); // Load vertex shader
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER); // Load fragment shader

		GL20.glAttachShader(programID, vertexShaderID); // Attach vertex shader
		GL20.glAttachShader(programID, fragmentShaderID); // Attach fragment shader
		bindAttributes(); // Bind shader attributes (to be defined in subclasses)
		GL20.glLinkProgram(programID); // Link the shader program
		GL20.glValidateProgram(programID); // Validate the linked program

		getAllUniformLocations(); // Get uniform locations for shader variables

		GL20.glUniform1f(programID, fragmentShaderID); // Example of using a fragment shader ID
	}

	/**
	 * Abstract method to retrieve all uniform locations used in the shader program.
	 * Subclasses must implement this method.
	 */
	protected abstract void getAllUniformLocations();

	/**
	 * Gets the location of a uniform variable in the shader program.
	 * 
	 * @param varName The name of the variable in the shader.
	 * @return The location of the uniform variable.
	 */
	protected int getUniformLocation(String varName) {
		return GL20.glGetUniformLocation(programID, varName);
	}

	/**
	 * Abstract method for binding shader attributes. Subclasses must implement this
	 * method to define how vertex attributes are bound to the shader program.
	 */
	protected abstract void bindAttributes();

	/**
	 * Loads a float value to a specified uniform location in the shader.
	 * 
	 * @param location The location of the uniform variable in the shader.
	 * @param value    The float value to load.
	 */
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value); // Load float to shader
	}

	/**
	 * Loads a 2D vector to a specified uniform location in the shader.
	 * 
	 * @param location The location of the uniform variable in the shader.
	 * @param vec      The 2D vector to load.
	 */
	protected void load2DVector(int location, Vector2f vec) {
		GL20.glUniform2f(location, vec.x, vec.y); // Load 2D vector to shader
	}

	/**
	 * Loads a 3D vector to a specified uniform location in the shader.
	 * 
	 * @param location The location of the uniform variable in the shader.
	 * @param vec      The 3D vector to load.
	 */
	protected void load3DVector(int location, Vector3f vec) {
		GL20.glUniform3f(location, vec.x, vec.y, vec.z); // Load 3D vector to shader
	}

	/**
	 * Loads a 4x4 matrix to a specified uniform location in the shader.
	 * 
	 * @param location The location of the uniform variable in the shader.
	 * @param mat      The 4x4 matrix to load.
	 */
	protected void loadMatrix(int location, Matrix4f mat) {
		mat.store(matrixBuffer); // Store matrix in buffer
		matrixBuffer.flip(); // Flip the buffer for reading

		GL20.glUniformMatrix4(location, false, matrixBuffer); // Load matrix to shader
	}

	/**
	 * Loads a boolean value to a specified uniform location in the shader.
	 * 
	 * @param location The location of the uniform variable in the shader.
	 * @param bool     The boolean value to load.
	 */
	protected void loadBoolean(int location, boolean bool) {
		float value = bool ? 1 : 0; // Convert boolean to float
		GL20.glUniform1f(location, value); // Load boolean as float to shader
	}

	/**
	 * Binds a vertex attribute to a specific location in the shader program.
	 * 
	 * @param variableName The name of the variable in the shader.
	 * @param attribute    The index of the attribute location to bind to.
	 */
	protected void bindAttribute(String variableName, int attribute) {
		GL20.glBindAttribLocation(programID, attribute, variableName); // Bind attribute location
	}

	/**
	 * Activates the shader program for rendering.
	 */
	public void start() {
		GL20.glUseProgram(programID); // Use the shader program
	}

	/**
	 * Deactivates the current shader program.
	 */
	public void stop() {
		GL20.glUseProgram(0); // Stop using any shader program
	}

	/**
	 * Cleans up the shader program by detaching and deleting the shaders, and
	 * deleting the program from OpenGL memory. This should be called when the
	 * program is no longer needed.
	 */
	public void cleanUp() {
		stop(); // Stop using the shader program
		GL20.glDetachShader(programID, vertexShaderID); // Detach vertex shader
		GL20.glDetachShader(programID, fragmentShaderID); // Detach fragment shader
		GL20.glDeleteShader(vertexShaderID); // Delete vertex shader
		GL20.glDeleteShader(fragmentShaderID); // Delete fragment shader
		GL20.glDeleteProgram(programID); // Delete the shader program
	}

	/**
	 * Loads and compiles a shader from the specified file.
	 * 
	 * @param file The file path of the shader source code.
	 * @param type The type of shader (GL20.GL_VERTEX_SHADER or
	 *             GL20.GL_FRAGMENT_SHADER).
	 * @return The ID of the compiled shader.
	 */
	private int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder(); // StringBuilder for shader source
		InputStream in = getClass().getResourceAsStream(file); // Load shader file
		if (in == null) {
			System.err.println("Shader file not found: " + file);
			System.exit(-1); // Exit the program if the shader file is not found
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(in)); // Read shader file

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("//\n"); // Append lines to shader source
			}
			reader.close(); // Close the reader
		} catch (IOException e) {
			e.printStackTrace(); // Print stack trace for IOException
			System.err.println("Could not load shader file!"); // Error message
			System.exit(-1); // Exit program on error
		}

		int shaderID = GL20.glCreateShader(type); // Create shader
		GL20.glShaderSource(shaderID, shaderSource); // Load shader source
		GL20.glCompileShader(shaderID); // Compile the shader

		// Check for compilation errors
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000)); // Print shader compilation errors
			System.err.println("Could not compile shader!"); // Error message
			System.exit(-1); // Exit program on error
		}

		return shaderID; // Return the compiled shader ID
	}
}
