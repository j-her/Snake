import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class snakeWorld here.
 * 
 * @author (Jackson Hercina) 
 * @version (#1, Thursday, January 25th, 2018)
 */
public class SnakeWorld  extends World
{
    private final int MAX_DOTS = (600*400)/(20*20);
    
    private int[] x = new int[MAX_DOTS];
    private int[] y = new int[MAX_DOTS];
    
    private int dots = 4;
    private Dot body;
    
    /**
     * Constructor for objects of class snakeWorld.
     * 
     */
    public SnakeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        for(int i = 0; i < dots; i++)
        {
            x[i] = 100- i*20;
            y[i] = 20;
        }
        
        prepareSnake();
        
        addFood(2);
    }
    
    public void act()
    {
        for( int i = dots; i > 0; i-- )
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
    }
    
    /**
     * prepareSnake - Places the snake into the world
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    private void prepareSnake()
    {
        for(int i = 0; i < dots; i++)
        {
            body = new Dot(i);
            addObject( body, x[i], y[i]);
        }
    }
    
    /**
     * setDX - Sets up the x location for dot
     * 
     * @param The dot's x location is the paramiter
     * @return Nothing was returned
     * 
     */
    public void setDX(int d, int dx)
    {
        x[d] = dx;
    }
    
    /** 
     * setDY - Sets up the y location for dot
     * 
     * @param The dot's y location is the paramiter
     * @return Nothing was returned
     * 
     */
    public void setDY(int d, int dy)
    {
        y[d] = dy;
    }
    
    /**
     * getMyX - Provides Dot with a x location
     * 
     * @param The dot is the paramiter
     * @return Dot's x location was returned
     * 
     */
    public int getMyX(int d)
    {
        return x[d];
    }
    
    /**
     * getMyY - Provides Dot with a y location
     * 
     * @param The dot is the paramiter
     * @return Dot's y location was returned
     * 
     */
    public int getMyY(int d)
    {
        return y[d];
    }
    
    /**
     * addToTail - Adds a new dot for each food collected
     * 
     * @param There are no paramiters
     * @return Nothing was returned
     * 
     */
    public void addToTail()
    {
        int parentX = x[dots - 1];
        int parentY = y[dots - 1];
        
        body = new Dot(dots);
        addObject(body, parentX, parentY);
        dots++;
    }
    
    /**
     * addFood - Adds new food to the world
     * 
     * @param The number of food in the world is the paramiter
     * @return Nothing was returned
     * 
     */
    public void addFood( int numFood )
    {
        for( int i = 0; i < numFood; i++)
        {
            addObject( new Food(), Greenfoot.getRandomNumber( getWidth() ), Greenfoot.getRandomNumber( getHeight() ) );
        }
    }
}
