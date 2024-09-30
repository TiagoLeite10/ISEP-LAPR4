package eapli.base.postitmanagement.application;

import eapli.base.postitmanagement.exception.ImageException;
import eapli.framework.application.ApplicationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@ApplicationService
public class PostItImageService {

    private static final String IMAGE_DESTINATION_FOLDER = "www/img";

    private static final String PATH_TO_DESTINATION_FOLDER = "../img/";

    public static String moveImage(String pathToImage) throws IOException {
        Path originPath = Path.of(pathToImage);
        String fileName = originPath.getFileName().toString();
        Path destinationPath = Path.of(IMAGE_DESTINATION_FOLDER, fileName);

        if (Files.exists(originPath)) {
            if (imageExtensionIsValid(pathToImage)) {
                throw new ImageException("The image extension must be one of the following: gif, png, jpg or jpeg!");
            } else {
                Files.move(originPath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                return PATH_TO_DESTINATION_FOLDER + fileName;
            }
        } else {
            throw new ImageException("The image path you specified does not exist!");
        }
    }


    private static boolean imageExtensionIsValid(String pathToImage) {
        String extension = getImageExtension(pathToImage);
        return extension.equals("gif") || extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg");

    }

    private static String getImageExtension(String pathToImage) {
        if (pathToImage.lastIndexOf(".") != -1 && pathToImage.lastIndexOf(".") != 0) {
            return pathToImage.substring(pathToImage.lastIndexOf(".")).toLowerCase();
        } else {
            return "";
        }
    }
}