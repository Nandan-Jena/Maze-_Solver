package com.GUI;

import com.TreeAlgo.DFS;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;       //needed to import it separately as there is another List in awt java package
import java.lang.*;
import java.awt.*;

public class View extends JFrame implements ActionListener,MouseListener{
    /**
     * Values: 0 = not visited
     *         1 = blocked wall
     *         2 = visited blocks
     *         9 = target position
     */
    private int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,9,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    //Constructor
    private int[] target = {8,11};
    private List<Integer> path= new ArrayList<Integer>();

    JButton Submit;
    JButton Clear;
    JButton Cancel;
    public View(){
        this.setTitle("Maze Solver");
        this.setSize(520,500);
        this.setLayout(null);  //absolute layout without restriction
        this.setLocationRelativeTo(null);  //GUI will appear at the centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Submit = new JButton("Submit");
        Submit.addActionListener(this);
        Submit.setBounds(120,400,80,30);

        Clear = new JButton("Clear");
        Clear.addActionListener(this);
        Clear.setBounds(200,400,80,30);

        Cancel = new JButton("Cancel");
        Cancel.addActionListener(this);
        Cancel.setBounds(280,400,80,30);

        this.addMouseListener(this);
        this.add(Submit);
        this.add(Clear);
        this.add(Cancel);

    }
    //maze creation
    @Override                   // needs to override the original function from java.awt
    public void paint(Graphics g){
        super.paint(g);

        for(int row=0;row< maze.length;row++){
            for(int col=0;col<maze[0].length;col++){
                Color color;
                switch (maze[row][col]){
                    case 1 :
                        color=Color.BLACK;
                        break;
                    case 9 :
                        color=Color.ORANGE;
                        break;
                    default: color=Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(40*col, 40*row, 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(40*col, 40*row, 40, 40);
            }
        }
        for(int i=0;i<path.size();i+=2){
            int pathX = path.get(i);
            int pathY = path.get(i+1);
            g.setColor(Color.GREEN);
            g.fillRect(40*pathY, 40*pathX, 40, 40);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Submit){
            try{
                boolean res = DFS.searchPath(maze,1,1,path);
                System.out.print(res);
                this.repaint();
            }
            catch (Exception excp){
                JOptionPane.showMessageDialog(null,excp.toString());
            }
        }

        if(e.getSource() == Cancel){
            int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm",JOptionPane.YES_NO_OPTION);
            if(response== 0){
                System.exit(0);
            }
        }
        if(e.getSource() == Clear){
            for(int row=0;row< maze.length;row++){
                for(int col=0;col<maze[0].length;col++){
                    if(maze[row][col] == 2)
                        maze[row][col]=0;
                }
            }
            path.clear();
            this.repaint();   //predefined function which repaints the maze whne clear button is clicked
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getX()>=0 && e.getX()<=maze[0].length*40 && e.getY()>=0 && e.getY()<=maze.length*40){
            int x = e.getY()/40;
            int y = e.getX()/40;
            if(maze[x][y]==1){
                return;
            }
            Graphics g = getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(40*target[1], 40*target[0], 40, 40);
            g.setColor(Color.orange);
            g.fillRect(40*y, 40*x, 40, 40);
            maze[target[0]][target[1]]=0;
            maze[x][y]=9;    //update the newly clicked block
            target[0]=x;
            target[1]=y;
        }
    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }
    public static void main(String[] args){
        View view = new View();
        view.setVisible(true);

    }
}

