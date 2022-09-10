package by.deliveryservice.util;

import by.deliveryservice.util.json.JsonUtil;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@UtilityClass
public class FileUtil {

    public static File getFileInResource(String nameFile) {
        String path = Objects.requireNonNull(JsonUtil.class.getResource("/")).getPath();
        return Paths.get((path + nameFile).substring(1)).toFile();
    }

    public static boolean isEmpty(String nameFile) {
        return getFileInResource(nameFile).length() == 0;
    }

    public void write(String nameFile, String json) {
        try {
            Files.write(getFileInResource(nameFile).toPath(), json.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid write file " + nameFile + " to JSON:\n", e);
        }
    }
}
