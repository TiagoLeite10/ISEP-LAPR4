package eapli.base.app.kiosk.console.presentation;

import eapli.base.postitmanagement.domain.PostIt;
import eapli.framework.visitor.Visitor;

public class PostItPrinter implements Visitor<PostIt> {

    @Override
    public void visit(PostIt visitee) {
        System.out.printf("%-50s %-20s %-20s %-20s", visitee.content(), visitee.line(),
                visitee.column(), visitee.type());
    }
}
