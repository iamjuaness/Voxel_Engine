package models;

/**
 * Represents a 3D cube model used for rendering in a graphics application.
 * This class defines the vertices, indices, and texture coordinates 
 * required to create and render a cube in 3D space.
 */
public class AtlasCubeModel {
    
    // Define vertices for the cube model.
    // Each vertex is represented by three float values: (x, y, z).
    // Vertices are defined for all corners of the cube to represent its 3D structure.
    public static float[] vertices = { 
        -0.5f, 0.5f, -0.5f,   // Vertex 0: Top-left-front
        -0.5f, -0.5f, -0.5f,  // Vertex 1: Bottom-left-front
        0.5f, -0.5f, -0.5f,   // Vertex 2: Bottom-right-front
        0.5f, 0.5f, -0.5f,    // Vertex 3: Top-right-front

        -0.5f, 0.5f, 0.5f,    // Vertex 4: Top-left-back
        -0.5f, -0.5f, 0.5f,   // Vertex 5: Bottom-left-back
        0.5f, -0.5f, 0.5f,    // Vertex 6: Bottom-right-back
        0.5f, 0.5f, 0.5f,     // Vertex 7: Top-right-back

        0.5f, 0.5f, -0.5f,    // Vertex 8: Top-right-front
        0.5f, -0.5f, -0.5f,   // Vertex 9: Bottom-right-front
        0.5f, -0.5f, 0.5f,    // Vertex 10: Bottom-right-back
        0.5f, 0.5f, 0.5f,     // Vertex 11: Top-right-back

        -0.5f, 0.5f, -0.5f,   // Vertex 12: Top-left-front
        -0.5f, -0.5f, -0.5f,  // Vertex 13: Bottom-left-front
        -0.5f, -0.5f, 0.5f,   // Vertex 14: Bottom-left-back
        -0.5f, 0.5f, 0.5f,    // Vertex 15: Top-left-back

        -0.5f, 0.5f, 0.5f,    // Vertex 16: Top-left-back
        -0.5f, 0.5f, -0.5f,   // Vertex 17: Top-left-front
        0.5f, 0.5f, -0.5f,    // Vertex 18: Top-right-front
        0.5f, 0.5f, 0.5f,     // Vertex 19: Top-right-back

        -0.5f, -0.5f, 0.5f,   // Vertex 20: Bottom-left-back
        -0.5f, -0.5f, -0.5f,  // Vertex 21: Bottom-left-front
        0.5f, -0.5f, -0.5f,   // Vertex 22: Bottom-right-front
        0.5f, -0.5f, 0.5f     // Vertex 23: Bottom-right-back
    };

    // Define indices for the cube's triangles.
    // Each triangle is defined by three vertex indices that form its corners.
    // Indices help to draw the faces of the cube using the defined vertices.
    public static int[] indices = { 
        0, 1, 3,    // Triangle 1: Front face
        3, 1, 2,    // Triangle 2: Front face
        
        4, 5, 7,    // Triangle 3: Back face
        7, 5, 6,    // Triangle 4: Back face
        
        8, 9, 11,   // Triangle 5: Right face
        11, 9, 10,  // Triangle 6: Right face
        
        12, 13, 15, // Triangle 7: Left face
        15, 13, 14, // Triangle 8: Left face
        
        16, 17, 19, // Triangle 9: Top face
        19, 17, 18, // Triangle 10: Top face
        
        20, 21, 23, // Triangle 11: Bottom face
        23, 21, 22  // Triangle 12: Bottom face
    };

    // Define texture coordinates (UV mapping) for the vertices.
    // Each UV coordinate corresponds to a vertex and is used for mapping a texture onto the cube.
    // UV values range from 0 to 1, where (0,0) is the bottom-left and (1,1) is the top-right of the texture.
    public static float[] uv = { 
        1.01f / 3f, 1.01f / 3f, // UV for Vertex 0
        1.01f / 3f, 1.99f / 3f, // UV for Vertex 1
        1.99f / 3f, 1.99f / 3f, // UV for Vertex 2
        1.99f / 3f, 1.01f / 3f, // UV for Vertex 3

        1.01f / 3f, 1.01f / 3f, // UV for Vertex 4
        1.01f / 3f, 1.99f / 3f, // UV for Vertex 5
        1.99f / 3f, 1.99f / 3f, // UV for Vertex 6
        1.99f / 3f, 1.01f / 3f, // UV for Vertex 7

        1.01f / 3f, 1.01f / 3f, // UV for Vertex 8
        1.01f / 3f, 1.99f / 3f, // UV for Vertex 9
        1.99f / 3f, 1.99f / 3f, // UV for Vertex 10
        1.99f / 3f, 1.01f / 3f, // UV for Vertex 11

        1.01f / 3f, 1.01f / 3f, // UV for Vertex 12
        1.01f / 3f, 1.99f / 3f, // UV for Vertex 13
        1.99f / 3f, 1.99f / 3f, // UV for Vertex 14
        1.99f / 3f, 1.01f / 3f, // UV for Vertex 15

        1.01f / 3f, 2.01f / 3f, // UV for Vertex 16
        1.01f / 3f, 0.99f,      // UV for Vertex 17
        1.99f / 3f, 0.99f,      // UV for Vertex 18
        1.99f / 3f, 2.01f / 3f, // UV for Vertex 19

        0.01f, 1.01f / 3f,      // UV for Vertex 20
        0.01f, 1.99f / 3f,      // UV for Vertex 21
        0.99f / 3f, 1.99f / 3f, // UV for Vertex 22
        0.99f / 3f, 1.01f / 3f  // UV for Vertex 23
    };
}
