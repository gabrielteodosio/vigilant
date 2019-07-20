package dev.dacoder.engine.level;

import dev.dacoder.engine.graphics.Shader;
import dev.dacoder.engine.graphics.Texture;
import dev.dacoder.engine.graphics.VertexArray;

public class Level {

    private VertexArray background;
    private Texture bgTexture;

    public Level() {
        float[] vertices = new float[] {
            -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
            -10.0f,  10.0f * 9.0f / 16.0f, 0.0f,
              0.0f,  10.0f * 9.0f / 16.0f, 0.0f,
              0.0f, -10.0f * 9.0f / 16.0f, 0.0f
        };

        byte[] indices = new byte[] {
            0, 1, 2,
            2, 3, 0
        };

        float[] textureCoordinates = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };

        background = new VertexArray(vertices, indices, textureCoordinates);
        bgTexture = new Texture("assets/bg.jpg");
    }

    public void render() {
        bgTexture.bind();
        Shader.BG.enable();

        background.render();

        Shader.BG.disable();
        bgTexture.unbind();
    }
}
