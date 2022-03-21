package ie.tudublin;

import javax.lang.model.util.ElementScanner14;

import processing.core.PApplet;

public class Loops extends PApplet
{


	int mode = 0;
	float rectX, rectY, rectY1,rectX1;
	int rectHue;
	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(HSB);
		background(0);

		rectY =250; 
		rectX =500;
	}

	public void keyPressed()
	{
		if (key >= '0' && key <='9')
		{
			mode = key - '0';
		}
		println(mode);
	}

	
	public void draw()
	{
		background(0);
		switch(mode)	
		{
			case 0:
				if (mouseX >= rectX + 50)
				{
					rectX += 50;
				}
				else if(mouseX <= rectX -50)
				{
					rectX -= 50;
				}
					noStroke();
					fill (0,255,255);
					rectMode(CENTER);
					rect(rectX, rectY,100,500);
					
				break;
			case 1:
				noStroke();
				if (mouseX >= rectX + 50)
				{
					rectX += 50;
				}
				else if(mouseX <= rectX -50)
				{
					rectX -= 50;
				}
				else if (mouseY >= rectY + 50)
				{
					rectY += 50;
				}
				else if(mouseY <= rectY -50)
				{
					rectY -= 50;
				}
				noStroke();
				fill (0,255,255);
				rectMode(CENTER);
				rect(rectX, rectY,100,100);
				break;
			case 2:
				if (mouseX <=150 || mouseX >=350 || mouseY <=175 || mouseY>325)
				{
					background(0);
					noStroke();
					fill (255,0,0);
					rect(250,250,200,150);
				}
				else{

					noStroke();
					fill (0,255,255);
					rect(250,250,200,150);
				}
			
				break;
			case 3:
				noStroke();
				rectHue =0;
				rectX =0;
				while(rectX<500)
				{
					fill(rectHue,255,255);
					rect(rectX + 25,250,50,500);
					rectHue += 18;
					rectX +=50;
				}
				break;

			case 4:
				
				rectHue =0;
				rectX =0;
				rectY =0;
				while(rectX<500 )
				{
					fill(rectHue,255,255);
					rect(rectX +25, rectY + 25,50,50);
					rectHue += 18;
					rectX +=50;
					rectY +=50;
				}

				break;
			
			case 5:
				noStroke();
				rectHue =0;
				rectX =0;
				rectY =0;
				rectX1 =500;
				while(rectX<500 )
				{
					fill(rectHue,255,255);
					rect(rectX +25, rectY + 25,50,50);
					rect(rectX1 -25, rectY + 25,50,50);
					rectHue += 18;
					rectX +=50;
					rectX1 -=50;
					rectY +=50;
				}
		
				break;
			case 6: //line grid
				colorMode(RGB);
				float border = width * 0.1f;
				for (int i = -5; i<= 5; i++)
				{
					float x = map(i, -5, 5, border, width - border);
					stroke(0, 255, 0);
					line(x, border, x, height - border);
					line(border, x, width - border, x);
					text(i,x,border * 0.5f);
					text(i,border * 0.5f,x);
				}
				break;
			case 7:
				background(0);
				stroke(255);
				colorMode(RGB);
				float cx = width/2;
				float cy = height/2;
				float radius = 200;
				int sides = (int)map(mouseX, 0, width, 0, 20);
				for(int i = 0 ; i < sides ; i++)
				{
					float theta = map(i, 0, sides, 0, TWO_PI);
					float x = cx + sin(theta) * radius;
					float y = cy + cos(theta) * radius;

					float theta2 = map(i, 0, sides, 0, TWO_PI);
					float x2 = cx + sin(theta) * radius;
					float y2 = cy + cos(theta) * radius;
					line(x,y,x2,y2);
					//circle(x, y, 20);

				}
		}
	}
}
