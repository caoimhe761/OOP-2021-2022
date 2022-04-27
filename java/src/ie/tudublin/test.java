package ie.tudublin;

import processing.core.PApplet;

public class test extends PApplet{
    

    int xspacing = 16;   // How far apart should each horizontal location be spaced
    int w;              // Width of entire wave

    float theta = 0.0F;  // Start angle at 0
    float amplitude = 75.0F;  // Height of wave
    float period = 500.0F;  // How many pixels before the wave repeats
    float dx;  // Value for incrementing X, a function of period and xspacing
    float[] yvalues;  // Using an array to store height values for the wave

    public void settings()
    {
        size(1024, 1000, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        size(1024, 1000);
        w = width+16;
        dx = (TWO_PI / period) * xspacing;
        
        //y = height / 2;
        //smoothedY = y;
        yvalues = new float[w/xspacing];
        colorMode(HSB);
    }

    public void draw() {
    background(0);
    calcWave();
    renderWave();
    }

    void calcWave() {
    // Increment theta (try different values for 'angular velocity' here
    theta += 0.02;

    // For every x value, calculate a y value with sine function
    float x = theta;
    for (int i = 0; i < yvalues.length; i++) {
        yvalues[i] = sin(x)*amplitude;
        x+=dx;
    }
    }

    void renderWave() {
    noStroke();
    fill(255);
    // A simple way to draw the wave with an ellipse at each location
    for (int x = 0; x < yvalues.length; x++) {
        ellipse(x*xspacing, height/2+yvalues[x], 16, 16);
    }
    }
}


/*

                    float c = map( 0, 0.5f, 0, 255);
                   
                    stroke(c, 255, 255);	
                    float radius = map(smoothedAmplitude, 0, 0.1f, 50, 300);		
                    int points = (int)map(mouseX, 0, 255, 3, 10);
                    int sides = points * 2;
                    float px = cx;
                    float py = cy - radius; 
                    for(int i = 0 ; i <= sides ; i ++)
                    {
                        float r = (i % 2 == 0) ? radius : radius / 2; 
                        // float r = radius;
                        float theta = map(i, 0, sides, 0, TWO_PI);
                        float x = cx + sin(theta) * r;
                        float y = cy - cos(theta) * r;
                        
                        //circle(x, y, 20);
                        line(px, py, x, y);
                        px = x;
                        py = y;
                    }
                    */