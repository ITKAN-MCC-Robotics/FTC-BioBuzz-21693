package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Mecanum Drivetrain Mechanism
 * 
 * This class handles the hardware initialization and basic movement logic 
 * for a 4-motor Mecanum drivetrain.
 */
public class DriveTrainMechanism {
    // Hardware members
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    /**
     * Initialize the drivetrain hardware
     * @param hwMap The hardware map from the OpMode
     */
    public void init(HardwareMap hwMap) {
        // Fetch motors from hardware map using the specified names
        frontLeft = hwMap.get(DcMotor.class, "front_left");
        frontRight = hwMap.get(DcMotor.class, "front_right");
        backLeft = hwMap.get(DcMotor.class, "back_left");
        backRight = hwMap.get(DcMotor.class, "back_right");

        // Set motor directions
        // Typically, left side motors are reversed so that positive power moves the robot forward
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set all motors to zero power
        setMotorPowers(0, 0, 0, 0);

        // Set motors to run without encoders by default for simple teleop
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        // Set zero power behavior to brake for better control
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Set power to each motor individually
     */
    public void setMotorPowers(double fl, double fr, double bl, double br) {
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }

    /**
     * Calculate and apply motor powers for mecanum drive
     * @param drive Forward/Backward movement (-1.0 to 1.0)
     * @param strafe Left/Right movement (-1.0 to 1.0)
     * @param rotate Clockwise/Counter-clockwise rotation (-1.0 to 1.0)
     */
    public void drive(double drive, double strafe, double rotate) {
        // Mecanum kinematics:
        // Front Left = drive + strafe + rotate
        // Front Right = drive - strafe - rotate
        // Back Left = drive - strafe + rotate
        // Back Right = drive + strafe - rotate
        
        double flPower = drive + strafe + rotate;
        double frPower = drive - strafe - rotate;
        double blPower = drive - strafe + rotate;
        double brPower = drive + strafe - rotate;

        // Normalize powers if any exceed 1.0
        double max = Math.max(Math.abs(flPower), Math.abs(frPower));
        max = Math.max(max, Math.abs(blPower));
        max = Math.max(max, Math.abs(brPower));

        if (max > 1.0) {
            flPower /= max;
            frPower /= max;
            blPower /= max;
            brPower /= max;
        }

        setMotorPowers(flPower, frPower, blPower, brPower);
    }
    
    // Getters for telemetry
    public double getFrontLeftPower() { return frontLeft.getPower(); }
    public double getFrontRightPower() { return frontRight.getPower(); }
    public double getBackLeftPower() { return backLeft.getPower(); }
    public double getBackRightPower() { return backRight.getPower(); }
}
