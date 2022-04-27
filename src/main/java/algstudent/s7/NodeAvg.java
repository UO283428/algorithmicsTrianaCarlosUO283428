package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;

import algstudent.s7.util.Node;
import algstudent.s7.ImageAverager;

public class NodeAvg extends Node{
	private ImageAverager manager;
	private int depth;
	private UUID parentID;
	
	private NodeAvg child1, child2, child3;
	private ArrayList<NodeAvg> children;
	
	public NodeAvg(ImageAverager manager) {
		this.manager = manager;
		this.depth = 0;
	}
	
	public NodeAvg(ImageAverager manager, int depth, UUID parentID) {
		this.manager = manager;
		this.depth = depth;
		this.parentID = parentID;
		calculateHeuristicValue();
		
	}

	@Override
	public void calculateHeuristicValue() {
		heuristicValue = manager.zncc();
	}

	@Override
	public ArrayList<NodeAvg> expand() {
		children = new ArrayList<NodeAvg>();
		
		ImageAverager child1Manager = new ImageAverager(manager); //Half 1 adding image
		child1Manager.sol[depth] = 1;
		child1Manager.half1_img.addSignal(child1Manager.dataset[depth]);
		child1 = new NodeAvg(child1Manager, depth+1, this.ID);
		
		ImageAverager child2Manager = new ImageAverager(manager); //Half 2 adding image
		child1Manager.sol[depth] = 2;
		child1Manager.half2_img.addSignal(child1Manager.dataset[depth]);
		child2 = new NodeAvg(child1Manager, depth+1, this.ID);
		
		ImageAverager child3Manager = new ImageAverager(manager); //Not adding image
		child1Manager.sol[depth] = 0;
		child3 = new NodeAvg(child1Manager, depth+1, this.ID);
		
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
		StringBuilder sb = new StringBuilder();

        sb.add(this.toStringSingleNode); //Add the last node
        UUID parentID = this.getParentID(); //Find its parent node
        
		NodeAvg node;
        while (parentID != null) { //While there is a parent node
        	node = 
            result.add(node);
            parentID = node.getParentID();
        }
              
		return result;

	}

	public ImageAverager getImageAverager() {
		return manager;
	}
}
