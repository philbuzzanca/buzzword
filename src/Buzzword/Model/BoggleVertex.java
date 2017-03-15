package Buzzword.Model;

import java.util.ArrayList;

/**
 * Created by Phil on 3/14/2017.
 */
public class BoggleVertex {

    private int xPosition;
    private int yPosition;

    public void setLetter(char letter) {
        this.letter = letter;
    }

    private char letter;
    private ArrayList<BoggleVertex> adjacentVertices;


    public char getLetter(){
        return letter;
    }

    public ArrayList<BoggleVertex> getAdjacentVertices() {
        return adjacentVertices;
    }

    boolean hasNeighbor(BoggleVertex neighbor){
        return adjacentVertices.contains(neighbor);
    }

    public void addNeighbor(BoggleVertex vertex){
        if(adjacentVertices.contains(vertex)) return; // don't add an already existing vertex!
        adjacentVertices.add(vertex);
        vertex.getAdjacentVertices().add(this);
    }

    public BoggleVertex(char letter){
        adjacentVertices = new ArrayList<BoggleVertex>();
        this.letter = letter;
    }

}
