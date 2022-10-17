import javax.print.attribute.standard.MediaName;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Program {
    private Path inFil = Paths.get("src/Members.txt");
    private Path utFil = Paths.get("src/TillTränare.txt");
    protected List<Member> member = new ArrayList<>();

    Program() {

        try(FileWriter writer = new FileWriter(utFil.toFile(), true);
            PrintWriter print = new PrintWriter(writer);
                Scanner scan = new Scanner(inFil)) {

            while(scan.hasNext()){
                saveMembersInList(scan);
                    writeOutputToReceptionAndTrainer(print,  member);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Hittade ingen fil!");
                e.printStackTrace();
                    System.exit(0);
        } catch (Exception e) {
            System.out.println("något blev fel!");
                e.printStackTrace();
                    System.exit(0);
        }
    }
    public String userInput() {
        return JOptionPane.showInputDialog(null, "Vilken medlem vill du se?");
    }
    public Member compareInputToList(String input, List<Member> member) {
        for (Member memb : member) {
            if (input.equalsIgnoreCase(memb.getName()) || (input.equalsIgnoreCase(memb.getIdNumber()))) {
                return memb;
            }
        }throw new NoSuchElementException();
    }
    public void writeOutputToReceptionAndTrainer(PrintWriter print, List<Member> member) {
        while (true) {
            String userInput = userInput();
            try {
                Member memb = compareInputToList(userInput, member);
                if (compareDate(memb.getDate().toString())) {
                    System.out.println(memb.getName() + " är en giltig medlem som betalade sin årsavgift:" + memb.getDate());
                    print.println(memb.getIdNumber() + ", " + memb.getName() + " tränade datum: " + LocalDateTime.now());
                } else {
                    System.out.println(memb.getName() + " har tyvärr inte betalat årsavgiften, hen betalade senast: " + memb.getDate());
                }
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog(null, "Skriv in ett giltigt namn eller id-nummer tack!");
            } catch (NullPointerException e) {
                System.exit(0);
            }
        }
    }

    public void saveMembersInList(Scanner scan) {
        String string1 = "";
        String string2 = "";
        while(scan.hasNextLine()){
            if(scan.hasNext()){
                string1 = scan.nextLine();
            }
            if(scan.hasNext()){
                string2 = scan.nextLine();
            }
           member.add(new Member(getIdNumberFromFile(string1),getNameFromFile(string1),getDateFromFile(string2)));
        }
    }
    public boolean compareDate (String input) {
        LocalDate date1 = LocalDate.now().minusYears(1);
        LocalDate date2 = LocalDate.parse(input);
        return date1.compareTo(date2) < 0;
    }
    public String getNameFromFile(String input){
        String[] stringArray = input.split(",");
        return stringArray[1].trim();
    }
    public String getIdNumberFromFile(String input){
        String[] stringArray = input.split(",");
        return stringArray[0].trim();
    }
    public LocalDate getDateFromFile(String input){
         return LocalDate.parse(input);
    }
}