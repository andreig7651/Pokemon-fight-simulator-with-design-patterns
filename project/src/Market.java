/**Am folosit aici dp de Factory,in functie de numele obiectului*/

public class Market  {
    public Market buyItem(String what_item){
        switch (what_item){
            case "Scut":
                return new Item(0, 0, 0, 2, 2);

            case "Vestă":
                return new Item(10, 0, 0, 0, 0);

            case "Săbiuță":
                return new Item(0, 3, 0, 0, 0);

            case "Baghetă Magică":
                return new Item(0, 0, 3, 0, 0);

            case "Vitamine":
                return new Item(2, 2, 2, 0, 0);

            case "Brad de Crăciun":
                return new Item(0, 3, 0, 1, 0);

            case "Pelerină":
                return new Item(0, 0, 0, 0, 3);

            default:
                return null;
        }
    }
}
