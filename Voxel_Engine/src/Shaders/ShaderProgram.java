package Shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

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
	}

	/**
	 * Abstract method for binding shader attributes. Subclasses must implement this
	 * method to define how vertex attributes are bound to the shader program.
	 */
	protected abstract void bindAttributes();

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
