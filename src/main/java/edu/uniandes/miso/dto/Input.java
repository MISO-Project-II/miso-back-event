package edu.uniandes.miso.dto;

import edu.uniandes.miso.entity.EvenType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class Input {
    private String name;
    private Date date;
    private String description;;
    private String city;
    private Long idSport;
    private Long idUserCreator;
    private EvenType evenType;
}
