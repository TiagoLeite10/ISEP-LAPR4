package eapli.base.eventmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Calendar;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeetingBuilderTest {

    private final String TITLE = "Meeting title";

    private final Calendar DATE = BuilderHelper.buildDate(3000, Calendar.AUGUST, 9);

    private final Integer DURATION = 60;

    private final SystemUser OWNER_USER = UserBuilderHelper.builder().with("manuela", "Password1",
            "Manuela", "Silva", "manuela@gmail.com").withRoles(BaseRoles.TEACHER).build();

    /**
     * Teste para garantir que é possível construir uma reunião com data, duração e dono.
     */
    @Test
    public void ensureCanBuildMeetingWithDurationDateOwnerUser() {
        final Meeting subject = new MeetingBuilder().with(TITLE, DATE, DURATION, OWNER_USER).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir uma reunião com título nulo.
     */
    @Test
    public void ensureCannotBuildMeetingWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(null).withEventDate(DATE).
                withEventDuration(DURATION).withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião com título vazio.
     */
    @Test
    public void ensureCannotBuildMeetingWithEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle("").withEventDate(DATE).
                withEventDuration(DURATION).withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião sem título.
     */
    @Test
    public void ensureCannotBuildMeetingWithoutTitle() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withEventDate(DATE).
                withEventDuration(DURATION).withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião com data nula.
     */
    @Test
    public void ensureCannotBuildMeetingWithNullDate() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(TITLE).withEventDate(null).
                withEventDuration(DURATION).withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião sem data.
     */
    @Test
    public void ensureCannotBuildMeetingWithoutDate() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(TITLE).
                withEventDuration(DURATION).withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião com duração nula.
     */
    @Test
    public void ensureCannotBuildMeetingWithNullDuration() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(TITLE).withEventDate(DATE).
                withEventDuration(null).withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião sem duração.
     */
    @Test
    public void ensureCannotBuildMeetingWithoutDuration() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(TITLE).withEventDate(DATE).
                withOwnerUser(OWNER_USER).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião com dono nulo.
     */
    @Test
    public void ensureCannotBuildMeetingWithNullOwnerUser() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(TITLE).withEventDate(DATE).
                withEventDuration(DURATION).withOwnerUser(null).build());
    }

    /**
     * Teste para garantir que não é possível construir uma reunião sem dono.
     */
    @Test
    public void ensureCannotBuildMeetingWithoutOwnerUser() {
        assertThrows(IllegalArgumentException.class, () -> new MeetingBuilder().withTitle(TITLE).withEventDate(DATE).
                withEventDuration(DURATION).build());
    }
}
