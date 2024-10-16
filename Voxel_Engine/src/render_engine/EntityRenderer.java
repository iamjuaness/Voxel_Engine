package render_engine;

import org.lwjgl.opengl.GL11; // OpenGL functions for 2D rendering
import org.lwjgl.opengl.GL13; // OpenGL functions for managing textures
import org.lwjgl.opengl.GL20; // OpenGL functions for shader management
import org.lwjgl.opengl.GL30; // OpenGL functions for managing vertex array objects
import org.lwjgl.util.vector.Matrix4f; // For matrix manipulation

import entities.Entity; // Entity class representing 3D objects
import shaders.StaticShader; // Class representing the shader used for rendering
import toolbox.Maths; // Utility class for mathematical operations

/**
 * The EntityRenderer class is responsible for rendering 3D entities in the
 * game. It takes a TexturedModel, binds its VAO (Vertex Array Object), binds
 * the texture, and issues the appropriate OpenGL draw calls to render the
 * model.
 */
public class EntityRenderer {

    /**
     * Renders the specified TexturedModel by binding its VAO, enabling the vertex
     * attributes, and drawing the model using its texture. This method is static,
     * allowing it to be called without creating an instance of the EntityRenderer
     * class.
     * 
     * @param entity The Entity to be rendered, which contains the model's
     *               VAO ID, vertex count, and texture ID.
     * @param shader The StaticShader used for rendering the model, allowing
     *               transformation and texture application.
     */
    public static void render(Entity entity, StaticShader shader) {
        // Bind the VAO of the model to prepare it for rendering.
        GL30.glBindVertexArray(entity.getModel().getModel().getVaoID());

        // Enable the vertex attribute array at index 0 for the shader to access the
        // vertex position data.
        GL20.glEnableVertexAttribArray(0);

        // Enable the vertex attribute array at index 1 for the shader to access the
        // texture coordinate data.
        GL20.glEnableVertexAttribArray(1);
        
        // Create the transformation matrix based on the entity's position, rotation,
        // and scale, and load it into the shader.
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(
            entity.getPosition(),
            entity.getRotX(),
            entity.getRotY(),
            entity.getRotZ(),
            entity.getScale()
        );
        shader.loadTransformationMatrix(transformationMatrix);

        // Activate the texture unit 0 (the first texture unit).
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        // Bind the texture associated with the model to the active texture unit.
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTexture().getTextureID());

        // Draw the vertices of the model as triangles. It uses the index buffer for
        // drawing with the vertex count from the model, starting from index 0.
        GL11.glDrawElements(
            GL11.GL_TRIANGLES,
            entity.getModel().getModel().getVertexCount(),
            GL11.GL_UNSIGNED_INT,
            0
        );

        // Disable the vertex attribute array for position data after rendering.
        GL20.glDisableVertexAttribArray(0);

        // Disable the vertex attribute array for texture coordinates after rendering.
        GL20.glDisableVertexAttribArray(1);

        // Unbind the VAO to prevent any unintended modifications.
        GL30.glBindVertexArray(0);
    }
}
