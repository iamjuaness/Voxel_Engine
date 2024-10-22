package entities;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Pointer {
    private static float size = 15.0f;
    private static float[] color = {1.0f, 1.0f, 1.0f}; // Color RGB
    
    private static int vaoID;
    private static int vboID;
    private static int shaderProgram;
    
    static {
        initPointer();
    }
    
    private static void initPointer() {
        // Crear y vincular VAO
        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        
        // Coordenadas normalizadas para la cruz
        float[] vertices = new float[] {
            // Línea horizontal
            -1.0f, 0.0f,
             1.0f, 0.0f,
            // Línea vertical
             0.0f, -1.0f,
             0.0f,  1.0f
        };
        
        // Crear y llenar el buffer de vértices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.flip();
        
        // Crear y llenar el VBO
        vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
        
        // Configurar el atributo de vértices
        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
        
        // Desvincula el VBO y VAO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        
        // Crear y compilar vertex shader
        int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, 
            "#version 120\n" +
            "attribute vec2 position;\n" +
            "uniform float size;\n" +
            "void main() {\n" +
            "    vec2 pos = position * size;\n" +
            "    gl_Position = vec4(pos, 0.0, 1.0);\n" +
            "}"
        );
        GL20.glCompileShader(vertexShader);
        
        // Crear y compilar fragment shader
        int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader,
            "#version 120\n" +
            "uniform vec3 color;\n" +
            "void main() {\n" +
            "    gl_FragColor = vec4(color, 1.0);\n" +
            "}"
        );
        GL20.glCompileShader(fragmentShader);
        
        // Crear y vincular el programa shader
        shaderProgram = GL20.glCreateProgram();
        GL20.glAttachShader(shaderProgram, vertexShader);
        GL20.glAttachShader(shaderProgram, fragmentShader);
        GL20.glBindAttribLocation(shaderProgram, 0, "position");
        GL20.glLinkProgram(shaderProgram);
        
        // Verificar si la compilación fue exitosa
        if (GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println("Vertex shader error: " + GL20.glGetShaderInfoLog(vertexShader, 1024));
        }
        
        if (GL20.glGetShaderi(fragmentShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println("Fragment shader error: " + GL20.glGetShaderInfoLog(fragmentShader, 1024));
        }
        
        // Limpiar shaders
        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);
    }
    
    public static void renderPointer() {
        // Guardar estados
        boolean depthTest = GL11.glGetBoolean(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        
        // Usar shader program
        GL20.glUseProgram(shaderProgram);
        
        // Establecer uniforms
        int sizeLoc = GL20.glGetUniformLocation(shaderProgram, "size");
        GL20.glUniform1f(sizeLoc, size / Display.getWidth() * 2.0f);
        
        int colorLoc = GL20.glGetUniformLocation(shaderProgram, "color");
        GL20.glUniform3f(colorLoc, color[0], color[1], color[2]);
        
        // Renderizar el puntero
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        
        GL11.glDrawArrays(GL11.GL_LINES, 0, 4);
        
        // Limpiar estado
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        GL20.glUseProgram(0);
        
        // Restaurar estados
        if (depthTest) GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
    public static void cleanup() {
        GL20.glDeleteProgram(shaderProgram);
        GL15.glDeleteBuffers(vboID);
        GL30.glDeleteVertexArrays(vaoID);
    }
    
    public static void setSize(float newSize) {
        size = newSize;
    }
    
    public static void setColor(float r, float g, float b) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
    }
}