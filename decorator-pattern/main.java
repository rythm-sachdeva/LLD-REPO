
public class main{
    public static void main(String[] args){
     System.out.println("Plain Text:");
      TextView PlainText = new PlainTextView("Hello, World!");
        PlainText.render();
        System.out.println("\n\nBold Text:");
      TextView boldText = new BoldTextDecorator(PlainText);
        boldText.render();
        System.out.println("\n\nItalic and Underlined Text:");
        TextView italicUnderlineText = new ItalicTextDecorator(new UnderlineTextDecorator(PlainText));
        italicUnderlineText.render();}
}

interface TextView{
    void render();
}

class PlainTextView implements TextView{
    private final String text;

    public PlainTextView(String text){
        this.text = text;
    }

    @Override
    public void render(){
        System.out.println(text);
    }
}

abstract class TextDecorator implements TextView{
    protected final TextView decoratedTextView;

    public TextDecorator(TextView decoratedTextView){
        this.decoratedTextView = decoratedTextView;
    }
}

class BoldTextDecorator extends TextDecorator{
    public BoldTextDecorator(TextView decoratedTextView){
        super(decoratedTextView);
    }

    @Override
    public void render(){
        System.out.print("<b>");
        decoratedTextView.render();
        System.out.print("</b>");
    }
}

class ItalicTextDecorator extends TextDecorator{
    public ItalicTextDecorator(TextView decoratedTextView){
        super(decoratedTextView);
    }

    @Override
    public void render(){
        System.out.print("<i>");
        decoratedTextView.render();
        System.out.print("</i>");
    }
}
class UnderlineTextDecorator extends TextDecorator{
    public UnderlineTextDecorator(TextView decoratedTextView){
        super(decoratedTextView);
    }

    @Override
    public void render(){
        System.out.print("<u>");
        decoratedTextView.render();
        System.out.print("</u>");
    }
}