package Buzzword.Model;

import java.util.HashSet;

/**
 * Created by Phil on 3/14/2017.
 */

public class BoggleGraph {

    public HashSet<BoggleVertex> getVertices() {
        return vertices;
    }

    private HashSet<BoggleVertex> vertices;
    private final int maxVertices = 16;
    private final int maxHeight = 4;
    private final int maxWidth = 4;
    private int numVertices;

    public void addVertex(BoggleVertex v){
        vertices.add(v);
        numVertices++;
    }

    public BoggleGraph(){
        vertices = new HashSet<BoggleVertex>();
        this.numVertices = 0;
    }




}
