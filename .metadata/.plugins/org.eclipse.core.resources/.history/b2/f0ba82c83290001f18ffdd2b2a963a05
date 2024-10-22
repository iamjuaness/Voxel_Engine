package models;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * The CubeModel class defines the vertices, indices, UV mappings, and normals for a 3D cube. 
 * It is structured to represent each face of the cube in 3D space, using vectors for vertex positions, 
 * texture coordinates (UVs), and normal vectors.
 */
public class CubeModel {

    // Vertex positions for the positive X face of the cube
    public static Vector3f[] PX_POS = {
        new Vector3f(0.5f, 0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, 0.5f),
        new Vector3f(0.5f, -0.5f, 0.5f),
        new Vector3f(0.5f, 0.5f, 0.5f),
        new Vector3f(0.5f, 0.5f, -0.5f)
    };

    // Vertex positions for the negative X face of the cube
    public static Vector3f[] NX_POS = {
        new Vector3f(-0.5f, 0.5f, -0.5f),
        new Vector3f(-0.5f, -0.5f, -0.5f),
        new Vector3f(-0.5f, -0.5f, 0.5f),
        new Vector3f(-0.5f, -0.5f, 0.5f),
        new Vector3f(-0.5f, 0.5f, 0.5f),
        new Vector3f(-0.5f, 0.5f, -0.5f)
    };

    // Vertex positions for the positive Y face of the cube (top face)
    public static Vector3f[] PY_POS = {
        new Vector3f(-0.5f, 0.5f, 0.5f),
        new Vector3f(-0.5f, 0.5f, -0.5f),
        new Vector3f(0.5f, 0.5f, -0.5f),
        new Vector3f(0.5f, 0.5f, -0.5f),
        new Vector3f(0.5f, 0.5f, 0.5f),
        new Vector3f(-0.5f, 0.5f, 0.5f)
    };

    // Vertex positions for the negative Y face of the cube (bottom face)
    public static Vector3f[] NY_POS = {
        new Vector3f(-0.5f, -0.5f, 0.5f),
        new Vector3f(-0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, 0.5f),
        new Vector3f(-0.5f, -0.5f, 0.5f)
    };

    // Vertex positions for the positive Z face of the cube
    public static Vector3f[] PZ_POS = {
        new Vector3f(-0.5f, 0.5f, 0.5f),
        new Vector3f(-0.5f, -0.5f, 0.5f),
        new Vector3f(0.5f, -0.5f, 0.5f),
        new Vector3f(0.5f, -0.5f, 0.5f),
        new Vector3f(0.5f, 0.5f, 0.5f),
        new Vector3f(-0.5f, 0.5f, 0.5f)
    };

    // Vertex positions for the negative Z face of the cube
    public static Vector3f[] NZ_POS = {
        new Vector3f(-0.5f, 0.5f, -0.5f),
        new Vector3f(-0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, -0.5f, -0.5f),
        new Vector3f(0.5f, 0.5f, -0.5f),
        new Vector3f(-0.5f, 0.5f, -0.5f)
    };

    // UV texture coordinates for mapping 2D textures to the cube's faces
    public static Vector2f[] UV = {
        new Vector2f(0.f, 0.f),
        new Vector2f(0.f, 1.f),
        new Vector2f(1.f, 1.f),
        new Vector2f(1.f, 1.f),
        new Vector2f(1.f, 0.f),
        new Vector2f(0.f, 0.f)
    };

    // Normal vectors for each vertex on the cube, all set to (0, 0, 0) for now
    public static Vector3f[] NORMALS = {
        new Vector3f(0.f, 0.f, 0.f),
        new Vector3f(0.f, 0.f, 0.f),
        new Vector3f(0.f, 0.f, 0.f),
        new Vector3f(0.f, 0.f, 0.f),
        new Vector3f(0.f, 0.f, 0.f),
        new Vector3f(0.f, 0.f, 0.f)
    };

    // List of all vertex positions in float array form, including all faces of the cube
    public static float[] vertices = {
        -0.5f, 0.5f, -0.5f,    // Front top-left
        -0.5f, -0.5f, -0.5f,   // Front bottom-left
        0.5f, -0.5f, -0.5f,    // Front bottom-right
        0.5f, 0.5f, -0.5f,     // Front top-right

        -0.5f, 0.5f, 0.5f,     // Back top-left
        -0.5f, -0.5f, 0.5f,    // Back bottom-left
        0.5f, -0.5f, 0.5f,     // Back bottom-right
        0.5f, 0.5f, 0.5f,      // Back top-right

        0.5f, 0.5f, -0.5f,     // Right top-back
        0.5f, -0.5f, -0.5f,    // Right bottom-back
        0.5f, -0.5f, 0.5f,     // Right bottom-front
        0.5f, 0.5f, 0.5f,      // Right top-front

        -0.5f, 0.5f, -0.5f,    // Left top-back
        -0.5f, -0.5f, -0.5f,   // Left bottom-back
        -0.5f, -0.5f, 0.5f,    // Left bottom-front
        -0.5f, 0.5f, 0.5f,     // Left top-front

        -0.5f, 0.5f, 0.5f,     // Top left-front
        -0.5f, 0.5f, -0.5f,    // Top left-back
        0.5f, 0.5f, -0.5f,     // Top right-back
        0.5f, 0.5f, 0.5f,      // Top right-front

        -0.5f, -0.5f, 0.5f,    // Bottom left-front
        -0.5f, -0.5f, -0.5f,   // Bottom left-back
        0.5f, -0.5f, -0.5f,    // Bottom right-back
        0.5f, -0.5f, 0.5f      // Bottom right-front
    };
	
    // Indices defining how vertices form triangles on each face of the cube.
    // Each face consists of two triangles, and each triangle is defined by three indices.
    // The indices refer to the positions in the vertices array.
    // There are 12 triangles in total (2 per face), for a total of 36 indices (6 faces * 2 triangles * 3 vertices).
    public static int[] indices = {
        0, 1, 3,    // Front face first triangle (top-left to bottom-right diagonal)
        3, 1, 2,    // Front face second triangle (bottom-left to top-right diagonal)
        
        4, 5, 7,    // Back face first triangle
        7, 5, 6,    // Back face second triangle
        
        8, 9, 11,   // Right face first triangle
        11, 9, 10,  // Right face second triangle
        
        12, 13, 15, // Left face first triangle
        15, 13, 14, // Left face second triangle
        
        16, 17, 19, // Top face first triangle
        19, 17, 18, // Top face second triangle
        
        20, 21, 23, // Bottom face first triangle
        23, 21, 22  // Bottom face second triangle
    };

    // Texture coordinates (UV mapping) for the cube's faces.
    // These map 2D texture images to each face of the cube.
    // Each face has 4 UV coordinates corresponding to the 4 vertices of the face.
    // This array is structured as [U, V] pairs, where U and V represent the texture's horizontal and vertical axes.
    public static float[] uv = {
        0, 0,   // Front face bottom-left corner
        0, 1,   // Front face top-left corner
        1, 1,   // Front face top-right corner
        1, 0,   // Front face bottom-right corner

        0, 0,   // Back face bottom-left corner
        0, 1,   // Back face top-left corner
        1, 1,   // Back face top-right corner
        1, 0,   // Back face bottom-right corner

        0, 0,   // Right face bottom-left corner
        0, 1,   // Right face top-left corner
        1, 1,   // Right face top-right corner
        1, 0,   // Right face bottom-right corner

        0, 0,   // Left face bottom-left corner
        0, 1,   // Left face top-left corner
        1, 1,   // Left face top-right corner
        1, 0,   // Left face bottom-right corner

        0, 0,   // Top face bottom-left corner
        0, 1,   // Top face top-left corner
        1, 1,   // Top face top-right corner
        1, 0,   // Top face bottom-right corner

        0, 0,   // Bottom face bottom-left corner
        0, 1,   // Bottom face top-left corner
        1, 1,   // Bottom face top-right corner
        1, 0    // Bottom face bottom-right corner
    };

}
