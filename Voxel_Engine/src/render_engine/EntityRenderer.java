package render_engine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11; // OpenGL functions for 2D rendering
import org.lwjgl.opengl.GL13; // OpenGL functions for managing textures
import org.lwjgl.opengl.GL20; // OpenGL functions for shader management
import org.lwjgl.opengl.GL30; // OpenGL functions for managing vertex array objects
import org.lwjgl.util.vector.Matrix4f; // For matrix manipulation

import entities.Entity; // Entity class representing 3D objects
import models.TexturedModel; // Class representing textured models
import shaders.StaticShader; // Class representing the shader used for rendering
import toolbox.Maths; // Utility class for mathematical operations

/**
 * The EntityRenderer class is responsible for rendering 3D entities in the
 * game. It takes a TexturedModel, binds its VAO (Vertex Array Object), binds
 * the texture, and issues the appropriate OpenGL draw calls to render the
 * model.
 */
public class EntityRenderer {
	
	static StaticShader shader = new StaticShader(); // Static instance of the shader used for rendering
    
    /**
     * Renders the 3D entities grouped by their textured models.
     * 
     * @param entities A map where the keys are textured models and the values are lists of entities
     *                 associated with each model. This allows batch rendering of entities that share
     *                 the same texture.
     */
    public void render(Map<TexturedModel, List<Entity>> entities) {
    	
    	// Iterate over each textured model in the provided map.
    	for (TexturedModel model : entities.keySet()) {
    		
            // Bind the VAO (Vertex Array Object) of the model to prepare it for rendering.
            GL30.glBindVertexArray(model.getModel().getVaoID());

            // Enable the vertex attribute array at index 0 for the shader to access the
            // vertex position data.
            GL20.glEnableVertexAttribArray(0);

            // Enable the vertex attribute array at index 1 for the shader to access the
            // texture coordinate data.
            GL20.glEnableVertexAttribArray(1);
            
            // Activate texture unit 0 (the first texture unit).
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            // Bind the texture associated with the model to the active texture unit.
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
            
            List<Entity> batch = entities.get(model); // Retrieve the list of entities for the current model.
            
            // Render each entity in the batch.
            for (Entity entity : batch) {
            	
                // Create the transformation matrix based on the entity's position, rotation,
                // and scale, and load it into the shader.
                Matrix4f transformationMatrix = Maths.createTransformationMatrix(
                    entity.getPosition(), // Position of the entity
                    entity.getRotX(), // Rotation around the X-axis
                    entity.getRotY(), // Rotation around the Y-axis
                    entity.getRotZ(), // Rotation around the Z-axis
                    entity.getScale() // Scale of the entity
                );
                shader.loadTransformationMatrix(transformationMatrix); // Load the transformation matrix into the shader.
                
                // Draw the vertices of the model as triangles. It uses the index buffer for
                // drawing with the vertex count from the model, starting from index 0.
                GL11.glDrawElements(
                    GL11.GL_TRIANGLES, // Drawing mode (triangles)
                    model.getModel().getVertexCount(), // Number of vertices to draw
                    GL11.GL_UNSIGNED_INT, // Type of the indices
                    0 // Offset in the index buffer
                );            	
            }
            
            // Disable the vertex attribute array for position data after rendering.
            GL20.glDisableVertexAttribArray(0);

            // Disable the vertex attribute array for texture coordinates after rendering.
            GL20.glDisableVertexAttribArray(1);

            // Unbind the VAO to prevent any unintended modifications.
            GL30.glBindVertexArray(0);
    	}
    }
}
