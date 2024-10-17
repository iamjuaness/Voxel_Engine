package Models;

/**
 * The RawModel class represents a 3D model that has been loaded into OpenGL.
 * It stores the ID of the VAO (Vertex Array Object) that holds the model's vertex data,
 * as well as the count of vertices that make up the model.
 * 
 * This class is primarily used for storing and accessing model data during rendering.
 */
public class RawModel {

    // The ID of the VAO that stores the vertex data of this model.
    private int vaoID;
    
    // The number of vertices that make up the model.
    private int vertexCount;

    /**
     * Constructs a new RawModel with the specified VAO ID and vertex count.
     * 
     * @param vaoID The ID of the VAO that contains the model's vertex data.
     * @param vertexCount The total number of vertices in the model.
     */
    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    /**
     * Returns the ID of the VAO that contains the model's vertex data.
     * 
     * @return The VAO ID.
     */
    public int getVaoID() {
        return vaoID;
    }

    /**
     * Returns the total number of vertices that make up the model.
     * 
     * @return The vertex count.
     */
    public int getVertexCount() {
        return vertexCount;
    }
}
