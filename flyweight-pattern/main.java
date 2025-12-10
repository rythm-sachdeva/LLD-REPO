import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class main {

    public static void main(String[] args){
        TextEditorClient editor = new TextEditorClient();

        // Render "Hello" with same style
        String word = "Hello";
        for (int i = 0; i < word.length(); i++) {
            editor.addCharacter(word.charAt(i), "Arial", 15, "#000000", 10 + i * 15, 100);
        }

        // Render "World" with different font and color
        String word2 = "World";
        for (int i = 0; i < word2.length(); i++) {
            editor.addCharacter(word2.charAt(i), "Times New Roman", 15, "#000000", 10 + i * 15, 100);
        }

        editor.renderText();
        System.out.println("Total unique flyweights created: " + editor.flyweightFactory.getTotalFlyweights());
    

    }
}

interface Flyweight{
    void draw(int x,int y);
}


class CharacterGlyph implements Flyweight{
    private final char symbol;
    private final String fontFamily;
    private final int fontSize;
    private final String color;
    public CharacterGlyph(char symbol, String fontFamily, int fontSize, String color){
        this.symbol = symbol;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.color = color;
    }
    @Override
    public void draw(int x, int y){
        System.out.println("Drawing character '" + symbol + "' at (" + x + "," + y + ") with font: " + fontFamily + ", size: " + fontSize + ", color: " + color);
    }
}

class FlyweightFactory{
    private final Map<String, Flyweight> flyweightMap = new HashMap<>();
    
    public Flyweight getFlyweight(char symbol,String fontFamily,int fontSize,String color){
        String key = symbol + "-" + fontFamily + "-" + fontSize + "-" + color;
        if(!flyweightMap.containsKey(key)){
            flyweightMap.put(key, new CharacterGlyph(symbol, fontFamily, fontSize, color));
        }
        return flyweightMap.get(key);
    }
   
    public int getTotalFlyweights(){
        return flyweightMap.size();
    }

}


class TextEditorClient{
    public final FlyweightFactory flyweightFactory = new FlyweightFactory();
    private List<RenderedCharacter> renderedCharacters = new ArrayList<>();

    public void addCharacter(char symbol,String fontFamily,int fontSize,String color,int x,int y){
        Flyweight flyweight = flyweightFactory.getFlyweight(symbol, fontFamily, fontSize, color);
       renderedCharacters.add(new RenderedCharacter(flyweight, x, y));
    }
    public void renderText(){
        for(RenderedCharacter rc : renderedCharacters){
            rc.draw();
        }
    }


    private static class RenderedCharacter{
        private final Flyweight flyweight;
        private final int x;
        private final int y;

        public RenderedCharacter(Flyweight flyweight, int x, int y){
            this.flyweight = flyweight;
            this.x = x;
            this.y = y;
        }

        public void draw(){
            flyweight.draw(x, y);
        }
    }
}