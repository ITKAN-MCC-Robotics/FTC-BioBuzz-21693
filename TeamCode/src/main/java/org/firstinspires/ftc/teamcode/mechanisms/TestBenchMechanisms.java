package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TestBenchMechanisms {
    private TouchSensor touchSensor;
    private DcMotor motor;
    private Servo servo;

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(TouchSensor.class, "touch");
        
        motor = hwMap.get(DcMotor.class, "motor1");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        servo = hwMap.get(Servo.class, "servo");
    }

    public boolean isTouchSensorPressed() {
        return touchSensor.isPressed();
    }

    public double getTouchValue() {
        return touchSensor.getValue();
    }

    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }
    
    public int getMotorPosition() {
        return motor.getCurrentPosition();
    }
    
    public void reverseMotor(boolean reverse) {
        motor.setDirection(reverse ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
    }
    
    public void setServoPosition(double position) {
        servo.setPosition(position);
    }
    
    public double getServoPosition() {
        return servo.getPosition();
    }
}
