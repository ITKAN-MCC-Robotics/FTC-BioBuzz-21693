package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * HelloWorldOpMode
 * 
 * This is a basic OpMode designed for students who are new to FTC programming.
 * Its purpose is to demonstrate how to use 'telemetry' to display information 
 * on the Driver Station screen.
 * 
 * Students: Try changing the text inside the quotes or adding new telemetry lines!
 */
@TeleOp(name = "Hello World", group = "Tutorial")
public class HelloWorldOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {
        // --- 1. Initialization Phase ---
        // This code runs when you press the "INIT" button on the Driver Station.
        
        telemetry.addData("Status", "Initialized - Hello Students!");
        telemetry.addData("Instruction", "Press Play to start the loop");
        telemetry.update(); // Always call update() to send the data to the Driver Station.

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // --- 2. Run Phase ---
        // This code runs once when you press the PLAY button.
        
        int counter = 0;

        // The while loop runs repeatedly until you press the STOP button.
        while (opModeIsActive()) {
            
            // Increment the counter so we can see something changing on the screen.
            counter++;

            // --- 3. Telemetry Feedback ---
            // addData takes two parts: a "Label" and the "Value".
            telemetry.addData("Message", "Hello World! I am a robot.");
            telemetry.addData("Loop Count", counter);
            
            // TIP: You can use telemetry to debug your robot by showing sensor values.
            telemetry.addData("Status", "Running...");
            
            // Update the display. Without this, the screen won't change.
            telemetry.update();
            
            // Small sleep to prevent the counter from going too fast (optional).
            sleep(50);
        }
    }
}
