package chunks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;
import cube.Block;
import juancraft.MainGameLoop;
import toolbox.PerlinNoiseGenerator;

/**
 * The Chunk class represents a section of the game world containing a collection of blocks.
 * Each chunk is defined by its list of blocks and an origin point that indicates its position in the 3D space.
 */
public class Chunk {

    // List of blocks that make up this chunk.
    private List<Block> blocks;

    // The origin position of the chunk in 3D space, represented as a Vector3f (x, y, z).
    private Vector3f origin;
    
    // Random instance for generating random values.
    static Random random = new Random();
    
    // Perlin noise generator for generating heights based on noise.
    static PerlinNoiseGenerator generator = new PerlinNoiseGenerator(random.nextInt(10), random.nextInt(10), random.nextInt(100), random.nextInt(50));

    // Flag to indicate if the chunk at the camera position is ready.
    public static boolean isChunkAtCameraReady = false;

    /**
     * Constructs a Chunk with a specified list of blocks and an origin position.
     *
     * @param blocks List of Block objects representing the blocks in the chunk.
     * @param origin The origin position of the chunk in the game world.
     */
    public Chunk(List<Block> blocks, Vector3f origin) {
        this.blocks = blocks; // Initialize the list of blocks in this chunk.
        this.origin = origin; // Set the origin position of the chunk in the world.
    }

    /**
     * Returns the list of blocks that make up this chunk.
     *
     * @return List of Block objects.
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * Returns the origin position of this chunk.
     *
     * @return The origin as a Vector3f.
     */
    public Vector3f getOrigin() {
        return origin;
    }
    
    /**
     * Retrieves the block at the given local coordinates within the chunk.
     *
     * @param x Local X coordinate (0 to 31).
     * @param y Local Y coordinate (depends on the height).
     * @param z Local Z coordinate (0 to 31).
     * @return The block at the specified local coordinates, or null if no block exists there.
     */
    public Block getBlockAt(int x, int y, int z) {
        // Iterate through the list of blocks and find the block at the given coordinates.
        for (Block block : blocks) {
            if (block.getX() == x && block.getY() == y && block.getZ() == z) {
                return block; // Return the found block.
            }
        }
        return null; // Return null if no block exists at those coordinates.
    }
    
    /**
     * Generates a chunk at the camera's current position.
     *
     * @param camPos The current position of the camera in the world.
     * @param usedPos List of positions that have already been used to prevent duplicate generation.
     */
    public static void generateChunkAtCameraPosition(Vector3f camPos, List<Vector3f> usedPos) {
        // Calculate the chunk coordinates based on the camera position.
        int camChunkX = (int) camPos.x / 32; // Calculate X chunk position.
        int camChunkZ = (int) camPos.z / 32; // Calculate Z chunk position.

        // Calculate the center of the chunk.
        float chunkCenterX = (camChunkX * 32) + 16; // Center of the chunk in X.
        float chunkCenterZ = (camChunkZ * 32) + 16; // Center of the chunk in Z.

        // Adjust camera position to the center of the chunk.
        camPos.x = chunkCenterX;
        camPos.z = chunkCenterZ;
        
        // Check if the camera is below the height of the ground.
        if (camPos.y < 0) { // Assuming ground level is 0.
            camPos.y = 1.0f; // Adjust the camera to a minimum height.
        }

        // Generate the chunk at the camera's current position.
        generateChunkAt(camChunkX, camChunkZ, usedPos);

        // Check if the chunk has blocks at the camera's position.
        if (isChunkAtPosition(camChunkX, camChunkZ)) {
            // Adjust the camera's position if it's inside a block.
            Block blockBelow = getBlockAtPosition(camPos.x, camPos.y - 1, camPos.z);
            if (blockBelow != null) {
                camPos.y = blockBelow.getY() + 1; // Adjust the camera to be above the block.
            }
        }

        // Mark the chunk as ready for rendering.
        isChunkAtCameraReady = true;
    }
    
    /**
     * Checks if a chunk exists at the specified coordinates.
     *
     * @param chunkX The X coordinate of the chunk.
     * @param chunkZ The Z coordinate of the chunk.
     * @return true if the chunk exists, false otherwise.
     */
    private static boolean isChunkAtPosition(int chunkX, int chunkZ) {
        // Iterate through the list of chunks.
        for (ChunkMesh chunkMesh : MainGameLoop.chunks) {
            if (chunkMesh != null) {
                // Get the origin of the chunk.
                Vector3f chunkOrigin = chunkMesh.chunck.getOrigin();
                // Check if the coordinates match.
                if ((int)(chunkOrigin.x / 32) == chunkX && (int)(chunkOrigin.z / 32) == chunkZ) {
                    return true; // The chunk exists.
                }
            }
        }
        return false; // No matching chunk found.
    }

    /**
     * Retrieves a block at the specified world position.
     *
     * @param x World X coordinate.
     * @param y World Y coordinate.
     * @param z World Z coordinate.
     * @return The block at the specified position, or null if no block exists there.
     */
    private static Block getBlockAtPosition(float x, float y, float z) {
        // Calculate chunk coordinates based on the world position.
        int chunkX = (int) (x / 32); // Assuming each chunk is 32x32.
        int chunkZ = (int) (z / 32);

        // Retrieve the corresponding chunk using the calculated coordinates.
        Chunk chunk = getChunkAt(chunkX, chunkZ);
        if (chunk != null) {
            // Calculate the local block position within the chunk.
            int localX = (int) (x % 32); // Local position in X.
            int localY = (int) y;        // Height, which is an integer value.
            int localZ = (int) (z % 32); // Local position in Z.

            // Return the block at the local position.
            return chunk.getBlockAt(localX, localY, localZ); // Method to get the block within the chunk.
        }
        return null; // Return null if no matching chunk is found.
    }
    
    /**
     * Retrieves a chunk at the specified chunk coordinates.
     *
     * @param chunkX The X coordinate of the chunk.
     * @param chunkZ The Z coordinate of the chunk.
     * @return The chunk at the specified coordinates, or null if no chunk is found.
     */
    public static Chunk getChunkAt(int chunkX, int chunkZ) {
        // Iterate through the list of chunks.
        for (ChunkMesh chunkMesh : MainGameLoop.chunks) {
            if (chunkMesh != null) {
                Vector3f chunkOrigin = chunkMesh.chunck.getOrigin();
                // Check if the chunk coordinates match the requested coordinates.
                if ((int)(chunkOrigin.x / 32) == chunkX && (int)(chunkOrigin.z / 32) == chunkZ) {
                    return chunkMesh.chunck; // Return the matching chunk.
                }
            }
        }
        return null; // Return null if no matching chunk is found.
    }

    /**
     * Generates a chunk at the specified coordinates and populates it with blocks.
     *
     * @param x The X coordinate of the chunk.
     * @param z The Z coordinate of the chunk.
     * @param usedPos List of positions that have already been used to avoid duplicate generation.
     */
    public static void generateChunkAt(int x, int z, List<Vector3f> usedPos) {
        // Create a list of blocks for the current chunk.
        List<Block> blocks = new ArrayList<Block>();
        
        // Create a grid of 32x32 blocks for the chunk.
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                // Generate height using Perlin noise or a similar generator.
                blocks.add(new Block(i, (int) generator.generateHeight(i + (x * 32), j + (z * 32)), j, Block.GRASS));
            }
        }
        
        // Create the chunk at the calculated position.
        Chunk chunk = new Chunk(blocks, new Vector3f(x * 32, 0, z * 32));
        // Create the mesh for the chunk from the blocks.
        ChunkMesh mesh = new ChunkMesh(chunk);
        
        // Add the generated chunk to the list of chunks.
        MainGameLoop.chunks.add(mesh);
        
        // Mark the position as used to prevent generating the same chunk twice.
        usedPos.add(new Vector3f(x * 32, 0, z * 32));
    }
}
