package hw2;

import java.util.ArrayList;
import java.util.List;

public class Site {
    public boolean isOpen;
    public boolean isFull;
    public boolean checked = false;
    public int id = 0;

    public List<Site> neighbors = new ArrayList<>();

    public Site() {
        this.isOpen = false;
        this.isFull = false;
    }

    public void addNeighbor(Site ngb) {
        neighbors.add(ngb);
    }

}
