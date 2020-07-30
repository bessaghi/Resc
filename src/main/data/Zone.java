package data;

import java.util.ArrayList;

public class Zone {
    private String task;
    private ArrayList<String> zones;

    public Zone(String task) {
        this.task = task;
        this.zones = new ArrayList<>();
    }

    public void addZone(String zone) {
        zones.add(zone);
    }

    public String getTask() {
        return task;
    }

    public ArrayList<String> getZones() {
        return zones;
    }
}
