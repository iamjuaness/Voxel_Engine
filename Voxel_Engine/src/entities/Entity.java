package entities;

import org.lwjgl.util.vector.Vector3f; // Importing the Vector3f class for 3D vector operations
import models.TexturedModel; // Importing the TexturedModel class representing the model with textures

/**
 * The Entity class represents an object in a 3D scene. It includes attributes
 * for the model, position, rotation, and scale, allowing for the representation
 * and manipulation of 3D objects.
 */
public class Entity {

	// The textured model associated with this entity
	private TexturedModel model;
	// The position of the entity in 3D space
	private Vector3f position;
	// The rotation of the entity around the X, Y, and Z axes
	private float rotX, rotY, rotZ;
	// The scale factor for the entity
	private float scale;

	/**
	 * Constructor for the Entity class. Initializes the entity's model, position,
	 * rotation, and scale.
	 * 
	 * @param model    The textured model representing the entity.
	 * @param position A Vector3f representing the entity's position in 3D space.
	 * @param rotX     The rotation angle around the X-axis in degrees.
	 * @param rotY     The rotation angle around the Y-axis in degrees.
	 * @param rotZ     The rotation angle around the Z-axis in degrees.
	 * @param scale    The uniform scale factor for the entity.
	 */
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	/**
	 * Increases the entity's position in 3D space by the specified amounts.
	 * This method modifies the entity's current position by adding the provided
	 * values to its existing x, y, and z coordinates.
	 * 
	 * @param dx The change in the x-coordinate (horizontal movement).
	 * @param dy The change in the y-coordinate (vertical movement).
	 * @param dz The change in the z-coordinate (depth movement).
	 */
	public void increasePosition(float dx, float dy, float dz) {
	    // Update the x, y, and z coordinates of the position vector
	    this.position.x += dx; // Increase the x position by dx
	    this.position.y += dy; // Increase the y position by dy
	    this.position.z += dz; // Increase the z position by dz
	}
	
	/**
	 * Increases the rotation of the entity around the x, y, and z axes by the
	 * specified amounts. This method modifies the entity's current rotation
	 * angles directly.
	 *
	 * @param dx The change in the rotation angle around the x-axis (in degrees).
	 * @param dy The change in the rotation angle around the y-axis (in degrees).
	 * @param dz The change in the rotation angle around the z-axis (in degrees).
	 */
	public void increaseRotation(float dx, float dy, float dz) {
	    // Update the rotation angles by adding the respective changes
	    this.rotX += dx; // Increase the rotation around the x-axis
	    this.rotY += dy; // Increase the rotation around the y-axis
	    this.rotZ += dz; // Increase the rotation around the z-axis
	}

	/**
	 * Increases the scale of the entity by a specified factor. This method modifies
	 * the entity's current scale value directly.
	 *
	 * @param scale The factor by which to increase the entity's scale. This value
	 *              is typically greater than 1.0 to increase the size or between
	 *              0.0 and 1.0 to decrease the size.
	 */
	public void increaseScale(float scale) {
	    this.scale += scale; // Increase the scale by the specified factor
	}



	// Getters and setters for the attributes

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
