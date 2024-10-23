package fisics;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;
import entities.Camera;
import cube.Block;
import chunks.Chunk;
import chunks.ChunkMesh;

public class CollisionHandler {

    // Constants for player dimensions and collision margin
    private static final float PLAYER_HEIGHT = 2.0f; // The height of the player/camera
    private static final float PLAYER_WIDTH = 0.8f;  // The width of the player/camera
    private static final float COLLISION_MARGIN = 0.1f; // A small margin for collision detection accuracy

    /**
     * Checks if the camera (player) is colliding with any blocks in the nearby chunks.
     * 
     * @param camera The Camera object representing the player's position and state.
     * @param chunks The list of ChunkMesh objects representing the surrounding chunks.
     * @return true if there is a collision, false otherwise.
     */
    public static boolean checkCollision(Camera camera, List<ChunkMesh> chunks) {
        // Get the camera's current position
        Vector3f position = camera.getPosition();
        
        // Determine the chunk coordinates the player is in
        int chunkX = (int) Math.floor(position.x / 32);
        int chunkZ = (int) Math.floor(position.z / 32);
        
        // Create a thread-safe copy of the chunk list for iteration
        List<ChunkMesh> chunksCopy;
        synchronized(chunks) {
            chunksCopy = new ArrayList<>(chunks);
        }
        
        // Only check for collisions with nearby chunks (within 1 chunk distance)
        for (ChunkMesh chunkMesh : chunksCopy) {
            if (chunkMesh == null || chunkMesh.chunck == null) continue;
            
            Chunk chunk = chunkMesh.chunck;
            Vector3f chunkOrigin = chunk.getOrigin();
            
            // Only check chunks that are adjacent to the current chunk (within 1 chunk radius)
            if (Math.abs(chunkOrigin.x / 32 - chunkX) <= 1 && Math.abs(chunkOrigin.z / 32 - chunkZ) <= 1) {
                // Check for collisions within this chunk
                if (checkChunkCollision(position, chunk)) {
                    return true; // A collision was detected
                }
            }
        }
        return false; // No collision was detected
    }

    /**
     * Checks for a collision between the player and the blocks in the given chunk.
     * 
     * @param position The player's current position.
     * @param chunk The chunk to check for collisions.
     * @return true if there is a collision with any block, false otherwise.
     */
    private static boolean checkChunkCollision(Vector3f position, Chunk chunk) {
        if (chunk == null || chunk.getBlocks() == null) return false;
        
        List<Block> blocks = chunk.getBlocks();
        Vector3f chunkOrigin = chunk.getOrigin();
        
        // Create a thread-safe copy of the block list for iteration
        List<Block> blocksCopy;
        synchronized(blocks) {
            blocksCopy = new ArrayList<>(blocks);
        }
        
        // Iterate through the blocks in the chunk and check for nearby blocks
        for (Block block : blocksCopy) {
            if (block != null) {
                // Calculate the absolute position of the block in the world
                float blockX = chunkOrigin.x + block.getX();
                float blockY = block.getY();
                float blockZ = chunkOrigin.z + block.getZ();
                
                // Check if the player is within a reasonable distance from the block
                if (Math.abs(position.x - blockX) < 2 && 
                    Math.abs(position.y - blockY) < 3 && 
                    Math.abs(position.z - blockZ) < 2) {
                    
                    // Check for a collision using a bounding box
                    if (position.x + PLAYER_WIDTH / 2 > blockX - 0.5f && 
                        position.x - PLAYER_WIDTH / 2 < blockX + 0.5f &&
                        position.y < blockY + 1.0f && 
                        position.y + PLAYER_HEIGHT > blockY &&
                        position.z + PLAYER_WIDTH / 2 > blockZ - 0.5f && 
                        position.z - PLAYER_WIDTH / 2 < blockZ + 0.5f) {
                        return true; // Collision detected
                    }
                }
            }
        }
        return false; // No collision detected
    }

    /**
     * Handles collision resolution by resetting the camera's position to avoid overlapping with blocks.
     * 
     * @param camera The Camera object representing the player.
     * @param oldPosition The camera's previous position (before movement).
     * @param chunks The list of nearby ChunkMesh objects.
     */
    public static void handleCollision(Camera camera, Vector3f oldPosition, List<ChunkMesh> chunks) {
        // Get the camera's current position
        Vector3f currentPos = camera.getPosition();
        Vector3f newPosition = new Vector3f(currentPos);
        
        // Flags to mark if a collision occurred on any axis
        boolean collisionX = false, collisionY = false, collisionZ = false;
        
        // Test movement in the X axis
        newPosition.x = currentPos.x;
        camera.setPosition(newPosition); // Temporarily update the camera's position
        if (checkCollision(camera, chunks)) {
            collisionX = true; // Collision detected on the X axis
        } else {
            oldPosition.x = currentPos.x; // Allow movement on the X axis
        }
        
        // Test movement in the Y axis
        newPosition.y = currentPos.y;
        camera.setPosition(newPosition);
        if (checkCollision(camera, chunks)) {
            collisionY = true; // Collision detected on the Y axis
        } else {
            oldPosition.y = currentPos.y; // Allow movement on the Y axis
        }
        
        // Test movement in the Z axis
        newPosition.z = currentPos.z;
        camera.setPosition(newPosition);
        if (checkCollision(camera, chunks)) {
            collisionZ = true; // Collision detected on the Z axis
        } else {
            oldPosition.z = currentPos.z; // Allow movement on the Z axis
        }

        // Resolve collisions by resetting the camera's position on the axes where collisions occurred
        if (collisionX) {
            currentPos.x = oldPosition.x; // Block movement on the X axis
        }
        if (collisionY) {
            currentPos.y = oldPosition.y; // Block movement on the Y axis
        }
        if (collisionZ) {
            currentPos.z = oldPosition.z; // Block movement on the Z axis
        }

        // Set the camera's position to the final resolved position
        camera.setPosition(currentPos);
    }
}
