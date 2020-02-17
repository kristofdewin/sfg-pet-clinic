package guru.springframework.sfgpetclinic.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.springdatajpa.OwnerSDJpaService;



@Route
public class MainView extends VerticalLayout {
    private final OwnerSDJpaService ownerSDJpaService;
    private Grid<Owner> ownerGrid;

    public MainView(OwnerSDJpaService ownerSDJpaService) {
        Button button1 = new Button("button1");
        this.ownerSDJpaService = ownerSDJpaService;
        this.ownerGrid = new Grid<>(Owner.class);
        add(button1);
        add(ownerGrid);
        listOwner();
    }

    private void listOwner() {
        ownerGrid.setItems(ownerSDJpaService.findAll());
    }
}
