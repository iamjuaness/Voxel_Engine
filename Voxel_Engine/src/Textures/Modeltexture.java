package textures;

/**
 * The Modeltexture class represents a texture associated with a 3D model. It
 * stores the ID of the texture that can be used for rendering.
 */
public class Modeltexture {

	// The ID of the texture, used for binding the texture in OpenGL.
	int textureID;

	/**
	 * Constructor that initializes the Modeltexture with a given texture ID.
	 * 
	 * @param textureID The ID of the texture to be associated with this model.
	 */
	public Modeltexture(int textureID) {
		this.textureID = textureID;
	}

	/**
	 * Gets the ID of the texture associated with this model.
	 * 
	 * @return The ID of the texture.
	 */
	public int getTextureID() {
		return textureID;
	}
}
