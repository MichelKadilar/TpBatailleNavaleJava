public class TestBoard {

    public static void main(String[] args){
        Board board = new Board("Bataille Navale");
        System.out.println(board.getTailleGrille());
        board.print();
    }
}
