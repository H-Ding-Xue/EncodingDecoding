import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnAndDec {
    private String offsetChar;
    private Map<String, Integer> refTable = new HashMap<>();
    private Map<Integer, String> shiftTable = new HashMap<>();
    private Map<Integer, String> refTableSwapped = new HashMap<>();
    private Map<String, Integer> shiftTableSwapped = new HashMap<>();


    public EnAndDec(){
        int i = 0;
        for(char alphabet = 'A'; alphabet <='Z'; alphabet++ )
        {
            refTable.put(String.valueOf(alphabet),i);
            i++;
        }
        i = i-1;
        refTable.put("Z",i);
        i = i+1;
        for(int k = 0; k <=9; k++ ){
            refTable.put(String.valueOf(k),i);
            i++;
        }
        refTable.put("(",36); refTable.put(")",37); refTable.put("*",38); refTable.put("+",39);
        refTable.put(",",40); refTable.put("-",41); refTable.put(".",42); refTable.put("/",43);



    }

    public String encode(String plainText){
        while(true){
            Scanner myObj = new Scanner(System.in);
            System.out.println("Please enter a character in the ref table");
            String input = myObj.nextLine();
            if(refTable.get(input)==null){
                continue;
            }
            else{
                offsetChar = input;
                break;
            }
        }
        String result = "";
        Integer offVal=refTable.get(offsetChar);
        refTableSwapped = refTable.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        for(int i = 0; i <refTableSwapped.size(); i++ ){
            shiftTable.put((i+offVal)%refTableSwapped.size(),refTableSwapped.get(i));

        }



        for (int i = 0; i < plainText.length(); i++) {
            if(refTable.get(String.valueOf(plainText.charAt(i)))!=null){
                result = result+shiftTable.get(refTable.get(String.valueOf(plainText.charAt(i))));
            }
            else{
                result = result +plainText.charAt(i);
            }
        }

        return offsetChar+result;
    }

    public String decode(String encodedText){
        System.out.println("Encoded text is "+encodedText);
        Integer offVal = refTable.get(encodedText.substring(0,1));
        String original = encodedText.substring(1);
        String result = "";

        for (int i = 0; i < original.length(); i++) {
            if(refTable.get(String.valueOf(original.charAt(i)))!=null){
                int correctedIndex = (refTable.get(String.valueOf(original.charAt(i)))+offVal)% refTable.size();
                result = result+refTableSwapped.get(correctedIndex);
            }
            else{
                result = result +original.charAt(i);
            }
        }
        return result;
    }

}
