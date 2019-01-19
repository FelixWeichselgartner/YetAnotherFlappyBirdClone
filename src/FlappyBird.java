public class FlappyBird {

    private static Boolean startGameloop(Player gamePlayer, Environment gameEnvironment) {
        gameEnvironment.setup();
        return gameEnvironment.gameloop(gamePlayer);
    }

    public static void main(String[] args) {
        System.out.println("Yet another Flappy Bird clone");
        String name = "Felix";
        Player newPlayer = new Player();
        newPlayer.setName(name);
        Environment gameEnvironment = new Environment();
        Boolean gameOver = startGameloop(newPlayer, gameEnvironment);
        if (gameOver == true) {
            System.out.println("You lost!");
            System.out.println(newPlayer.getName() + " got " + newPlayer.getScore() + " points!");
        }
        return;
    }
}
