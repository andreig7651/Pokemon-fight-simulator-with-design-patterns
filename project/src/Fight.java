import java.io.IOException;
import java.util.ArrayList;

/**Aici am implementat batalia cu Neutrel-ul,identica pentru amandoi.Decidem atacul pe care o sa il folosim,
   apoi,in functie de ce vom face,scadem viata neutrelului*/

public class Fight{
    Pokemon fight_neutrel(Pokemon neutrel,Pokemon fighter) throws CloneNotSupportedException, InterruptedException, IOException {

        Pokemon save_fighter=(Pokemon) fighter.clone();
        Logger fight_logger = Logger.Instance();

        while ((fighter.HP!=0)&&(neutrel.HP!=0)){
            String[]actions={"atac","abilitate 1","abilitate 2"};
            int which_action;

            do {
                which_action=(int)((Math.random() * (3 - 0)) + 0);
            }while ((actions[which_action].equals("abilitate 1") && fighter.abilities.get(0).cool_time!=0) || (
                    actions[which_action].equals("abilitate 2") && fighter.abilities.get(1).cool_time!=0));

            String action = actions[which_action];
            fight_logger.display_action(action,fighter);

            if(action.equals("atac")) {
                if (fighter.Attack != 0) {
                    if ((neutrel.HP + neutrel.Defense - fighter.Attack) >= 0) {
                        if ((neutrel.HP + neutrel.Defense - fighter.Attack) <= neutrel.HP)
                            neutrel.HP = neutrel.HP + neutrel.Defense - fighter.Attack;
                    }else
                        neutrel.HP = 0;
                }else {
                    if ((neutrel.HP + neutrel.Special_Defense - fighter.Special_Attack) >= 0) {
                        if ((neutrel.HP + neutrel.Special_Defense - fighter.Special_Attack) <= neutrel.HP) {
                            neutrel.HP = neutrel.HP + neutrel.Special_Defense - fighter.Special_Attack;
                        }
                    }else
                        neutrel.HP = 0;
                }
            }

            if((action.equals("abilitate 1")) && (fighter.abilities.get(0).cool_time == 0)){
                Ability ability1 = fighter.abilities.get(0);
                if((neutrel.HP - ability1.damage) >= 0)
                    neutrel.HP -= ability1.damage;
                else
                    neutrel.HP = 0;

                if(ability1.stun == 1 && neutrel.stun == 0)
                    neutrel.stun = -1;

                if(ability1.dodge == 1 && fighter.dodge == 0)
                    fighter.dodge = -1;

                fighter.abilities.get(0).cool_time = ability1.cooldown + 1;
            }

            if((action.equals("abilitate 2")) && (fighter.abilities.get(1).cool_time == 0)){
                Ability ability2 = fighter.abilities.get(1);

                if((neutrel.HP - ability2.damage) >= 0)
                    neutrel.HP -= ability2.damage;
                else
                    neutrel.HP = 0;

                if(ability2.stun == 1 && neutrel.stun == 0)
                    neutrel.stun = -1;

                if(ability2.dodge == 1 && fighter.dodge == 0)
                    fighter.dodge = -1;

                fighter.abilities.get(1).cool_time = ability2.cooldown+1;
            }

            if(neutrel.stun == 0 || neutrel.stun == -1) {
                if (fighter.dodge == 0 || fighter.dodge == -1) {
                    fight_logger.display_action("atac",neutrel);
                    if ((fighter.HP + fighter.Defense - neutrel.Attack) < fighter.HP)
                        fighter.HP = fighter.HP + fighter.Defense - neutrel.Attack;
                        if(fighter.HP < 0)
                            fighter.HP = 0;
                } else {
                    fight_logger.dodge(neutrel);
                    fighter.dodge = 0;
                }
            }else {
                fight_logger.stun(neutrel);
                neutrel.stun = 0;
            }

            if(neutrel.stun == -1)
                neutrel.stun = 1;

            if(fighter.dodge == -1)
                fighter.dodge = 1;

            if(fighter.abilities.get(0).cool_time != 0)
                fighter.abilities.get(0).cool_time--;

            if(fighter.abilities.get(1).cool_time != 0)
                fighter.abilities.get(1).cool_time--;

            fight_logger.display_pokemon(fighter);
            fight_logger.display_pokemon(neutrel);
        }

        //putin probabil(aproape imposibili),dar am tratat si situatia cand Neutrel castiga
        if(neutrel.HP == 0) {
            fight_logger.win_neutrel(fighter);
            fighter = save_fighter;//refacem Pokemon-ul la starea initiala
            fighter.upgrade_stats();
        }else{
            fight_logger.lose(neutrel);
            fighter = save_fighter;
        }

        return fighter;
    }

}
