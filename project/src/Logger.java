import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**Clasa Logger,ce are rol de afisare a evenimentelor ce se desfasoara intr-o aventura
   Am folosit design pattern-ul de Singleton,Logger-ul va fi unic si pe cel de Observer care va trimite o notificare
   atunci cand incepe o noua aventura sau se termina*/

public class Logger extends Observer{

    String out_file = "file.out";
    FileWriter file = new FileWriter(out_file);

    /**Singleton*/
    private static Logger Instance;

    private Logger() throws IOException {
    }

    public static Logger Instance() throws IOException {
        if (Instance == null)
            Instance = new Logger();
        return Instance;
    }
    /**Singleton*/

    /**Observer*/
    public Logger(Subject subject) throws IOException {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {

        if (subject.getState() > 0)
            System.out.println("------Round " + subject.getState() + " of the tournament------\n");
        else
            System.out.println("------End of round " + (-subject.getState()) + " of the tournament------\n");
    }
    /**Observer*/

    //metoda ce afiseaza actiunea Pokemon-ului
    void display_action(String attack, Pokemon pokemon) throws InterruptedException, IOException {

        switch (attack){
            case "atac":
                System.out.println(pokemon.name + " uses " + attack);
                TimeUnit.MILLISECONDS.sleep(125);
                String text = pokemon.name + " uses " + attack + "\n";
                try {
                    file.write(text);
                    file.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;

            case "abilitate 1":
                System.out.println(pokemon.name + " uses " + attack);
                TimeUnit.MILLISECONDS.sleep(125);
                text = pokemon.name + " uses " + attack + "\n";
                try {
                    file.write(text);
                    file.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;

            case "abilitate 2":
                System.out.println(pokemon.name + " uses " + attack);
                TimeUnit.MILLISECONDS.sleep(125);
                text = pokemon.name + " uses " + attack + "\n";
                try {
                    file.write(text);
                    file.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;

        }
    }

    void print_trainers(Trainer trainer1, Trainer trainer2) throws InterruptedException {
        System.out.println("-----" + trainer1.name + " VS " + trainer2.name + "-----");
        TimeUnit.MILLISECONDS.sleep(125);
        String text = "-----" + trainer1.name + " VS "+ trainer2.name + "-----\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void print_neutrel(int index, Pokemon pokemon) throws InterruptedException {
        switch (index){
            case 1:
                System.out.println("-----"+pokemon.name + " VS Neutrel 1-----");
                TimeUnit.MILLISECONDS.sleep(125);
                String text="-----"+pokemon.name + " VS Neutrel 1-----\n";
                try{
                    file.write(text);
                    file.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;

            case 2:
                System.out.println("-----" + pokemon.name + " VS Neutrel 2-----");
                TimeUnit.MILLISECONDS.sleep(125);
                text="-----"+pokemon.name + " VS Neutrel 2-----\n";
                try{
                    file.write(text);
                    file.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    void winner(Pokemon pokemon, Trainer trainer) throws InterruptedException {
            System.out.println("Congratiulations! Trainer " + trainer.name +
                    " won this fight!" + pokemon.name + " has leveled up!");
            TimeUnit.MILLISECONDS.sleep(125);
            String text = "Congratiulations! Trainer " + trainer.name+
                    " won this fight!" + pokemon.name + " has leveled up!\n";
            try{
                file.write(text);
                file.flush();
            }catch (IOException e){
                e.printStackTrace();
            }

    }

    void win_neutrel(Pokemon pokemon) throws InterruptedException {
        System.out.println("\nCongratiulations! You won this fight!" + pokemon.name + " has leveled up!");
        TimeUnit.MILLISECONDS.sleep(125);
        String text = "\nCongratiulations! You won this fight!" + pokemon.name + " has leveled up!\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void display_pokemon(Pokemon pokemon) throws InterruptedException {
        System.out.println(pokemon.name + " HP: "+pokemon.HP);
        TimeUnit.MILLISECONDS.sleep(125);
        String text = pokemon.name + " HP: " + pokemon.HP + "\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    void display_stats(Pokemon pokemon) throws InterruptedException {
        System.out.println("New stats for " + pokemon.name + " HP: " + pokemon.HP);
        TimeUnit.MILLISECONDS.sleep(125);
        String text = "New stats for " + pokemon.name + " HP: " + pokemon.HP + "\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("ATT: " + pokemon.Attack + " Sp. ATT: " + pokemon.Special_Attack);
        TimeUnit.MILLISECONDS.sleep(125);
        text = "ATT: " + pokemon.Attack + " Sp. ATT: " + pokemon.Special_Attack + "\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("DEF: " + pokemon.Defense + " Sp. DEF: " + pokemon.Special_Defense + "\n");
        TimeUnit.MILLISECONDS.sleep(125);
        text = "DEF: " + pokemon.Defense + " Sp. DEF: " + pokemon.Special_Defense + "\n\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void lose(Pokemon neutrel) throws InterruptedException {
        System.out.println("Too bad! " + neutrel.name + " won this time... Keep practicing!");
        TimeUnit.MILLISECONDS.sleep(125);
        String out_file="file.out";
        String text="Too bad! " + neutrel.name + " won this time... Keep practicing!\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void dodge(Pokemon pokemon) throws InterruptedException {
        System.out.println(pokemon.name + " missed");
        TimeUnit.MILLISECONDS.sleep(125);
        String text = pokemon.name + " missed\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void stun(Pokemon pokemon) throws InterruptedException {
        System.out.println(pokemon.name + " stunned");
        TimeUnit.MILLISECONDS.sleep(125);
        String text = pokemon.name + " stunned\n";
        try{
            file.write(text);
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
