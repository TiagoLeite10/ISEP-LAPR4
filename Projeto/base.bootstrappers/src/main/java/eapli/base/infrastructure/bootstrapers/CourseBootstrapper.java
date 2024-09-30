package eapli.base.infrastructure.bootstrapers;

import eapli.base.coursemanagement.application.CreateCourseController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CourseBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(CourseBootstrapper.class);

    @Override
    public boolean execute() {
        register("Java-1", "Curso de Java", "Introdução ao desenvolvimento em Java", 3, 14);
        register("Python-1", "Curso de Python", "Aprenda a programar em Python", 5, 20);
        register("C-1", "Curso de C", "Fundamentos da linguagem C", 2, 10);
        register("JavaScript-1", "Curso de JavaScript", "Desenvolvimento web com JavaScript", 4, 16);
        register("HTML-1", "Curso de HTML", "Crie páginas web com HTML", 2, 12);
        register("CSS-1", "Curso de CSS", "Estilize suas páginas web com CSS", 2, 12);
        register("SQL-1", "Curso de SQL", "Banco de dados e consultas SQL", 3, 15);
        register("Android-1", "Curso de Android", "Desenvolvimento de aplicativos Android", 5, 20);
        register("iOS-1", "Curso de iOS", "Criação de aplicativos para iOS", 4, 18);
        register("Ruby-1", "Curso de Ruby", "Programação em Ruby", 3, 12);
        register("PHP-1", "Curso de PHP", "Desenvolvimento web com PHP", 3, 14);
        register("React-1", "Curso de React", "Construa interfaces com React", 4, 16);
        register("Angular-1", "Curso de Angular", "Desenvolvimento de aplicações com Angular", 4, 16);
        register("NodeJS-1", "Curso de Node.js", "Desenvolvimento de servidores com Node.js", 3, 14);
        register("Swift-1", "Curso de Swift", "Programação em Swift para iOS", 3, 12);
        return true;
    }

    private void register(final String courseCode, final String courseTitle, final String courseDescription, final int minimumEnrolments, final int maximumEnrolments) {
        // dependency injection - when constructing the object one must inject the dependencies to
        // infrastructure objects it needs. this should be handled by a DI/IoC container like Spring
        // Framework
        final CreateCourseController controller = new CreateCourseController(
                AuthzRegistry.authorizationService(), PersistenceContext.repositories().courses());
        try {
            controller.createCourse(courseCode, courseTitle, courseDescription, minimumEnrolments, maximumEnrolments);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", courseCode);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
