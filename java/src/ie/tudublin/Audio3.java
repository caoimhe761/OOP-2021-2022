package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.*;

public class Audio3 extends PApplet
{
    PImage bg;
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 3;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16); 
        // ab = ai.mix; 
        ap = minim.loadFile("C:\\Users\\caoim\\OneDrive - Technological University Dublin\\College\\2nd year\\object orientated\\semester2\\git\\OOP-2021-2022\\data\\Amster.mp3", 1024);
        ap.play();
       // ap.mute();
        ab = ap.mix;
        colorMode(HSB);
        
        y = height / 2;
        smoothedY = y;
     
        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw()
    {
        //background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        float cx, c1x = width / 2;
        float cy, c1y = height / 2;

        switch (mode) {
			case 0:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, i, halfH - f);                    
                    fill(c,255,255);
                }
                break;
        case 1:
            background(0);
            for(int i = 0 ; i < ab.size() ; i ++)
            {
                //float c = map(ab.get(i), -1, 1, 0, 255);
                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                line(i, halfH + f, halfH - f, i);                    
            }
            break;
        case 2:
            {
                  

                background(0);
                    float largeCircle;

                    float radius = min(width, height) / 2;
                    largeCircle = radius * 1.8f;
                 
                    
                    cx = width / 2;
                    cy = height / 2;
                    
                    // Draw the clock background
                    fill(255);
                    noStroke();
                    circle(cx, cy, largeCircle);
                    

                    strokeWeight(2);
                    noFill();
                    float r = map(smoothedAmplitude, 0, 0.5f, 100, 20);
                    c1x = width-((2*radius)-r);
                    c1y = radius;
                    fill(80);
                    circle(c1x, c1y, r);
                            
                        
                    /*
                    // Angles for sin() and cos() start at 3 o'clock;
                    // subtract HALF_PI to make them start at the top
                    float s = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
                    float m = map(minute() + norm(second(), 0, 60), 0, 60, 0, TWO_PI) - HALF_PI; 
                    float h = map(hour() + norm(minute(), 0, 60), 0, 24, 0, TWO_PI * 2) - HALF_PI;
                    
                    // Draw the hands of the clock
                    stroke(255);
                    strokeWeight(1);
                    line(cx, cy, cx + cos(s) * secondsRadius, cy + sin(s) * secondsRadius);
                    strokeWeight(2);
                    line(cx, cy, cx + cos(m) * minutesRadius, cy + sin(m) * minutesRadius);
                    strokeWeight(4);
                    line(cx, cy, cx + cos(h) * hoursRadius, cy + sin(h) * hoursRadius);
                    
                    // Draw the minute ticks
                    strokeWeight(2);
                    beginShape(POINTS);
                    for (int a = 0; a < 360; a+=6) {
                        float angle = radians(a);
                        float x = cx + cos(angle) * secondsRadius;
                        float y = cy + sin(angle) * secondsRadius;
                        vertex(x, y);
                    }
                    endShape();*/
                   // }
                   break;
            }
        case 3:
        {
            float secondsRadius;
            int radius = min(width, height) / 2;
            secondsRadius = radius * 0.72f;
            
            cx = width / 2;
            cy = height / 2;
            /*
            background(0);
            float secondsRadius;
            float clockDiameter;
        
              
            int radius = min(width, height) / 2;
            secondsRadius = radius * 0.72f;
            clockDiameter = radius * 1.8f;
            
            cx = width / 2;
            cy = height / 2;
            
            // Draw the clock background
            
            
            noStroke();
            ellipse(cx, cy, clockDiameter, clockDiameter);
            */
            
            loadPixels();
            float A = map(smoothedAmplitude, 0, 0.001f, 100, 20);
            float n = (A * 10.0F) / width;
            float w = 16.0F;         // 2D space width
            float h = 16.0F;         // 2D space height
            float dx = w / width;    // Increment x this amount per pixel
            float dy = h / height;   // Increment y this amount per pixel
            float x = -w/2;          // Start x at -1 * width / 2
            for (int i = 0; i < width; i++) {
                float y = -h/2;        // Start y at -1 * height / 2
                for (int j = 0; j < height; j++) {
                float r = sqrt((x*x) + (y*y));    // Convert cartesian to polar
                float theta = atan2(y,x);         // Convert cartesian to polar
                // Compute 2D polar coordinate function
                float val = sin(n*cos(r) + 5 * theta);           // Results in a value between -1 and 1
                float c = map(i, 0, ab.size(), 0, 255);
                //float val = cos(r);                            // Another simple function
                //float val = sin(theta);                        // Another simple function
                // Map resulting vale to grayscale value
                pixels[i+j*width] = color((int) ((val + 1.0) * c));     // Scale to between 0 and 255
                y += dy;                // Increment y
                }
                x += dx;                  // Increment x
            }
            updatePixels();
            stroke(255);
            strokeWeight(0);
            beginShape(POINTS);
            
            
            for (int a = 0; a < 360; a+=20) {
                
                float r = map(smoothedAmplitude, 0, 0.002f, 100, 20);
                float angle = radians(a);
                float cx2 = cx + cos(angle) * secondsRadius;
                float y = cy + sin(angle) * secondsRadius;
                float p = map(ab.get(a), -1, 1, 0, 360);
                circle(cx2, y, r);
                fill(p, 255,255);
            }
            //  endShape();
            
            break;
        }

        case 4:{
            float a=1;                 // Angle of rotation
            //float offset = PI/24.0F;  // Angle offset between boxes
            int num = 12;
            
            lights();
            
            background(0, 0, 26);
            
            
            for(int i = 0; i < ab.size(); i++) {
                float offset = lerpedBuffer[i]/100.0F; 
                float c = map(i, 0, ab.size()-1, 0, 255);
                //float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
                translate(width/2-200, height/-100); 
                fill(c);
                rotateY(a + offset*i);
                rotateX(a/2 + offset*i);
                box(50);
                popMatrix();
                }
    
            for(int i = 0; i < ab.size(); i++) {
                float offset = lerpedBuffer[i] * halfH * 0.0000001f;
                float c = map(i, 0, ab.size(), 0, 255);
                
            // float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
                translate(width/2, height/2); //position
            // fill(c);
                rotateY(a*2 / offset/i);
                rotateX(a*2 / offset/i);
                box(200);
                fill(c);
                popMatrix();
                }



            for(int i = 0; i < ab.size(); i++) {
                float f = lerpedBuffer[i] * halfH * 0.1f;
                //float c = map(i, 0, ab.size(), 0, 255);
                
                float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
                translate((width/2)-200, (height/2)+20); //position
            // fill(c);
                rotateY(a/2 - f*i);
                rotateX(a/2 - f*i);
                box(100);
                fill(gray);
                popMatrix();
                    }
                
                    
            for(int i = 0; i < ab.size(); i++) {
                float offset = lerpedBuffer[i] * halfH * 0.1f;
                float c = map(i, 0, ab.size(), 0, 255);
                
                // float gray = map(i, 0, num-1, 0, 255);
                    pushMatrix();
                    translate(width-100, height-250); //position
                // fill(c);
                    rotateY(a/2 - offset*i);
                    rotateX(a/2 - offset*i);
                    box(100);
                    fill(c);
                    popMatrix();
                    }


                    for(int i = 0; i < ab.size(); i++) {
                        float offset = lerpedBuffer[i] ;
                        float c = map(i, 0, ab.size(), 0, 255);
                        
                    // float gray = map(i, 0, num-1, 0, 255);
                        pushMatrix();
                        
                        translate(width, height/2-100); //position
                    // fill(c);
                        rotateY(a/2 + offset*i);
                        rotateX(a/2 + offset*i);
                        box(100);
                        fill(c);
                        popMatrix();
                        }
                            
                            
                            
            for(int i = 0; i < ab.size(); i++) {
                float offset = lerpedBuffer[i]/100.0F; 
                float c = map(i, 0, ab.size(), 0, 255);
                
               // float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
                translate(20,20); //position
               // fill(c);
                rotateY(a + offset*i);
                rotateX(a/2 + offset*i);
                box(200);
                fill(c);
                popMatrix();
                }
                           
            for(int i = 0; i < ab.size(); i++) {
                float offset = lerpedBuffer[i]/100.0F; 
                float c = map(i, 0, ab.size(), 0, 255);
                
               // float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
                translate(800,100); //position
               // fill(c);
                rotateY(offset*i/a);
                rotateX(offset*i/a);
                box(200);
                fill(c);
                popMatrix();
                }
    
    
             
           // float a=1;                 // Angle of rotation
           // float offset = PI/24.0F;  // Angle offset between boxes
            //int num = 12; 
            for(int i = 0; i < num; i++) {
            float offset = lerpedBuffer[i]/10000.0F;
            float gray = map(i, 0, num-1, 0, 255);
            pushMatrix();
            translate((width/2)-200, (height/2)+300); 
            fill(gray);
            rotateY(a + offset*i);
            rotateX(a/2 + offset*i);
            box(200);
            popMatrix();
            }

           // float a;                 // Angle of rotation
            float offset = PI/24.0F;  // Angle offset between boxes
            //int num = 12;
            translate(width-100, height/2); 
  
            for(int i = 0; i < num; i++) {
                float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
                translate(width/2-200, height/2+300); 
                fill(gray);
                rotateY(a + offset*i);
                rotateX(a/2 + offset*i);
                box(200);
                popMatrix();
            }
            
            a += 0.01;    
      break;

    }}}}
