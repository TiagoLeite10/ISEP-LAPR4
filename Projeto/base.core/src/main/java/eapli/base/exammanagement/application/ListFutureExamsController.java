package eapli.base.exammanagement.application;

import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repositories.ExamRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Controlador para listar os futuros exames de um aluno.
 */
@UseCaseController
public class ListFutureExamsController {

    private final ListExamService svc;

    public ListFutureExamsController(final AuthorizationService authz, final ExamRepository repo) {
        // dependency injection - only the external plugable dependencies are injected.
        svc = new ListExamService(authz, repo);
    }

    public Iterable<Exam> listFutureExams(final Username user) {
        return svc.allFutureExams(user);
    }

}
