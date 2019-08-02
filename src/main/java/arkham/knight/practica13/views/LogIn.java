package arkham.knight.practica13.views;

import arkham.knight.practica13.Models.Usuario;
import arkham.knight.practica13.Services.UsuarioService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class LogIn extends VerticalLayout {

    TextField textField;
    PasswordField passwordField;
    Button button;

    private UsuarioService usuarioServices;

    public LogIn(@Autowired UsuarioService usuarioServices) {
        this.usuarioServices = usuarioServices;
        H1 title = new H1();
        title.getStyle().set("color", "blue");
        Icon icon = VaadinIcon.CHART_3D.create();
        icon.setSize("30px");
        icon.getStyle().set("top", "-4px");
        title.add(icon);
        title.add(new Text(" Temp Dynamics"));

        textField = new TextField("Usuario");
        passwordField = new PasswordField("Contraseña");
        button = new Button("Log in");

        textField.setRequired(true);
        passwordField.setRequired(true);
        passwordField.setRevealButtonVisible(true);

        button.addClickListener(event -> {

            if (textField.getValue().isEmpty()) {
                textField.setRequiredIndicatorVisible(true);
                textField.setInvalid(true);
            }

            if (passwordField.getValue().isEmpty()) {
                passwordField.setInvalid(true);
                passwordField.setRequiredIndicatorVisible(true);
            }

            if (!textField.isEmpty() && !passwordField.isEmpty()) {

                Usuario usuario = usuarioServices.login(textField.getValue(), passwordField.getValue());

                if (usuario != null) {
                    VaadinSession.getCurrent().getSession().setAttribute("usuario", usuario);
                    if (getUI().isPresent())
                        getUI().get().navigate(Encuesta.class);
                } else {

                    Dialog dialog = new Dialog();

                    dialog.add(new VerticalLayout(new H3("El usuario no fue encontrado!")));
                    dialog.open();


                    textField.setRequiredIndicatorVisible(true);
                    passwordField.setRequiredIndicatorVisible(true);

                }
            }

        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setSizeFull();

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.setAlignSelf(Alignment.CENTER, textField, passwordField);
        verticalLayout.setSizeFull();

        verticalLayout.add(title, textField, passwordField, button);

        horizontalLayout.add(verticalLayout);

        add(horizontalLayout);
    }

}
