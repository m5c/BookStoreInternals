package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

/**
 * @author Maximilian Schiedermeier
 */
public class LauncherTest {

  @Test
  public void testLauncherPrintings() {

    DesktopLauncher.main(new String[] {});
  }

  @Test
  public void testLauncherInit() {
      DesktopLauncher launcher = new DesktopLauncher();
  }
}
