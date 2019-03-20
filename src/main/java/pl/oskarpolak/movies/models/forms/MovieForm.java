package pl.oskarpolak.movies.models.forms;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class MovieForm {
    private String title;
    private String author;
    private int year;
    private String shortDescription;
    private String longDescription;
    private String type;
}
