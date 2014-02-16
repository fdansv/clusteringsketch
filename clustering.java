import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Francisco on 16/02/14.
 */
public class clustering extends PApplet {
    private ArrayList<PVector> listOfPoints = new ArrayList<PVector>();

    public void setup(){
        size(800,800);
    }
    public void draw(){
        background(255);
        for(PVector point: listOfPoints){
            ellipse(point.x, point.y, 5, 5);
        }

    }

    @Override
    public void mouseClicked() {
        listOfPoints.add(new PVector(mouseX, mouseY));
    }

    @Override
    public void keyPressed() {

    }
}
