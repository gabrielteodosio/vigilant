package dev.dacoder.engine.utils;

import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {

    private ShaderUtils() {
    }

    /**
     * Reads from a file the Vertex and Fragment shader code.
     *
     * @param vertexPath
     * @param fragmentPath
     * @return
     */
    public static int load(String vertexPath, String fragmentPath) {
        String vertex = FileUtils.loadAsString(vertexPath);
        String fragment = FileUtils.loadAsString(fragmentPath);

        return create(vertex, fragment);
    }

    /**
     * Reads shader code, compiles and links to a program that is attached to OpenGL.
     *
     * @param vertex
     * @param fragment
     * @return
     */
    public static int create(String vertex, String fragment) {
        // get reference from created program
        int program = glCreateProgram();

        // get reference from created shaders
        int vertexID = glCreateShader(GL_VERTEX_SHADER);
        int fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        // opengl reads loaded data
        glShaderSource(vertexID, vertex);
        glShaderSource(fragmentID, fragment);

        // compile and verify status of the vertex shader
        glCompileShader(vertexID);
        if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile vertex shader");
            System.err.println(glGetShaderInfoLog(vertexID, 2048));
            return -1;
        }

        // compile and verify status of the fragment shader
        glCompileShader(fragmentID);
        if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile fragment shader");
            System.err.println(glGetShaderInfoLog(fragmentID, 2048));
            return -1;
        }

        // attaching, linking and validating shaders to program
        glAttachShader(program, vertexID);
        glAttachShader(program, fragmentID);
        glLinkProgram(program);
        glValidateProgram(program);

        // removing already linking shaders
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);

        return program;
    }

}
