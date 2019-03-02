package com.videos.luisdalopez56.glsurface01;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurface extends GLSurfaceView {
    Renderizado miRender;


    public GLSurface(Context context) {
        super(context);
        miRender = new Renderizado();
        setRenderer(miRender);
    }
}

class Renderizado implements GLSurfaceView.Renderer {

    private Cuadrado cuadrado;
    private Triangulo triangulo;
    private float anguloTriangulo= 0.0f, anguloCuadrado= 0.0f, velocidadTriangulo= 0.5f, velocidadCuadrado= -0.4f;

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        triangulo = new Triangulo();
        cuadrado = new Cuadrado();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepthf(2.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);



    }



    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        if (alto == 0) alto = 1;
        float aspecto = (float) ancho / alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45, aspecto, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        gl.glRotatef(anguloTriangulo, 0.0f, 1.0f, 0.0f);

        triangulo.draw(gl);
        gl.glLoadIdentity();
        gl.glTranslatef(1.5f, 0.0f, -6.0f);
        gl.glRotatef(anguloCuadrado, 1.0f, 1.0f, 1.0f);
        cuadrado.draw(gl);


        anguloTriangulo += velocidadTriangulo;
        anguloCuadrado += velocidadCuadrado;

    }


}

