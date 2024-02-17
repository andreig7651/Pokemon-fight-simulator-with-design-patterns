//clasa ability in care declar campurile unei abilitati,dar realizez si citirea din fisier
//cooldown este fix si reprezinta cat timp nu pot folosi o abilitate
//cool_time va fi cooldown+1(deoarece iau in considerare si runda curenta) si va fi decrementat dupa fiecare runda

public class Ability {
    int damage;
    int stun;
    int dodge;
    int cooldown;
    int cool_time;

    public void read_Ability(String[]abilities){

        String ability;

        ability = abilities[0].substring(abilities[0].lastIndexOf(" ") + 1);
        damage = Integer.parseInt(ability);

        ability = abilities[1].substring(abilities[1].lastIndexOf(" ") + 1);
        if(ability.equals("Yes"))
            stun = 1;
        else
            stun = 0;

        ability = abilities[2].substring(abilities[2].lastIndexOf(" ") + 1);
        if(ability.equals("Yes"))
            dodge = 1;
        else
            dodge = 0;

        ability = abilities[3].substring(abilities[3].lastIndexOf(" ") + 1);

        cooldown = Integer.parseInt(ability);
        cool_time = 0;
    }

}
