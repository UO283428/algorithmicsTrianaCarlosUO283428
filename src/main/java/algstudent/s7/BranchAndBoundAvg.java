package algstudent.s7;

import java.util.ArrayList;

import algstudent.s7.util.Heap;
import algstudent.s7.util.Node;

public class BranchAndBoundAvg {

	private Heap ds;
	private NodeAvg rootNode;
	private NodeAvg bestNode;
	private double pruneLimit;
	protected int PNodes, GNodes, TNodes;

	public BranchAndBoundAvg() {
		ds = new Heap();
		PNodes = 0;
		GNodes = 0;
		TNodes = 0;
	}


	public NodeAvg getRootNode() {
		return rootNode;
	}

	public void printSolutionTrace() {
		System.out.println(rootNode.toString());
	}

	public void branchAndBound(NodeAvg rootNode) {
		this.rootNode = rootNode;
		ds.insert(rootNode); //First node to be explored
		
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();
			PNodes++;
			ArrayList<NodeAvg> children = node.expand(); 
			
			GNodes += 3;		
			for (NodeAvg child : children) {
				if (child.isSolution()) {
					double cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					}else {
						TNodes++;
					}
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}else {
						TNodes++;
					}
			}
		} //while
	}


	public void branchAndBoundPrune(NodeAvg rootNode) {
		this.rootNode = rootNode;
		ds.insert(rootNode); //First node to be explored
		
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();
			PNodes++;
			ArrayList<NodeAvg> children = node.expand(); 
			
			GNodes += 3;
			for (NodeAvg child : children) {
				if (child.isSolution()) {
					double cost = child.getHeuristicValuePrune();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					}else {
						TNodes++;
					}
				}
				else
					if (child.getHeuristicValuePrune() < pruneLimit) {
						ds.insert(child);
					}else {
						TNodes++;
					}
			}
		} //while
	}


	public Node getBestNode() {
		return bestNode;
	}

}