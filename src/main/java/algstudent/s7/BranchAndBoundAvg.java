package algstudent.s7;

import java.util.ArrayList;

import algstudent.s7.util.Heap;
import algstudent.s7.util.Node;

public class BranchAndBoundAvg {

	private Heap ds;
	private NodeAvg rootNode;
	private NodeAvg bestNode;
	private int pruneLimit;

	public BranchAndBoundAvg(NodeAvg node) {
		rootNode = node;
	}


	public Object getRootNode() {
		return rootNode;
	}

	public void printSolutionTrace() {
		System.out.println(rootNode.toString());
	}

	public void BranchAndBound(Node rootNode) {
		ds.insert(rootNode); //First node to be explored
		
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	
			
			ArrayList<NodeAvg> children = node.expand(); 
			
			for (NodeAvg child : children)
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					}
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}
		} //while
	}


	public Node getBestNode() {
		return bestNode;
	}

}