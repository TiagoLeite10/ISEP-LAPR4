package eapli.base.app.kiosk.console.presentation;

import eapli.base.boardmanagement.domain.AccessPermission;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.visitor.Visitor;
import org.springframework.data.util.Pair;

public class MemberPrinter implements Visitor<Pair<SystemUser, AccessPermission>> {

    @Override
    public void visit(Pair<SystemUser, AccessPermission> visitee) {
        System.out.printf("%-50s %-30s ", visitee.getFirst().name(),
                visitee.getSecond());
    }
}
