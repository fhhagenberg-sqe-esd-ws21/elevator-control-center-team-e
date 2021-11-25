package at.fhhagenberg.sqe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SystemInfoTest {
    @Test
    @Disabled
    public void testJavaVersion() {
        assertEquals("17", SystemInfo.javaVersion());
    }

    @Test
    @Disabled
    public void testJavafxVersion() {
        assertEquals("17", SystemInfo.javafxVersion());
    }
}