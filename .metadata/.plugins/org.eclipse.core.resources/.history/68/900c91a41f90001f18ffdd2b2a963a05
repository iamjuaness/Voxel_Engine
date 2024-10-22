package juancraft;

import java.util.List;
import org.lwjgl.util.vector.Vector3f;
import entities.Entity;

/**
 * The Chunck class represents a chunk of entities within the game world.
 * Each chunk is characterized by a list of entities (blocks) and an origin point,
 * representing its position in the world.
 */
public class Chunck {

    // List of entities that make up the blocks within this chunk.
    private List<Entity> blocks;

    // The origin position of the chunk in 3D space (x, y, z).
    private Vector3f origin;

    /**
     * Constructs a Chunck with a specified list of blocks and an origin position.
     *
     * @param blocks List of Entity objects representing the blocks in the chunk.
     * @param origin The origin position of the chunk in the game world.
     */
    public Chunck(List<Entity> blocks, Vector3f origin) {
        this.setBlocks(blocks); // Set the list of blocks.
        this.origin = origin; // Set the origin position of the chunk.
    }

    /**
     * Gets the list of blocks (entities) within this chunk.
     *
     * @return A list of Entity objects representing the blocks.
     */
    public List<Entity> getBlocks() {
        return blocks;
    }

    /**
     * Sets the list of blocks (entities) for this chunk.
     *
     * @param blocks A list of Entity objects representing the blocks.
     */
    public void setBlocks(List<Entity> blocks) {
        this.blocks = blocks;
    }

    /**
     * Gets the origin position of this chunk in the game world.
     *
     * @return A Vector3f representing the origin position (x, y, z) of the chunk.
     */
    public Vector3f getOrigin() {
        return origin;
    }
}
