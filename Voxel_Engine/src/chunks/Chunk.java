package chunks;

import java.util.List;
import org.lwjgl.util.vector.Vector3f;
import cube.Block;

/**
 * The Chunck class represents a chunk of entities within the game world.
 * Each chunk is characterized by a list of entities (blocks) and an origin point,
 * representing its position in the world.
 */
public class Chunk {

    // List of blocks that make up this chunk.
    private List<Block> blocks;

    // The origin position of the chunk in 3D space, defined by a Vector3f (x, y, z).
    private Vector3f origin;

    /**
     * Constructs a Chunck with a specified list of blocks and an origin position.
     *
     * @param blocks List of Block objects representing the blocks in the chunk.
     * @param origin The origin position of the chunk in the game world.
     */
    public Chunk(List<Block> blocks, Vector3f origin) {
        this.blocks = blocks; // Initialize the list of blocks in this chunk.
        this.origin = origin; // Set the origin position of the chunk in the world.
    }

	public List<Block> getBlocks() {
		return blocks;
	}

	public Vector3f getOrigin() {
		return origin;
	}
}
