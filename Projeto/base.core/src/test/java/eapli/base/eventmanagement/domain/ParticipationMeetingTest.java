package eapli.base.eventmanagement.domain;

import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe para realizar testes à classe ParticipationMeeting.
 */
public class ParticipationMeetingTest {

    private final SystemUser USER = UserBuilderHelper.builder().with("MarcoPaulo", "Password1",
            "Marco", "Paulo", "marcopaulo@gmail.com").build();

    private final SystemUser OTHER_USER = UserBuilderHelper.builder().with("luisa", "Password1",
            "Luísa", "Sobral", "luisa@gmail.com").build();

    /**
     * Método para contruir uma participação numa reunião.
     *
     * @return A participação numa reunião.
     */
    private ParticipationMeeting buildParticipationMeeting() {
        return new ParticipationMeeting(USER);
    }

    /**
     * Teste para garantir que uma participação numa reunião tem de possuir um utilizador.
     */
    @Test
    void ensureParticipationMeetingHasUser() {
        assertThrows(IllegalArgumentException.class, () -> new ParticipationMeeting(null));
    }

    /**
     * Teste para garantir que quando uma participação numa reunião é criada tem um estado 'CREATED'.
     */
    @Test
    void ensureParticipationMeetingCreateHasStatusCreated() {
        final ParticipationMeeting subject = buildParticipationMeeting();

        assertTrue(subject.isCreated());
    }

    /**
     * Teste para garantir que é possível colocar no estado 'INVITED' uma participação numa reunião.
     */
    @Test
    void ensureParticipationMeetingPossibleStatusInvited() {
        final ParticipationMeeting subject = buildParticipationMeeting();
        subject.invitedParticipation();

        assertTrue(subject.isInvited());
    }

    /**
     * Teste para garantir que é possível colocar no estado 'CANCELED' uma participação numa reunião.
     */
    @Test
    void ensureParticipationMeetingPossibleStatusCanceled() {
        final ParticipationMeeting subject = buildParticipationMeeting();
        subject.canceledParticipation();

        assertTrue(subject.isCanceled());
    }

    /**
     * Teste para garantir que é possível colocar no estado 'ACCEPTED' uma participação numa reunião.
     */
    @Test
    void ensureParticipationMeetingPossibleStatusAccepted() {
        final ParticipationMeeting subject = buildParticipationMeeting();
        subject.acceptedParticipation();

        assertTrue(subject.isAccepted());
    }

    /**
     * Teste para garantir que é possível colocar no estado 'REFUSED' uma participação numa reunião.
     */
    @Test
    void ensureParticipationMeetingPossibleStatusRefused() {
        final ParticipationMeeting subject = buildParticipationMeeting();
        subject.refusedParticipation();

        assertTrue(subject.isRefused());
    }

    /**
     * Teste para garantir que duas participações numa reunião são iguais para o mesmo utilizador.
     */
    @Test
    void ensureTwoParticipationMeetingWithSameUsersAreTheSame() {
        final ParticipationMeeting aParticipationMeeting = buildParticipationMeeting();
        final ParticipationMeeting anotherParticipationMeeting = new ParticipationMeeting(USER);

        assertTrue(aParticipationMeeting.sameAs(anotherParticipationMeeting));
    }

    /**
     * Teste para garantir que duas participações numa reunião não são iguais para um utilizador diferente.
     */
    @Test
    void ensureTwoParticipationMeetingWithDifferentUsersAreNotTheSame() {
        final ParticipationMeeting aParticipationMeeting = buildParticipationMeeting();
        final ParticipationMeeting anotherParticipationMeeting = new ParticipationMeeting(OTHER_USER);

        assertFalse(aParticipationMeeting.sameAs(anotherParticipationMeeting));
    }

    /**
     * Teste para garantir que uma participação numa reunião é a mesma para a mesma instância.
     */
    @Test
    void ensureParticipationMeetingAreTheSameForTheSameInstance() {
        final ParticipationMeeting aParticipationMeeting = new ParticipationMeeting();

        assertTrue(aParticipationMeeting.sameAs(aParticipationMeeting));
    }

    /**
     * Teste para garantir que uma participação numa reunião não é a mesma para diferentes tipos de objetos.
     */
    @Test
    void ensureParticipationMeetingNotTheSameForDifferenteObjectTypes() {
        final ParticipationMeeting aParticipationMeeting = buildParticipationMeeting();

        assertFalse(aParticipationMeeting.sameAs(USER));
    }

    /**
     * Teste para verificar que o pedido para participação na meeting é aceite com sucesso se estiver no estado invited.
     */
    @Test
    void ensureParticipationMeetingIsSuccessfullyAccepted() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.invitedParticipation();
        subject.acceptMeeting(true);
        assertTrue(subject.isAccepted());
    }

    /**
     * Teste para verificar que o pedido para participação na meeting é recusado com sucesso se estiver no estado
     * invited.
     */
    @Test
    void ensureParticipationMeetingIsSuccessfullyRejected() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.invitedParticipation();
        subject.acceptMeeting(false);
        assertTrue(subject.isRefused());
    }

    /**
     * Teste para verificar que não é possível aceitar um convite se este ainda estiver no estado created.
     */
    @Test
    void ensureParticipationMeetingCannotBeAcceptedWhenInCreatedState() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(true));
    }

    /**
     * Teste para verificar que não é possível recusar um convite se este ainda estiver no estado created.
     */
    @Test
    void ensureParticipationMeetingCannotBeRefusedWhenInCreatedState() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(false));
    }

    /**
     * Teste para verificar que não é possível aceitar um convite se este já tiver sido aceite.
     */
    @Test
    void ensureParticipationMeetingCannotBeAcceptAfterBeingAccepted() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.acceptedParticipation();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(true));
    }

    /**
     * Teste para verificar que não é possível recusar um convite se este já tiver sido aceite.
     */
    @Test
    void ensureParticipationMeetingCannotBeRefusedAfterBeingAccepted() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.acceptedParticipation();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(false));
    }

    /**
     * Teste para verificar que não é possível aceitar um convite se este já tiver sido recusado.
     */
    @Test
    void ensureParticipationMeetingCannotBeAcceptAfterBeingRefused() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.refusedParticipation();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(true));
    }

    /**
     * Teste para verificar que não é possível recusar um convite se este já tiver sido recusado.
     */
    @Test
    void ensureParticipationMeetingCannotBeRefusedAfterBeingRefused() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.refusedParticipation();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(false));
    }

    /**
     * Teste para verificar que não é possível aceitar um convite se este já tiver sido cancelado.
     */
    @Test
    void ensureParticipationMeetingCannotBeAcceptAfterBeingCancelled() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.canceledParticipation();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(true));
    }

    /**
     * Teste para verificar que não é possível recusar um convite se este já tiver sido cancelado.
     */
    @Test
    void ensureParticipationMeetingCannotBeRefusedAfterBeingCancelled() {
        ParticipationMeeting subject = this.buildParticipationMeeting();
        subject.canceledParticipation();
        assertThrows(IllegalStateException.class, () -> subject.acceptMeeting(false));
    }
}
