 import java.lang.*;
 import java.lang.String;
 class CharLiteral {
    public static void main(String arg[]) {
        char character;
        TextWindow tw = new TextWindow("CharLiteral");
        tw.setDoubleSpace();
        character = 74;
        tw.line("Initialize 74: " + character);
        character = 'J';
        tw.line("Initialize 'J': " + character);
        character = 0x4A;
        tw.line("Initialize 0x4A: " + character);
        character = 0112;
        tw.line("Initialize 0112: " + character);
        character = '\u004A';
        tw.line("Initialize '\\u004A': " + character);
    }
 

     static class TextWindow {

        public TextWindow() {
        }
    }}

