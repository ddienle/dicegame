
package android.app;

import java.util.Random;

import android.graphics.Point;
import android.util.Log;

public class DiceRandom {
	ImageAndLocation[] dices;

	public DiceRandom(int n)
	{
	}
	
	int getSignRandom() {
		Random generator = new Random();
		
		int sign = generator.nextInt(2);
		if (sign == 0)
			sign=-1;
		else
			sign=1;
		
		return sign;
	}
	
	Point randomizeLocation(int radius)
	{
		Point p;
		p = new Point();
	
		int signX, signY;
		    
		Point loc = new Point();
		int locX=0;
		int locY=0;
		    
		int len = radius + 1;
		
		Random generator = new Random();
		
		Point temp = new Point();
		while (len > radius) 
		{
		     signX = getSignRandom();        
		     signY = getSignRandom();
		     locX = generator.nextInt(radius);
		     locY = generator.nextInt(radius);
		     locX = GameInfo.CENTER_X + locX*signX;
		     locY = GameInfo.CENTER_Y + locY*signY;
		     temp.x = locX;
		     temp.y = locY;
		     if (isOverlap(temp)==1)
		    	 continue;
		     len = (int)Math.sqrt(Math.pow((locX - GameInfo.CENTER_X), 2) + Math.pow((locY-GameInfo.CENTER_Y), 2));
		}
		    
		loc.x = locX;
		loc.y = locY;

		return p;
	}
	
	int isOverlap(Point p)
	{
	    int lenX = (GameInfo.H_WIDTH/2);
	    int lenY = (GameInfo.H_HEIGHT/2);
	    int xMin = p.x-lenX;
	    int xMax = p.x+lenX;
	    int yMin = p.y-lenY;
	    int yMax = p.y+lenY;
	    
	    int signX=1;
	    int signY=1;
	   
	    for (int i=0; i<dices.length; i++)
	    {
	    	ImageAndLocation dice = dices[i];
	        for (int iX=0; iX<=1; iX++)
	        {
	            signX = -signX;
	            for (int iY=0; iY<=1; iY++) 
	            {
	                signY = -signY;
	                if (((dice.loc.x + signX*lenX) >= xMin) && ((dice.loc.x + signX*lenX) <= xMax)                    
	                    && ((dice.loc.y + signY*lenY) >= yMin) && ((dice.loc.y + signY*lenY) <= yMax))
	                {
	                    return 1;
	                }
	            }        
	        }               
	    }
		
		return 0;
	}
	
}

/*
@interface DiceRandom : NSObject {
   NSMutableArray *dices;
}

@property(nonatomic, retain) NSMutableArray *dices;

-(BOOL) isOverLap: (int) x :(int) y;
-(id) initWithNumberOfDices: (int)n andArray: (int[]) a;
-(CGPoint) randomizeLocation: (int) radius;
-(int) getSignRandom;
-(void) print;
@end

-(id) initWithNumberOfDices:(int)n andArray:(int *)countOfDices
{
    int nextDice[6];
    
    nextDice[cua] = bau;
    nextDice[ca] = cua;
    nextDice[ga] = tom;
    nextDice[nai] = ca;
    nextDice[tom] = nai;
    nextDice[bau] = ga;
    
    NSLog(@"Next cua = %i, ca = %i, ga = %i, nai = %i, tom=%i, bau=%i", nextDice[cua], nextDice[ca], nextDice[ga], nextDice[nai], nextDice[tom], nextDice[bau]);
    dices = [[NSMutableArray alloc] initWithObjects: nil];
    ImageAndLocation* dice;
    
    CGPoint location;
    int num;
    for (int i=1; i<=n; i++)
    {
        num = arc4random()%6;
        int j;
        for (j =0; j<6; j++)
        {
            if (countOfDices[j] >= MINIMUM_FOR_RULE)
            {
                countOfDices[j] = 0;
                num = nextDice[j];
                break;
            }
        }
        location = [self randomizeLocation: RADIUS];
        dice = [[ImageAndLocation alloc] init];
        dice.loc = location;
        dice.img = num;
        
        [dices addObject:dice];
        NSLog(@"Random Location %i: X=%f Y=%f", i, dice.loc.x, dice.loc.y);
    }
    return self;
}

-(BOOL) isOverLap: (int) x :(int) y
{
    int lenX = (H_WIDTH/2);
    int lenY = (H_HEIGHT/2);
    int xMin = x-lenX;
    int xMax = x+lenX;
    int yMin = y-lenY;
    int yMax = y+lenY;
    
    int signX=1;
    int signY=1;
    for (ImageAndLocation* dice in dices)
    {
        for (int iX=0; iX<=1; iX++)
        {
            signX = -signX;
            for (int iY=0; iY<=1; iY++) 
            {
                signY = -signY;
                if (((dice.loc.x + signX*lenX) >= xMin) && ((dice.loc.x + signX*lenX) <= xMax)                    
                    && ((dice.loc.y + signY*lenY) >= yMin) && ((dice.loc.y + signY*lenY) <= yMax))
                {
                    return TRUE;
                }
            }        
        }               
    }
    return  FALSE;
}

-(int) getSignRandom
{
    int sign = 1;
    int rand;
    rand = arc4random()%2;
    if (rand == 0)
        sign =  -1;
    //NSLog(@"getSignRandom is called, sign is %i ", sign);
    return sign;
}

-(CGPoint) randomizeLocation: (int) radius
{
    int signX, signY;
    
    CGPoint loc;
    int locX, locY;
    
    int len = radius + 1;
    
    while (len > radius) 
    {
        signX = [self getSignRandom];        
        signY = [self getSignRandom];
        locX = arc4random()%radius;
        locY = arc4random()%radius;
        locX = CENTER_X + locX*signX;
        locY = CENTER_Y + locY*signY;
        if ([self isOverLap: locX: locY])
            continue;
        len = sqrt(pow((locX - CENTER_X), 2) + pow((locY-CENTER_Y), 2));
    }
    
    loc.x = locX;
    loc.y = locY;
    
    return loc;
}

-(void) print
{
    NSLog(@"Print function is called: Dice count %i", [dices count]);
    for (ImageAndLocation* dice in dices)
    {
        NSLog(@"\nDice: %i %f %f", dice.img, dice.loc.x, dice.loc.y);
    }
}

*/