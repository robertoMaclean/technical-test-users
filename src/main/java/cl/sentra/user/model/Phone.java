package cl.sentra.user.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String citycode;
    private String countrycode;
    @ManyToOne
    private User user;

    public Phone() {
    }

    public Phone(String number, String citycode, String countrycode) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
    }

    public String getNumber() {
        return number;
    }

    public String getCitycode() {
        return citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
