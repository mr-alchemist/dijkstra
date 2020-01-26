import java.util.Arrays;

import storage.FactorArray;

public class Program {

	public Program() {
		
	}
	
	public static void main(String[] args) {
		Program program = new Program();
		program.run();
	}
	
	void run() {
		int[][] matrix = new int[][] {
			{-1, 2, 3, 6,-1,-1,-1},
			{ 2,-1, 4,-1, 9,-1,-1},
			{ 3, 4,-1, 1, 7, 6,-1},
			{ 6,-1, 1,-1,-1, 4,-1},
			{-1, 9, 7,-1,-1, 1, 5},
			{-1,-1, 6, 4, 1,-1, 8},
			{-1,-1,-1,-1, 5, 8,-1}
		};
		
		int[] route = findShortestRoute(matrix, 0, 6);
		
		for(int i = 0;i < route.length; i++) {
			System.out.println(route[i]);
		}
		
	}
	
	int[] findShortestRoute(int[][] matrix, int from, int to) {
		int N = matrix.length;
		if(from < 0 || to < 0 || from >= N || to >= N )return null;
		int[] distance = new int[N];
		int[] routeVia = new int[N];
		boolean[] performed = new boolean[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(routeVia, -1);
		Arrays.fill(performed, false);
		
		distance[from] = 0;
		for(int j = 0; j < N - 1 ;j++) {//обрабатываем N-1 вершин, т.к. последнюю необработанную вершину обрабатывать не нужно
			int index = -1;
			for(int i = 0; i < N ;i++) {//выбираем вершину, которую будем обрабатывать. Это вершина с наименьшим расстоянием и из тех, что еще не обрабатывались.
				if(performed[i]) continue;
				if(index == -1) {
					index = i;
					continue;
				}
				if(distance[i] < distance[index]) {
					index = i;
				}
			}
			
			//-------
			if(index == to) break;
			
			int curValue = distance[index];//значение в текущей обрабатываемой вершине
			for(int p = 0; p < matrix[0].length ;p++) {
				if(performed[p])continue;
				int weight = matrix[index][p];
				if(weight == -1)continue;
				if((curValue + weight) < distance[p]) {
					distance[p] = (curValue + weight);
					routeVia[p] = index;
				}
			}
			
			performed[index] = true;
			
		}
		
		FactorArray<Integer> faRoute = new FactorArray<Integer>();
		int cur = to;
		faRoute.add(cur);
		while(cur != from) {
			cur = routeVia[cur];
			faRoute.add(cur);
		}
		
		int[] route = new int[faRoute.size()];
		for(int j = route.length - 1; j >= 0 ; j--)
			route[j] = faRoute.get(route.length - j - 1);
		
		return route;
	}
	
}
