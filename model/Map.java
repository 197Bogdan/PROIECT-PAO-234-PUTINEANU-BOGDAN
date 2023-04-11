public class Map {
    private int width;
    private int height;
    private List<MapSection> sections;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.sections = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sections.add(new MapSection(x, y));
            }
        }
    }
    public void initialize(List<Player> players) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sections[x*height + y] = new MapSection(x, y);
            }
        }
        for (Player player : players) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            MapSection section = getSection(x, y);
            section.addPlayer(player);
            player.setSection(section);
        }
    }

}

    
