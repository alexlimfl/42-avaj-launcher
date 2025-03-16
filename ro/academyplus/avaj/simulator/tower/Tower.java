package ro.academyplus.avaj.simulator.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ro.academyplus.avaj.simulator.flyable.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();
    private List<Flyable> toRemove = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable) {
        toRemove.add(p_flyable);
    }
    
    protected void conditionChanged() {
        for (Flyable flyable : observers)
            flyable.updateConditions();
        observers.removeAll(toRemove);
    }
}
