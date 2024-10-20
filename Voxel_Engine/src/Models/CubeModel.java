package models;

/**
 * Represents a 3D cube model used for rendering in a graphics application.
 * This class defines the vertices, indices, and texture coordinates 
 * required to create and render a cube in 3D space.
 */
public class CubeModel {
    
    // Define vertices for the cube model.
    // Each vertex is represented by three float values: (x, y, z)
    public static float[] vertices = { 
        -0.5f, 0.5f, -0.5f,   // Vertex 0
        -0.5f, -0.5f, -0.5f,  // Vertex 1
        0.5f, -0.5f, -0.5f,   // Vertex 2
        0.5f, 0.5f, -0.5f,    // Vertex 3
        -0.5f, 0.5f, 0.5f,    // Vertex 4
        -0.5f, -0.5f, 0.5f,   // Vertex 5
        0.5f, -0.5f, 0.5f,    // Vertex 6
        0.5f, 0.5f, 0.5f,     // Vertex 7
        0.5f, 0.5f, -0.5f,    // Vertex 8
        0.5f, -0.5f, -0.5f,   // Vertex 9
        0.5f, -0.5f, 0.5f,    // Vertex 10
        0.5f, 0.5f, 0.5f,     // Vertex 11
        -0.5f, 0.5f, -0.5f,   // Vertex 12
        -0.5f, -0.5f, -0.5f,  // Vertex 13
        -0.5f, -0.5f, 0.5f,   // Vertex 14
        -0.5f, 0.5f, 0.5f,    // Vertex 15
        -0.5f, 0.5f, 0.5f,    // Vertex 16
        -0.5f, 0.5f, -0.5f,   // Vertex 17
        0.5f, 0.5f, -0.5f,    // Vertex 18
        0.5f, 0.5f, 0.5f,     // Vertex 19
        -0.5f, -0.5f, 0.5f,   // Vertex 20
        -0.5f, -0.5f, -0.5f,  // Vertex 21
        0.5f, -0.5f, -0.5f,   // Vertex 22
        0.5f, -0.5f, 0.5f     // Vertex 23
    };

    // Define indices for the cube's triangles.
    // Each triangle is defined by three vertex indices.
    public static int[] indices = { 
        0, 1, 3,    // Triangle 1
        3, 1, 2,    // Triangle 2
        4, 5, 7,    // Triangle 3
        7, 5, 6,    // Triangle 4
        8, 9, 11,   // Triangle 5
        11, 9, 10,  // Triangle 6
        12, 13, 15, // Triangle 7
        15, 13, 14, // Triangle 8
        16, 17, 19, // Triangle 9
        19, 17, 18, // Triangle 10
        20, 21, 23, // Triangle 11
        23, 21, 22  // Triangle 12
    };

    // Define texture coordinates (UV mapping) for the vertices.
    // Each UV coordinate corresponds to a vertex and is used for texture mapping.
    public static float[] uv = { 
        0, 0,
        0, 1,
        1, 1,
        1, 0,            
        0, 0,
        0, 1,
        1, 1,
        1, 0,            
        0, 0,
        0, 1,
        1, 1,
        1, 0,
        0, 0,
        0, 1,
        1, 1,
        1, 0,
        0, 0,
        0, 1,
        1, 1,
        1, 0,
        0, 0,
        0, 1,
        1, 1,
        1, 0
    };
}
