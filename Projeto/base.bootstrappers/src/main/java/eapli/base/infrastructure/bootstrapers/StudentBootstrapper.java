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

public class StudentBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(StudentBootstrapper.class);

    @Override
    public boolean execute() {
        Calendar birthDate = Calendar.getInstance();

        birthDate.set(1990, Calendar.JANUARY, 15);
        register("joaosilva", "Senha123", "João", "Silva", "joao.silva@example.com", "286080494", birthDate);

        birthDate.set(1985, Calendar.MARCH, 8);
        register("anapereira", "Passw0rd", "Ana", "Pereira", "ana.pereira@example.com", "275220192", birthDate);

        birthDate.set(1992, Calendar.APRIL, 25);
        register("pedrogoncalves", "P3dro456", "Pedro", "Gonçalves", "pedro.goncalves@example.com", "260900966", birthDate);

        birthDate.set(1988, Calendar.JUNE, 12);
        register("mariasantos", "Mari@987", "Maria", "Santos", "maria.santos@example.com", "264174100", birthDate);

        birthDate.set(1991, Calendar.AUGUST, 21);
        register("ricardosilveira", "Ricard0!", "Ricardo", "Silveira", "ricardo.silveira@example.com", "256349169", birthDate);

        birthDate.set(1994, Calendar.MAY, 18);
        register("sofiamoreira", "Sof1@123", "Sofia", "Moreira", "sofia.moreira@example.com", "273215400", birthDate);

        birthDate.set(1993, Calendar.JULY, 27);
        register("andrelima", "L1m@123", "André", "Lima", "andre.lima@example.com", "296937037", birthDate);

        birthDate.set(1996, Calendar.MARCH, 30);
        register("isabelferreira", "Isabel1@", "Isabel", "Ferreira", "isabel.ferreira@example.com", "291455255", birthDate);

        birthDate.set(1987, Calendar.AUGUST, 8);
        register("paulosantos", "Paulo123", "Paulo", "Santos", "paulo.santos@example.com", "220543950", birthDate);

        birthDate.set(1995, Calendar.JANUARY, 3);
        register("julianasilva", "Juli@na12", "Juliana", "Silva", "juliana.silva@example.com", "262346311", birthDate);

        birthDate.set(1998, Calendar.MARCH, 22);
        register("leonardomartins", "M@rtins123", "Leonardo", "Martins", "leonardo.martins@example.com", "221659633", birthDate);

        birthDate.set(1997, Calendar.MAY, 14);
        register("carolinagomes", "CarolGomes1", "Carolina", "Gomes", "carolina.gomes@example.com", "287865266", birthDate);

        birthDate.set(1999, Calendar.APRIL, 17);
        register("henriquealves", "H3nriqu3", "Henrique", "Alves", "henrique.alves@example.com", "253467160", birthDate);

        birthDate.set(1994, Calendar.AUGUST, 5);
        register("luisrodrigues", "Lu1sRod", "Luís", "Rodrigues", "luis.rodrigues@example.com", "250739275", birthDate);

        birthDate.set(1997, Calendar.DECEMBER, 10);
        register("andreiacosta", "Andrei@567", "Andreia", "Costa", "andreia.costa@example.com", "268964440", birthDate);

        birthDate.set(1993, Calendar.OCTOBER, 17);
        register("bernardosilva", "Bernard0", "Bernardo", "Silva", "bernardo.silva@example.com", "286533324", birthDate);

        birthDate.set(1995, Calendar.SEPTEMBER, 21);
        register("patriciarios", "PatyR1os", "Patrícia", "Rios", "patricia.rios@example.com", "202226018", birthDate);

        birthDate.set(1991, Calendar.NOVEMBER, 30);
        register("marcosantos", "M@rcos123", "Marco", "Santos", "marco.santos@example.com", "234940581", birthDate);

        return true;
    }

    private void register(final String username, final String password, final String firstName, final String lastName,
                          final String email, final String taxPayerNumber, final Calendar birthDate) {
        // dependency injection - when constructing the object one must inject the dependencies to
        // infrastructure objects it needs. this should be handled by a DI/IoC container like Spring
        // Framework
        final AddUserController controller = new AddUserController(PersistenceContext.repositories().teachers(),
                PersistenceContext.repositories().students());
        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.STUDENT);

            controller.addStudent(username, password, firstName, lastName, email, roles, taxPayerNumber, birthDate);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
