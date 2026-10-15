package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
public class HelloWorldOpMode extends OpMode {

    private int counter = 0;

    @Override
    public void init() {
        // --- 1. Initialization Phase ---
        // This code runs when you press the "INIT" button on the Driver Station.
        
        telemetry.addData("Status", "Initialized - Hello Students!");
        telemetry.addData("Instruction", "Press Play to start the loop");
        // telemetry.update() is called automatically in OpMode, but can be called manually.
    }

    @Override
    public void loop() {
        // --- 2. Run Phase ---
        // This code runs repeatedly when you press the PLAY button.
        
        // Increment the counter so we can see something changing on the screen.
        counter++;

        // --- 3. Telemetry Feedback ---
        // addData takes two parts: a "Label" and the "Value".
        telemetry.addData("Message", "Hello World! I am a robot.");
        telemetry.addData("Loop Count", counter);

        
        // TIP: You can use telemetry to debug your robot by showing sensor values.
        telemetry.addData("Status", "Running...");
        
        // Update the display. In OpMode, telemetry.update() is called automatically after loop() returns.
    }
}
