package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;

import algstudent.s7.util.Node;
import algstudent.s7.ImageAverager;

public class NodeAvg extends Node{
	private ImageAverager manager;
	private boolean managerNull;
	private int depth;
	private NodeAvg parent;
	
	private NodeAvg child1, child2, child3;
	private ArrayList<NodeAvg> children;
	private double heuristicValuePrune;
	
	public NodeAvg(ImageAverager manager) {
		super();
		this.manager = manager;
		managerNull = true;
		this.depth = 0;
	}
	
	public NodeAvg(ImageAverager manager, int depth, NodeAvg parent) {
		super();
		this.manager = manager;
		managerNull = false;
		this.depth = depth;
		this.parent = parent;
		calculateHeuristicValue();
		calculateHeuristicValuePrune();
	}

	@Override
	public void calculateHeuristicValue() {
		heuristicValue = (-1) * manager.zncc();
	}

	@Override
	public ArrayList<NodeAvg> expand() {
		children = new ArrayList<NodeAvg>();
		
		ImageAverager child1Manager = new ImageAverager(manager); //Half 1 adding image
		child1Manager.sol[depth] = 1;
		child1Manager.half1_img.addSignal(child1Manager.dataset[depth]);
		child1 = new NodeAvg(child1Manager, depth+1, this);
		
		ImageAverager child2Manager = new ImageAverager(manager); //Half 2 adding image
		child2Manager.sol[depth] = 2;
		child2Manager.half2_img.addSignal(child2Manager.dataset[depth]);
		child2 = new NodeAvg(child2Manager, depth+1, this);
		
		ImageAverager child3Manager = new ImageAverager(manager); //Not adding image
		child3Manager.sol[depth] = 0;
		child3 = new NodeAvg(child3Manager, depth+1, this);
		
		children.add(child1);
		children.add(child2);
		children.add(child3);
		
		return children;
	}

	@Override
	public boolean isSolution() {
		return (depth == manager.dataset.length) ? true :  false;
	}

	@Override
	public String toString() {
		return nodeToString(this);
	}

	public String nodeToString(NodeAvg node) {
		StringBuilder sb = new StringBuilder();
        sb.append(getSol(node));
        if (node.child1 != null) {
            sb.append(nodeToString(node.child1));
        }
        if (node.child2 != null) {
        	sb.append(nodeToString(node.child2));
        }
        if (node.child3 != null) {
            sb.append(nodeToString(node.child3));
        }
        return sb.toString();
	}

	public String getSol(NodeAvg node) {
		String actualNode = "";
		for (int i = 0; i < node.manager.sol.length-1; i++) {
			actualNode += node.manager.sol[i];
			actualNode += ",";
		}
		actualNode += node.manager.sol[node.manager.sol.length-1];
		String thisNode = " Depth: " + node.depth + " X={" + actualNode + "}" + " Heuristic: " + node.getHeuristicValue() + " ID: " + node.ID + "\n";
		return thisNode;
	}

	public ImageAverager getImageAverager() {
		return manager;
	}

	public double getHeuristicValuePrune() {
		return heuristicValuePrune;
	}

	private void calculateHeuristicValuePrune() {
		if (prune()) {
			heuristicValuePrune = Integer.MAX_VALUE;
		}else {
			heuristicValuePrune = (-1) * manager.zncc();
		}
	}

	private boolean prune() {
		if (!parent.managerNull) {
			if (parent.manager.zncc() > manager.zncc()) {
				return true;
			}
		}
		return false;
	}
}
