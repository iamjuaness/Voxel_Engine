package RenderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import Models.RawModel;

/**
 * The EntityRenderer class is responsible for rendering 3D entities in the
 * game. It takes a RawModel, binds its VAO (Vertex Array Object), and issues
 * the appropriate OpenGL draw calls to render the model.
 */
public class EntityRenderer {

	/**
	 * Renders the specified RawModel by binding its VAO and drawing its vertices.
	 * This method is static, allowing it to be called without creating an instance
	 * of the EntityRenderer class.
	 * 
	 * @param model The RawModel to be rendered, which contains the VAO ID and
	 *              vertex count.
	 */
	public static void render(RawModel model) {
		// Bind the VAO of the model to prepare it for rendering.
		GL30.glBindVertexArray(model.getVaoID());

		// Enable the vertex attribute array at index 0 for the shader to access the
		// vertex data.
		GL20.glEnableVertexAttribArray(0);

		// Draw the vertices of the model as triangles. The drawing starts from vertex 0
		// and uses the vertex count from the model.
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

		// Disable the vertex attribute array to clean up after rendering.
		GL20.glDisableVertexAttribArray(0);

		// Unbind the VAO to prevent further modifications.
		GL30.glBindVertexArray(0);
	}
}
