package eapli.base.boardmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Representa a entidade BoardMember.
 */
@Entity
public class BoardMember implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar joinedDate;

    @ManyToOne(optional = false)
    private SystemUser member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessPermission accessPermission;

    public BoardMember(final SystemUser member, final AccessPermission accessPermission) {
        Preconditions.noneNull(member, accessPermission, "Board members must not contain null user!");

        this.joinedDate = Calendar.getInstance();
        this.member = member;
        this.accessPermission = accessPermission;
    }

    protected BoardMember() {
        // for ORM only
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof BoardMember)) {
            return false;
        }

        final BoardMember that = (BoardMember) other;
        if (this == other) {
            return true;
        }

        return member.equals(that.member);
    }

    @Override
    public boolean equals(final Object o) {
        return sameAs(o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Name: " + this.member.name() + "\nJoined date: "
                + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(this.joinedDate.getTime())
                + "\nAccess Permissions: " + this.accessPermission.toString();
    }
}
