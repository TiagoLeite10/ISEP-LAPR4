package eapli.base.infrastructure.bootstrapers;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class TeacherBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(TeacherBootstrapper.class);

    @Override
    public boolean execute() {
        Calendar birthDate = Calendar.getInstance();

        birthDate.set(1990, Calendar.JANUARY, 15);
        register("joaodasilva", "Senha123", "João", "da Silva", "joao.silva@example.com", "236033573", birthDate, "ABCD");

        birthDate.set(1985, Calendar.MARCH, 8);
        register("anacouto", "Passw0rd", "Ana", "Pereira", "ana.pereira@example.com", "236057707", birthDate, "EFGH");

        birthDate.set(1992, Calendar.APRIL, 25);
        register("pedrosilva", "P3dro456", "Pedro", "Gonçalves", "pedro.goncalves@example.com", "277111846", birthDate, "AAKL");

        birthDate.set(1988, Calendar.JUNE, 12);
        register("mariaalmeida", "M@ria789", "Maria", "Almeida", "maria.almeida@example.com", "290399700", birthDate, "MNOA");

        birthDate.set(1991, Calendar.AUGUST, 21);
        register("carlossantos", "Car10s@", "Carlos", "Santos", "carlos.santos@example.com", "272801216", birthDate, "QRST");

        birthDate.set(1994, Calendar.MAY, 18);
        register("fernandalima", "Fern@nda123", "Fernanda", "Lima", "fernanda.lima@example.com", "264713680", birthDate, "UVWX");

        birthDate.set(1993, Calendar.JULY, 27);
        register("rafaelcosta", "Rafael456", "Rafael", "Costa", "rafael.costa@example.com", "268509824", birthDate, "YZAB");

        birthDate.set(1996, Calendar.MARCH, 30);
        register("camilasilva", "C@mila789", "Camila", "da Silva", "camila.silva@example.com", "255850654", birthDate, "CDEF");

        birthDate.set(1987, Calendar.AUGUST, 8);
        register("andreoliveira", "Andr3@", "André", "Oliveira", "andre.oliveira@example.com", "202020720", birthDate, "GHIJ");

        birthDate.set(1995, Calendar.JANUARY, 3);
        register("julianamartins", "Jul1@n4", "Juliana", "Martins", "juliana.martins@example.com", "238420574", birthDate, "KLMN");

        birthDate.set(1998, Calendar.MARCH, 22);
        register("miguelrodrigues", "Miguel123", "Miguel", "Rodrigues", "miguel.rodrigues@example.com", "200430297", birthDate, "OPQR");

        birthDate.set(1997, Calendar.MAY, 14);
        register("gabrielalopes", "G@briel10", "Gabriela", "Lopes", "gabriela.lopes@example.com", "297760017", birthDate, "STUV");

        birthDate.set(1990, Calendar.JANUARY, 15);
        register("josealves", "Senha@123", "José", "Alves", "jose.alves@example.com", "240228480", birthDate, "WXYZ");

        birthDate.set(1985, Calendar.MARCH, 8);
        register("marialima", "M@ria123", "Maria", "Lima", "maria.lima@example.com", "221987940", birthDate, "AEND");

        birthDate.set(1992, Calendar.APRIL, 25);
        register("andresantos", "Andr3s@", "André", "Santos", "andre.santos@example.com", "230697020", birthDate, "EQQH");

        birthDate.set(1988, Calendar.JUNE, 12);
        register("carolcosta", "Carol789", "Carolina", "Costa", "carolina.costa@example.com", "272278483", birthDate, "IJKL");

        birthDate.set(1991, Calendar.AUGUST, 21);
        register("paulomartins", "P@ul0M", "Paulo", "Martins", "paulo.martins@example.com", "211486981", birthDate, "MNOP");

        return true;
    }

    private void register(final String username, final String password, final String firstName, final String lastName,
                          final String email, final String taxPayerNumber, final Calendar birthDate, final String acronym) {
        // dependency injection - when constructing the object one must inject the dependencies to
        // infrastructure objects it needs. this should be handled by a DI/IoC container like Spring
        // Framework
        final AddUserController controller = new AddUserController(PersistenceContext.repositories().teachers(),
                PersistenceContext.repositories().students());
        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.TEACHER);

            controller.addTeacher(username, password, firstName, lastName, email, roles, taxPayerNumber, birthDate, acronym);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
