import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dot  extends Actor
{
    private int x;
    private int y;
    private int d;
    private final int DOT_SIZE=20;
    
    public Dot(int dot)
    {
        d = dot;
        if( dot == 0 )
        {
           setImage( "SnakeHead.gif" );
           getImage().mirrorHorizontally();
        }
        else
        {
            setImage( "close.png" );
        }
    }
    
    /**
     * Act - do whatever the Head wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if( d==0 )
        {
            lead();
            checkForFood();
            checkForWall();
            checkForTail();
        }
        else
        {
            follow();
        }
    }   
    
    /**
     * follow - Makes the dots follow the snake head
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void follow()
    {
        SnakeWorld world = (SnakeWorld) getWorld();
        x = world.getMyX(d);
        y = world.getMyY(d);
        setLocation( x, y);
    }
    
    /**
     * GrowTail - Adds a dot to the snake's tail
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void GrowTail()
    {
        SnakeWorld world = (SnakeWorld) getWorld();
        world.addToTail();
    }
    
    /**
     * lead - Checks if the movement keys are being pressed
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void lead()
    {
        double angle;
        SnakeWorld world = (SnakeWorld) getWorld();
        x = getX();
        y = getY();
        
        if( Greenfoot.isKeyDown("left") )
        {
            setRotation(180);
        }
        else if( Greenfoot.isKeyDown("right") )
        {
            setRotation(0);
        }
        else if( Greenfoot.isKeyDown("up") )
        {
            setRotation(270);
        }
        else if( Greenfoot.isKeyDown("down") )
        {
            setRotation(90);
        }
        
        angle = Math.toRadians( getRotation() );
        x = (int) Math.round( getX() + Math.cos(angle) * DOT_SIZE);
        y = (int) Math.round( getY() + Math.sin(angle) * DOT_SIZE);
        
        setLocation(x, y);
        world.setDX( d, x );
        world.setDY( d, y );
    }
    
    /**
     * checkForFood - When the snake touches food, one dot and one food is added
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void checkForFood()
    {
        SnakeWorld world = (SnakeWorld) getWorld();
        
        if( isTouching( Food.class ))
        {
            removeTouching(Food.class);
            GrowTail();
            world.addFood(1);
        }
    }
    
    /**
     * checkForWall - When the snake touches a wall you lose
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void checkForWall()
    {
        if(isAtEdge())
        {
            getWorld().showText( "You Lose!", getWorld().getWidth()/2, getWorld().getHeight()/2 );
            Greenfoot.stop();
        }
    }
    
    /**
     * checkForTail - When the snake touches a dot you lose
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void checkForTail()
    {
        if( isTouching(Dot.class))
        {
            getWorld().showText( "You Lose!", getWorld().getWidth()/2, getWorld().getHeight()/2 );
            Greenfoot.stop();
        }
    }
    
}
