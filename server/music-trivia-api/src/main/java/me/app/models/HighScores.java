package me.app.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class HighScores {
    //fields
    private int highScoresId;
    private int score;
    private LocalDate date;
    private LocalTime time;
    private int playerId;

    //constructors
    public HighScores(int highScoresId, int score, LocalDate date, LocalTime time, int playerId) {
        this.highScoresId = highScoresId;
        this.score = score;
        this.date = date;
        this.time = time;
        this.playerId = playerId;
    }

    public HighScores() {
    }

    //getters and setters
    public int getHighScoresId() {
        return highScoresId;
    }

    public void setHighScoresId(int highScoresId) {
        this.highScoresId = highScoresId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighScores that = (HighScores) o;
        return highScoresId == that.highScoresId && score == that.score && playerId == that.playerId && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(highScoresId, score, date, time, playerId);
    }

    @Override
    public String toString() {
        return "HighScores{" +
                "highScoresId=" + highScoresId +
                ", score=" + score +
                ", date=" + date +
                ", time=" + time +
                ", playerId=" + playerId +
                '}';
    }
}
