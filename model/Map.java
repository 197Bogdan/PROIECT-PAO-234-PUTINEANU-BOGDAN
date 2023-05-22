package model;

import service.AuditCSVService;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int width;
    private final int height;
    private List<MapSection> sections;

    public Map(int width, int height) {
        AuditCSVService.addLog("Creating map...");
        AuditCSVService.addLog("Spawning items...");
        this.width = width;
        this.height = height;
        this.sections = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sections.add(new MapSection(x, y));
            }
        }
    }

    public List<MapSection> getSections() {
        return sections;
    }

    public void setSections(List<MapSection> sections) {
        this.sections = sections;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

    
