package com.TreeAlgo;

import java.util.*;

public class DFS {
    public static boolean searchPath(int[][] maze,int x,int y,List<Integer> path){
        if(maze[x][y] == 9){   //target is found
            path.add(x);
            path.add(y);
            return true;
        }
        if(maze[x][y] == 0){ //non visited cell
            maze[x][y] = 2;
            int[] dx = {1,0,-1,0};
            int[] dy = {0,1,0,-1};

            for(int i=0;i<dx.length;i++){
                int newx=x+dx[i];
                int newy=y+dy[i];
                if(newx>=0 && newx< maze.length && newy>=0 && newy<maze[0].length && searchPath(maze,newx,newy,path)){
                    path.add(x);
                    path.add(y);
                    return true;
                }
            }
        }
        return false; //if visited cell is 1(wall) or 2(already visited)
    }
    public static void main(String[] args){
        DFS obj = new DFS();
        int[][] maze = {
                {0,1,0},
                {0,0,0},
                {1,1,9}
        };
        List<Integer> path = new ArrayList<>();
        boolean res= obj.searchPath(maze,0,0,path);
        //System.out.print(res);
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+" ");
        }
    }
}
