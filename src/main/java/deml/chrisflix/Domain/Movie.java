package deml.chrisflix.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Movie {

    @Id
    private long id;
    private String title;
    private String desciptionLong;
    private String buzzwords;
    private String actors;
    private String genre;
    private BigDecimal imdbScore;
    private Integer year;
    private Integer playingTime;
    private long playedTimeInSeconds;
    private boolean activated;

    public Movie(final long id,
                 final String title,
                 final String desciptionLong,
                 final String buzzwords,
                 final String actors,
                 final String genre,
                 final BigDecimal imdbScore,
                 final Integer year,
                 final Integer playingTime,
                 final long playedTimeInSeconds,
                 final boolean activated) {
        this.id = id;
        this.title = title;
        this.desciptionLong = desciptionLong;
        this.buzzwords = buzzwords;
        this.actors = actors;
        this.genre = genre;
        this.imdbScore = imdbScore;
        this.year = year;
        this.playingTime = playingTime;
        this.playedTimeInSeconds = playedTimeInSeconds;
        this.activated = activated;
    }

    public Movie() {

    }

    public String getActorsFrontend() {
        if (actors == null) return "---";
        List<String> listActors = new ArrayList<>(List.of(actors.split(";")));
        listActors = listActors.stream().filter(a -> !"".equals(a) && !" ".equals(a)).collect(Collectors.toList());
        return String.join(", ", listActors);
    }

    public String getGenreFrontend() {
        List<String> listGenre = new ArrayList<>();
        if (genre == null) return "---";
        if (genre.contains("action")) {
            listGenre.add("Action");
        }
        if (genre.contains("drama")) {
            listGenre.add("Drama");
        }
        if (genre.contains("fantasy")) {
            listGenre.add("Fantasy");
        }
        if (genre.contains("horror")) {
            listGenre.add("Horror");
        }
        if (genre.contains("comedy")) {
            listGenre.add("Komödie");
        }
        if (genre.contains("crime")) {
            listGenre.add("Krimi");
        }
        if (genre.contains("romance")) {
            listGenre.add("Romantik");
        }
        if (genre.contains("scifi")) {
            listGenre.add("Sciene Fiction");
        }
        if (genre.contains("thriller")) {
            listGenre.add("Thriller");
        }

        return String.join("/", listGenre);
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDesciptionLong() {
        return desciptionLong;
    }

    public void setDesciptionLong(final String desciptionLong) {
        this.desciptionLong = desciptionLong;
    }

    public String getBuzzwords() {
        return buzzwords;
    }

    public void setBuzzwords(final String buzzwords) {
        this.buzzwords = buzzwords;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(final String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public BigDecimal getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(final BigDecimal imdbScore) {
        this.imdbScore = imdbScore;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public Integer getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(final Integer playingTime) {
        this.playingTime = playingTime;
    }

    public long getPlayedTimeInSeconds() {
        return playedTimeInSeconds;
    }

    public void setPlayedTimeInSeconds(final long playedTimeInSeconds) {
        this.playedTimeInSeconds = playedTimeInSeconds;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(final boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desciptionLong='" + desciptionLong + '\'' +
                ", buzzwords='" + buzzwords + '\'' +
                ", actors='" + actors + '\'' +
                ", genre='" + genre + '\'' +
                ", imdbScore=" + imdbScore +
                ", year=" + year +
                ", playingTime=" + playingTime +
                ", playedTimeInSeconds=" + playedTimeInSeconds +
                '}';
    }
}
