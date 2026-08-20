package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestBenchOpMode1 {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private com.qualcomm.robotcore.hardware.Servo servo;

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        servo = hwMap.get(com.qualcomm.robotcore.hardware.Servo.class, "servo");
    }

    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
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
