package arkham.knight.practica13.views;

import arkham.knight.practica13.models.Usuario;
import arkham.knight.practica13.utils.email.EmailService;
import arkham.knight.practica13.services.UsuarioService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.Random;

@Route("")
public class LogIn extends VerticalLayout implements BeforeEnterObserver {

    TextField textField;
    PasswordField passwordField;
    Button button;

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Autowired
    private EmailService emailService;

    public LogIn(@Autowired UsuarioService usuarioServices) {

        if (usuarioServices.listarUsuarios().size() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setAdmin(true);
            usuarioServices.crearUsuario(admin);
            Usuario usuario = new Usuario();
            usuario.setUsername("usuario");
            usuario.setPassword("1234");
            usuario.setAdmin(false);
            usuarioServices.crearUsuario(usuario);
        }
        H1 title = new H1();
        title.getStyle().set("color", "blue");
        Icon icon = VaadinIcon.ABACUS.create();
        icon.setSize("30px");
        icon.getStyle().set("top", "-4px");
        title.add(icon);
        title.add(new Text(" Encuestas"));

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

                if (usuarioServices.login(textField.getValue(), passwordField.getValue()) != null) {
                    VaadinSession.getCurrent().getSession().setAttribute("usuario", usuarioServices.login(textField.getValue(), passwordField.getValue()));
                    if (getUI().isPresent() && usuarioServices.login(textField.getValue(), passwordField.getValue()).isAdmin())
                        getUI().get().navigate(GraficosView.class);
                    else
                        getUI().get().navigate(EncuestaView.class);
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


        //registrarse

        Dialog dialog = new Dialog();
        VerticalLayout verticalDialog = new VerticalLayout();
        verticalDialog.setWidthFull();

        TextField usuarioField = new TextField();
        usuarioField.setLabel("Usuario");

        EmailField emailField = new EmailField();
        emailField.setLabel("Email");

        Button registrarse = new Button("Registrarse");
        verticalDialog.add(usuarioField, emailField, registrarse);
        dialog.add(verticalDialog);
        Notification notification = new Notification(
                "El usuario ya existe", 3000);
        registrarse.addClickListener(event -> {
            dialog.close();
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setAdmin(false);
            nuevoUsuario.setUsername(usuarioField.getValue());
            nuevoUsuario.setPassword(generatePassword());
            nuevoUsuario.setEmail(emailField.getValue());
            String pass = generatePassword();
            usuarioServices.crearUsuario(new Usuario(usuarioField.getValue(), pass, emailField.getValue(), false));
            emailService.sendSimpleMessage(emailField.getValue(),
                    "Registro de pagina para el usuario " + usuarioField.getValue(),
                    usuarioField.getValue() + ", su contraseña es la siguiente: " + pass);
            notification.setText("Usuario creado existosamente, verifique su email para completar el registro");
        });

        Button registro = new Button("Registrar Nuevo Usuario");
        registro.addClickListener(event -> dialog.open());
        verticalLayout.add(title, textField, passwordField, button, registro);
        horizontalLayout.add(verticalLayout);
        add(horizontalLayout);
    }

    private String generatePassword() {
        StringBuilder returnValue = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("usuario");
        if (VaadinSession.getCurrent().getSession().getAttribute("usuario") != null && usuario.isAdmin())
            event.forwardTo("graphs");
        else if (VaadinSession.getCurrent().getSession().getAttribute("usuario") != null && !usuario.isAdmin()) {
            event.forwardTo("encuesta");
        }
    }
}
