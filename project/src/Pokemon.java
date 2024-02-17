import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**Clasa Pokemon in care ne declaram campurile specifice si cateva metode*/

class Pokemon implements Cloneable{
    String name;
    int HP;
    int Attack;
    int Special_Attack;
    int Defense;
    int Special_Defense;
    ArrayList<Ability>abilities;
    ArrayList<Item>objects;
    int dodge;//daca pokemonul e pe dodge
    int stun;//daca e stun

    public Pokemon() {
        this.abilities = new ArrayList<Ability>(2);
        this.objects = new ArrayList<Item>(3);
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public void upgrade_stats() throws InterruptedException, IOException {
        Logger fight_logger=Logger.Instance();
        HP++;
        if(Attack != 0)
            Attack++;
        else
            Special_Attack++;
        Defense++;
        Special_Defense++;
        fight_logger.display_stats(this);
    }

    //initializare campuri Pokemon,folosind builder-ul
    public Pokemon read_stats(String[]mystats) throws NumberFormatException{

        Pokemon pokemon;
        if(!mystats[0].equals("Neutrel1") || (!mystats[0].equals("Neutrel2"))) {
            if (!mystats[2].equals(""))
                pokemon = new PokemonBuilder().name(mystats[0]).HP(Integer.parseInt(mystats[1])).Attack(Integer.parseInt(mystats[2])).Defense(Integer.parseInt(mystats[4])).Sp_Defense(Integer.parseInt(mystats[5])).Abilities(mystats[6], mystats[7]).build();
            else
                pokemon = new PokemonBuilder().name(mystats[0]).HP(Integer.parseInt(mystats[1])).Sp_Attack(Integer.parseInt(mystats[3])).Defense(Integer.parseInt(mystats[4])).Sp_Defense(Integer.parseInt(mystats[5])).Abilities(mystats[6], mystats[7]).build();
        }else {
            pokemon = new PokemonBuilder().name(mystats[0]).HP(Integer.parseInt(mystats[1])).Attack(Integer.parseInt(mystats[2])).Defense(Integer.parseInt(mystats[4])).Sp_Defense(Integer.parseInt(mystats[5])).build();
        }

        pokemon.dodge = 0;
        pokemon.stun = 0;
        return pokemon;
    }

    public void decrease_cooltime(){
        if(abilities.get(0).cool_time != 0)
            abilities.get(0).cool_time--;

        if(abilities.get(1).cool_time != 0)
            abilities.get(1).cool_time--;
    }

}
