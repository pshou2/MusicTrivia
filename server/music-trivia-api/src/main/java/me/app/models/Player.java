package me.app.models;

import java.util.Objects;

public class Player {
    //fields
    private int playerId;
    private String gamerTag;
    private String tagLine;

    //constructors
    public Player(int playerId, String gamerTag, String tagLine) {
        this.playerId = playerId;
        this.gamerTag = gamerTag;
        this.tagLine = tagLine;
    }

    public Player() {
    }

    //getters and setters

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId == player.playerId && Objects.equals(gamerTag, player.gamerTag) && Objects.equals(tagLine, player.tagLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, gamerTag, tagLine);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", gamerTag='" + gamerTag + '\'' +
                ", tagLine='" + tagLine + '\'' +
                '}';
    }
}
