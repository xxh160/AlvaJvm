package jvm;

import jvm.cmd.Cli;
import org.junit.Test;

/**
 * @author hwd
 * @date 2021-01-09 11:16 AM
 **/
public class CliTest {

    @Test
    public void helpTest() {
        String[] args = {"-h"};
        Cli.main(args);
    }

    @Test
    public void classpathTest() {
        String[] args = {"b -cp a"};
        Cli.main(args);
    }

    @Test
    public void noOptionTest() {
        String[] args = {};
        Cli.main(args);
    }
}
