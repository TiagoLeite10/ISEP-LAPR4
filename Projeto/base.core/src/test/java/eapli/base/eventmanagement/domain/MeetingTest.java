package eapli.base.eventmanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe Meeting.
 */
public class MeetingTest {

    private final String TITLE = "Meeting title";

    private final EventDate EVENT_DATE = EventDate.valueOf(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0));

    private final EventDuration EVENT_DURATION = EventDuration.valueOf(90);

    private final SystemUser OWNER_USER = UserBuilderHelper.builder().with("MarcoPaulo", "Password1",
            "Marco", "Paulo", "marcopaulo@gmail.com").build();

    private final SystemUser USER = UserBuilderHelper.builder().with("JoaoPedro", "Password1",
            "João", "Lima", "joaopedro@gmail.com").build();

    private final SystemUser OTHER_USER = UserBuilderHelper.builder().with("luisa", "Password1",
            "Luísa", "Sobral", "luisa@gmail.com").build();

    /**
     * Método para contruir uma reunião.
     *
     * @return A reunião construída.
     */
    private Meeting buildMeeting() {
        return new Meeting(TITLE, EVENT_DATE, EVENT_DURATION, OWNER_USER);
    }

    /**
     * Teste para garantir que uma reunião tem de possuir um título.
     */
    @Test
    void ensureMeetingHasTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(null, EVENT_DATE, EVENT_DURATION, OWNER_USER));
    }

    /**
     * Teste para garantir que uma reunião tem de possuir um título não vazio.
     */
    @Test
    void ensureMeetingHasNotEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting("", EVENT_DATE, EVENT_DURATION, OWNER_USER));
    }

    /**
     * Teste para garantir que uma reunião tem de possuir uma data.
     */
    @Test
    void ensureMeetingHasDate() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(TITLE, null, EVENT_DURATION, OWNER_USER));
    }

    /**
     * Teste para garantir que uma reunião tem de possuir uma duração.
     */
    @Test
    void ensureMeetingHasDuration() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(TITLE, EVENT_DATE, null, OWNER_USER));
    }

    /**
     * Teste para garantir que uma reunião tem de possuir um dono.
     */
    @Test
    void ensureMeetingHasOwnerUser() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(TITLE, EVENT_DATE, EVENT_DURATION, null));
    }

    /**
     * Teste para garantir que uma reunião tem o título esperado.
     */
    @Test
    void ensureMeetingHasExpectedTitle() {
        final Meeting subject = buildMeeting();

        assertEquals(TITLE, subject.title());
    }

    /**
     * Teste para garantir que uma reunião tem a data de início esperada.
     */
    @Test
    void ensureMeetingHasExpectedStartDate() {
        final Meeting subject = buildMeeting();

        assertEquals(EVENT_DATE, subject.eventDate());
    }

    /**
     * Teste para garantir que uma reunião tem a data de fim esperada.
     */
    @Test
    void ensureMeetingHasExpectedEndDate() {
        final Meeting subject = buildMeeting();

        assertEquals(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 16, 30), subject.eventEndDate());
    }

    /**
     * Teste para garantir que uma reunião não tem a data de fim esperada.
     */
    @Test
    void ensureMeetingNotHasExpectedEndDate() {
        final Meeting subject = buildMeeting();

        assertNotEquals(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 16, 29), subject.eventEndDate());
    }

    /**
     * Teste para garantir que uma reunião tem a duração esperada.
     */
    @Test
    void ensureMeetingHasExpectedDuration() {
        final Meeting subject = buildMeeting();

        assertEquals(EVENT_DURATION, subject.eventDuration());
    }

    /**
     * Teste para garantir que uma reunião é anterior a uma data.
     */
    @Test
    void ensureMeetingEventDateLessThanDate() {
        final Meeting subject = buildMeeting();

        assertTrue(subject.eventDateLessThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 23, 0)));
    }

    /**
     * Teste para garantir que uma reunião não é anterior a uma data.
     */
    @Test
    void ensureMeetingEventDateNotLessThanDate() {
        final Meeting subject = buildMeeting();

        assertFalse(subject.eventDateLessThan(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 11, 0)));
    }

    /**
     * Teste para garantir que é possível convidar um utilizador para uma reunião e colocar este convite no estado 'INVITED'.
     */
    @Test
    void ensureMeetingInvitationPossibleStatusInvited() {
        final Meeting subject = buildMeeting();
        subject.addMeetingInvitation(USER);
        subject.invitedMeetingInvitation(USER);

        assertTrue(subject.meetingInvitation(USER).isInvited());
    }

    /**
     * Teste para garantir que é possível convidar um utilizador para uma reunião e colocar este convite no estado 'CANCELED'.
     */
    @Test
    void ensureMeetingInvitationPossibleStatusCanceled() {
        final Meeting subject = buildMeeting();
        subject.addMeetingInvitation(USER);
        subject.cancelMeetingInvitation(USER);

        assertTrue(subject.meetingInvitation(USER).isCanceled());
    }

    /**
     * Teste para garantir que é possível convidar um utilizador para uma reunião e colocar este convite no estado 'ACCEPTED'.
     */
    @Test
    void ensureMeetingInvitationPossibleStatusAccepted() {
        final Meeting subject = buildMeeting();
        subject.addMeetingInvitation(USER);
        subject.acceptedMeetingInvitation(USER);

        assertTrue(subject.meetingInvitation(USER).isAccepted());
    }

    /**
     * Teste para garantir que é possível convidar um utilizador para uma reunião e colocar este convite no estado 'REFUSED'.
     */
    @Test
    void ensureMeetingInvitationPossibleStatusRefused() {
        final Meeting subject = buildMeeting();
        subject.addMeetingInvitation(USER);
        subject.refusedMeetingInvitation(USER);

        assertTrue(subject.meetingInvitation(USER).isRefused());
    }

    /**
     * Teste para garantir que não é possível encontrar o convite para uma reunião de um utilizador que não foi convidado.
     */
    @Test
    void ensureMeetingInvitationNotFoundForUserNotAdded() {
        final Meeting subject = buildMeeting();

        assertNull(subject.meetingInvitation(USER));
    }

    /**
     * Teste para garantir que o número de convites aceites para uma reunião é o esperado.
     */
    @Test
    void ensureMeetingHasExpectedInvitationNumberWithStatusAccepted() {
        final Meeting subject = buildMeeting();

        final Set<SystemUser> users = new HashSet<>();
        users.add(USER);
        users.add(OTHER_USER);

        subject.addMeetingInvitationList(users);
        subject.acceptedMeetingInvitation(USER);
        subject.refusedMeetingInvitation(OTHER_USER);

        assertEquals(1, subject.numberAcceptedParticipants());
    }

    /*
    // AVISO: Este teste encontra-se comentado, pois para executá-lo é necessário aguardar 1 minuto até o assertThrows ser executado.
    // Teste para garantir que não é possível cancelar uma reunião que já tenha sido realizada.
    @Test
    void ensureCannotCancelMeetingThatHasAlreadyTakenPlace() throws InterruptedException {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.MINUTE, 1);
        final Meeting subject = new Meeting(EventDate.valueOf(date), EVENT_DURATION, OWNER_USER);


        // Aguarda um minuto antes de executar o assertThrows
        Thread.sleep(60 * 1000);
        assertThrows(MeetingNotHeldException.class, subject::cancelMeeting);
    }
    */

    /**
     * Teste para garantir que é possível cancelar uma reunião que ainda não tenha sido realizada.
     */
    @Test
    void ensurePossibleCancelMeetingThatHasNotYetTakenPlace() {
        final Meeting subject = buildMeeting();
        subject.cancelMeeting();

        assertTrue(subject.canceled());
    }

    /**
     * Teste para garantir que duas reuniões são iguais para a mesma data, duração e dono.
     */
    @Test
    void ensureTwoMeetingWithSameDatesDurationsOwnerUsersAreTheSame() {
        final Meeting aMeeting = buildMeeting();
        final Meeting anotherMeeting = new MeetingBuilder().withTitle(TITLE).
                withEventDate(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0)).
                withEventDuration(90).withOwnerUser(OWNER_USER).build();

        assertTrue(aMeeting.sameAs(anotherMeeting));
    }

    /**
     * Teste para garantir que duas reuniões não são iguais para uma data diferente.
     */
    @Test
    void ensureTwoMeetingWithDifferentDatesAreNotTheSame() {
        final Meeting aMeeting = buildMeeting();
        final Meeting anotherMeeting = new MeetingBuilder().withTitle(TITLE).
                withEventDate(BuilderHelper.buildDateTime(3001, Calendar.JANUARY, 22, 16, 30)).
                withEventDuration(90).withOwnerUser(OWNER_USER).build();

        assertFalse(aMeeting.sameAs(anotherMeeting));
    }

    /**
     * Teste para garantir que duas reuniões não são iguais para uma duração diferente.
     */
    @Test
    void ensureTwoMeetingWithDifferentDurationsAreNotTheSame() {
        final Meeting aMeeting = buildMeeting();
        final Meeting anotherMeeting = new MeetingBuilder().withTitle(TITLE).
                withEventDate(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0)).
                withEventDuration(60).withOwnerUser(OWNER_USER).build();

        assertFalse(aMeeting.sameAs(anotherMeeting));
    }

    /**
     * Teste para garantir que duas reuniões não são iguais para um dono diferente.
     */
    @Test
    void ensureTwoMeetingWithDifferentOwnerUsersAreNotTheSame() {
        final Meeting aMeeting = buildMeeting();
        final Meeting anotherMeeting = new MeetingBuilder().withTitle(TITLE).
                withEventDate(BuilderHelper.buildDateTime(3000, Calendar.AUGUST, 23, 15, 0)).
                withEventDuration(90).withOwnerUser(USER).build();

        assertFalse(aMeeting.sameAs(anotherMeeting));
    }

    /**
     * Teste para garantir que uma reunião é a mesma para a mesma instância.
     */
    @Test
    void ensureMeetingAreTheSameForTheSameInstance() {
        final Meeting aMeeting = new Meeting();

        assertTrue(aMeeting.sameAs(aMeeting));
    }

    /**
     * Teste para garantir que uma reunião não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureMeetingNotTheSameForDifferenteObjectTypes() {
        final Meeting aMeeting = buildMeeting();

        assertFalse(aMeeting.sameAs(USER));
    }
}
