import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/** -aici se desfasoara o runda din arena,adica doi antrenori si pokemonii lor(carora le stim poztitia in lista antrenorilor,
se vor duela cu Neutrel 1 si Neutrel 2 pana ajung la confruntarea directa
    -atacul fiecarui Pokemon,fie ca este atac,abilitate 1 sau abilitate2,va reprezenta un thread;practic "atacul" e acelasi,
 indiferent daca il da primul Pokemon sau al doilea*/

public class Arena {

void arena_event(int i, int j, Trainer trainer1, Trainer trainer2, Pokemon neutrel1, Pokemon neutrel2) throws InterruptedException, CloneNotSupportedException, IOException {

        Pokemon fighter1;
        Pokemon fighter2;

        int type;//tipul de evenimen pe care se va intra,generat random
        do {
            type = (int) ((Math.random() * (4 - 1)) + 1);
            Fight fights = new Fight();
            Logger fight_logger = Logger.Instance();

            /**Lupta cu Neutrel-ul corespunzator*/
            Event event = new Event(type, fight_logger, trainer1.pokemons.get(i), trainer1, i,neutrel1, neutrel2, fights);
            Event event2 = new Event(type, fight_logger, trainer2.pokemons.get(j), trainer2, j,neutrel1, neutrel2, fights);
            Thread training1 = new Thread(event);
            Thread training2 = new Thread(event2);
            training1.start();
            training1.join();
            training2.start();
            training2.join();

            if (type == 3) {

                fighter1 = trainer1.pokemons.get(i);
                fighter2 = trainer2.pokemons.get(j);
                fight_logger.print_trainers(trainer1, trainer2);
                Pokemon save_fighter = (Pokemon) fighter1.clone();
                Pokemon save_fighter2 = (Pokemon) fighter2.clone();

                while ((fighter1.HP != 0) && (fighter2.HP != 0)) {
                    Battle battle1 = new Battle(fight_logger,fighter1,trainer1,i,fighter2,trainer2,j);
                    Thread attack1 = new Thread(battle1);
                    attack1.start();
                    attack1.join();

                    Battle battle2 = new Battle(fight_logger,fighter2,trainer2,j,fighter1,trainer1,i);
                    Thread attack2 = new Thread(battle2);
                    attack2.start();
                    attack2.join();

                    /**Pentru ca stun sau dodge sa nu se aplice in runda curenta,le dau valoarea -1,atunci cand o abilitate
                     da stun sau dodge;dupa ce luptatorii si-au folosit atacurile,le transform in 1*/
                    if (fighter1.stun == -1)
                        fighter1.stun = 1;

                    if (fighter2.dodge == -1)
                        fighter2.dodge = 1;

                    if (fighter2.stun == -1)
                        fighter2.stun = 1;

                    if (fighter1.dodge == -1)
                        fighter1.dodge = 1;

                    //scad cooltime-ul la finalul rundei
                    fighter1.decrease_cooltime();
                    fighter2.decrease_cooltime();

                    fight_logger.display_pokemon(fighter1);
                    fight_logger.display_pokemon(fighter2);
                }

                /**Aici se va decide castigatorul si i se vor upgrada statusurile*/
                if (fighter1.HP == 0) {
                    fight_logger.winner(fighter2, trainer2);
                    fighter2 = save_fighter2;
                    fighter2.upgrade_stats();
                    trainer2.pokemons.set(j, fighter2);
                    trainer1.pokemons.set(i,save_fighter);
                    break;
                }

                if (fighter2.HP == 0) {
                    fight_logger.winner(fighter1, trainer1);
                    fighter1 = save_fighter;
                    fighter1.upgrade_stats();
                    trainer1.pokemons.set(i, fighter1);
                    trainer2.pokemons.set(j,save_fighter2);
                    break;
                }
            }


        } while (type != 3);

    }

}
