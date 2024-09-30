package eapli.base.enrollmentmanagement.domain;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.coursemanagement.domain.CourseCode;
import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import org.springframework.data.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BulkEnrollmentFileCSV implements BulkEnrollmentFile {
    @Override
    public List<Pair<MecanographicNumber, CourseCode>> readFile(String filePath) {
        if (filePath == null)
            throw new IllegalArgumentException("The file path cannot be null!");

        List<Pair<MecanographicNumber, CourseCode>> fileData = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int fileLine = 1;

            scanner.nextLine(); // Ler e ignorar o cabeçalho

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length > 2) {
                    throw new ErrorInFileException("There is an error in the file (invalid number of arguments " +
                            "separeted by comma)!", fileLine);
                }

                MecanographicNumber mecanographicNumber = null;
                try {
                    mecanographicNumber = MecanographicNumber.valueOf(data[MECANOGRAPHIC_NUMBER_INDEX]);
                } catch (IllegalArgumentException ex) {
                    throw new ErrorInFileException("The student mechanographic number have and incorret format!", fileLine);
                }

                fileData.add(Pair.of(mecanographicNumber, CourseCode.valueOf(data[COURSE_CODE_INDEX])));

                fileLine++;
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file doesn't exists!");
        }


        return fileData;
    }
}
