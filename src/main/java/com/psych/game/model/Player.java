package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Table(name = "players")
public class Player extends Auditable {

    @Setter
    @Getter
    @NotBlank
    private String name;

    @Getter
    @Setter
    @URL
    private String psychFaceURL;

    @Getter
    @Setter
    @URL
    private String picURL;

    @Getter
    @Setter
    @OneToOne
    private Stats stats;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "players")
    private List<Game> games;

}
