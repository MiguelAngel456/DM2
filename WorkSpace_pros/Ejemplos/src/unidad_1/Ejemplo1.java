package unidad_1;

public class Ejemplo1 {
    public static void main(String[] args){
        Runtime r=Runtime.getRuntime();
    //    String comando="/Applications/Firefox.app/Contents/MacOS/firefox";
    //    String comando="Notepad"; // en windows
        String comando="gedit"; // en linux
        Process p;
        try {
            p = r.exec (comando);
        }  catch (Exception e){
            
            System.out.println("error en:"+comando);
            e.printStackTrace();            
        }
    }

}
