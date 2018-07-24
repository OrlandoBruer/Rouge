import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.ImageIcon;

import GraphStuff.*;

public class RoomMap {
	
	private Space[][] mapGrid;
	private Graph mapGraph;
	private int width, height;
	public final EnemyManager enemyManager;

	/**
	 * @param width
	 * @param height
	 * @param container
	 */
	public RoomMap(int width, int height, Container container, File file) throws FileNotFoundException {
		
		this.width = width;
		this.height = height;
		mapGrid = new Space[width][height];
		mapGraph = new Graph(width * height);
		enemyManager = new EnemyManager(this);
		
		// scanning in the room layout from a .txt file
		Scanner scan = new Scanner(file);
		String[][] scanned = new String[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				scanned[x][y] = scan.next();
			}
		}
		
		SpaceHandler spaceHandler = new SpaceHandler();
		// Initialising all Space objects and placing them into the Grid (Nested Array)
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				
				int index = (y * width) + x;
				
				if (scanned[x][y].equals("-")) {
					mapGrid[x][y] = new Space(30 + (x * 50), 10 + (y * 50), x, y, index, new ImageIcon("assets/stoneFloor.png"), this);
				} else if (scanned[x][y].equals("*")) {
					mapGrid[x][y] = new Wall(30 + (x * 50), 10 + (y * 50), x, y, index, this);
				} else if (scanned[x][y].equals("1")) {
					mapGrid[x][y] = new Space(30 + (x * 50), 10 + (y * 50), x, y, index, new ImageIcon("assets/stoneFloor.png"), this);
					Enemy enemy = new Enemy(mapGrid[x][y]);
					mapGrid[x][y].setEntity(enemy);
					enemyManager.add(enemy);
				}
				
				mapGrid[x][y].addActionListener(spaceHandler);
				container.add(mapGrid[x][y]);
			}
		}
		
		// Setting up the Graph of walkable spaces
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int index = (y * width) + x;
				int source = mapGrid[x][y].getAdjListIndex();
				mapGraph.getVertex(index).setSpace(mapGrid[x][y]);
				// order of checking: up, right, down, left 
				// (x, y - 1), (x + 1, y), (x, y + 1), (x - 1, y)
				// up
				if (y > 0) {
					if (mapGrid[x][y - 1].isWalkable()) {
						mapGraph.addEdge(source, mapGrid[x][y - 1].getAdjListIndex(), 1);
						//System.out.println(source + " " + mapGrid[x][y - 1].getAdjListIndex());
					}
				}
				// right
				if (x < width - 1) {
					if (mapGrid[x + 1][y].isWalkable()) {
						mapGraph.addEdge(source, mapGrid[x + 1][y].getAdjListIndex(), 1);
					}
				}
				// down
				if (y < height - 1) {
					if (mapGrid[x][y + 1].isWalkable()) {
						mapGraph.addEdge(source, mapGrid[x][y + 1].getAdjListIndex(), 1);
					}
				}
				// left
				if (x > 0) {
					if (mapGrid[x - 1][y].isWalkable()) {
						mapGraph.addEdge(source, mapGrid[x - 1][y].getAdjListIndex(), 1);
					}
				}
			}
		}
		
	}
	
	/**
	 * @param origin
	 * @param destination
	 * @return
	 */
	public int distanceBetween(Space origin, Space destination) {
		
		// RESET THE GRAPH
		for (Vertex v:mapGraph.getVertices()) {
			v.minDistance = Double.POSITIVE_INFINITY;
			v.path = new LinkedList<Vertex>();
		}
		// Use Dijkstra's
		this.calculate(mapGraph.getVertex(origin.getAdjListIndex()));
		
		return (int)mapGraph.getVertex(destination.getAdjListIndex()).minDistance;
	}
	
	/**
	 * @param origin
	 * @param destination
	 * @return
	 */
	public ArrayList<Space> pathBetween(Space origin, Space destination) {
		
		// RESET THE GRAPH
				for (Vertex v:mapGraph.getVertices()) {
					v.minDistance = Double.POSITIVE_INFINITY;
					v.path = new LinkedList<Vertex>();
				}
		// Use Dijkstra's
		this.calculate(mapGraph.getVertex(origin.getAdjListIndex()));
		
		ArrayList<Space> output = new ArrayList<Space>();
		for (Vertex pathVert:mapGraph.getVertex(destination.getAdjListIndex()).path) {
			if (pathVert == mapGraph.getVertex(origin.getAdjListIndex())) {
				continue;
			}
			output.add((Space)pathVert.space);
		}
		return output;
	}
	
	/**
	 * @param source
	 */
	private void calculate(Vertex source){
		// Algo:
		// 1. Take the unvisited node with minimum weight.
		// 2. Visit all its neighbours.
		// 3. Update the distances for all the neighbours (In the Priority Queue).
		// Repeat the process till all the connected nodes are visited.
		
		source.minDistance = 0;
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			Vertex u = queue.poll();
		
			for(Edge neighbour:u.neighbours){
				Double newDist = u.minDistance+neighbour.weight;
				
				if(neighbour.target.minDistance>newDist){
					// Remove the node from the queue to update the distance value.
					queue.remove(neighbour.target);
					neighbour.target.minDistance = newDist;
					
					// Take the path visited till now and add the new node.s
					neighbour.target.path = new LinkedList<Vertex>(u.path);
					neighbour.target.path.add(u);
					
					//Reenter the node with new distance.
					queue.add(neighbour.target);					
				}
			}
		}
	}
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Space getSpace(int x, int y) {
		return mapGrid[x][y];
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Space getPlayerLocation() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (mapGrid[x][y].isPlayerLocation()) {
					return mapGrid[x][y];
				}
			}
		}
		return null;
	}
	
	public void showMoveableSpaces() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (Game.player.canMoveTo(mapGrid[x][y])) {
					mapGrid[x][y].setSprite(new ImageIcon("assets/stoneFloorMoveable.png"));
				} else if (mapGrid[x][y].isEmpty()) {
					mapGrid[x][y].setSprite(null);
				}
			}
		}
		
	}
	
	private class SpaceHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Space space = (Space)event.getSource();
			RoomMap roomMap = space.getRoomMap();
			
			if (Game.player.canMoveTo(space)) {
				Game.player.move(space);
			}
			
			
		}
	}
}
