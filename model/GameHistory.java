package model;

public class GameHistory {
    int gameId;
    int gameLength;
    String gameWinner;

    public GameHistory(int gameId, int gameLength, String gameWinner) {
        this.gameId = gameId;
        this.gameLength = gameLength;
        this.gameWinner = gameWinner;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameLength() {
        return gameLength;
    }

    public void setGameLength(int gameLength) {
        this.gameLength = gameLength;
    }

    public String getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }
}
