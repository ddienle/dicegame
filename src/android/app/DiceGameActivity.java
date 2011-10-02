package android.app;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DiceGameActivity extends Activity {
    /** Called when the activity is first created. */
	RelativeLayout rl;
	//ImageButton btn1, btn2, btn3, btn4, btn5, btn6;
	float width;
	float height;
	int diceTop=0;
    int diceLeft=0;
    int diceWidth=0;
    int diceHeight=0;
    float scale = 1.0f;
    int chuongW = 155;
    int chuongH = 179;
    int indexOfNewDiceInPlayToHide;
    int numberOfDiceInPlayWasOver;
    
    RelativeLayout.LayoutParams  oldLayoutParamsOfChuongInMoving;
    RelativeLayout.LayoutParams  newLayoutParamsOfChuongInMoving;
    //ImageButton[] btn;
    //ImageButton[] btnInPlay;
    ImageView chuong;
    ArrayList<Dice> dices;
    ArrayList<Dice> dicesInPlay;
    TranslateAnimation moveOldtoNew;
    int numberOfDiceInPlay = 0;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        
        setContentView(R.layout.main);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rl = new RelativeLayout(this);
        this.addContentView(rl, layoutParams);
       
        Display display = getWindowManager().getDefaultDisplay(); 
        width = display.getWidth();
        height = display.getHeight();

        Log.i("Scale = ", "" + scale);
        //System.out.print("Scale = " + scale);
        //Log.i("Screen size = ", "width = " + width + "height = " + height + scale + (height/orgHeight));
        
        //imageArray = new Integer[7];
        this.prepareImageAndButton();
    }
    
    void prepareImageAndButton()
    {
        diceTop=0;
        diceLeft=0;
        diceWidth = 47;
        diceHeight = 47;
 
        numberOfDiceInPlay = 0;
        dices = new ArrayList<Dice>();
        dicesInPlay = new ArrayList<Dice>();
        
        int order = 1;
        int image = R.drawable.h1;
        ImageButton objButton = new ImageButton(this);
        objButton.setBackgroundResource(R.drawable.h1);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(0, diceTop, diceWidth, diceHeight);
        objButton.setPadding(0, 0, diceWidth, diceHeight);
        rl.addView(objButton, layoutParams1);
        boolean isInPlay = false;
        boolean isChuongOver = false;       
        Dice objDice = new Dice(order, image, objButton, isInPlay, isChuongOver);
        dices.add(objDice);

        order = 2;
        image = R.drawable.h2;
        objButton = new ImageButton(this);
        objButton.setBackgroundResource(R.drawable.h2);
        RelativeLayout.LayoutParams  layoutParams2 = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(diceWidth+5, diceTop, 2*diceWidth+5, diceHeight);
        objButton.setPadding(0, 0, diceWidth, diceHeight);
        rl.addView(objButton, layoutParams2);
        isInPlay = false;
        isChuongOver = false;       
        objDice = new Dice(order, image, objButton, isInPlay, isChuongOver);
        dices.add(objDice);
       
        order = 3;
        image = R.drawable.h3;
        objButton = new ImageButton(this);
        objButton.setBackgroundResource(R.drawable.h3);
        RelativeLayout.LayoutParams  layoutParams3  = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams3.setMargins(2*(diceWidth+5), 0, (2*(diceWidth+5)+diceWidth), diceHeight);
        objButton.setPadding(0, 0, diceWidth, diceHeight);
        rl.addView(objButton, layoutParams3);
        isInPlay = false;
        isChuongOver = false;       
        objDice = new Dice(order, image, objButton, isInPlay, isChuongOver);
        dices.add(objDice);

        order = 4;
        image = R.drawable.h4;
        objButton = new ImageButton(this);
        objButton.setBackgroundResource(R.drawable.h4);
        RelativeLayout.LayoutParams  layoutParams4  = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);        
        layoutParams4.setMargins(3*(diceWidth+5), 0, 3*(diceWidth+5)+diceWidth, diceHeight);
        objButton.setPadding(0, 0, diceWidth, diceHeight);
        rl.addView(objButton, layoutParams4);
        
        isInPlay = false;
        isChuongOver = false;       
        objDice = new Dice(order, image, objButton, isInPlay, isChuongOver);
        dices.add(objDice);
        
        order = 5;
        image = R.drawable.h5;
        objButton = new ImageButton(this);
        objButton.setBackgroundResource(R.drawable.h5);
        RelativeLayout.LayoutParams  layoutParams5  = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);        
        layoutParams5.setMargins(4*(diceWidth+5), 0, (20+4*(diceWidth+5)+diceWidth), diceHeight);
        objButton.setPadding(0, 0, diceWidth, diceHeight);
        rl.addView(objButton, layoutParams5);
      
        isInPlay = false;
        isChuongOver = false;       
        objDice = new Dice(order, image, objButton, isInPlay, isChuongOver);
        dices.add(objDice);

        order = 6;
        image = R.drawable.h6;
        objButton = new ImageButton(this);
        objButton.setBackgroundResource(R.drawable.h6);
        RelativeLayout.LayoutParams  layoutParams6  = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);        
        layoutParams6.setMargins(5*(diceWidth+5), 0, (5*(diceWidth+5)+diceWidth), diceHeight);
        objButton.setPadding(0, 0, diceWidth, diceHeight);
        rl.addView(objButton, layoutParams6);
       
        isInPlay = false;
        isChuongOver = false;       
        objDice = new Dice(order, image, objButton, isInPlay, isChuongOver);
        dices.add(objDice);
        
        Log.i("dice ", "diceWidth = " + diceWidth + "diceHeight = " + diceHeight);
       
        ImageButton btnRun = new ImageButton(this);
  
        int w = 95;
        int h = 59;
        Log.i("Scale", "" + scale + "w = " + w + "h = " + h);
        //btnRun.setBackgroundResource(R.drawable.m);
        btnRun.setBackgroundResource(R.drawable.my_selector);
        
        RelativeLayout.LayoutParams  layoutParamsButtonRun  = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);        
        
        layoutParamsButtonRun.setMargins(40, 383, 40+w, 383+h);
        //layoutParamsButtonRun.setMargins(0, 0, 50, 50);
        
        btnRun.setPadding(0, 0, w, h);
        
        rl.addView(btnRun, layoutParamsButtonRun);
        
        for (Dice d:dices) {
        	setOnClickListener(d);
        }
        
        chuong = new ImageView(this);
        chuong.setBackgroundResource(R.drawable.small_chuong);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        
        //params.setMargins(80, 200, 80+this.chuongW, 200+this.chuongH);
        params.setMargins(-20, -20, this.chuongW, this.chuongH);
        
        //chuong.setScaleType(scaleType);
        rl.addView(chuong, params);
        
        
        btnRun.setOnClickListener
        	(
        			new View.OnClickListener() {
				
        				@Override
        				public void onClick(View v) {
        					Log.i("Button Run is clicked ", "aaa");
    						Random generator = new Random();
    				
    						int n = generator.nextInt(6);
    						n++;
    						
    						Log.i("btnRunOnClicked", "width " + width + "height " + height);
    						//Log.i("btnRun onclicked", "random n is " + n);
    						//RelativeLayout.LayoutParams  oldLayoutParams;
    						oldLayoutParamsOfChuongInMoving = (LayoutParams)chuong.getLayoutParams();
    						//TranslateAnimation moveOldtoNew;
    						//RelativeLayout.LayoutParams  newLayoutParams
    						newLayoutParamsOfChuongInMoving = oldLayoutParamsOfChuongInMoving;
    						
    						if (numberOfDiceInPlay>0)
    						{    		
    							numberOfDiceInPlayWasOver = 0;
    							Log.i("Run", "numberOfDiceInPlay " + numberOfDiceInPlay);
    							for (int i=0; i<numberOfDiceInPlay; i++)
    							{
    								newLayoutParamsOfChuongInMoving = (LayoutParams)(((dicesInPlay.get(i)).getBtn()).getLayoutParams());      
    								//chuong.setLayoutParams(newLayoutParams);       
    								indexOfNewDiceInPlayToHide = i;
    								break;
    							}
    							
    							Log.i("btnRunOnCliced", "fromX " + (width-oldLayoutParamsOfChuongInMoving.leftMargin) + "toX " + (width-newLayoutParamsOfChuongInMoving.leftMargin));
    							Log.i("btnRunOnCliced", "fromY " + (height-oldLayoutParamsOfChuongInMoving.topMargin) + "toY " + (height-newLayoutParamsOfChuongInMoving.topMargin));
    							
    							moveOldtoNew = new TranslateAnimation(
    											(0),
    											(newLayoutParamsOfChuongInMoving.leftMargin),
    											(0), 
    											(newLayoutParamsOfChuongInMoving.topMargin));
    							
    							moveOldtoNew.setDuration(500);
    							
    							moveOldtoNew.setAnimationListener(
    									new AnimationListener() {
											
											@Override
											public void onAnimationStart(Animation animation) {
											}
											
											@Override
											public void onAnimationRepeat(Animation animation) {
											}
											
											@Override
											public void onAnimationEnd(Animation animation) {
												Log.i("animation end", "ended");
												numberOfDiceInPlayWasOver++;
												(dicesInPlay.get(indexOfNewDiceInPlayToHide)).setChuongOver(true);
												(dicesInPlay.get(indexOfNewDiceInPlayToHide)).getBtn().setVisibility(View.INVISIBLE);		
												
												if (numberOfDiceInPlayWasOver < numberOfDiceInPlay)
												{

													//newLayoutParamsOfChuongInMoving = (LayoutParams)(((dicesInPlay.get(numberOfDiceInPlayWasOver+1)).getBtn()).getLayoutParams());      
					    							//moveOldtoNew = new TranslateAnimation(
			    									//		(0),
			    									//		(newLayoutParamsOfChuongInMoving.leftMargin),
			    									//		(0), 
			    									//		(newLayoutParamsOfChuongInMoving.topMargin));
					    							//moveOldtoNew.setDuration(500);
													//moveOldtoNew.
													//chuong.startAnimation(moveOldtoNew);
												}
											}
										});
    						
    							chuong.startAnimation(moveOldtoNew);
    						}
    						
        				}
        				
        			}
        	);
    }
    
    void setOnClickListener(final Dice objDice)
    {
    	final ImageButton thisButton = objDice.getBtn();
    	thisButton.setOnClickListener(new View.OnClickListener() {
			
    		@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
		        RelativeLayout.LayoutParams  newLayoutParams  = (LayoutParams) thisButton.getLayoutParams();      
		        int left = newLayoutParams.leftMargin;
		        int top = newLayoutParams.topMargin;
		        int bot = newLayoutParams.bottomMargin;
		        int right = newLayoutParams.rightMargin;
		        int order= objDice.getOrder();
		    
		        //Log.i("Button", "Click button is " + noOfButton);
		        //Log.i("log1", "top = " + top + "left = " + left + "right = " + right + "bottom = " + bot);
	        	
				order--;
				left = (diceWidth+5)*order;
	        	
				if (order==4)
	        		right = (20+4*(diceWidth+5)+diceWidth);
	        	else
	        		right = (diceWidth+5)*order+diceWidth;
	        	
	        	if (top < 2*diceWidth)
		        {
		        	top += 2*diceWidth;
		        	bot += 2*diceWidth;
		        	dicesInPlay.add(objDice);
		        	numberOfDiceInPlay++;
		        	
		        }
		        else
		        {
		        	top = 0;
		        	bot = diceHeight;
		        	dicesInPlay.remove(objDice);
		        	numberOfDiceInPlay--;
		        }
		        
		        newLayoutParams.setMargins(left, top, right, bot);
				thisButton.setLayoutParams(newLayoutParams);
			}
        }); 	
    }
}