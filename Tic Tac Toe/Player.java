public class Player
{
    private String name;

    private char type;


    public Player( String name, char type )
    {
        this.name = name;
        this.type = type;
    }


    public String getName()
    {
        return name;
    }


    public char getType()
    {
        return type;
    }


    public void setName( String name )
    {
        this.name = name;
    }


    public void setType( char type )
    {
        this.type = type;
    }

}
