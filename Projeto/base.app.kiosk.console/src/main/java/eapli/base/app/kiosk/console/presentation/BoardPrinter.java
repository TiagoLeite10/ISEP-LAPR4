package eapli.base.app.kiosk.console.presentation;

import eapli.base.boardmanagement.domain.Board;
import eapli.framework.visitor.Visitor;

public class BoardPrinter implements Visitor<Board> {

    @Override
    public void visit(Board visitee) {
        System.out.printf("%-30s %-30s %-30s", visitee.title(), visitee.creationDateTime(),
                visitee.createdBy());
    }
}
