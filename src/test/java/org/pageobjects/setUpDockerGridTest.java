package org.pageobjects;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class setUpDockerGridTest {

    @BeforeTest
    public void startGrid() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start start_dockerGrid.bat");
        Thread.sleep(15000);
    }

    @AfterTest
    public void stopGrid() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start stop_dockerGrid.bat");
        Thread.sleep(5000);

        Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
    }
}
