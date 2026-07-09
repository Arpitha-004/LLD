import java.util.*;

class SnakeLadder {

    class Dice{
        Random rand = new Random();

        public int roll(){
            return rand.nextInt(6)+1;
        }
    }
    
    class Player{
        String name;
        int position;

        public Player(String name) {
            this.name = name;
            this.position = 0;
        }

        public String getName(){
            return name;
        }

        public int getPosition(){
            return position;
        }
        
        public void setPosition(int position){
            this.position = position;
        }
    }

    class Snake{
        int head,tail;

        public Snake(int head,int tail){
            this.head = head;
            this.tail = tail;
        }
        public int getHead(){
            return head;
        }
        public int getTail(){
            return tail;
        }

    }

    class Ladder{
        int start,end;

        public Ladder(int start,int end){
            this.start = start;
            this.end = end;
        }
        public int getStart(){
            return start;
        }
        public int getEnd(){
            return end;
        }
    }

    class Board{
        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();

        public Board(){
            snakes.add(new Snake(99,54));
            snakes.add(new Snake(70,55));
            snakes.add(new Snake(52,42));

            ladders.add(new Ladder(3,22));
            ladders.add(new Ladder(8, 30));
            ladders.add(new Ladder(28, 84));
        }

        public int checkPosition(int position){
            for(Snake s:snakes){
                if(s.getHead() == position){
                    System.out.println("Bitten by Snake");
                    return s.getTail();
                }
            }
            for(Ladder l:ladders){
                if(l.getStart() == position){
                    System.out.println("Climbing the ladder");
                    return l.getEnd();
                }
            }
            return position;
        }
    }

    class Game{
        Queue<Player> players = new LinkedList<>();
        public Game(){
            players.add(new Player("Diamond"));
            players.add(new Player("Ruby"));
            players.add(new Player("Emerald"));
        }
        Dice d = new Dice();
        Board b =  new Board();
        public void startGame(){
            while(true){
                Player p = players.poll();
                int num = d.roll();

                System.out.println(p.getName()+" rolled "+num);

                int newPos = p.getPosition() + num;

                if(newPos <= 100){
                    newPos = b.checkPosition(newPos);
                    p.setPosition(newPos);
                }

                System.out.println(p.getName()+" is at "+p.getPosition());

                if(p.getPosition()==100){
                    System.out.println(p.getName()+" won ");
                    break;
                }

                players.offer(p);
            }
        }
        
    }
    public static void main(String[] args) {
        SnakeLadder sl = new SnakeLadder();
        Game g = sl.new Game();
        g.startGame();
    }
}
