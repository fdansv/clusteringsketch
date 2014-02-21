package clusteringsketch;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Francisco on 16/02/14.
 */
public class clustering extends PApplet { // Class is in lower caps because it's a PApplet
    private ArrayList<GroupedVector> listOfPoints = new ArrayList<GroupedVector>();
    private int currentGroup = 0;

    public void setup(){
        size(800,800);
        noStroke();
        smooth();
    }
    public void draw(){
        background(255);
        for(GroupedVector point: listOfPoints){
            fill(0);
            text(point.group, point.x, point.y-10);
            ellipse(point.x, point.y, 5, 5);
        }

    }

    @Override
    public void mouseClicked() {
        listOfPoints.add(new GroupedVector(mouseX, mouseY));
    }

    @Override
    public void keyPressed() {
        currentGroup++;
        for(GroupedVector point: listOfPoints){
            if(point.group==0){
                point.assignGroup(currentGroup);
                for(GroupedVector comparedPoint: listOfPoints){
                    if(comparedPoint.group==0 && dist(point.x, point.y, comparedPoint.x, comparedPoint.y)<CLUSTERING_THRESHOLD){
                        comparedPoint.assignGroup(currentGroup);
                    }
                }
            }
            currentGroup++;
        }
    }
    
    public GroupedVector generateCenter(ArrayList<GroupedVector> list, int group){
        int sumx = 0, sumy = 0, count = 0;
        for(GroupedVector element: list){
            if(element.getGroup() == group){
                count++;
                sumx += element.x;
                sumy += element.y;
            }
        }
        return new GroupedVector(sumx/count, sumy/count);
    }

    class GroupedVector extends PVector{

        private int group = 0;

        public GroupedVector(int mouseX, int mouseY) {
            super(mouseX, mouseY);
        }

        public void assignGroup(int group){
            this.group = group;
        }
    }

    public static final int CLUSTERING_THRESHOLD = 200;
}
