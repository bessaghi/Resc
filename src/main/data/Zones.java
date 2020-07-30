package data;

import java.util.ArrayList;
import java.util.Optional;

public class Zones {
    private ArrayList<Zone> mappers;

    public Zones() {
        this.mappers = new ArrayList<>();
    }

    public Optional<Zone> findByTask(String task) {
        return mappers.stream()
                .filter(t -> task.equals(t.getTask()))
                .findFirst();
    }

    public Zone createNewZone(String task) {
        Zone zone = new Zone(task);
        mappers.add(zone);
        return zone;
    }
}
