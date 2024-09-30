package eapli.base.takenexammanagement.application;

import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.repositories.StudentRepository;
import eapli.base.takenexammanagement.dto.GradePerStudent;
import eapli.base.takenexammanagement.repositories.TakenExamRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

/**
 * Controlador para listar as notas dos exames de um aluno.
 */
@UseCaseController
public class ListGradesController {
    private final AuthorizationService authz;

    private final StudentRepository studentRepository;

    private final TakenExamRepository takenExamRepository;


    public ListGradesController(final AuthorizationService authz, final StudentRepository studentRepository,
                                final TakenExamRepository takenExamRepository) {
        this.authz = authz;
        this.studentRepository = studentRepository;
        this.takenExamRepository = takenExamRepository;
    }

    private Optional<Student> currentUserStudent() {
        return authz.session().flatMap(s -> studentRepository.findByUsername(s.authenticatedUser().username()));
    }

    public Iterable<GradePerStudent> listAllGrades() {
        final Student student = currentUserStudent().orElseThrow(IllegalStateException::new);
        return takenExamRepository.findAllGrades(student);
    }
}
