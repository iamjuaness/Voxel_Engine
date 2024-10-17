package RenderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import Models.RawModel;

/**
 * The Loader class is responsible for loading 3D model data into OpenGL and
 * managing VAOs (Vertex Array Objects) and VBOs (Vertex Buffer Objects). It
 * stores vertex data in VBOs, binds them to VAOs, and keeps track of the
 * created VAOs and VBOs for later cleanup.
 */
public class Loader {

	// List to store IDs of all created VAOs, used for cleanup.
	static List<Integer> vaos = new ArrayList<Integer>();
	// List to store IDs of all created VBOs, used for cleanup.
	static List<Integer> vbos = new ArrayList<Integer>();

	/**
	 * Loads the provided vertex data into a VAO and returns a RawModel object.
	 * 
	 * @param vertices Array of vertex data to be loaded into the VAO.
	 * @param indices  Array of index data for indexed drawing.
	 * @return A RawModel containing the ID of the created VAO and the number of
	 *         vertices.
	 */
	public RawModel loadToVao(float[] vertices, int[] indices) {

		// Create a new VAO and bind the vertex data to it.
		int vaoID = creatVAO();
		storeDataInAttributeList(vertices, 0, 3);

		// Bind index buffer for indexed drawing.
		bindIndicesbuffer(indices);
		// Unbind the VAO to prevent further modification.
		GL30.glBindVertexArray(0);

		// Return a new RawModel with the ID of the VAO and the length of the indices
		// array.
		return new RawModel(vaoID, indices.length);
	}

	/**
	 * Creates a new VAO (Vertex Array Object) and binds it.
	 * 
	 * @return The ID of the created VAO.
	 */
	private int creatVAO() {
		// Generate a new VAO ID using OpenGL.
		int vaoID = GL30.glGenVertexArrays();
		// Store the VAO ID for cleanup purposes.
		vaos.add(vaoID);
		// Bind the VAO to start using it.
		GL30.glBindVertexArray(vaoID);

		return vaoID;
	}

	/**
	 * Stores the provided vertex data into a VBO (Vertex Buffer Object) and binds
	 * it to a specific attribute list of the current VAO.
	 * 
	 * @param data            The vertex data to be stored in the VBO.
	 * @param attributeNumber The index of the attribute list where the data will be
	 *                        stored.
	 * @param dimensions      The number of dimensions of each vertex (e.g., 3 for
	 *                        x, y, z).
	 */
	private void storeDataInAttributeList(float[] data, int attributeNumber, int dimensions) {
		// Generate a new VBO ID using OpenGL.
		int vboID = GL15.glGenBuffers();
		// Store the VBO ID for cleanup purposes.
		vbos.add(vboID);
		// Bind the VBO to the array buffer target.
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		// Convert the data into a FloatBuffer.
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		// Store the buffer data in the VBO with dynamic draw usage.
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
		// Link the VBO to the specified attribute list of the VAO.
		GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);
		// Unbind the VBO to prevent further modification.
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	/**
	 * Binds an index buffer to the current VAO for indexed drawing.
	 * 
	 * @param indices Array of indices for drawing the vertices.
	 */
	private void bindIndicesbuffer(int[] indices) {
		// Generate a new VBO ID for the index buffer.
		int vboID = GL15.glGenBuffers();
		// Store the VBO ID for cleanup purposes.
		vbos.add(vboID);
		// Bind the VBO to the element array buffer target.
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		// Convert the indices into an IntBuffer.
		IntBuffer buffer = storeDataInIntBuffer(indices);
		// Store the buffer data in the VBO with static draw usage.
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	/**
	 * Converts an array of integers into an IntBuffer to be used by OpenGL.
	 * 
	 * @param data The integer array to be converted into an IntBuffer.
	 * @return An IntBuffer containing the provided data.
	 */
	IntBuffer storeDataInIntBuffer(int[] data) {
		// Create a new IntBuffer with the same size as the data array.
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		// Put the data into the buffer.
		buffer.put(data);
		// Flip the buffer to prepare it for reading.
		buffer.flip();

		return buffer;
	}

	/**
	 * Converts an array of floats into a FloatBuffer to be used by OpenGL.
	 * 
	 * @param data The float array to be converted into a FloatBuffer.
	 * @return A FloatBuffer containing the provided data.
	 */
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		// Create a new FloatBuffer with the same size as the data array.
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		// Put the data into the buffer.
		buffer.put(data);
		// Flip the buffer to prepare it for reading.
		buffer.flip();

		return buffer;
	}

	/**
	 * Cleans up the created VAOs and VBOs by deleting them from OpenGL memory. This
	 * method should be called when the program is closing to free up resources.
	 */
	public void cleanUp() {
		// Delete all VAOs.
		vaos.forEach(GL30::glDeleteVertexArrays);

		// Delete all VBOs.
		vbos.forEach(GL15::glDeleteBuffers);
	}
}
