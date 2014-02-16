import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Francisco on 16/02/14.
 */
public class clustering extends PApplet {
    private ArrayList<GroupedVector> listOfPoints = new ArrayList<GroupedVector>();
    private int currentGroup = 0;

    public void setup(){
        size(800,800);
    }
    public void draw(){
        background(255);
        for(GroupedVector point: listOfPoints){
            fill((currentGroup+1)*40);
            ellipse(point.x, point.y, 5, 5);
        }

    }

    @Override
    public void mouseClicked() {
        listOfPoints.add(new GroupedVector(mouseX, mouseY, 0));
    }

    @Override
    public void keyPressed() {
        for(GroupedVector point: listOfPoints){
            currentGroup++;
            point.assignGroup(currentGroup);
            for(GroupedVector comparedPoint: listOfPoints){
                if(dist(point.x, point.y, comparedPoint.x, comparedPoint.y)<CLUSTERING_THRESHOLD){
                    comparedPoint.assignGroup(currentGroup);
                }
            }
        }
    }

    class GroupedVector extends PVector{

        private int group;

        public GroupedVector(int mouseX, int mouseY, int i) {
            super(mouseX, mouseY);
            group = i;
        }

        public void assignGroup(int group){
            this.group = group;
        }
    }

    public static final int CLUSTERING_THRESHOLD = 30;
}
