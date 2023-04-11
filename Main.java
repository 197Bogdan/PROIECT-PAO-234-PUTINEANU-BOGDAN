public class Main {
public static void main(String[] args) {
    // create map
    Map map = new Map(10, 10);

    // create players
    Player player1 = new Player("Player 1", 100);
    player1.setWeapon(new MeleeWeapon("Knife", 10));
    Player player2 = new Player("Player 2", 100);
    player2.setWeapon(new RangedWeapon("Bow", 20));

    // add players to map
    map.initialize(Arrays.asList(player1, player2));

    // simulate game
    while (player1.isAlive() && player2.isAlive()) {
        // move players randomly
        for (Player player : Arrays.asList(player1, player2)) {
            int x = player.getSection().getX() + (int) (Math.random() * 3) - 1;
            int y = player.getSection().getY() + (int) (Math.random() * 3) - 1;
            MapSection section = map.getSection(x, y);
            player.setSection(section);
        }

        // check for encounters
        for (MapSection section : map.getSections()) {
            List<Player> players = section.getPlayers();
            if (players.size() > 1) {
                // simulate encounter
                Player attacker = players.get(0);
                Player defender = players.get(1);
                System.out.println(attacker.getName() + " attacks " + defender.getName() + "!");
                defender.setHealth(defender.getHealth() - attacker.getWeapon().getDamage());
                if (!defender.isAlive()) {
                    System.out.println(defender.getName() + " dies!");
                    section.removePlayer(defender);
                    break;
                }
            }
        }

        // print status
        System.out.println("Player 1: " + player1.getHealth() + " health");
        System.out.println("Player 2: " + player2.getHealth() + " health");
        System.out.println();
    }

    // print result
    if (player1.isAlive()) {
        System.out.println(player1.getName() + " wins!");
    } else if (player2.isAlive()) {
        System.out.println(player2.getName() + " wins!");
    } else {
        System.out.println("It's a tie!");
    }
}

}
