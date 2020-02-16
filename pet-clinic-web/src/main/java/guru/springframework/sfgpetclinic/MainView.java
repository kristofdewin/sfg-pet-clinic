package guru.springframework.sfgpetclinic;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.springdatajpa.PetSDJpaService;

@Route
public class MainView extends VerticalLayout {

    private final PetSDJpaService petSDJpaService;
    private Grid<Pet> grid;

    public MainView(PetSDJpaService petSDJpaService) {
        this.petSDJpaService = petSDJpaService;
        this.grid=new Grid<>(Pet.class);
        add(grid);
        listPet();
    }

    private void listPet() {
        grid.setItems(petSDJpaService.findAll());
    }
    }


