package ie.tudublin;

public class Cat extends Animal
{
    private int numLives;

    public void kill()
    {
        if (numLives > 0)
        {
            //prints
            System.out.println("Ouch!");
            //subtracts from number of lives
            numLives --;
        }
        else
        {
            System.out.println("Im dead");
        }
    }

    public int getNumLives() {
        return numLives;
    }


    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }


    public Cat(String name)
    {
        super(name);
        numLives = 9;
    }
}