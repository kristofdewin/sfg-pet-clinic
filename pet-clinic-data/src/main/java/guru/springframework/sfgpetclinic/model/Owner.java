package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Builder
    public Owner(Long id, String firstName, String lastName, String adress, String city, String telephone, Set<Pet> pets, String userName, String pass) {
        super(id,firstName, lastName);
        this.adress = adress;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
        this.userName = userName;
        this.pass = pass;
    }

    @Column(name = "adress")
    private String adress;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String pass;
}
