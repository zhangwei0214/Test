package enumtest;

enum Signal {
    GREEN, YELLOW, RED
}

/*
 * http://www.cnblogs.com/happyPawpaw/archive/2013/04/09/3009553.html
 * enum 在switch中的使用
 */
public class EnumSwitch {
    Signal color = Signal.RED;

    public void change() {
        switch (color) {
        case RED:
            color = Signal.GREEN;
            break;
        case YELLOW:
            color = Signal.RED;
            break;
        case GREEN:
            color = Signal.YELLOW;
            break;
        }
    }
}
