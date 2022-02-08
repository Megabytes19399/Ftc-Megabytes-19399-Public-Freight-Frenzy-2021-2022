package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto Test Blue")
public class AutoTestBlue extends LinearOpMode {

  private DcMotor leftBackMotor;
  private DcMotor leftFrontMotor;
  private DcMotor rightFrontMotor;
  private DcMotor rightBackMotor;
  private DcMotor DuckMotor;
  private DcMotor swivelMotor;
  private DcMotor actuatorMotor;
  private Servo clawServo;
  
  private ElapsedTime runtime = new ElapsedTime();
    


  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    
    // Put initialization blocks here.
    leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
    leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
    rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
    rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
    
    DuckMotor = hardwareMap.get(DcMotor.class, "duckMotor");
    swivelMotor = hardwareMap.get(DcMotor.class, "Swivel");
    actuatorMotor = hardwareMap.get(DcMotor.class, "LinearActuator");
    clawServo = hardwareMap.servo.get("Claw");
    /*
    rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    */
    
    
    rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  
    waitForStart();
    
    ///**********     DRIVE CODE START     ***********/////
    Move(.75,.5); //.6 high .5 norm
    
    
    
    turn(1.35,.4); // 
    strafe(1.2,.5);
    Duck(4.5,-.8);
    strafe(.5,-.5);
    turn(.3,.55);

    Move(.8,-.5);
    turn(.8,-.45); // .8 high battery .9 norm
    
    
    armRotation(1.5,.5);
    armExtend(2.75,.5);
    
    Move(.9,.4);
    
    claw();
    
    Move(.9,-.5);
    strafe(.6,.5);
   
    armExtend(2.75,-.5);
    armRotation(1.5,-.5);
    
    
    
    ///**********     DRIVE CODE END     ***********/////

    
  }


  
    private void Move(double Duration, double Power) {
      rightFrontMotor.setPower(Power);
      leftFrontMotor.setPower(-Power);
      rightBackMotor.setPower(Power);
      leftBackMotor.setPower(-Power);
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      rightFrontMotor.setPower(0);
      leftFrontMotor.setPower(0);
      rightBackMotor.setPower(0);
      leftBackMotor.setPower(0);
      outputTelementry();
      
    }
    
    private void turn(double Duration, double Power){

      rightFrontMotor.setPower(Power);
      leftFrontMotor.setPower(Power);
      rightBackMotor.setPower(Power);
      leftBackMotor.setPower(Power);
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      rightFrontMotor.setPower(0);
      leftFrontMotor.setPower(0);
      rightBackMotor.setPower(0);
      leftBackMotor.setPower(0);
      outputTelementry();
    }
   
   
    private void strafe(double Duration, double Power){
      // Negative power should be a left strafe
      rightFrontMotor.setPower(Power);
      leftFrontMotor.setPower(Power);
      rightBackMotor.setPower(-Power);
      leftBackMotor.setPower(-Power);
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      rightFrontMotor.setPower(0);
      leftFrontMotor.setPower(0);
      rightBackMotor.setPower(0);
      leftBackMotor.setPower(0);
      outputTelementry();
    }
    
    private void Duck(double Duration, double Power){
      // Negative power should be a left strafe
      DuckMotor.setPower(Power);
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      DuckMotor.setPower(0);
      outputTelementry();
    }
    
    private void pause(double Duration){
      // Negative power should be a left strafe
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
    }
 
     private void armRotation(double Duration, double Power){
    
        swivelMotor.setPower(-Power);
        
    
        runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      swivelMotor.setPower(0);
      
     }
     
     private void armExtend(double Duration, double Power){
    
        actuatorMotor.setPower(Power);
        
    
        runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      actuatorMotor.setPower(0);
      
      }
     
     private void claw(){
    
      
    
        runtime.reset();
      
      while( (runtime.seconds() <  2) && (opModeIsActive())){
          clawServo.setPosition(.75);
      } 
      
      clawServo.setPosition(0);
      
     }
      
      private void outputTelementry(){
        
        telemetry.update();
        telemetry.addData("Front Right Encoder",  rightFrontMotor.getCurrentPosition());
        telemetry.addData("Front Left Encoder",  leftFrontMotor.getCurrentPosition());
        telemetry.addData("Back Right Encoder",  rightBackMotor.getCurrentPosition());
        telemetry.addData("Back Left Encoder",  leftBackMotor.getCurrentPosition());
        telemetry.update();
        
        //telemetry.addData("Average Encoder", )
        
      }
      
    
    
  }
 
 
 
  

  
 


//Move and align duck spin
//spin duck 
//align for warehouse attack 
//move slowly about 2/3 of way to warehouse then go fast to get over.+
