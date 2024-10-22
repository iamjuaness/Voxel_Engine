package cube;

/**
 * The Block class represents a single block in a 3D grid, typically used in terrain generation
 * for games or simulations. Each block has a position in 3D space and a type that defines its characteristics.
 */
public class Block {
    
    // Public member variables representing the position of the Block in a 3D grid.
    public int x; // The X coordinate of the Block in 3D space.
    public int y; // The Y coordinate of the Block in 3D space.
    public int z; // The Z coordinate of the Block in 3D space.

    // Static constants defining different types of blocks.
    public static int GRASS = 0;     // representing a grass block type.
    public static int DIRT = 1;      // representing a dirt block type.
    public static int STONE = 2;     // representing a stone block type.
    public static int TREEBARK = 3;  // representing a tree bark block type.
    public static int TREELEAF = 4;  // representing a tree leaf block type.

    // The type of the Block, indicating what kind of material it represents.
    public int type; // The type of the Block (e.g., DIRT, GRASS).

    /**
     * Constructor for the Block class.
     * Initializes a Block object with specified X, Y, Z coordinates and a block type.
     *
     * @param x The X coordinate of the Block in 3D space.
     * @param y The Y coordinate of the Block in 3D space.
     * @param z The Z coordinate of the Block in 3D space.
     * @param type The type of the Block (must be one of the predefined types: DIRT, GRASS, STONE, TREEBARK, TREELEAF).
     */
    public Block(int x, int y, int z, int type) {
        this.x = x;      // Set the X coordinate of the Block.
        this.y = y;      // Set the Y coordinate of the Block.
        this.z = z;      // Set the Z coordinate of the Block.
        this.type = type; // Set the type of the Block (e.g., DIRT, GRASS).
    }
}
