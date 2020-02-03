package mail.letters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mail.Inhabitant;
import mail.contents.ListInhabitant;
import mail.exceptions.NotEnoughMoneyException;
import mail.letters.BillOfExchange;

/**
 * The fools letters class
 */
public class FoolLetter extends Letter<ListInhabitant> {

    // ATTRIBUTES

    protected static final int DEFAULT_COST = 1;
    protected static final int GIVE_COST = 5;
    protected static final int PERCENT_CHANCE_SEND = 10;

    // BUILDER

    public FoolLetter(Inhabitant sender, Inhabitant receiver, List<Inhabitant> content) {
        super(sender, receiver, new ListInhabitant(content));
    }

    // GETTERS

    /**
     * Give the cost of the letter
     * 
     * @return the price
     */
    public int getCost() {
        return DEFAULT_COST;
    }

    // METHODS

    /**
     * Initialize the send of first FoolLetter
     * @param sender the sender
     * @throws NotEnoughMoneyException Exception if the sender not enough money
     */
    public static void initiateFoolLetter(Inhabitant sender) throws NotEnoughMoneyException {
        List<Inhabitant> listGive = new ArrayList<Inhabitant>();
        List<Inhabitant> list = randomTenInhabitant(sender);

        listGive.add(sender);

        for (Inhabitant in : list) {
            FoolLetter letter = new FoolLetter(sender, in, listGive);
            sender.sendsLetter(letter);
        }
    }

    /**
     * The Letter is opensendersender
     * 
     * @throws NotEnoughMoneyException Exception if the sender not enough money
     */
    public void action() throws NotEnoughMoneyException {
        if (this.randomSend()) {
            System.out.println(">>> " + this.sender.getNameOfInhabitant() + " sends a fool letter to " + this.receiver.getNameOfInhabitant());
            this.sendMoney();
            this.changeListInhabitant();
            this.sendFoolLetter();

        }

    }

    /**
     * Roll a dice if the receiver answer of this letter
     * 
     * @return boolean * true if he answers * false otherwise
     */
    private boolean randomSend() {
        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;
        return randInt <= PERCENT_CHANCE_SEND;
    }

    /**
     * Send the money to the Inhabitant on the Fool Letters
     * 
     * @throws NotEnoughMoneyException Exception if the sender not enough money
     */
    private void sendMoney() throws NotEnoughMoneyException {
        for (Inhabitant in : this.content.getValue()) {
            if ( in != null) {
                BillOfExchange letter = new BillOfExchange(this.receiver, in, GIVE_COST);
                this.receiver.sendsLetter(letter);
            }
        }
    }

    /**
     * Change the list of inhabitant
     */
    private void changeListInhabitant(){
        if (this.content.getValue().size() >= 4) {
           Inhabitant in = this.content.getValue().get(0);
            this.content.removeInhabitant(in);
        }
        this.content.addInhabitant(this.receiver);
    }

    /**
     * Give a list of ten random Inhabitants except the inhabitant in parameter
     * @param sender the inhabitant excepted in the list
     * @return a list 
     */
    private static List<Inhabitant> randomTenInhabitant(Inhabitant sender){

        List<Inhabitant> list = new ArrayList<Inhabitant>();

        while (list.size() < 10 && list.size() < sender.getCity().getInhabitant().size() - 1 ) {
            Inhabitant in = sender.getCity().getRandomInhabitant();
            if (! sender.equals(in))
                list.add(in);
        }
        return list;
    }

    /**
     * Send the fool Letter to 10 Inhabitant
     * @throws NotEnoughMoneyException if the sender not enough money
     */ 
    private void sendFoolLetter() throws NotEnoughMoneyException {

        List<Inhabitant> list = randomTenInhabitant(this.receiver);

        for (Inhabitant in : list) {
            FoolLetter letter = new FoolLetter(this.receiver, in, this.content.getValue());
            this.receiver.sendsLetter(letter);
        }

    }



    
} 