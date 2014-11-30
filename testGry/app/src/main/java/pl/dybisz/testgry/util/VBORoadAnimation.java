package pl.dybisz.testgry.util;

import static android.util.FloatMath.sin;

/**
 * Created by user on 2014-11-30.
 */
public class VBORoadAnimation {
    private final static int COMPONENTS_PER_VERTEX = 3;
    private final static float SIN_ARGUMENT_MULTIPLIER = 0.5f;
    private final static float SIN_VALUE_MULTIPLIER = 1.0f;
    private final int arrayOfVerticesLength;
    private float currentTime = 0.0f;
    private float helpCounter =0.0f;
    private int verticesPerBorder;
    private float timeUnitLength;
    private float roadWidth;

    public VBORoadAnimation(int verticesPerBorder, float timeUnitLength, float roadWidth) {
        this.verticesPerBorder = verticesPerBorder;
        this.timeUnitLength = timeUnitLength;
        this.roadWidth = roadWidth;
        this.arrayOfVerticesLength =  verticesPerBorder * 2 * COMPONENTS_PER_VERTEX;
    }

    public float[] generateVerticesAfterOneFrame(float[] oldVertices) {
        /* Copy everything beside first 2 vertices */
        float[] newVertices = new float[arrayOfVerticesLength];
        System.arraycopy(oldVertices,2 * COMPONENTS_PER_VERTEX, newVertices, 0,
                arrayOfVerticesLength -(2 * COMPONENTS_PER_VERTEX));

        /* Add 2 new vertices */
        // Left border x, y ,z
        newVertices[arrayOfVerticesLength - 6] = 0.0f;
        newVertices[arrayOfVerticesLength - 5] = SIN_VALUE_MULTIPLIER*sin(SIN_ARGUMENT_MULTIPLIER*currentTime);
        newVertices[arrayOfVerticesLength - 4] = timeUnitLength * helpCounter;
        // Right border x,y,z
        newVertices[arrayOfVerticesLength - 3] = roadWidth;
        newVertices[arrayOfVerticesLength - 2] = SIN_VALUE_MULTIPLIER*sin(SIN_ARGUMENT_MULTIPLIER*currentTime);
        newVertices[arrayOfVerticesLength - 1] = timeUnitLength * helpCounter;
        // Update current time
        currentTime += 0.5;
        // Update help counter
        helpCounter++;

        return newVertices;


    }

    public float[] generateStartShape() {
        float[] vertices = new float[arrayOfVerticesLength];

        for (int i = 0; i < arrayOfVerticesLength; ) {
            // Left border x, y ,z
            vertices[i++] = 0.0f;
            vertices[i++] = SIN_VALUE_MULTIPLIER*sin(SIN_ARGUMENT_MULTIPLIER*currentTime);
            vertices[i++] = timeUnitLength * helpCounter;
            // Right border x,y,z
            vertices[i++] = roadWidth;
            vertices[i++] = SIN_VALUE_MULTIPLIER*sin(SIN_ARGUMENT_MULTIPLIER*currentTime);
            vertices[i++] = timeUnitLength * helpCounter;
            // Update current time
            currentTime += 0.5;
            // Update help counter
            helpCounter++;
        }

        return vertices;
    }
}
