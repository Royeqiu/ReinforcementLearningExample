package main;

import Reinforcement.*;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by roye1 on 2017/8/16.
 */
public class game {
    static int [] initialBoard={0,0,0,0,0,0,0,0,0};
   static public void main(String args[])
   {
       int isfinished=0;
       int []currentBoard=initialBoard.clone();
       RuleJudger ruleJudger=new RuleJudger();
       Hashtable<Integer,Integer>scoreBoard=new Hashtable<>();
       TicTacToePlayer blackPlayer=new TicTacToePlayer();
       //blackPlayer.initPlayer(initialBoard,1);
       Action blackMove;
       TicTacToePlayer whitePlayer=new TicTacToePlayer();

       Action whiteMove;
       boolean firstRound=true;
       //HumanTranslator humanTranslator=new HumanTranslator();
       //blackPlayer.showQTable();
       int epoh=3000000;
       for(int time=0;time<epoh;time++)
       {
           //System.out.println(time+": game:");
           isfinished=0;
           firstRound=true;
           currentBoard=initialBoard;
           while(isfinished==0)
           {
               if(firstRound)
               {
                   blackPlayer.initPlayer(initialBoard,1);
               }
               blackMove=blackPlayer.movePiece(currentBoard);
               currentBoard=getNextBoard(blackPlayer.getCurrentBoard(),blackMove,1);
               isfinished=ruleJudger.isFinished(currentBoard);
               //showBoard(currentBoard);
               if(isfinished!=0)
               {
                   break;
               }
               if(!firstRound)
               {
                   whitePlayer.update(false,currentBoard);
               }

           /*
           int HumanAction=userInsert();
           while(!ruleJudger.isActionValid(currentBoard,humanTranslator.getCurrentActioin(HumanAction)))
           {
               HumanAction=userInsert();
           }
           */
               if(firstRound)
               {
                   whitePlayer.initPlayer(currentBoard,2);
                   firstRound=false;
               }
               whiteMove=whitePlayer.movePiece(currentBoard);
               currentBoard=getNextBoard(currentBoard,whiteMove,2);
               isfinished=ruleJudger.isFinished(currentBoard);
               //showBoard(currentBoard);
               if(isfinished!=0)
               {
                   break;
               }
               blackPlayer.update(false,currentBoard);
           }
           if(!scoreBoard.containsKey(isfinished))
           {
               scoreBoard.put(isfinished,1);
           }
           else
           {
                scoreBoard.put(isfinished,scoreBoard.get(isfinished)+1);
           }
           if((time%10000)==0)
           {
                System.out.println(time);
                blackPlayer.deJump();
                //whitePlayer.deJump();
           }
           blackPlayer.update(true,currentBoard);
           whitePlayer.update(true,currentBoard);
       }

       whitePlayer.showQTable();
       for(int key:scoreBoard.keySet())
       {
           System.out.println(key+" : "+scoreBoard.get(key));
       }
   }
   static int []getNextBoard(int []board,Action action,int pieceColor)
   {
       int nextBoard[]=board.clone();
       nextBoard[action.getActionNum()]=pieceColor;
       return nextBoard;
   }
   static int userInsert()
   {
       Scanner scanner=new Scanner(System.in);

       int num=scanner.nextInt();

       while(num<0||num>8)
       {

           num=scanner.nextInt();
       }
       return num;
   }
   static void showBoard(int []board)
   {
       String sign="＿";
       for(int i=0;i<9;i+=3)
       {
           for(int j=0;j<3;j++)
           {

               if(board[i+j]==0)
               {
                   sign="＿";
               }
               else if(board[i+j]==1)
               {
                   sign="Ｏ";
               }
               else if(board[i+j]==2)
               {
                   sign="Ｘ";
               }
               System.out.print(sign+" ");
           }
           System.out.println();
       }
       System.out.println();
   }
}
