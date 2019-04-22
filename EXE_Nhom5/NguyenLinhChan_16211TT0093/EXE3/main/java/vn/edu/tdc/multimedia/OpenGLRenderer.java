package vn.edu.tdc.multimedia;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnable(GL10.GL_DEPTH_TEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float fovy = 50.0f; // Field of view angle, in degrees, in the Y direction.
        float aspect = (float)width / (float)height;
        float zNear = 0.1f;
        float zFar = 100.0f;
        // Set up a perspective projection matrix
        GLU.gluPerspective(gl, fovy, aspect, zNear, zFar);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with the identity matrix.
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(rotationAngle, 1.0f, 1.0f, 1.0f);
        cube.draw(gl);
        rotationAngle -= 0.4f;
    }

    private Cuba cube;

    private float rotationAngle;

    public OpenGLRenderer()
    {
        cube = new Cuba();
    }
}
