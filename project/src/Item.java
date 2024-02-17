/**Aici am definit obiectele si am creat un constructor si o metoda ce aplica item-ul Pokemon-ului*/

public class Item extends Market{

    String Name;
    int HP;
    int Attack;
    int Special_Attack;
    int Defense;
    int Special_Defense;

    Item(int HP, int Attack, int Special_Attack, int Defense, int Special_Defense) {
        this.HP = HP;
        this.Attack = Attack;
        this.Special_Attack = Special_Attack;
        this.Defense = Defense;
        this.Special_Defense = Special_Defense;

    }

    void use_item(Pokemon pokemon){
        pokemon.HP += HP;
        if(pokemon.Attack != 0)
            pokemon.Attack += Attack;
        else
            pokemon.Special_Attack += Special_Attack;
        pokemon.Defense += Defense;
        pokemon.Special_Defense += Special_Defense;
    }

}
