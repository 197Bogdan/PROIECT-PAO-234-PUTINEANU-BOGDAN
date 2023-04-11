public class Player {
    private String name;
    private int health;
    private Weapon weapon;
    private List<Item> items;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
