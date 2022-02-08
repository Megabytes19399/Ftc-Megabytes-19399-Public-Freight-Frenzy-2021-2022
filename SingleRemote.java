
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.teamcode.Drive;
//import org.firstinspires.ftc.teamcode.Duck;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math;
import java.lang.reflect.Method;


@TeleOp(name="Single Remote", group="Linear Opmode")

public class SingleRemote extends LinearOpMode{
        
        
    //Variables
    public double driveScale = 0.4;
    public double strafeScale = 0.4;
    public double duckScale = 1;
    public double swivelScale = 1;
    public double actuatorScale = 1;
    
    public double swivelPosition;
    public double actuatorPosition;
    
    
    private ElapsedTime runtime = new ElapsedTime();
    public double motorMax = 1.0;
    DcMotor leftFrontMotor = null;
    DcMotor rightFrontMotor = null;
    DcMotor leftRearMotor = null;
    DcMotor rightRearMotor = null;
    
    DcMotor duckMotor = null;
    DcMotor swivelMotor = null;
    DcMotor actuatorMotor = null;

    Servo clawServo = null;
    
    double rightFrontMotorPower = 0;
    double leftFrontMotorPower = 0;
    double rightRearMotorPower = 0;
    double leftRearMotorPower = 0;
    
    double duckPower = 0;
    double swivelPower = 0;
    double actuatorPower = 0;
    double clawPower = 0;
    
    
    public double controller1RightBumperOutput = 0;
    public double controller1LeftBumperOutput = 0;
    public double controller2RightBumperOutput = 0;
    public double controller2LeftBumperOutput = 0;
    
    public double controller1AOutput = 0;
    public double controller1BOutput = 0;
    public double controller2AOutput = 0;
    public double controller2BOutput = 0;
    
    public String driveMode;
    //Classes

   // public class controller{
        //Sticks
        public double controller2_rightStickX =0;
        public double controller2_rightStickY =0;
        public double controller2_leftStickX =0;
        public double controller2_leftStickY =0;
        
        //Bumpers
        public boolean controller2_rightBumper;
        public boolean controller2_leftBumper;

        //Triggers
        public double controller2_rightTrigger =0;
        public double controller2_leftTrigger =0;
        
        //buttons
        public boolean controller2_A;
        public boolean controller2_B;

        
        public double controller1_rightStickX =0;
        public double controller1_rightStickY  =0;
        public double controller1_leftStickX =0;
        public double controller1_leftStickY =0;
        
        //Bumpers
        public boolean controller1_rightBumper;
        public boolean controller1_leftBumper;

        //Triggers
        public double controller1_rightTrigger =0;
        public double controller1_leftTrigger =0;
        
        public boolean controller1_A;
        public boolean controller1_B;
        public boolean controller1_X;
        public boolean controller1_Y;
        
        public boolean controller1_D_up;
        public boolean controller1_D_down;


    
    public void getControlls(){
        //getControlUpdate();
        controller1_rightStickX = gamepad1.right_stick_x;
        controller1_rightStickY = -gamepad1.right_stick_y;
        controller1_leftStickX = gamepad1.left_stick_x;
        controller1_leftStickY = -gamepad1.left_stick_y;

        //Update Bumper
        controller1_rightBumper = gamepad1.right_bumper;
        controller1_leftBumper = gamepad1.left_bumper;
        
        //Update Trigger
        controller1_rightTrigger = gamepad1.right_trigger;
        controller1_leftTrigger = gamepad1.left_trigger;

        controller1_A = gamepad1.a;
        controller1_B = gamepad1.b;
        controller1_X = gamepad1.x;
        controller1_Y = gamepad1.y;
        
        controller1_D_up = gamepad1.dpad_up;
        controller1_D_down = gamepad1.dpad_down;

        //Update gamepad 2
        //Update sticks
        controller2_rightStickX = gamepad2.right_stick_x;
        controller2_rightStickY = -gamepad2.right_stick_y;
        controller2_leftStickX = gamepad2.left_stick_x;
        controller2_leftStickY = -gamepad2.left_stick_y;
        //Update Bumper
        controller2_rightBumper = gamepad2.right_bumper;
        controller2_leftBumper = gamepad2.left_bumper;
            if (controller2_rightBumper){
                controller2RightBumperOutput = 1;
            }
            else if (controller2_leftBumper){
                controller2LeftBumperOutput = 1;

            }
            else {
                controller2RightBumperOutput = 0;
                controller2LeftBumperOutput = 0;
            }
        //Update Trigger
        controller2_rightTrigger = gamepad2.right_trigger;
        controller2_leftTrigger = gamepad2.left_trigger;
        
        //Update buttons
        controller2_A = gamepad2.a;
        controller2_B = gamepad2.b;
        
        
        if (controller2_A){
                controller2AOutput = 1;
            }
            else if (controller2_B){
                controller2BOutput = 1;

            }
            else {
                controller2AOutput = 0;
                controller2BOutput = 0;
            }
        
        if (controller1_A){
                controller1AOutput = 1;
            }
            else if (controller1_B){
                controller1BOutput = 1;

            }
            else {
                controller1AOutput = 0;
                controller1BOutput = 0;
            }
    }
    
    public void sendTelemetry (){
        //updateTelemetry();
        telemetry.update();

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        
        telemetry.addData("\nDrive Mode", driveMode);
        
        telemetry.addData("\nController 1 left stick X", "%.3f", controller1_leftStickX);
        telemetry.addData("Controller 1 left stick Y", "%.3f", controller1_leftStickY);
        telemetry.addData("\nController 1 right stick X", "%.3f", controller1_rightStickX);
        telemetry.addData("Controller 1 right stick Y", "%.3f", controller1_rightStickY);
        telemetry.addData("\nController 1 A", "%.3f", controller1AOutput);
        telemetry.addData("Controller 1 B", "%.3f", controller1BOutput);
        
        
        telemetry.addData("\nController 2 left stick X", "%.3f", controller2_leftStickX);
        telemetry.addData("Controller 2 left stick Y", "%.3f", controller2_leftStickY);
        telemetry.addData("\nController 2 right bumper", "%.3f", controller2RightBumperOutput);
        telemetry.addData("Controller 2 left bumper", "%.3f", controller2LeftBumperOutput);
        telemetry.addData("\nController 2 A", "%.3f", controller2AOutput);
        telemetry.addData("Controller 2 B", "%.3f", controller2BOutput);

        telemetry.addData("\nDrive Scale", "%.3f", driveScale);
        telemetry.addData("Right Front Motor", "%.3f", rightFrontMotor.getPower());
        telemetry.addData("Left Front Motor", "%.3f", leftFrontMotor.getPower());
        telemetry.addData("Right Rear Motor", "%.3f", rightRearMotor.getPower());
        telemetry.addData("Left Rear Motor", "%.3f", leftRearMotor.getPower());
        
        telemetry.addData("Duck Motor", "%.3f", duckPower);
        telemetry.addData("Servo Position in", "%.3f", clawPower);
        
        telemetry.addData("Swivel Encoder", swivelPosition);
        telemetry.addData("Acuator Encoder", actuatorPosition );
        
        
    }
    
    @Override
    public void runOpMode() {
        waitForStart();
        runtime.reset();
        
        double yStep = 0;
        
        leftFrontMotor = hardwareMap.dcMotor.get("leftFrontMotor");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFrontMotor");
        leftRearMotor = hardwareMap.dcMotor.get("leftBackMotor");
        rightRearMotor = hardwareMap.dcMotor.get("rightBackMotor");  
        
        duckMotor = hardwareMap.dcMotor.get("duckMotor");
        swivelMotor = hardwareMap.dcMotor.get("Swivel");
        actuatorMotor = hardwareMap.dcMotor.get("LinearActuator");
        clawServo = hardwareMap.servo.get("Claw");
        
        swivelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        actuatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        while (opModeIsActive()){
            
              getControlls();
            
            rightFrontMotorPower = 0;
            leftFrontMotorPower = 0;
            rightRearMotorPower = 0;
            leftRearMotorPower = 0;
            
            //speed control
            if (controller1_A){
                driveScale = .4;
                strafeScale = .4;
            }
            else if (controller1_B){
                driveScale = .6; 
                strafeScale = .5;
            } 
            else if (controller1_X){
                driveScale = 1;
                strafeScale = .8;
            }
            
            //Calls the Drive systems
            double X; double Y;
            X = controller1_leftStickX;
            Y = controller1_leftStickY;
            
            Double X1 = controller1_leftStickX;
            Double Y1 = controller1_leftStickY;
            
            //Assign motors according to Hardware map
            

            //Standard Drive
            //Makes the robot only either go forward or turn not both.
            /*
            if (Math.abs(X) > Math.abs(Y)){
                Y = 0;
            }
            else if (Math.abs(Y) > Math.abs(X)){
                X = 0;
            }
            else{
                Y = 0;
                X = 0;
            }
            */
            
            leftFrontMotorPower = Math.max(-motorMax, Math.min(leftFrontMotorPower, motorMax));
            rightFrontMotorPower = Math.max(-motorMax, Math.min(rightFrontMotorPower, motorMax));
            leftRearMotorPower = Math.max(-motorMax, Math.min(leftRearMotorPower, motorMax));
            rightRearMotorPower = Math.max(-motorMax, Math.min(rightRearMotorPower, motorMax));
            
            //Set each power to the postion of dominate stick cordinate
            if((Math.abs(X1)+Math.abs(Y1)) >= (Math.abs(controller1_rightStickY)+Math.abs(controller1_rightStickX))){
                leftFrontMotorPower += (Y+X) * driveScale;
                rightFrontMotorPower += (Y-X) * driveScale ;
                leftRearMotorPower += (Y+X) * driveScale; 
                rightRearMotorPower += (Y-X) * driveScale;
        
                
                
                leftFrontMotor.setPower(-leftFrontMotorPower);
                rightFrontMotor.setPower(rightFrontMotorPower);
                leftRearMotor.setPower(-leftRearMotorPower);
                rightRearMotor.setPower(rightRearMotorPower);
                driveMode = "Standard";
            }
            else{
                double y2 = gamepad1.right_stick_y; // Remember, this is reversed!
                double x2 = -gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing
    
                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio, but only when
                // at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(y2) + Math.abs(x2), 1);
                double frontLeftPower = (y2 + x2) / denominator;
                double backLeftPower = (y2 - x2) / denominator;
                double frontRightPower = (y2 - x2) / denominator;
                double backRightPower = (y2 + x2) / denominator;
    
                leftFrontMotor.setPower(frontLeftPower * strafeScale);
                rightFrontMotor.setPower(-frontRightPower * strafeScale);
                leftRearMotor.setPower(backLeftPower * strafeScale);
                rightRearMotor.setPower(-backRightPower * strafeScale);
                driveMode = "Strafe";
                
                  
            }

             //Duck
             
            if (controller1_rightBumper){
                duckPower = -1;
            }
            else if (controller1_leftBumper){
                duckPower = 1;
            }
            else {
                duckPower = 0;
            }
            
            //Swivel
            
            if(controller1_D_up){
                swivelPower = -.75;
                
            }
            else if(controller1_D_down){
                swivelPower = .6;
            }
            else 
            {
                swivelPower = 0;
            }
            
            
            //Linear Actuator
            if (controller1_rightTrigger > controller1_leftTrigger){
                actuatorPower = controller1_rightTrigger;
            }
            else if(controller1_rightTrigger < controller1_leftTrigger){
                actuatorPower = -controller1_leftTrigger;
            }
            else{
                actuatorPower = 0;
            }
            
            //Claw
            if(controller1_Y){
                clawPower =  .75;
                
            }
        
            else{
                clawPower = 0;
          
            }

            // sets the non drive motors to there setings
            duckMotor.setPower(duckPower * duckScale);
            swivelMotor.setPower(swivelPower * swivelScale);
            actuatorMotor.setPower(actuatorPower * actuatorScale);
            clawServo.setPosition(clawPower);
            
            actuatorPosition = 1; 
            swivelPosition = swivelMotor.getCurrentPosition();
            
            sendTelemetry();

        }

    }

}



