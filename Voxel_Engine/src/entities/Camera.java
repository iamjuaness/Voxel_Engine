package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * The Camera class represents a camera in a 3D space, allowing for movement and rotation based on user input.
 * It uses keyboard input for forward and backward movement and mouse input for rotation.
 */
public class Camera {

    // The position of the camera in 3D space.
    private Vector3f position;
    
    // Rotation angles for the camera around the X, Y, and Z axes.
    private float rotX, rotY, rotZ;
    
    // Speed at which the camera moves.
    private float speed = 0.3f;
    
    // Speed at which the camera rotates.
    private float turnSpeed = 0.1f;

    /**
     * Constructor to create a Camera instance.
     * 
     * @param position The initial position of the camera in 3D space.
     * @param rotX The initial rotation angle around the X axis.
     * @param rotY The initial rotation angle around the Y axis.
     * @param rotZ The initial rotation angle around the Z axis (not used in this implementation).
     */
    public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }

    /**
     * Updates the camera's position and rotation based on user input.
     * The W and S keys (or UP and DOWN arrows) move the camera forward and backward.
     * The mouse movement updates the camera's rotation.
     */
    public void move() {
        // Variable to control movement direction
        float moveAt = 0;

        // Move the camera forward when W or UP key is pressed.
        if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            moveAt = -speed; // Move forward in the negative Z direction.
        }
        // Move the camera backward when S or DOWN key is pressed.
        else if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            moveAt = speed; // Move backward in the positive Z direction.
        }

        // Update rotation based on mouse movement.
        rotX += -Mouse.getDY() * turnSpeed; // Adjust rotation around X based on vertical mouse movement.
        rotY += Mouse.getDX() * turnSpeed;  // Adjust rotation around Y based on horizontal mouse movement.

        // Calculate directional movement based on rotation angles
        float dx = (float) -(moveAt * Math.sin(Math.toRadians(rotY)));
        float dy = (float) (moveAt * Math.sin(Math.toRadians(rotX)));
        float dz = (float) (moveAt * Math.cos(Math.toRadians(rotY)));

        // Update the camera's position
        position.x += dx;
        position.y += dy;
        position.z += dz;
    }

    /**
     * Gets the current position of the camera.
     * 
     * @return The position of the camera as a Vector3f.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Gets the current rotation around the X axis.
     * 
     * @return The rotation angle around the X axis.
     */
    public float getRotX() {
        return rotX;
    }

    /**
     * Gets the current rotation around the Y axis.
     * 
     * @return The rotation angle around the Y axis.
     */
    public float getRotY() {
        return rotY;
    }

    /**
     * Gets the current rotation around the Z axis.
     * 
     * @return The rotation angle around the Z axis (not used in this implementation).
     */
    public float getRotZ() {
        return rotZ;
    }
}
