package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

/**
 * @author Maximilian Schiedermeier
 */
public class LauncherTest {

    @Test
    public void testLauncherPrintings() {

        DesktopLauncher desktopLauncher = new DesktopLauncher();
        desktopLauncher.printData();
    }
}
