public class MapSection {
    private int x;
    private int y;
    private List<Player> players;
    private List<Item> items;

    public MapSection(int x, int y) {
        this.x = x;
        this.y = y;
        this.players = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
