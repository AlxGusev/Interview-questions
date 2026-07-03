package hard.tests;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOTests {

    private final Path INPUT_FILE_PATH = Paths.get("src/test/resources/source.txt");
    private final Path OUTPUT_FILE_PATH = Paths.get("src/test/resources/destination.txt");

    // File -> InputStream -> byte[] (JVM) -> OutputStream -> File
    @Test
    public void blockingIOTest() throws IOException {
        try (InputStream is = Files.newInputStream(INPUT_FILE_PATH);
             OutputStream os = Files.newOutputStream(OUTPUT_FILE_PATH, WRITE, CREATE)) {

            byte[] buffer = new byte[8192];
            int read;

            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals("Hello, World!", Files.readString(OUTPUT_FILE_PATH));
        Files.delete(OUTPUT_FILE_PATH);
    }

    // File -> FileChannel -> transferTo() -> FileChannel -> File
    @Test
    public void nonBlockingIOTest() throws IOException {
        try (FileChannel src = FileChannel.open(INPUT_FILE_PATH, READ);
             FileChannel dst = FileChannel.open(OUTPUT_FILE_PATH, WRITE, CREATE)) {

            src.transferTo(0, src.size(), dst);
        }
        assertEquals("Hello, World!", Files.readString(OUTPUT_FILE_PATH));
        Files.delete(OUTPUT_FILE_PATH);
    }
}
