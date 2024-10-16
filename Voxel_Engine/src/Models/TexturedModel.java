package models;

import textures.Modeltexture;

/**
 * The TexturedModel class represents a 3D model that has a texture applied to
 * it. It combines a raw 3D model with its corresponding texture for rendering.
 */
public class TexturedModel {

	// The raw 3D model data (vertices, indices, etc.).
	RawModel model;

	// The texture associated with the 3D model.
	Modeltexture texture;

	/**
	 * Constructs a TexturedModel with a given raw model and texture.
	 * 
	 * @param model   The raw model containing the 3D vertex data.
	 * @param texture The texture to be applied to the model.
	 */
	public TexturedModel(RawModel model, Modeltexture texture) {
		this.model = model;
		this.texture = texture;
	}

	/**
	 * Returns the raw model associated with this textured model.
	 * 
	 * @return The raw model.
	 */
	public RawModel getModel() {
		return model;
	}

	/**
	 * Returns the texture associated with this textured model.
	 * 
	 * @return The texture.
	 */
	public Modeltexture getTexture() {
		return texture;
	}
}
