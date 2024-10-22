package cube;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Vertex {

    // Member variables representing the position, normal, and texture coordinates (UV) of the vertex.
    public Vector3f positions, normals;  // 3D vectors representing the position and normal vector of the vertex
    public Vector2f uvs;                 // 2D vector representing the texture coordinates (UV mapping)

    // Constructor for the Vertex class.
    // It initializes a vertex with a given position, texture coordinates (UV), and normal vector.
    public Vertex(Vector3f positions, Vector2f uvs, Vector3f normals) {
        this.positions = positions;  // Assign the 3D position of the vertex
        this.normals = normals;      // Assign the 3D normal vector (used for lighting/shading calculations)
        this.uvs = uvs;              // Assign the 2D UV texture coordinates
    }
}
