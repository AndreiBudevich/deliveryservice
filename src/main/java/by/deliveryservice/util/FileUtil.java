package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

@UtilityClass
public class FileUtil {

    public static File getFileInResource(String nameFile) {
        String path = Objects.requireNonNull(JsonUtil.class.getResource("/")).getPath();
        return Paths.get((path + nameFile).substring(1)).toFile();
    }
}
