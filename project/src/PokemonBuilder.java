/**Am folosit dp de Builder pentru a creea un Pokemon,deoarece,Neutrel nu are abilitati speciale,
   iar un Pokemon poate avea fie Att,fie Sp.Att*/
public class PokemonBuilder {
    private Pokemon pokemon = new Pokemon();

    public Pokemon build(){return pokemon;}

    public PokemonBuilder name(String name){
        pokemon.name = name;
        return this;
    }

    public PokemonBuilder HP(int HP){
        pokemon.HP = HP;
        return this;
    }

    public PokemonBuilder Attack(int Attack){
        pokemon.Attack = Attack;
        return this;
    }

    public PokemonBuilder Sp_Attack(int Sp_Attack){
        pokemon.Special_Attack = Sp_Attack;
        return this;
    }

    public PokemonBuilder Defense(int Defense){
        pokemon.Defense = Defense;
        return this;
    }

    public PokemonBuilder Sp_Defense(int Sp_Defense){
        pokemon.Special_Defense = Sp_Defense;
        return this;
    }

    public PokemonBuilder Abilities(String Ability1 ,String Ability2){

            if(Ability1.equals(""))
                return this;
            Ability ability1 = new Ability();
            String[] stats = Ability1.split(";", 4);
            ability1.read_Ability(stats);
            pokemon.abilities.add(ability1);

            Ability ability2 = new Ability();
            stats = Ability2.split(";", 4);
            ability2.read_Ability(stats);
            pokemon.abilities.add(ability2);

        return this;
    }

}
