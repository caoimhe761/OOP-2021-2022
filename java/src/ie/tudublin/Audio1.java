package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

   

    int mode = 1; //what mode is default

    float[] lerpedBuffer; //??
    float y = 0; //??
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
                ap.mute();
            }
        }
	}

    public void settings()
    {
        size(1024, 1024, P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        //ab = ai.mix; 
        ap = minim.loadFile("C:\\Users\\caoim\\OneDrive - Technological University Dublin\\College\\2nd year\\object orientated\\semester2\\git\\OOP-2020-2021\\java\\data\\amster.mp3", 1024);
        ap.play();
        ap.mute();
        ab = ap.mix;
        colorMode(RGB); //can change

     //   y = height / 2;
       // smoothedY = y;

        lerpedBuffer = new float[width];
    }

    //float off = 0;

    public void draw()
    {
       // background(10);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        //off += 5;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
         sum += abs(ab.get(i));
          lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.2f); //makes the movement 

        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
      //  float cx = width / 2;
       //float cy = height / 2;

        switch (mode) {
			case 0:
                background(10);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, i, halfH - f);                    
                }
                break;
        case 1:
            background(10);
            for(int i = 0 ; i < ab.size() ; i ++)
            {
                //float c = map(ab.get(i), -1, 1, 0, 255);
                //float c = map(i, 0, ab.size(), 0, 255);
                stroke(255, 0, 0);
                float f = lerpedBuffer[i] * halfH * 0.05f;
                line(i, halfH + f, halfH - f, i);                    
            }
            break;
        case 2:
            {
                   // float c = map(smoothedAmplitude, 0, 0.5f, 0, 255);
                    background(10);
                  //  stroke(c, 255, 255);	
                   // float radius = map(smoothedAmplitude, 0, 0.1f, 50, 300);		
                    int points = (int)map(mouseX, 0, 255, 3, 10);
                    int sides = points * 2;
                 //   float px = cx;
                   // float py = cy - radius; 
                    for(int i = 0 ; i <= sides ; i ++)
                    {
                   //     float r = (i % 2 == 0) ? radius : radius / 2; 
                        // float r = radius;
                    //    float theta = map(i, 0, sides, 0, TWO_PI);
                       // float x = cx + sin(theta) * r;
                      //  float y = cy - cos(theta) * r;
                        
                        //circle(x, y, 20);
                    //    line(px, py, x, y);
                      //  px = x;
                    //    py = y;
                    }
            }
            /*
        /*case 3:
            background(0);
            strokeWeight(2);
            noFill();
            float r = map(smoothedAmplitude, 0, 0.5f, 100, 2000);
            float c = map(smoothedAmplitude, 0, 0.5f, 0, 255);
           stroke(c, 255, 255);
            circle(cx, cy, r);
        case 4:
         
            background(0);
            strokeWeight(2);
            for(int i = 0 ; i < ab.size() ; i +=10)
            {
                //float c = map(ab.get(i), -1, 1, 0, 255);
                float cc = map(i, 0, ab.size(), 0, 255);
                stroke(cc, 255, 255);
                float f = lerpedBuffer[i] * halfH * 4.0f;
                line(i, halfH + f, i, halfH - f);
                fill(cc);
                circle(i, halfH + f, 5);                    
                circle(i, halfH - f, 5);                    
            }
            */
            break;

        
       /* case 5:
            stroke(255);
            fill(100, 255, 255);        
            
            circle(width / 2, halfH, lerpedA * 100);

            circle(100, y, 50);
            y += random(-10, 10);
            smoothedY = lerp(smoothedY, y, 0.1f);        
            circle(200, smoothedY, 50);
            break;*/
        }


        
        // Other examples we made in the class
        /*
        stroke(255);
        fill(100, 255, 255);        
        
        circle(width / 2, halfH, lerpedA * 100);

        circle(100, y, 50);
        y += random(-10, 10);
        smoothedY = lerp(smoothedY, y, 0.1f);        
        circle(200, smoothedY, 50);
        */

    }        
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
    translate(width/2-200, height/2); //position
   // fill(c);
    rotateY(a/2 - f*i);
    rotateX(a/2 - f*i);
    box(100);
    fill(gray);
    popMatrix();
    }
    
for(int i = 0; i < ab.size(); i++) {
    float f = lerpedBuffer[i] * halfH * 0.1f;
    //float c = map(i, 0, ab.size(), 0, 255);
    
    float gray = map(i, 0, num-1, 0, 255);
    pushMatrix();
    translate(width/2, height/2+200); //position
   // fill(c);
    rotateY(a/2 - f*i);
    rotateX(a/2 - f*i);
    box(50);
    fill(gray);
    popMatrix();
    }

















    translate(width/2, height/2); //position
            
    for(int i = 0; i < ab.size(); i++) {
        float offset = lerpedBuffer[i] * halfH * 0.1f;
        float c = map(i, 0, ab.size(), 0, 255);
        
       // float gray = map(i, 0, num-1, 0, 255);
        pushMatrix();
       // fill(c);
        rotateY(a/2 - offset*i);
        rotateX(a/2 - offset*i);
        box(400);
        fill(c);
        popMatrix();
        }

        a += 0.01;    













        for(int i = 0; i < ab.size(); i++) {
            float offset = lerpedBuffer[i] ;
            float c = map(i, 0, ab.size(), 0, 255);
            
           // float gray = map(i, 0, num-1, 0, 255);
            pushMatrix();
           // fill(c);
            rotateY(a/2 + offset*i);
            rotateX(a/2 + offset*i);
            box(400);
            fill(c);
            popMatrix();
            }

            a += 0.01;  








            float a=1;                 // Angle of rotation
            //float offset = PI/24.0F;  // Angle offset between boxes
           // int num = 200;
            
            lights();
            
            background(0, 0, 26);
            translate(width/2, height/2); //position
            
            for(int i = 0; i < ab.size(); i++) {
                float offset = lerpedBuffer[i]/100.0F; 
                float c = map(i, 0, ab.size(), 0, 255);
                
               // float gray = map(i, 0, num-1, 0, 255);
                pushMatrix();
               // fill(c);
                rotateY(a + offset*i);
                rotateX(a/2 + offset*i);
                box(200);
                fill(c);
                popMatrix();
                }
    
                a += 0.01; 