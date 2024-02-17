import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**Citirea celor 2 antrenori din fisier si desfasurarea celor 4 aventuri din Turneu*/

public class Tournament {
    public static void main(String args[]) throws CloneNotSupportedException, InterruptedException, IOException {
        //Initializarea antrenorilor si pokemon-ilor lor
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        Pokemon neutrel1 = new Pokemon();
        Pokemon neutrel2 = new Pokemon();
        Market Pokemart = new Market();
        /**Am folosit si muzica de fundal dintr-un joc Pokemon vechi,pentrua fi mai interactiv :)))*/
        AudioInputStream audioStream=null;
        Clip audioClip = null;

        try {
            File myfile = new File("D:\\Anul 2\\Programare orientata pe obiecte\\Teme\\Proiect\\project\\src\\input\\test0.in");
            File audioFile = new File("D:\\Anul 2\\Programare orientata pe obiecte\\Teme\\Proiect\\project\\src\\Battle Theme.wav");

            try {
                audioStream = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            }catch (UnsupportedAudioFileException e) {
                System.out.println("Format unsupported");
            }catch (LineUnavailableException e){
                System.out.println("Line unavailable");
            }

            Scanner myReader = new Scanner(myfile);
            myReader.nextLine();

            String line = myReader.nextLine();
            String[] words = line.split("###", 8);
            neutrel1 = neutrel1.read_stats(words);

            line = myReader.nextLine();
            words = line.split("###", 8);
            neutrel2 = neutrel2.read_stats(words);

            line = myReader.nextLine();
            words = line.split("###", 2);
            trainer1.name = words[0];
            trainer1.age = Integer.parseInt(words[1]);
            TimeUnit.MILLISECONDS.sleep(1100);
            for (int i = 0; i < 3; i++) {
                Pokemon pokemon = new Pokemon();
                line = myReader.nextLine();
                words = line.split("###", 8);
                pokemon = pokemon.read_stats(words);

                for (int j = 0; j < 3; j++) {
                    line = myReader.nextLine();
                    Item new_item = (Item) Pokemart.buyItem(line);
                    new_item.Name = line;
                    new_item.use_item(pokemon);
                    pokemon.objects.add(new_item);
                }

                trainer1.pokemons.add(pokemon);
            }

            line = myReader.nextLine();
            words = line.split("###", 2);
            trainer2.name = words[0];
            trainer2.age = Integer.parseInt(words[1]);
            for (int i = 0; i < 3; i++) {
                Pokemon pokemon = new Pokemon();
                line = myReader.nextLine();
                words = line.split("###", 8);
                pokemon = pokemon.read_stats(words);
                pokemon.name = words[0];

                for (int j = 0; j < 3; j++) {
                    line = myReader.nextLine();
                    Item new_item = (Item) Pokemart.buyItem(line);
                    new_item.Name = line;
                    new_item.use_item(pokemon);
                    pokemon.objects.add(new_item);
                }

                trainer2.pokemons.add(pokemon);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Arena fight_arena = new Arena();
        Subject subject = new Subject();
        new Logger(subject);
        for(int i = 0; i <= 2; i++) {
            subject.setState(i + 1);
            fight_arena.arena_event(i, i, trainer1, trainer2, neutrel1, neutrel2);
            subject.setState(-(i + 1));
        }

        int max1 = trainer1.pokemons.get(0).HP;
        int pos1 = 0;
        for(int i = 0; i <= 2; i++)
            if(trainer1.pokemons.get(i).HP > max1) {
                max1 = trainer1.pokemons.get(i).HP;
                pos1 = i;
            }

        int max2 = trainer2.pokemons.get(0).HP;
        int pos2 = 0;
        for(int j = 0; j <= 2; j++)
            if(trainer2.pokemons.get(j).HP>max2) {
                max2 = trainer2.pokemons.get(j).HP;
                pos2 = j;
            }

        subject.setState(4);
        fight_arena.arena_event(pos1, pos2, trainer1, trainer2, neutrel1, neutrel2);
        subject.setState(-4);

        audioClip.close();
        audioStream.close();
    }
}
