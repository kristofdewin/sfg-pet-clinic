package guru.springframework.sfgpetclinic.views.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route("login")
@PageTitle("Login | Pet Clinic")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        //Makes LoginView full size and centers its content both horizontally and vertically,
        //by calling setAlignItems(Alignment.CENTER) and setJustifyContentMode(JustifyContentMode.CENTER).
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        //Sets the LoginForm action to "login" to post the login form to Spring Security
        login.setAction("login");

        add(
                new H1("Pet Clinic app"),
                login
        );
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        //inform the user about an authentication error
        //Reads query parameters and shows an error if a login attempt fails
        if(!beforeEnterEvent.getLocation()
        .getQueryParameters()
        .getParameters()
        .getOrDefault("error", Collections.emptyList())
        .isEmpty()){
            login.setError(true);
        }

    }
}
