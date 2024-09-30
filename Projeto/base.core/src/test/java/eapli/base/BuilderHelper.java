package eapli.base;

import java.util.Calendar;

/**
 * Classe para criação de algums métodos auxiliares aos testes unitários realizados.
 */
public class BuilderHelper {

    /**
     * Método para contruir uma data com ano, mês e dia.
     *
     * @return A data construída.
     */
    public static Calendar buildDate(final int year, final int month, final int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);

        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }

    /**
     * Método para contruir uma data com ano, mês, dia, hora e minutos.
     *
     * @return A data construída.
     */
    public static Calendar buildDateTime(final int year, final int month, final int day, final int hour, final int minute) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day, hour, minute);

        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date;
    }

    /**
     * Método para contruir a hora de início com hora e minutos.
     *
     * @return A hora de início construída.
     */
    public static Calendar buildTime(final int hour, final int minute) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, minute);

        return time;
    }
}
