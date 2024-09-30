package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BoardElement implements ValueObject {

    private final String title;

    public BoardElement(String title) {
        this.title = title;
    }

    protected BoardElement() {
        this.title = null;
    }

    public String title() {
        return this.title;
    }
}
