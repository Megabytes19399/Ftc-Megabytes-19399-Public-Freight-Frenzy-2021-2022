package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto Test Red")
public class AutoTestRed extends LinearOpMode {

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
    
    rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
    rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  
    waitForStart();
    
    // Move Code
    // Move forward 30
    
    //rotate -135
    //estimate turn(Time, Power)
    Move(.5,.5); //.6 high .5 norm
    turn(.3,.5);
    strafe(1,.5);
    Duck(4.5,.8);
    strafe(.5,-.5);
    turn(.3,-.5);

    Move(.7,.5);
    turn(.8,-.5); // .8 high battery .9 norm
    
    strafe(.7,.4);
    
    armRotation(1.8,.5);
    armExtend(2.75,.5);
    
    Move(.6,.35);
    
    claw();
    
    strafe(.8,-.4);
    Move(1,-.5);
    
    armExtend(2.75,-.5);
    armRotation(1.8,-.5);
    
    
    //Move(.1,-.5);
    
  }


  
    private void Move(double Duration, double Power) {
      rightFrontMotor.setPower(Power);
      leftFrontMotor.setPower(-Power);
      rightBackMotor.setPower(Power);
      leftBackMotor.setPower(-Power);
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive()))
      {
      } 
      
      rightFrontMotor.setPower(0);
      leftFrontMotor.setPower(0);
      rightBackMotor.setPower(0);
      leftBackMotor.setPower(0);
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
    }
    
    private void Duck(double Duration, double Power){
      // Negative power should be a left strafe
      DuckMotor.setPower(Power);
      
      runtime.reset();
      
      while( (runtime.seconds() <  Duration ) && (opModeIsActive())){
      } 
      
      DuckMotor.setPower(0);
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
        

        telemetry.addData("Front Right Encoder",  rightFrontMotor.getCurrentPosition());
        telemetry.addData("Front Left Encoder",  leftFrontMotor.getCurrentPosition());
        telemetry.addData("Back Right Encoder",  rightBackMotor.getCurrentPosition());
        telemetry.addData("Back Left Encoder",  leftBackMotor.getCurrentPosition());
        telemetry.update();
  }
 
 
}
  

  
 


//Move and align duck spin
//spin duck 
//align for warehouse attack 
//move slowly about 2/3 of way to warehouse then go fast to get over.
