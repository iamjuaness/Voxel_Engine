package toolbox;

import org.lwjgl.util.vector.Matrix4f; // Importing the Matrix4f class for matrix operations
import org.lwjgl.util.vector.Vector3f; // Importing the Vector3f class for 3D vector operations

/**
 * The Maths class provides utility methods for mathematical operations related
 * to 3D graphics. It includes methods for creating transformation matrices that
 * can be used to transform objects in 3D space.
 */
public class Maths {

	/**
	 * Creates a transformation matrix that combines translation, rotation, and
	 * scaling. This matrix can be used to transform an object's vertices from
	 * object space to world space or camera space.
	 * 
	 * @param translation A Vector3f representing the translation (position) of the
	 *                    object.
	 * @param rotX        The rotation angle around the X-axis in radians.
	 * @param rotY        The rotation angle around the Y-axis in radians.
	 * @param rotZ        The rotation angle around the Z-axis in radians.
	 * @param scale       The uniform scale factor applied to the object.
	 * @return A Matrix4f representing the transformation matrix.
	 */
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rotX, float rotY, float rotZ,
			float scale) {
		// Initialize a new Matrix4f object
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity(); // Set the matrix to the identity matrix

		// Apply translation
		Matrix4f.translate(translation, matrix, matrix); // Translate the matrix by the given vector

		// Apply rotation around the X-axis in radians.
		Matrix4f.rotate((float) Math.toRadians(rotX), new Vector3f(1, 0, 0), matrix, matrix);
		// Apply rotation around the Y-axis in radians.
		Matrix4f.rotate((float) Math.toRadians(rotY), new Vector3f(0, 1, 0), matrix, matrix);
		// Apply rotation around the Z-axis in radians.
		Matrix4f.rotate((float) Math.toRadians(rotZ), new Vector3f(0, 0, 1), matrix, matrix);

		// Apply scaling
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix); // Scale the matrix by the given factor

		// Return the constructed transformation matrix
		return matrix;
	}
}