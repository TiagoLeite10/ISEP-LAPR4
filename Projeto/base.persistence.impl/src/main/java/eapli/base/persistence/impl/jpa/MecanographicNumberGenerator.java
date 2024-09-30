package eapli.base.persistence.impl.jpa;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.Year;
import java.util.Properties;

/**
 * Gerador do número mecanográfico para a instância de Student. O número mecanográfico deve seguir o formato "202300001"
 * que contêm o ano da matrícula e um número sequencial.
 * .
 *
 * @author Gabriel Gonçalves (1191296)
 */
public class MecanographicNumberGenerator extends SequenceStyleGenerator {

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%05d";

    private String format;

    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object object) throws HibernateException {
        final var prefix = String.valueOf(Year.now().getValue());
        final var seq = super.generate(session, object);

        return MecanographicNumber.valueOf(String.format(format, prefix, seq));
    }

    @Override
    public void configure(final Type type, final Properties params, final ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        final String numberFormat = ConfigurationHelper
                .getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT).replace("%", "%2$");
        this.format = "%1$s" + numberFormat;
    }
}
