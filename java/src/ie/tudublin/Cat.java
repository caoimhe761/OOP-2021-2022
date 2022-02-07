package ie.tudublin;

public class Cat extends Animal
{
    private int numLives;

    //public accessor
    public int getNumLives() {
        return numLives;
    }

    //cat constructor
    public Cat(String name)
    {
        super(name);
        numLives = 9;
    }

    //method
    public void kill()
    {
        if (numLives > 0)
        {
            System.out.println("Ouch!");
            numLives --;
        }
        else
        {
            System.out.println("Im dead");
        }
    }


    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }


    
}