package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.mechanisms.TestBenchOpMode1;

/**
 * TestOpMode
 * 
 * This OpMode demonstrates more advanced controls:
 * - Motors, Servos, and Digital Sensors
 * - Toggle logic (debouncing)
 * - Variable speed control
 */
@TeleOp(name = "Test Bench OpMode", group = "Tutorial")
public class TestOpMode extends LinearOpMode {

    // Instantiate our hardware class
    TestBenchOpMode1 robot = new TestBenchOpMode1();

    // Variables to keep track of our robot's state
    double motorPower = 0.0;
    double servoPosition = 0.5;
    
    // Toggle variables to prevent "bouncing" (multiple triggers in one press)
    boolean lastTouchState = false;
    boolean motorRunning = false;

    @Override
    public void runOpMode() {
        // --- 1. Initialization ---
        robot.init(hardwareMap);
        
        // Optional: Reverse the motor if it's spinning the wrong way
        // robot.reverseMotor(true);
        
        // Explain controls to the user on the Driver Station
        telemetry.addLine("--- CONTROLS ---");
        telemetry.addLine("A: Start Motor (0.5)");
        telemetry.addLine("B: Stop Motor");
        telemetry.addLine("D-Pad Up/Down: Control Servo");
        telemetry.addLine("RB: Increase Speed | RT: Decrease Speed");
        telemetry.addLine("Touch Sensor: Toggle Motor");
        telemetry.addLine("-----------------");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // --- 2. Motor Controls (A/B Buttons) ---
            if (gamepad1.a) {
                motorRunning = true;
                motorPower = 0.5;
            } else if (gamepad1.b) {
                motorRunning = false;
                motorPower = 0.0;
            }

            // --- 3. Speed Adjustments (RB/RT) ---
            // RB (Right Bumper) increases the power
            if (gamepad1.right_bumper) {
                motorPower += 0.01; // Small increments while held
            }
            // RT (Right Trigger) decreases the power
            // triggers return a value from 0.0 to 1.0
            if (gamepad1.right_trigger > 0.1) {
                motorPower -= 0.01;
            }

            // --- 4. Touch Sensor Toggle Logic ---
            // We only want to toggle ONCE when the sensor is pressed.
            boolean currentTouchState = robot.isTouchSensorPressed();
            if (currentTouchState && !lastTouchState) {
                // If it was JUST pressed (rising edge)
                motorRunning = !motorRunning; 
                if (motorRunning) motorPower = 0.5;
                else motorPower = 0.0;
                
                // Feedback: Rumble the controller when the sensor is pressed!
                gamepad1.rumble(500); 
            }
            lastTouchState = currentTouchState; // Update for next loop

            // --- 5. Servo Controls (D-Pad) ---
            if (gamepad1.dpad_up) {
                servoPosition += 0.005;
            } else if (gamepad1.dpad_down) {
                servoPosition -= 0.005;
            }

            // --- 6. Safety & Limits ---
            // Clip values to ensure they stay within valid ranges (0.0 to 1.0)
            motorPower = Range.clip(motorPower, -1.0, 1.0);
            servoPosition = Range.clip(servoPosition, 0.0, 1.0);

            // --- 7. Apply to Hardware ---
            if (motorRunning) {
                robot.setMotorSpeed(motorPower);
            } else {
                robot.setMotorSpeed(0.0);
            }
            robot.setServoPosition(servoPosition);

            // --- 8. Telemetry Data ---
            telemetry.addData("Motor State", motorRunning ? "RUNNING" : "STOPPED");
            telemetry.addData("Motor Power", "%.2f", motorPower);
            telemetry.addData("Motor Encoders", robot.getMotorPosition());
            telemetry.addData("Servo Position", "%.3f", servoPosition);
            telemetry.addData("Touch Sensor", currentTouchState);
            telemetry.update();
            
            // Small pause to keep the loop timing consistent
            sleep(10);
        }
    }
}
