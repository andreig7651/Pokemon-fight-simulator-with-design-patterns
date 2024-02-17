import java.io.IOException;

/**Aici primul Pokemon il va ataca pe cel de al doilea.Generam random actiunea,apoi,in functie de ce obtinem
  ii scadem viata celuilalt si aplicam efectele abilitatilor.Daca (Sp.)Defense este mai mare decat atacul,atunci
  viata Pokemonului nu va scadea.Daca diferenta dintre HP+Def al adversarului si Att nostru este mai mica decat 0,
  atunci HP devine automat 0*/

public class Battle implements Runnable {
    public Battle(Logger fight_logger, Pokemon fighter1, Trainer trainer1,int i,Pokemon fighter2, Trainer trainer2,int j) throws InterruptedException, CloneNotSupportedException, IOException {

        String[] actions = {"atac", "abilitate 1", "abilitate 2"};
        int which_action;
        do {
            which_action = (int) ((Math.random() * (3 - 0)) + 0);
        } while ((actions[which_action].equals("abilitate 1") && fighter1.abilities.get(0).cool_time != 0) || (
                actions[which_action].equals("abilitate 2") && fighter1.abilities.get(1).cool_time != 0));

        String action = actions[which_action];
        //adversarul nu se poate feri cand e atacat de o abilitate
        if (!action.equals("atac"))
            fighter2.dodge = 0;

        if (fighter1.stun == 0 || fighter1.stun == -1) {
            if (fighter2.dodge == 0 || fighter2.dodge == -1) {
                fight_logger.display_action(action, fighter1);
                if (action.equals("atac")) {
                    if (fighter1.Attack != 0) {
                        if ((fighter2.HP + fighter2.Defense - fighter1.Attack) >= 0) {
                            if ((fighter2.Defense - fighter1.Attack) < 0)
                                fighter2.HP = fighter2.HP + fighter2.Defense - fighter1.Attack;
                        } else {
                            fighter2.HP = 0;
                        }
                    } else {
                        if ((fighter2.HP + fighter2.Special_Defense - fighter1.Special_Attack) >= 0) {
                            if (((fighter2.Special_Defense - fighter1.Special_Attack) < 0)) {
                                fighter2.HP = fighter2.HP + fighter2.Special_Defense - fighter1.Special_Attack;
                            }
                        } else {
                            fighter2.HP = 0;
                        }
                    }
                }

                if ((action.equals("abilitate 1")) && (fighter1.abilities.get(0).cool_time == 0)) {
                    Ability ability1 = fighter1.abilities.get(0);
                    if ((fighter2.HP - ability1.damage) >= 0)
                        fighter2.HP -= ability1.damage;
                    else
                        fighter2.HP = 0;

                    //marcam stun si dodge cu -1,pentru a se aplica din runda urmatoare
                    if (ability1.stun == 1 && fighter2.stun==0)
                        fighter2.stun = -1;

                    if (ability1.dodge == 1 && fighter1.dodge==0)
                        fighter1.dodge = -1;

                    fighter1.abilities.get(0).cool_time = ability1.cooldown + 1;
                }

                if ((action.equals("abilitate 2")) && (fighter1.abilities.get(1).cool_time == 0)) {
                    Ability ability2 = fighter1.abilities.get(1);

                    if ((fighter2.HP - ability2.damage) >= 0)
                        fighter2.HP -= ability2.damage;
                    else
                        fighter2.HP = 0;

                    if (ability2.stun == 1 && fighter2.stun==0)
                        fighter2.stun = -1;

                    if (ability2.dodge == 1 && fighter1.dodge==0)
                        fighter1.dodge = -1;

                    fighter1.abilities.get(1).cool_time = ability2.cooldown + 1;

                }
            } else if (fighter2.dodge == 1) {
                //adversarul se apara
                fight_logger.dodge(fighter1);
                fighter2.dodge = 0;
            }
        } else if (fighter1.stun == 1) {
            //primul Pokemon rateaza
            fight_logger.stun(fighter1);
            fighter1.stun = 0;
        }

        trainer1.pokemons.set(i,fighter1);
        trainer2.pokemons.set(j,fighter2);

    }

    @Override
    public void run() {
    }

}
