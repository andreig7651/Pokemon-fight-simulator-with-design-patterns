import java.io.IOException;

/**Implementarea luptei cu Neutrel 1 sau 2*/

public class Event implements Runnable{

    public Event(int type, Logger fight_logger, Pokemon fighter, Trainer trainer, int i,Pokemon neutrel1, Pokemon neutrel2, Fight fights) throws InterruptedException, CloneNotSupportedException, IOException {

        if(type==1) {
            Pokemon neutrel1_clone = (Pokemon) neutrel1.clone();//folosim clona pentru a nu altera Neutrel-ul
            fight_logger.print_neutrel(type, fighter);
            fighter = fights.fight_neutrel(neutrel1_clone, fighter);
            trainer.pokemons.set(i, fighter);//setam Pokemon-ul cu statisticile upgradate

        }else if(type==2) {
            Pokemon neutrel2_clone = (Pokemon) neutrel2.clone();
            fight_logger.print_neutrel(type, fighter);
            fighter = fights.fight_neutrel(neutrel2_clone, fighter);
            trainer.pokemons.set(i, fighter);
        }

    }

    public void run(){

    }
}
