package org.pageobjects;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * setUpDockerGridTest class is responsible for starting and stopping the Selenium Grid
 * using Docker before and after the tests.
 */
public class setUpDockerGridTest {

    /**
     * Starts the Selenium Grid by executing the start_dockerGrid.bat script.
     *
     * @throws IOException if there is an error in executing the script
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    @BeforeTest
    public void startGrid() throws IOException, InterruptedException {
        // Execute the batch file to start the Docker Grid
        Runtime.getRuntime().exec("cmd /c start start_dockerGrid.bat");
        // Wait for 15 seconds to ensure the grid is up and running
        Thread.sleep(15000);
    }

    /**
     * Stops the Selenium Grid by executing the stop_dockerGrid.bat script and
     * terminates any open command prompt windows.
     *
     * @throws IOException if there is an error in executing the script
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    @AfterTest
    public void stopGrid() throws IOException, InterruptedException {
        // Execute the batch file to stop the Docker Grid
        Runtime.getRuntime().exec("cmd /c start stop_dockerGrid.bat");
        // Wait for 5 seconds to ensure the grid is stopped
        Thread.sleep(5000);

        // Terminate any open command prompt windows
        Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
    }

    // Uncomment the following methods if you are using a Unix-based system

//     /**
//      * Starts the Selenium Grid by executing the start_docker.sh script.
//      *
//      * @throws IOException if there is an error in executing the script
//      * @throws InterruptedException if the thread is interrupted while sleeping
//      */
//     @BeforeTest
//     public void startGrid() throws IOException, InterruptedException {
//         Runtime.getRuntime().exec("sh ./start_docker.sh");
//         Thread.sleep(15000); // Adjust sleep time as needed
//     }
//
//     /**
//      * Stops the Selenium Grid by executing the stop_docker.sh script.
//      *
//      * @throws IOException if there is an error in executing the script
//      * @throws InterruptedException if the thread is interrupted while sleeping
//      */
//     @AfterTest
//     public void stopGrid() throws InterruptedException, IOException {
//         Runtime.getRuntime().exec("sh ./stop_docker.sh");
//         Thread.sleep(5000);
//     }
}